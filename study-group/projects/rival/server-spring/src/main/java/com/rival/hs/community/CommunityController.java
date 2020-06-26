package com.rival.hs.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Minwoo on 2017. 4. 3..
 */

@Controller
public class CommunityController {


    @RequestMapping(value = "/community")
    public String communityView() {

        return "community";
    }

}
