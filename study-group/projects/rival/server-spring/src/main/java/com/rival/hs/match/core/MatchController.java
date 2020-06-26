package com.rival.hs.match.core;

import com.rival.hs.match.dao.MatchMongoRepository;
import com.rival.hs.match.domain.MatchDo;
import com.rival.hs.mongodb.CounterDo;
import com.rival.hs.mongodb.CounterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by Minwoo on 2017. 3. 16..
 */

@Controller
public class MatchController implements MatchControllerMapper {

    @Autowired
    MatchMongoRepository matchMongoRepository;

    @Autowired
    CounterRepository counterRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    /*Matching board Detail*/
    @Override
    public String getMatchDetail(@PathVariable Long id, Model model) {
        MatchDo matchDo = matchMongoRepository.findOne(id);
        model.addAttribute("board", matchDo);
        return "match/match_detail_view";
    }

    @Override
    @RequestMapping(value="/match", method = RequestMethod.GET)
    public String match() {

        return "match/match";

    }
    /*축구, 풋볼 게시판 가져오기 & 지역 검색*/
    @RequestMapping(value="/match/board/list", method = RequestMethod.GET)
    public String SportBoard(Model model, @RequestParam(value="type", required = false) String type, @RequestParam(value="city", required = false)String city, Pageable pageable){
        Page<MatchDo> board = matchMongoRepository.findByType(type, city, pageable);

        model.addAttribute("board",board);
        model.addAttribute("title",type);
        model.addAttribute("city",city);

        return "match/matching";

    }
     /*matchCreateView 이동*/
    @RequestMapping(value="/match/new", method = RequestMethod.GET)
    public String matchCreateView() {
        return "/match/matchCreateView";
    }

    /*경기 만들기*/
    @RequestMapping(value="/match/new", method = RequestMethod.POST)
    public String matchCreate(@Validated MatchDo form, BindingResult result, Model model) {

        MatchDo board = new MatchDo();
        board.setTitle(form.getTitle());
        board.setTeam(form.getTeam());
        board.setType(form.getType());
        board.setCity(form.getCity());
        board.setStadium(form.getStadium());
        board.setContents(form.getContents());
        CounterDo counterDo = counterRepository.getCounterDo("MATCH_TB");
        board.setId(counterDo.getSequence_value());

        matchMongoRepository.save(board);

        String UrlType = null;
        try {
            UrlType = new String("redirect:/match/board/list?type=" + URLEncoder.encode(form.getType(),"UTF-8") + "&page=0&size=10&city=" + URLEncoder.encode(form.getCity(),"UTF-8"));
            System.out.println(UrlType);
        }catch (Exception e){
            System.out.println(e);
        }
        return UrlType;
    }

    /*경기 수정*/
    public String matchModifyId(@PathVariable Long id, MatchDo form){
        MatchDo board = matchMongoRepository.findOne(form.getId());
        BeanUtils.copyProperties(board, form);
        return "match/matchModify";
    }

    public String matchModify(@RequestParam(value = "id", required = false) Long id, MatchDo form, BindingResult result){
        System.out.println(id);
        MatchDo board = new MatchDo();
        board.setTitle(form.getTitle());
        board.setTeam(form.getTeam());
        board.setType(form.getType());
        board.setCity(form.getCity());
        board.setStadium(form.getStadium());
        board.setContents(form.getContents());
        board.setId(id);
        matchMongoRepository.save(board);

        String UrlType = null;
        try {
            UrlType = new String("redirect:/match/board/list?type=" + URLEncoder.encode(form.getType(),"UTF-8") + "&page=0&size=10&city=" + URLEncoder.encode(form.getCity(),"UTF-8"));
            System.out.println(UrlType);
        }catch (Exception e){
            System.out.println(e);
        }
        return UrlType;
    }

}
