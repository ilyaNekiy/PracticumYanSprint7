package edu.praktikum.sprint7.generators;

import edu.praktikum.sprint7.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.praktikum.sprint7.utils.Utils.TodayDate;
import static edu.praktikum.sprint7.utils.Utils.randomString;

public class OrderGenerator {

    public static Order randomOrderWithoutColor(){
        return new Order()
                .setFirstName(randomString())
                .setLastName(randomString())
                .setAddress(randomString())
                .setMetroStation(randomString())
                .setPhone(randomString())
                .setRentTime(new Random().nextInt(100) + 1)
                .setDeliveryDate(TodayDate())
                .setComment(randomString());
    }
    public static Order randomOrderOneColor(){
        List<String> collorsArray = new ArrayList<>();
        collorsArray.add("BLACK");// Создаем массив
        return  randomOrderWithoutColor().setColor(collorsArray);
    }
    public static Order randomOrderTwoColor(){
        List<String> collorsArray = new ArrayList<>();
        collorsArray.add("BLACK");// Создаем массив
        collorsArray.add("GREY");
        return  randomOrderWithoutColor().setColor(collorsArray);
    }

}
