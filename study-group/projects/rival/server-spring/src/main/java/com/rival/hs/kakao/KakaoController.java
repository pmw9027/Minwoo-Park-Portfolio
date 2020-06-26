package com.rival.hs.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 14..
 */

@RestController
public class KakaoController {

    @Autowired
    KakaoMongoRepository kakaoMongoRepository;

    @Autowired
    private KakaoDao dao;
    private KakaoAPI kakaoAPI = new KakaoAPI();
    private JsonParser jsonParser = new JsonParser();

    @RequestMapping(value="/kakao", method = RequestMethod.POST)
    public String kakao(@RequestBody KakaoDo body) {


        dao.save(body);

        return "redirect:/index";
    }

    @RequestMapping(value="/kakaoInfo", method = RequestMethod.GET)
    public List<KakaoDo> kakaoInfo(@RequestParam(required = false) String _id) {


        List<KakaoDo> t = kakaoMongoRepository.findByKakaoId(_id);

        return kakaoMongoRepository.findByKakaoId(_id);
    }

}