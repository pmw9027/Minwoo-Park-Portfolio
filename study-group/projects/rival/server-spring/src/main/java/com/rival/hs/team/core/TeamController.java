package com.rival.hs.team.core;

import com.rival.hs.team.domain.TeamDo;
import com.rival.hs.team.dao.TeamMongoRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Minwoo on 2017. 3. 18..
 */

@Controller
public class TeamController implements TeamControllerMapper{


    @Autowired
    TeamMongoRepository teamMongoRepository;

    @Override
    public String getTeamCreateView() {
        return "team/teamNew";
    }

    @Override
    public String getTeamDetailView(Model model, String id) {
        TeamDo teamDo = teamMongoRepository.findByName(id);

        model.addAttribute("team", teamDo);
        return "team/team_detail_view";
    }

    @Override
    public String getTeamMenuView(Model model, HttpSession session) {
        List<TeamDo> Buffer = new ArrayList<>();
        List<TeamDo> teamDoList = teamMongoRepository.findAll();
        String kakao_id = "385806550"; //땅현리 id 세션값  일단 이렇게 처리해두겠음
        for(int i=0;i<teamDoList.size();i++)
        {
            for(int j=0;j<teamDoList.get(i).getMember_id().size();j++) // 카카오 ID에 대해 팀 검사
            {
                if(kakao_id.equals(teamDoList.get(i).getMember_id().get(j)))
                {
                    Buffer.add(teamDoList.get(i));
                }
            }
        }
        model.addAttribute("MyteamList",Buffer);

        return "team/team";
    }
    @Override
    public String getTeamListView(Model model, Pageable pageable) {

        Page<TeamDo> teams = teamMongoRepository.findAll(pageable);
        model.addAttribute("teams", teams);

        return "team/teamListView";
    }
    @Override
    public String postTeamCreate(TeamDo form, BindingResult result, Model model, HttpSession session,HttpServletRequest request) {
        MultipartFile uploademblen = null,uploadimg=null;


        TeamDo teamdo = new TeamDo();
        teamdo.setName(form.getName());
        teamdo.setCaptain(form.getCaptain());
        teamdo.setCity(form.getCity());
        teamdo.setIntroduce(form.getIntroduce());
        teamdo.setType(form.getType());

        uploademblen = form.getUpload_emblem();
        uploadimg = form.getUpload_img();
        System.out.println(teamdo.toString());
        if (uploademblen != null || uploadimg !=null) {

            String emblemName = uploademblen.getOriginalFilename();
            String imgName = uploadimg.getOriginalFilename();
            String root_path = request.getSession().getServletContext().getRealPath("/");
            String attach_path = "WEB-INF/classes/static/img/";

            String emblen_name = uploademblen.getOriginalFilename();
            String img_name = uploadimg.getOriginalFilename();

            File emblen_file = new File(root_path+attach_path+emblemName);
            File img_file = new File(root_path+attach_path+img_name);
            // 1. FileOutputStream 사용
            // byte[] fileData = file.getBytes();
            // FileOutputStream output = new FileOutputStream("C:/images/" + fileName);
            // output.write(fileData);

            // 2. File 사용
            try {
                System.out.println("패쓰"+emblen_file.getPath());
                uploademblen.transferTo(emblen_file);
                uploadimg.transferTo(img_file);
                teamdo.setImage(form.getUpload_img().getOriginalFilename());
                teamdo.setEmblem(form.getUpload_emblem().getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String kakao_id = (String) session.getAttribute("id"); //세션값. 수정해야됨 !!
        teamdo.setMember_id(kakao_id);

        teamMongoRepository.save(teamdo);
        return "redirect:/team/team";

    }
}

