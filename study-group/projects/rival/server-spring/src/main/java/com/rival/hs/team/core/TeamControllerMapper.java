package com.rival.hs.team.core;

import com.rival.hs.team.domain.TeamDo;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Minwoo on 2017. 4. 24..
 */
public interface TeamControllerMapper {


    @RequestMapping("/team/board/{id}")
    String getTeamDetailView(Model model, @PathVariable String id);

    @RequestMapping(value = "/team")
    String getTeamMenuView(Model model,HttpSession session);

    @RequestMapping("/team/board/list")
    String getTeamListView(Model model, Pageable pageable);

    @RequestMapping("/team/board/new")
    String getTeamCreateView();


    @RequestMapping( value = "/team_make", method = RequestMethod.POST)
    String postTeamCreate(TeamDo form,  BindingResult result, Model model, HttpSession session,HttpServletRequest request);

}
