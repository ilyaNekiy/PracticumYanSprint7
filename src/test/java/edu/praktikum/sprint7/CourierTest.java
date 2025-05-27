package edu.praktikum.sprint7;
import edu.praktikum.sprint7.clients.CourierClient;
import edu.praktikum.sprint7.models.Courier;
import edu.praktikum.sprint7.models.CourierCreds;
import edu.praktikum.sprint7.models.CourierId;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static edu.praktikum.sprint7.generators.CourierGenerator.*;
import static edu.praktikum.sprint7.models.CourierCreds.credsFromCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CourierTest {

    private final CourierClient courierClient = new CourierClient();

private int id;

@Test
public void createCourierTest() {
    Courier courier = randomCourier();

    Response response = courierClient.create(courier);

    response.then().assertThat().body("ok",equalTo(true)).and().statusCode(SC_CREATED);

    Response loginResponse = courierClient.login(credsFromCourier(courier));
    loginResponse.then().assertThat().body("id", notNullValue());
    id = loginResponse.as(CourierId.class).getId();

    assertEquals("Некорректный статус-код", SC_OK, loginResponse.statusCode());
}

    @Test
    public void createDuplicateCourierTest() {

        Courier courier = fixedCourierData();
        Courier courierSame = fixedCourierData();
        courierClient.create(courier);
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.as(CourierId.class).getId();
        Response response2 = courierClient.create(courierSame);
        response2.then().assertThat().body("message",equalTo("Этот логин уже используется")).and().statusCode(SC_CONFLICT);
    }
    @Test
    public void createCourierWithoutMandatoryLoginAttributesTest() {

        Courier courierWithoutLogin = courierWithoutLogin();
        courierClient.create(courierWithoutLogin)
                .then()
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .statusCode(SC_BAD_REQUEST);
    }
    @Test
    public void createCourierWithoutMandatoryPasswordAttributesTest() {

        Courier courierWithoutPassword = courierWithoutPassword();

        courierClient.create(courierWithoutPassword)
                .then()
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }
    @Test
    public void createCourierWithoutMandatoryFirstNameAttributesTest() {

        Courier courierWithoutFirstName = courierWithoutFirstName();

        courierClient.create(courierWithoutFirstName)
                .then()
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);


    }

    @Test
    public void loginWithoutMandatoryPasswordTest() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        CourierCreds courierCredsWithoutPassword = new CourierCreds().setLogin(courier.getLogin());
        courierClient.login(courierCredsWithoutPassword)
                .then()
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);;
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.as(CourierId.class).getId();
 }
    @Test
    public void loginWithoutMandatoryLoginTest() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        CourierCreds courierCredsWithoutLogin = new CourierCreds().setLogin(courier.getPassword());
        courierClient.login(courierCredsWithoutLogin)
                .then()
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);;
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.as(CourierId.class).getId();
    }
    @Test
    public void loginWrongLoginTest() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        CourierCreds courierCredsWrongLogin = new CourierCreds().setPasswordAndWrongLogin(courier.getPassword());
        courierClient.login(courierCredsWrongLogin)
                .then()
                .assertThat()
                .body("message",equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.as(CourierId.class).getId();
    }

    @Test
    public void loginWrongPasswordTest() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        CourierCreds courierCredsWrongPassword = new CourierCreds().setLoginAndWrongPassword(courier.getLogin());
        courierClient.login(courierCredsWrongPassword)
                .then()
                .assertThat()
                .body("message",equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);;
        Response loginResponse = courierClient.login(credsFromCourier(courier));
        id = loginResponse.as(CourierId.class).getId();
    }



   @After
   public void tearDown() {
       courierClient.delete(id);
   }
}