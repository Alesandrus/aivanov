package ru.job4j.testTask;

/**
 * Created by user on 20.04.2017.
 */
public class Pair {
    private Float price;
    private Integer volume;

    public Pair(Float price, Integer volume) {
        this.price = price;
        this.volume = volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getVolume() {
        return volume;
    }
}
