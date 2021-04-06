package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "imperative-tacofancy-api")
public interface ImperativeTacoClient {

    @GET
    @Path("/base_layers/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Filling> getBaseLayer();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Mixin> getMixins();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Condiment> getCondiments();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Seasoning> getSeasonings();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    List<Shell> getShells();

}
