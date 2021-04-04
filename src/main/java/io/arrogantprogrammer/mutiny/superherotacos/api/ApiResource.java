package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.Filling;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.TacofancyRESTClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    private static final Logger logger = LoggerFactory.getLogger(ApiResource.class);

    @Inject
    @RestClient
    TacofancyRESTClient tacofancyRESTClient;

    @GET
    @Path("/taco")
    public String getRandomTaco() {

        return tacofancyRESTClient.getRandomTaco();
    }

    @GET
    @Path("/filling")
    public Uni<Filling> getTacoFilling() {

        Multi<Filling> fillings = tacofancyRESTClient.getBaseLayer()
                .onItem()
                .transformToMulti(filling -> Multi.createFrom().items(filling.stream())).select().first();
//        fillings.subscribe().with(filling -> {
//            logger.debug("filling: {}", filling);
//        });
        Uni<Filling> randomFilling = tacofancyRESTClient.getBaseLayer().onItem().transform(allFillings -> {
            return allFillings.get(new Random().nextInt(allFillings.size()));
        });
        return randomFilling; //fillings;//tacofancyRESTClient.getBaseLayer();
    }
}
