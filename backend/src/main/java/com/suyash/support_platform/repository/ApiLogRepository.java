package com.suyash.support_platform.repository;

import com.suyash.support_platform.model.ApiLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiLogRepository extends MongoRepository<ApiLog,String> {
}
