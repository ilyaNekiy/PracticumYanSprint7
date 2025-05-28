package edu.praktikum.sprint7;


import edu.praktikum.sprint7.clients.OrderClient;
import edu.praktikum.sprint7.models.Order;

import edu.praktikum.sprint7.models.OrderTrack;
import io.restassured.response.Response;
import org.junit.Test;
import static edu.praktikum.sprint7.generators.OrderGenerator.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class OrderTest {
    private final OrderClient orderClient = new OrderClient();
    private String track;

    @Test
    public void createOrderOneColorTest() {
        Order order= randomOrderOneColor();
        orderClient.create(order)
                .then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED).log();
    }
    @Test
   public void createOrderTwoColorTest() {
        Order order= randomOrderTwoColor();
        orderClient.create(order)
                .then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
   }
   @Test
   public void createOrderWithoutColorTest() {
        Order order= randomOrderWithoutColor();
        orderClient.create(order)
            .then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
}
    @Test
    public void getOrderViaTrackTest() {
        Order order= randomOrderWithoutColor();
        Response response=orderClient.create(order);
        track = response.as(OrderTrack.class).getTrack();
        Response getOrderViaTrackResponse=orderClient.getOrderViaTrack(track);
        getOrderViaTrackResponse.then()
                .assertThat()
                .body("order.firstName",equalTo(order.getFirstName()))
                .body("order.lastName",equalTo(order.getLastName()))
                .body("order.address",equalTo(order.getAddress()))
                .body("order.metroStation",equalTo(order.getMetroStation()))
                .body("order.phone",equalTo(order.getPhone()))
                .body("order.rentTime",equalTo(order.getRentTime()))
                .body("order.comment",equalTo(order.getComment()))
                .and()
                .statusCode(SC_OK).log().all();
    }


}
