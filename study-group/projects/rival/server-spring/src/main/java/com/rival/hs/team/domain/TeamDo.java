package com.rival.hs.team.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 18..
 */
@Document(collection = "TEAM_TB")
public class TeamDo {

    private String id;
    private String name;
    private String type;
    private String city;
    private String introduce;
    private String captain;
    @Field("member_id")
    private List<String> member_id = new ArrayList<>();

    private String emblem;
    private MultipartFile upload_emblem;
    private MultipartFile upload_img;


    public MultipartFile getUpload_img() {
        return upload_img;
    }

    public void setUpload_img(MultipartFile upload_img) {
        this.upload_img = upload_img;
    }

    public MultipartFile getUpload_emblem() {
        return upload_emblem;
    }

    public void setUpload_emblem(MultipartFile upload_emblem) {
        this.upload_emblem = upload_emblem;
    }

    private String image;


    public TeamDo() {


    }

    public TeamDo(String id, String name, String type, String city, String introduce, String captain, String emblem, String image,String memberid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.city = city;
        this.introduce = introduce;
        this.captain = captain;
        this.emblem = emblem;
        this.image = image;
        this.member_id.add(memberid);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getMember_id() {
        return member_id;
    }

    @Override
    public String toString() {
        return "TeamDo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", introduce='" + introduce + '\'' +
                ", captain='" + captain + '\'' +
                ", emblem='" + emblem + '\'' +
                ", upload_emblem=" + upload_emblem +
                ", upload_img=" + upload_img +
                ", image='" + image + '\'' +
                '}';
    }

    public void setMember_id(String member_id) {
        this.member_id.add(member_id);
    }
}
