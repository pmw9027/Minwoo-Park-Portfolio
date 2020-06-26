package com.rival.hs.kakao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface KakaoMongoRepository extends MongoRepository<KakaoDo, String> {

    @Query(value = "{ 'kakao_info._id' : ?0}")
    List<KakaoDo> findByKakaoId(String _id);

}
