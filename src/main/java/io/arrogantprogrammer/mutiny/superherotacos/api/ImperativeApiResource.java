package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.Condiment;
import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.Filling;
import io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos.Mixin;
import io.arrogantprogrammer.mutiny.superherotacos.api.rest.client.ImperativeTacoClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Random;

@Path("/imperative-api")
public class ImperativeApiResource {

    @Inject
    @RestClient
    ImperativeTacoClient imperativeTacoClient;

    @GET
    @Path("/filling")
    public String getRandomTacoFilling() {

        List<Filling> allFillings = imperativeTacoClient.getBaseLayer();
        Filling filling = allFillings.get(new Random().nextInt(allFillings.size()));
        return filling.getName();
    }

    @GET
    @Path("/mixin")
    public String getRandomMixin() {
        List<Mixin> allMixins = imperativeTacoClient.getMixins();
        Mixin mixin = allMixins.get(new Random().nextInt(allMixins.size()));
        return mixin.getName();
    }

    @GET
    @Path("/condiment")
    public String getRandomCondiment() {
        List<Condiment> allCondiments = imperativeTacoClient.getCondiments();
        Condiment condiment = allCondiments.get(new Random().nextInt(allCondiments.size()));
        return condiment.getName();
    }
}
