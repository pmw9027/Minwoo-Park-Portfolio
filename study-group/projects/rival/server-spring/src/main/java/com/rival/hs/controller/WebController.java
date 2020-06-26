package com.rival.hs.controller;

/**
 * Created by Sung on 2017. 3. 8..
 */

import com.rival.hs.match.dao.MatchMongoRepository;
import com.rival.hs.team.domain.TeamDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    MatchMongoRepository matchMongoRepository;

    @RequestMapping(value="/")
    public String root() {

        return "redirect:index";
    }


    @RequestMapping(value="/index")
    public String index(HttpSession httpSession) {
        return "index";
    }

   /* @RequestMapping(value="/test")
    public String test(Model model) {

        ArrayList<Object> test = new ArrayList<>();

        Float[] t1 = new Float[10];
        Float[] t2 = new Float[10];

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";


        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/Minwoo/Downloads/8.csv"), "euc-kr"));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(cvsSplitBy);

                if(field[0].contains("x좌표"))
                    continue;
                float a[] = {Float.valueOf(field[0]),Float.valueOf(field[1])};

                test.add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ModelAndView mav = new ModelAndView("test");
        mav.addObject("messages", "hello");
        model.addAttribute("messages", test);


        return "test";
    }*/


    @RequestMapping(value="/login")
    public String login(HttpSession session) {
        return "login";
    }
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userLoginInfo", null);
        return "redirect:index";

    }

    @RequestMapping(value="/create")
    public String create(){return "register";}

    @RequestMapping(value="/generic")
    public String Generic(){return "generic";}

    @RequestMapping(value="/elements")
    public String Elements(){return "elements";}


    @RequestMapping(value="/teamNew")
    public String teamNew(Model model){
        model.addAttribute("TeamDo",new TeamDo());
        return "team/teamNew";
    }

}
