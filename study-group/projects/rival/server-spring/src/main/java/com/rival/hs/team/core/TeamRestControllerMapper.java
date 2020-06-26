package com.rival.hs.team.core;

import com.rival.hs.team.domain.TeamDo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 24..
 */
public interface TeamRestControllerMapper {


    @RequestMapping(value="/teamN", method = RequestMethod.GET)
    List<TeamDo> name(@RequestParam(required = false) String name);

    @RequestMapping(value="/teamCT", method = RequestMethod.GET)
    List<TeamDo> cityAndType(@RequestParam(required = false) String city, @RequestParam(required = false) String type) ;


}
