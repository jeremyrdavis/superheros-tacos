package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ReactiveBeerClient;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ReactiveTacoClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple5;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuApiResource {

    @Inject
    @RestClient
    ReactiveTacoClient tacoClient;

    @Inject
    @RestClient
    ReactiveBeerClient beerClient;

    @GET
    @Path("/randomtacoandbeer")
    public Uni<String> getRandomTacoAndBeer() {
        return Uni.<String>combine().all().unis(randomTaco(), randomBeer()).asTuple().map(tuple ->{
            return new StringBuilder()
                    .append("Today's Taco and Beer is a ")
                    .append(tuple.getItem1())
                    .append(" and a cold ")
                    .append(tuple.getItem2())
                    .toString();
        });
    }

    @GET
    @Path("/dailytacoandbeer")
    public Uni<String> getDailyTacoAndBeer() {
        return Uni
                .<String>combine()
                .all()
                .unis(
                        tacoClient.getBaseLayers().onItem().transform(fillings -> {
                            return fillings.get(new Random().nextInt(fillings.size())).getName();
                        }),
                        tacoClient.getCondiments().onItem().transform(condiments -> {
                            return condiments.get(new Random().nextInt(condiments.size())).getName();
                        }),
                        tacoClient.getMixins().onItem().transform(mixins -> {
                            return mixins.get(new Random().nextInt(mixins.size())).getName();
                        }),
                        tacoClient.getSeasonings().onItem().transform(seasonings -> {
                            return seasonings.get(new Random().nextInt(seasonings.size())).getName();
                        }),
                        tacoClient.getShells().onItem().transform(shells -> {
                            return shells.get(new Random().nextInt(shells.size())).getName();
                        }),
                        Multi.createBy()
                                .repeating()
                                .uni(AtomicInteger::new, page ->
                                        //getListOfBeers(page.incrementAndGet())
                                        beerClient.getBeersPage(page.incrementAndGet())
                                                .onFailure().recoverWithUni((Uni.createFrom().item(Collections.emptyList())))
                                )
                                .until(List::isEmpty)
                                .onItem()
                                .disjoint()
                                .collect()
                                .asList()
                                .onItem()
                                .transform(beers -> {
                                    return ((Beer) beers.get(new Random().nextInt(beers.size()))).getName();
                                }))
                .asTuple()
                .map(t -> {
                    return new StringBuilder()
                            .append("Today's Taco and Beer is a ")
                            .append(t.getItem1())
                            .append(" with ")
                            .append(t.getItem2())
                            .append(", ")
                            .append(t.getItem3())
                            .append(", ")
                            .append(t.getItem4())
                            .append(", wrapped in a ")
                            .append(t.getItem5())
                            .append(" served with a cold ")
                            .append(t.getItem6()).toString();
                });
    }

    private Uni<String> randomBeer() {
        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                        //getListOfBeers(page.incrementAndGet())
                        beerClient.getBeersPage(page.incrementAndGet())
                                .onFailure().recoverWithUni((Uni.createFrom().item(Collections.emptyList())))
                )
                .until(List::isEmpty)
                .onItem()
                .disjoint()
                .collect()
                .asList()
                .onItem()
                .transform(beers -> {
                    return ((Beer) beers.get(new Random().nextInt(beers.size()))).getName();
                });

    }

    private Uni<String> randomTaco() {
        return Uni
                .<String>combine()
                .all()
                .unis(
                        tacoClient.getBaseLayers().onItem().transform(fillings -> {
                            return fillings.get(new Random().nextInt(fillings.size())).getName();
                        }),
                        tacoClient.getCondiments().onItem().transform(condiments -> {
                            return condiments.get(new Random().nextInt(condiments.size())).getName();
                        }),
                        tacoClient.getMixins().onItem().transform(mixins -> {
                            return mixins.get(new Random().nextInt(mixins.size())).getName();
                        }),
                        tacoClient.getSeasonings().onItem().transform(seasonings -> {
                            return seasonings.get(new Random().nextInt(seasonings.size())).getName();
                        }),
                        tacoClient.getShells().onItem().transform(shells -> {
                            return shells.get(new Random().nextInt(shells.size())).getName();
                        }))
                .asTuple()
                .map(t -> {
                    return new StringBuilder()
                            .append("Today's Taco and Beer is a ")
                            .append(t.getItem1())
                            .append(" with ")
                            .append(t.getItem2())
                            .append(", ")
                            .append(t.getItem3())
                            .append(", ")
                            .append(t.getItem4())
                            .append(", wrapped in a ")
                            .append(t.getItem5()).toString();
                });
    }
}
