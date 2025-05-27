package edu.praktikum.sprint7.clients;

import edu.praktikum.sprint7.models.Order;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String API_V1_ORDERS = "/api/v1/orders";
    private static final String API_V1_ORDERS_BY_TRACK = "/api/v1/orders/track?t=";

    public OrderClient() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
    public Response create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(API_V1_ORDERS);
    }


    public Response getOrderViaTrack(String track) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(API_V1_ORDERS_BY_TRACK + track);
    }
}
