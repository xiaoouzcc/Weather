package project.release.sdk.com.weather.utils;

import project.release.sdk.com.weather.R;

/**
 * @author 左成城
 * @desc
 * @date 2017/1/4 17:14
 */
public class WeatherUtis {

    public static int getWeatherState(String weather){
        int type = 1;
            switch (weather){
                case "晴":
                    type =R.drawable.fine;
                    break;
                case "阴":
                    type =R.drawable.yin;
                    break;
                case "多云":
                    type =R.drawable.cloudy;
                    break;
                case "小雨":
                    type =R.drawable.small_rain;
                    break;
                case "大雨":
                    type =R.drawable.big_rain;
                    break;
                case "雷电":
                    type =R.drawable.thunder;
                    break;
                case "小雪":
                    type =R.drawable.small_snow;
                    break;
                case "大雪":
                    type =R.drawable.big_snow;
                    break;
                case "霾":
                    type =R.drawable.fog;
                    break;
            }
        return type;
    }
}

