package com.rival.hs.stadium.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 3. 29..
 */

@Document(collection = "STADIUM_TB")
public class StadiumDo {

    /**
     *@auter MinwooPark<pmw9027@outlook.kr>
     *
     *@param String     stadium_name        개방시설명
     *@param String     location_name       개방장소명
     *@param String     type                개방시설유형구분
     *@param String     holiday             휴관일
     *@param String     weekday_time_start  평일운영시작시각
     *@param String     weekday_time_end    평일운영종료시각
     *@param String     b_fee               유료사용여부
     *@param String     standard_time       사용기준시간
     *@param Integer    fare                사용료
     *@param String     name                초과사용단위시간
     *@param String     excess_fare         초과사용료
     *@param String     capacity            수용가능인원수
     *@param String     area                면적
     *@param String     information         부대시설정보
     *@param String     book_way            신청방법구분
     *@param String     picture             시설사진정보
     *@param String     road_address        소재지도로명주소
     *@param String     management_agency   관리기관명
     *@param String     department          담당부서명
     *@param String     phone_num           사용안내전화번호
     *@param String     homepage            홈페이지주소
     *@param String     latitude            위도
     *@param String     longitude           경도
     *@param String     data_time           데이터기준일자
     */

    @Id
    private String  id;
    private String  stadium_name;
    private String  location_name;
    private String  type;
    private String  holiday;
    private String  weekday_time_start;
    private String  weekday_time_end;
    private String  holiday_time_start;
    private String  holiday_time_end;
    private String  b_fee;
    private String  standard_time;
    private String  fare;
    private String  name;
    private String  excess_fare;
    private String  capacity;
    private String  area;
    private String  information;
    private String  book_way;
    private String  picture;
    private String  road_address;
    private String  management_agency;
    private String  department;
    private String  phone_num;
    private String  homepage;
    private String  latitude;
    private String  longitude;
    private String  data_time;


    public void setStadium_name(String stadium_name) {
        this.stadium_name = stadium_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public void setWeekday_time_start(String weekday_time_start) {
        this.weekday_time_start = weekday_time_start;
    }

    public void setWeekday_time_end(String weekday_time_end) {
        this.weekday_time_end = weekday_time_end;
    }

    public void setB_fee(String b_fee) {
        this.b_fee = b_fee;
    }

    public void setStandard_time(String standard_time) {
        this.standard_time = standard_time;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExcess_fare(String excess_fare) {
        this.excess_fare = excess_fare;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setBook_way(String book_way) {
        this.book_way = book_way;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setRoad_address(String road_address) {
        this.road_address = road_address;
    }

    public void setManagement_agency(String management_agency) {
        this.management_agency = management_agency;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }

    public void setHoliday_time_start(String holiday_time_start) {
        this.holiday_time_start = holiday_time_start;
    }

    public void setHoliday_time_end(String holiday_time_end) {
        this.holiday_time_end = holiday_time_end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStadium_name() {
        return stadium_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getType() {
        return type;
    }

    public String getHoliday() {
        return holiday;
    }

    public String getWeekday_time_start() {
        return weekday_time_start;
    }

    public String getWeekday_time_end() {
        return weekday_time_end;
    }

    public String getHoliday_time_start() {
        return holiday_time_start;
    }

    public String getHoliday_time_end() {
        return holiday_time_end;
    }

    public String getB_fee() {
        return b_fee;
    }

    public String getStandard_time() {
        return standard_time;
    }

    public String getFare() {
        return fare;
    }

    public String getName() {
        return name;
    }

    public String getExcess_fare() {
        return excess_fare;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getArea() {
        return area;
    }

    public String getInformation() {
        return information;
    }

    public String getBook_way() {
        return book_way;
    }

    public String getPicture() {
        return picture;
    }

    public String getRoad_address() {
        return road_address;
    }

    public String getManagement_agency() {
        return management_agency;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getData_time() {
        return data_time;
    }



    @Override
    public String toString() {
        return "StadiumDo{" +
                "id='" + id + '\'' +
                ", stadium_name='" + stadium_name + '\'' +
                ", location_name='" + location_name + '\'' +
                ", type='" + type + '\'' +
                ", holiday='" + holiday + '\'' +
                ", weekday_time_start='" + weekday_time_start + '\'' +
                ", weekday_time_end='" + weekday_time_end + '\'' +
                ", holiday_time_start='" + holiday_time_start + '\'' +
                ", holiday_time_end='" + holiday_time_end + '\'' +
                ", b_fee='" + b_fee + '\'' +
                ", standard_time='" + standard_time + '\'' +
                ", fare='" + fare + '\'' +
                ", name='" + name + '\'' +
                ", excess_fare='" + excess_fare + '\'' +
                ", capacity='" + capacity + '\'' +
                ", area='" + area + '\'' +
                ", information='" + information + '\'' +
                ", book_way='" + book_way + '\'' +
                ", picture='" + picture + '\'' +
                ", road_address='" + road_address + '\'' +
                ", management_agency='" + management_agency + '\'' +
                ", department='" + department + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", homepage='" + homepage + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", data_time='" + data_time + '\'' +
                '}';
    }
}
