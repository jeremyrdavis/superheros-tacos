package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.Filling;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "tacofancy-api")
public interface TacofancyRESTClient {

    @GET
    @Path("/random/")
    @Produces(MediaType.APPLICATION_JSON)
    String getRandomTaco();

    @GET
    @Path("/base_layers/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Filling>> getBaseLayer();

}
