package com.rival.hs.user;


import com.rival.hs.user.domain.UserDo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by user on 2017-03-16.
 */
public interface UserRepository extends MongoRepository<UserDo,String> {

   public List<UserDo> findAll();
   public List<UserDo> findById(String id);
   public List<UserDo> findByName(String name);



}
