package com.rival.hs.user.core;

import com.rival.hs.kakao.KakaoDo;
import com.rival.hs.user.domain.UserDo;
import com.rival.hs.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 2017-03-18.
 */
@RestController
public class UserController implements UserControllerMapper{

    @Autowired
    UserRepository userMongoRepository;

    @Override
    public List<UserDo> index(@RequestParam(required = false) String id, @RequestParam(required = false) String name) {

        if(name ==null)
            return userMongoRepository.findById(id);
        else
            return userMongoRepository.findByName(name);
    }

    @Override
    public String postAccountView(Model model, @RequestBody KakaoDo body) {

        UserDo userDo = userMongoRepository.findOne(body.getAccess_token());
        model.addAttribute("account", userDo);

        return "account/account_view";
    }
}
