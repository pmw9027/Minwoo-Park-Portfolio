package com.rival.hs.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Minwoo on 2017. 4. 24..
 */
@Repository
public class CounterRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    public CounterDo getCounterDo(String tableName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(tableName));
        Update update = new Update();
        update.inc("sequence_value", 1);

        CounterDo counterDo = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), CounterDo.class);

        return counterDo;
    }
}
