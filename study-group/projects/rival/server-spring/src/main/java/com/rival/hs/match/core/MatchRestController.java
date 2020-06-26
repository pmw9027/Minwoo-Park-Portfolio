package com.rival.hs.match.core;

import com.rival.hs.match.dao.MatchMongoRepository;
import com.rival.hs.match.domain.MatchDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 11..
 */

@RestController
public class MatchRestController implements MatchRestControllerMapper{

    @Autowired
    MatchMongoRepository matchMongoRepository;



    @RequestMapping(value="/match/all", method = RequestMethod.GET)
    public List<MatchDo> index(@RequestParam(required = false) String city, @RequestParam(required = false) String type) {

        return matchMongoRepository.findByCityAndType(city, type);
    }

    @RequestMapping(value="/match/{m_no}", method = RequestMethod.GET)
    public MatchDo getMatch(@PathVariable("m_no") Long bno) {


        return matchMongoRepository.findOne(bno);
    }

    // 축구, 풋볼 게시판 가져오기 & 지역 검색
    @RequestMapping(value="/match/list", method = RequestMethod.GET)
    public List<MatchDo> getMatchList(){

        List<MatchDo> output = matchMongoRepository.findAll();

        return output;
    }

    /*@RequestMapping(value="/save", method = RequestMethod.GET)
    public void save(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String emblem,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String contents,
            @RequestParam(required = false) Integer people_num,
            @RequestParam(required = false) String stadium,
            @RequestParam(required = false) String time_game) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        String now = dateFormat.format(cal.getTime());

        System.out.println(type+"\n"+city+"\n"+team+"\n"+emblem+"\n"+contents+"\n"+title+"\n"+people_num+"\n"+stadium+"\n"+now+"\n"+time_game);

        matchMongoRepository.save(new MatchDo(id, type, city, team,emblem, contents, title, people_num, stadium, now, time_game));
    }*/

}
