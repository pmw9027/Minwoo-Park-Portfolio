package com.rival.hs.match.core;

import com.rival.hs.match.domain.MatchDo;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Minwoo on 2017. 4. 11..
 */
public interface MatchControllerMapper {


    @RequestMapping(value="/match", method = RequestMethod.GET)
    String match();

    @RequestMapping("/match/board/{id}")
    String getMatchDetail(@PathVariable Long id, Model model);

    @RequestMapping(value="/match/board/list", method = RequestMethod.GET)
    String SportBoard(Model model, @RequestParam(value="type", required = false) String type, @RequestParam(value="city", required = false)String city, Pageable pageable);

    @RequestMapping(value="/match/board/new", method = RequestMethod.GET)
    String matchCreateView();

    @RequestMapping(value="/match/new", method = RequestMethod.POST)
    String matchCreate(@Validated MatchDo form, BindingResult result, Model model);

    @RequestMapping(value="/match/modify/{id}")
    String matchModifyId(@PathVariable Long id, MatchDo form);

    @RequestMapping(value="/match/modify", method = RequestMethod.POST)
    String matchModify(@RequestParam(value = "id", required = false) Long id, MatchDo form, BindingResult result);
}
