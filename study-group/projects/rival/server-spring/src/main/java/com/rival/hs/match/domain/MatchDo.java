package com.rival.hs.match.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 3. 16..
 */

@Document(collection = "GAME_TB")
public class MatchDo {

    /**
     * @author MinwooPark<pmw9027@outlook.kr>
     * @param Long    id              팀 뭐시기
     * @param String    type            팀 뭐시기
     * @param String    city            팀 뭐시기
     * @param String    team            팀 뭐시기
     * @param String    emblem            팀 뭐시기
     * @param String    contents        팀 뭐시기
     * @param String    title           팀 뭐시기
     * @param Integer   people_num      팀 뭐시기
     * @param String    getStadium         팀 뭐시기
     * @param String    time_update     팀 뭐시기
     * @param String    time_game       팀 뭐시기
     * @param String    city            팀 뭐시기
     */

    @Id
    private Long id;
    private String type;
    private String city;
    private String team;
    private String emblem;
    private String contents;
    private String title;
    private int people_num;
    private String stadium;
    private String time_update;
    private String time_game;
    public MatchDo() {

    }

    public MatchDo(String type, String city, String team, String emblem, String contents, String title, int people_num, String stadium, String time_update, String time_game) {
        this.type = type;
        this.city = city;
        this.team = team;
        this.emblem = emblem;
        this.contents = contents;
        this.team = team;
        this.title = title;
        this.people_num = people_num;
        this.stadium = stadium;
        this.time_update = time_update;
        this.time_game = time_game;
    }

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTeam() {return team;}

    public void setTeam(String team) {this.team = team;}

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTime_update() {
        return time_update;
    }

    public void setTime_update(String time_update) {
        this.time_update = time_update;
    }

    public String getTime_game() {
        return time_game;
    }

    public void setTime_game(String time_game) {
        this.time_game = time_game;
    }
}

