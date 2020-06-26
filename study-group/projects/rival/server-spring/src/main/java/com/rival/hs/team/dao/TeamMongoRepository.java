package com.rival.hs.team.dao;

import com.rival.hs.team.domain.TeamDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 18..
 */
public interface TeamMongoRepository extends MongoRepository<TeamDo, String> {

    List<TeamDo> findAllByName(String name);
    //List<TeamDo> findByMember_id(int id);
    //public List<TeamDo> findByMember_id(String id);


    @Query(value = "{ 'city' : ?0, 'type' : ?1 }")
    List<TeamDo> findByCityAndType(String city,String type);

    Page<TeamDo> findAll(Pageable pageable);

    TeamDo findByName(String name);

}