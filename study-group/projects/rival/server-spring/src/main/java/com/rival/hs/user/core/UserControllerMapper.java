package com.rival.hs.user.core;

import com.rival.hs.kakao.KakaoDo;
import com.rival.hs.user.domain.UserDo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 26..
 */
public interface UserControllerMapper {

    @RequestMapping(value="/user", method = RequestMethod.GET)
    List<UserDo> index(@RequestParam(required = false) String id, @RequestParam(required = false) String name);

    @RequestMapping(value="/user/account", method = RequestMethod.POST)
    String postAccountView(Model model, @RequestBody KakaoDo body);




}
