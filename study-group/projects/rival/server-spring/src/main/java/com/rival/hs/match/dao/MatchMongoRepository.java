package com.rival.hs.match.dao;

import com.rival.hs.match.domain.MatchDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 16..
 */

public interface MatchMongoRepository extends MongoRepository<MatchDo, Long> {

    @Query(value = "{ 'city' : ?0, 'type' : ?1 }")
    List<MatchDo> findByCityAndType(String city, String type);

    @Query(value = "{ 'type' : ?0, 'city' : ?1 }")
    Page<MatchDo> findByType(String type, String city, Pageable pageable);

    MatchDo findOne(Long id);

    /*  @Query(value = "{ 'city' : ?0}")
    public Page<MatchDo> findByLocation(String city, Pageable pageable);*/
    //public List<GameDo> find

    //public List<MatchDo> find
}
