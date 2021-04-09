package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.beers.Beer;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RegisterRestClient(configKey = "reactive-punkapi")
public interface ReactiveBeerClient {

    @GET
    @Path("/beers?page={page}")
    Uni<List<Beer>> getBeersPage(@PathParam("page") int page);

    @GET
    @Path("/beers")
    Uni<List<Beer>> getBeers();
}
