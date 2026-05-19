package com.nagaraju.tests;

import com.nagaraju.base.BaseTest;
import com.nagaraju.constants.APIConstants;
import com.nagaraju.constants.EndPoints;
import com.nagaraju.data.UserData;
import com.nagaraju.dataproviders.UserDataProvider;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(UsersTest.class);

    @Test
    public void getAllUsers() {
        given()
                .contentType(APIConstants.JSON)
        .when()
                .get(EndPoints.USERS)
        .then()
                .statusCode(200);
    }

    @Test
    public void getUser() {
        given()
                .contentType(APIConstants.JSON)
                .pathParam("id", 1)
        .when()
                .get(EndPoints.USERS_ID)
        .then()
                .statusCode(200);
    }

    @Test(
            dataProvider = "userData",
            dataProviderClass = UserDataProvider.class
    )
    public void createNewUser(UserData userData) {
        Response response = given()
                .contentType(APIConstants.JSON)
        .body(userData)
                .post(EndPoints.USERS)
                .thenReturn();

        log.info("Created User: {}", response.prettyPrint());
    }

    @Test
    public void updateExistingUser() {
        Map<String, Object> userPayload = new HashMap<>();

        userPayload.put("name", "Atal Bhai");
        userPayload.put("email", "atalbhai@gmail.com");

        Response response = given()
                .contentType(APIConstants.JSON)
                .pathParam("id", 1)
                .body(userPayload)
        .when()
                .put(EndPoints.USERS_ID)
                .thenReturn();

        log.info("Updated user: {}", response.prettyPrint());
    }

    @Test
    public void deleteUser() {
        Response response =  given()
                .contentType(APIConstants.JSON)
                .pathParam("id", 1)
        .when()
                .delete(EndPoints.USERS_ID)
        .thenReturn();

        log.info("Deleted user: {}", Optional.ofNullable(response.jsonPath().get("id")));
    }

    @Test
    public void validateInvalidUserId() {

        Response response = given()
                .contentType(APIConstants.JSON)
                .pathParam("id", 22)
       .when()
                .get(EndPoints.USERS_ID)
                .thenReturn();

       log.info("Invalid user Response: {}", response.prettyPrint());

        response.then().statusCode(anyOf(is(404)));

        Assert.assertTrue(response.asString().equals("{}") || response.asString().isEmpty());
    }

    @Test
    public void validateEmptyRequestBody() {

        Response response =
                given()
                        .contentType(APIConstants.JSON)
                        .body("{}")

                        .when()
                        .post(EndPoints.USERS)

                        .thenReturn();

        log.info("Empty Body Response: {}", response.prettyPrint());

        response.then()
                .statusCode(anyOf(
                        is(200),
                        is(201),
                        is(400)
                )
        );
    }

    @Test
    public void validateInvalidEndpoint() {

        Response response =
                given()
                        .contentType(APIConstants.JSON)
                        .when()
               .get("/invalid-users")

                        .thenReturn();

        log.info("Invalid Endpoint Response: {}", response.asPrettyString());

        response.then()
                .statusCode(404);
    }

}
