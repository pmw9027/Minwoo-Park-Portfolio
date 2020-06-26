package com.lpk.energy.room;

import com.lpk.energy.ClassDo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 8..
 */
public interface RoomMongoRepository extends MongoRepository<RoomDo, String> {

    public List<RoomDo> findByBuilding(String Building);

}
