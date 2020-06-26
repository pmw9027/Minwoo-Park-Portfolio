package com.lpk.energy.room;

import com.lpk.energy.ClassDo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 8..
 */

@Document(collection = "ROOM_TB")
public class RoomDo  {

    String building;
    String room;
    List<ClassDo> times;

    public RoomDo() {

        this.times =  new ArrayList<>();
    }

    public RoomDo(String building, String room) {
        this.building = building;
        this.room = room;
        this.times =  new ArrayList<>();
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTime(ClassDo time) {
        this.times.add(time);
    }

    public List<ClassDo> getTimes() {
        return times;
    }
}
