package com.rival.hs.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HeeJoongKim on 2017-04-26.
 */
@Controller
public class AccountController {

    @RequestMapping(value="/account")
    public String account() {
        return "/account/account_view";
    }
}
