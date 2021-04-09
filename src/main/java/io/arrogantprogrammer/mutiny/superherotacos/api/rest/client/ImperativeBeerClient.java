package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.beers.Beer;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RegisterRestClient(configKey = "imperative-punkapi")
public interface ImperativeBeerClient {

    @GET
    @Path("/beers")
    List<Beer> getBeers();

    @GET
    @Path("/beers?page={page}")
    List<Beer> getBeersPage(@PathParam("page") int page);
}
