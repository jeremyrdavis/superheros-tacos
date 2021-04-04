package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ApiTest {

    @Test
    public void testRandomTaco() {
        given()
                .when()
                .get("/api/taco/")
                .then()
                .statusCode(200);
    }

}
