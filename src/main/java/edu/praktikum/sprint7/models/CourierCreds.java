package edu.praktikum.sprint7.models;

public class CourierCreds {

    private String login;

    private String password;

    public static CourierCreds credsFromCourier(Courier courier) {
        return new CourierCreds().setLogin(courier.getLogin()).setPassword(courier.getPassword());
    }

    public CourierCreds setLogin(String login) {
        this.login = login;
        return this;
    }
    public CourierCreds setPassword(String password) {
        this.password = password;
        return this;
    }
    public CourierCreds setLoginAndWrongPassword(String login) {
        this.login=login;
        this.password = "wrongPassword";
        return this;
    }
    public CourierCreds setPasswordAndWrongLogin(String password) {
        this.login="wrongLogin";
        this.password = password;
        return this;
    }



    public String getPassword() {
        return password;
    }
    public String getLogin() {
        return login;
    }


}