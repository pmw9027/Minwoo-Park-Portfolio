package Main;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class WeatherVo {
    String date;
    int max_temp;
    int min_temp;
    String weather;

    WeatherVo() {

    }
    WeatherVo(String date,int max_temp, int min_temp, String weather) {
        this.date = date;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public int getMax_temp() {
        return max_temp;
    }

    public int getMin_temp() {
        return min_temp;
    }

    public String getWeather() {
        return weather;
    }
}
