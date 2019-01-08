package com.amor.mongo.dao;

import com.amor.mongo.entity.SpitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: LHL
 * @ProjectName: mongodemo
 * @Package: com.aqh.mongo.dao
 * @ClassName: AdminRepository
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface SpitRepository extends MongoRepository<SpitEntity, String> {

    public Page<SpitEntity> findByParentid(String parentid, Pageable pageable);
}
