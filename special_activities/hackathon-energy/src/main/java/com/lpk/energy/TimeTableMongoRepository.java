package com.lpk.energy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
public interface TimeTableMongoRepository extends MongoRepository<ClassDo, String> {

    @Query(value = "{ 'room' : ?0}")
    public List<ClassDo> findByTime(String room);

}
