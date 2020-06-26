package com.lpk.energy.weather;

/**
 * Created by user on 2017-04-07.
 */
public class weatherDo {
    private int hour;
    private double temp;
    private String wfen;
    public  weatherDo()
    {}
    public weatherDo(int hour, double temp, String wfen) {
        this.hour = hour;
        this.temp = temp;
        this.wfen = wfen;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWfen() {
        return wfen;
    }

    public void setWfen(String wfen) {
        this.wfen = wfen;
    }
}
