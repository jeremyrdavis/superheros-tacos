package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ImperativeBeerClient;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reactive-beer-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveBeerApiResource {

    @Inject
    @RestClient
    ImperativeBeerClient imperativeBeerClient;

    @GET
    @Path("/ipas")
    public Multi<Beer> getIPAs() {
        return Multi.<Beer>createFrom()
                .iterable(imperativeBeerClient.getBeers())
                .select()
                .where(beer -> {
                    return beer.getTagline().contains("IPA");
                });
    }

}
