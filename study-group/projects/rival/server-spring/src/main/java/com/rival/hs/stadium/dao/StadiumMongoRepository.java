package com.rival.hs.stadium.dao;

import com.rival.hs.stadium.domain.StadiumDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by Minwoo on 2017. 4. 1..
 */
public interface StadiumMongoRepository extends MongoRepository<StadiumDo, String> {


    Page<StadiumDo> findAll(Pageable pageable);

    @Query(value = "{ 'location_name' : ?0}")
    Page<StadiumDo> findAllByLocation_name(String location_name, Pageable pageable);

    StadiumDo findById(String id);

}
