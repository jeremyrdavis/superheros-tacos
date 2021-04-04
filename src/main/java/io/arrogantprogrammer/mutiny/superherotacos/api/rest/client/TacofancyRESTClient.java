package io.arrogantprogrammer.mutiny.superherotacos.api.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "tacofancy-api")
public interface TacofancyRESTClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getRandomTaco();

}
