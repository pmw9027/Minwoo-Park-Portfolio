package com.lpk.energy.enertalk;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
public interface RealTimeUsageMongoRepository  extends MongoRepository<RealTimeUsageDo, String> {
}
