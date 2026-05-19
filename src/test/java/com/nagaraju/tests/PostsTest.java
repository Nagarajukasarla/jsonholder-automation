package com.nagaraju.tests;

import com.nagaraju.base.BaseTest;
import com.nagaraju.constants.APIConstants;
import com.nagaraju.constants.EndPoints;
import com.nagaraju.data.PostData;
import com.nagaraju.dataproviders.PostDataProvider;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class PostsTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(PostsTest.class);

    @Test(
            dataProvider = "postData",
            dataProviderClass = PostDataProvider.class
    )
    public void createNewPost(PostData postData) {
        Response response =
                given()
                        .contentType(APIConstants.JSON)
                        .body(postData)
                .when()
                        .post(EndPoints.POSTS)
                .thenReturn();

        log.info("Created Post Response: {}", response.prettyPrint());

        response.then().statusCode(anyOf(is(201), is(200)));
    }
}
