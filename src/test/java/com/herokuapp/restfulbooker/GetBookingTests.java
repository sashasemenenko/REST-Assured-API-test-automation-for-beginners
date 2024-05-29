package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests {

    @Test
    public void getBookingTest() {
        // Get response with booking
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
        response.print();

        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify all fields
        SoftAssert softAssert = new SoftAssert();

        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Eric", "First name in response is not expected");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Brown", "Last name in response is not expected");

        int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 501, "Total price in response is not expected");

        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositpaid, "Deposit paid should be true, but it's not");

        String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2019-09-17", "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2019-11-26", "checkout in response is not expected");

        //String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
        //softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "Additional needs in response is not expected");

        softAssert.assertAll();
    }
}
