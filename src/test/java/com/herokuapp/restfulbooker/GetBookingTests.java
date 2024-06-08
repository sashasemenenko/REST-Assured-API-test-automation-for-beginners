package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests extends BaseTest {

    @Test(enabled = false)
    public void getBookingTest() {
        // Create booking
        Response responseCreate = createBooking();
        responseCreate.print();

        // Set path parameter
        spec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

        // Get response with booking
        Response response = RestAssured.given(spec).get("/booking/{bookingId}");
        response.print();

        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify all fields
        SoftAssert softAssert = new SoftAssert();

        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Oleksandr", "First name in response is not expected");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Semenenko", "Last name in response is not expected");

        int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 150, "Total price in response is not expected");

        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertFalse(depositpaid, "Deposit paid should be false, but it's not");

        String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2024-07-01", "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2024-07-15", "checkout in response is not expected");

        String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "Additional needs in response is not expected");

        softAssert.assertAll();
    }

    @Test
    public void getBookingXMLTest() {
        // Create booking
        Response responseCreate = createBooking();
        responseCreate.print();

        // Set path parameter
        spec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

        // Get response with booking
        Header xml = new Header("Accept", "application/xml");
        spec.header(xml);
        Response response = RestAssured.given(spec).get("/booking/{bookingId}");
        response.print();

        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify all fields
        SoftAssert softAssert = new SoftAssert();

        String actualFirstName = response.xmlPath().getString("booking.firstname");
        softAssert.assertEquals(actualFirstName, "Oleksandr", "First name in response is not expected");

        String actualLastName = response.xmlPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName, "Semenenko", "Last name in response is not expected");

        int price = response.xmlPath().getInt("booking.totalprice");
        softAssert.assertEquals(price, 150, "Total price in response is not expected");

        boolean depositpaid = response.xmlPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositpaid, "Deposit paid should be false, but it's not");

        String actualCheckin = response.xmlPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2024-07-01", "checkin in response is not expected");

        String actualCheckout = response.xmlPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2024-07-15", "checkout in response is not expected");

        String actualAdditionalneeds = response.xmlPath().getString("booking.additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "Additional needs in response is not expected");

        softAssert.assertAll();
    }
}

