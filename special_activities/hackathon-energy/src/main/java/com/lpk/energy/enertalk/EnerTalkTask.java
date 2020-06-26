package com.lpk.energy.enertalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
@Component
public class EnerTalkTask {


    @Autowired
    RealTimeUsageMongoRepository realTimeUsageMongoRepository;

    EnerTalkAPI enerTalkAPI = new EnerTalkAPI();

    @Scheduled(fixedDelayString = "1000")
    public void reportCurrentTime() {

        try {

            //enerTalkAPI.getRealTimeUsage(EnerTalkAPI.site_3);

            realTimeUsageMongoRepository.save(enerTalkAPI.getRealTimeUsage(EnerTalkAPI.site_3));

        }

        catch (Exception e) {

            System.out.println(e);

        }
    }
}

