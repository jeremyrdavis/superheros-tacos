package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ReactiveTacoClient;
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
import java.util.Random;

@Path("/reactive-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveApiResource {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveApiResource.class);

    @Inject
    @RestClient
    ReactiveTacoClient reactiveTacoClient;

    @GET
    @Path("/filling")
    public Uni<String> getRandomTacoFilling() {
        return reactiveTacoClient.getBaseLayers().onItem().transform(allFillings -> {
                    return allFillings.get(new Random().nextInt(allFillings.size())).getName();
                });
    }

    @GET
    @Path("/mixin")
    public Uni<String> getRandomMixin() {
        return reactiveTacoClient.getMixins().onItem().transform(mixins -> {
            return mixins.get(new Random().nextInt(mixins.size())).getName();
        });
    }

    @GET
    @Path("/condiment")
    public Uni<String> getRandomCondiment() {
        return reactiveTacoClient.getCondiments().onItem().transform(condiments -> {
            return condiments.get(new Random().nextInt(condiments.size())).getName();
        });
    }

    @GET
    @Path("/taco")
    public String getRandomTaco() {

        return reactiveTacoClient.getRandomTaco();
    }

}