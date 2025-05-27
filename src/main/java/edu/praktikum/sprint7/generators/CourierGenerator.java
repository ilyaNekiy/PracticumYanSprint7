package edu.praktikum.sprint7.generators;

import edu.praktikum.sprint7.models.Courier;

import static edu.praktikum.sprint7.utils.Utils.randomString;

public class CourierGenerator {
    public static String courierLogin="FixedCourierLogin";
    public static String courierPassword="FixedCourierPassword";
    public static String courierFirstName="FixedCourierFirstName";

    public static Courier randomCourier() {
        return new Courier()
                .setLogin(randomString())
                .setPassword(randomString())
                .setFirstName(randomString());
    }
    public static Courier fixedCourierData() {
        return new Courier()
                .setFirstName(courierFirstName)
                .setLogin(courierLogin)
                .setPassword(courierPassword);
    }
    public static Courier courierWithoutLogin() {
        return new Courier()
                .setPassword(randomString())
                .setFirstName(randomString());
    }
    public static Courier courierWithoutPassword() {
        return new Courier()
                .setLogin(randomString())
                .setFirstName(randomString());
    }
    public static Courier courierWithoutFirstName() {
        return new Courier()
                .setLogin(randomString())
                .setPassword(randomString());
    }
}