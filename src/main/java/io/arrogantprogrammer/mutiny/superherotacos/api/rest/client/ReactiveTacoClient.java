package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.*;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "tacofancy-api")
public interface ReactiveTacoClient {

    @GET
    @Path("/random/")
    @Produces(MediaType.APPLICATION_JSON)
    String getRandomTaco();

    @GET
    @Path("/base_layers/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Filling>> getBaseLayers();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Mixin>> getMixins();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Condiment>> getCondiments();

    @GET
    @Path("/seasonings/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Seasoning>> getSeasonings();

    @GET
    @Path("/shells/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Shell>> getShells();

    @GET
    @Path("/shells/")
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Shell> getMultiShells();
}
