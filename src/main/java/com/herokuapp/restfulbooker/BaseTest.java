package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {

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
        Response response = RestAssured.given().contentType(ContentType.JSON).
                body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        return response;
    }
}

