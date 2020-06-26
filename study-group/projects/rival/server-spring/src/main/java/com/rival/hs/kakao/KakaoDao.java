package com.rival.hs.kakao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 14..
 */

@Repository
public interface KakaoDao extends MongoRepository<KakaoDo, String> {



}
