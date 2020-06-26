package com.rival.hs.team.core;

import com.rival.hs.team.domain.TeamDo;
import com.rival.hs.team.dao.TeamMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 24..
 */

@RestController
public class TeamRestController implements TeamRestControllerMapper{

    @Autowired
    TeamMongoRepository teamMongoRepository;


    @Override
    public List<TeamDo> name(@RequestParam(required = false) String name) {

        return teamMongoRepository.findAllByName(name);
    }

    @Override
    public List<TeamDo> cityAndType(@RequestParam(required = false) String city,@RequestParam(required = false) String type) {

        List<TeamDo> t = teamMongoRepository.findByCityAndType(city,type);
        System.out.println(t.toString());

        return teamMongoRepository.findByCityAndType(city,type);
    }
}
