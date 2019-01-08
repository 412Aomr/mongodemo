package com.amor.mongo.service;

import com.amor.mongo.entity.SpitEntity;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: mongodemo
 * @Package: com.aqh.mongo.service
 * @ClassName: SpitService
 * @Description:
 * @Version: 1.0
 */
public interface SpitService {
    List<SpitEntity> findAll();

    SpitEntity findById(String spitId);

    SpitEntity findByName(String name);

    void save(SpitEntity spit);

    void update(SpitEntity spit);

    void deleteById(String spitId);

    Page<SpitEntity> pageQuery(String parentid, int page, int size);

    void addthumbup(String spitId);

    void addShare(String spitId);
}
