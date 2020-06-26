package com.rival.hs.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Minwoo on 2017. 4. 11..
 */
@Component

public class WeatherTask {





    @Scheduled(fixedDelayString = "1000")
    public void reportCurrentTime() {

        try {

            //enerTalkAPI.getRealTimeUsage(EnerTalkAPI.site_3);

            //realTimeUsageMongoRepository.save(enerTalkAPI.getRealTimeUsage(EnerTalkAPI.site_3));

        }

        catch (Exception e) {

            System.out.println(e);

        }
    }


}
