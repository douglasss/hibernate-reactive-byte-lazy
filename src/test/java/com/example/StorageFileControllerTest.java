package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class StorageFileControllerTest {


    @Test
    void testFetchDataLazily() {
        StorageFile storageFile = given().multiPart("file", "some content", "text/plain")
                .post("/storage")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().as(StorageFile.class);

        given().get("/storage/1")
                .then()
                .statusCode(200);
    }

}