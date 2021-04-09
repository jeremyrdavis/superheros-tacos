package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ImperativeBeerClient;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ReactiveBeerClient;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/reactive-beer-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveBeerApiResource {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveBeerApiResource.class);

    @Inject
    @RestClient
    ImperativeBeerClient imperativeBeerClient;

    @Inject
    @RestClient
    ReactiveBeerClient reactiveBeerClient;

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

    @GET
    @Path("/allipas")
    public String getRandomIPA() {
        Multi.<Beer>createFrom()
                .iterable(imperativeBeerClient.getBeers())
                .select()
                .where(beer -> {
                    return beer.getTagline().contains("IPA");
                });

        return "foo";
    }

    @GET
    @Path("/beer/random")
    public Multi<Beer> getRandomBeer() {

        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                //getListOfBeers(page.incrementAndGet())
                    reactiveBeerClient.getBeersPage(page.incrementAndGet())
                            .onFailure().recoverWithUni((Uni.createFrom().item(Collections.emptyList())))
                )
                .until(List::isEmpty)
                .onItem()
                .disjoint();
    }

    Uni<List<Beer>> getListOfBeers(int page) {
        logger.debug("page {}", page);
        return reactiveBeerClient.getBeersPage(page)
                .onFailure().recoverWithUni((Uni.createFrom().item(Collections.emptyList())));


/*
        if (page == 14) {
            return Uni.createFrom().item(Collections.emptyList());
        }else {
            return reactiveBeerClient.getBeers();
        }
*/
    }
        /*
        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                        Uni.createFrom().item(imperativeBeerClient.getBeersPage(page.incrementAndGet()))
                )
                .until(List::isEmpty)
                .onItem()
                .disjoint();
/*
                .collect()
                .asList()
                .onItem()
                .transform(list -> {
                    return ((Beer) list.get(new Random().nextInt(list.size()))).getName();
                }).toString();
    }
*/


}
