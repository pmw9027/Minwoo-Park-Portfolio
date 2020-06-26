package com.lpk.energy;


import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 4. 7..
 */


@Document(collection = "CLASS_TB")
public class ClassDo {

    /**
     *  @param
     *  @param
     *  @param
     *  @param
     *  @param
     *  @Param
     *  @param
     */
    private String classId;
    private String name;
    private String time;
    private String room;
    private String professor;
    private String student_count;
    private StringBuffer test;

    public ClassDo(String classId, String name, String time, String room, String professor, String student_count) {
        this.classId = classId;
        this.name = name;
        this.time = time;
        this.room = room;
        this.professor = professor;
        this.student_count = student_count;
        this.test = new StringBuffer("");
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getStudent_count() {
        return student_count;
    }

    public void setStudent_count(String student_count) {
        this.student_count = student_count;
    }

    public void setTest(char test) {
        this.test.append(test);
    }

    public String getTest() {
        return test.toString();
    }
}
