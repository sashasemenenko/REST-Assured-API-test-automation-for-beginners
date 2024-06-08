package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }

    protected Response createBooking() {
        // Create JSON body
        JSONObject body  = new JSONObject();
        body.put("firstname", "Oleksandr");
        body.put("lastname", "Semenenko");
        body.put("totalprice", 150);
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2024-07-01");
        bookingdates.put("checkout", "2024-07-15");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Baby crib");

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).
                body(body.toString()).post("/booking");
        return response;
    }
}

