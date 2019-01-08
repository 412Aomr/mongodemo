package com.amor.mongo.service.impl;

import com.amor.mongo.dao.SpitRepository;
import com.amor.mongo.entity.SpitEntity;
import com.amor.mongo.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: LHL
 * @ProjectName: mongodemo
 * @Package: com.aqh.mongo.service.impl
 * @ClassName: SpitServiceImpl
 * @Description:
 * @Version: 1.0
 */
@Service
public class SpitServiceImpl implements SpitService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SpitRepository spitDao;

    @Override
    public List<SpitEntity> findAll(){
        return spitDao.findAll();
    }

    @Override
    public SpitEntity findById(String id){
        return spitDao.findById(id).get();
    }

    @Override
    public SpitEntity findByName(String name) {
        Query query=new Query(Criteria.where("nickname").is(name));
        SpitEntity mgt =  mongoTemplate.findOne(query , SpitEntity.class);
        return mgt;
    }

    @Override
    public void save(SpitEntity spit){
        spit.set_id(UUID.randomUUID().toString());
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spitEntity");
        }
        spitDao.save(spit);
    }

    @Override
    public void update(SpitEntity spit){
        spitDao.save(spit);
    }

    @Override
    public void deleteById(String id){
        Query query=new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,"spitEntity");
        // spitDao.deleteById(id);
    }


    @Override
    public Page<SpitEntity> pageQuery(String parentid, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageable);
    }
    @Override
    public void addShare(String spitId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("share", 1);
        mongoTemplate.updateFirst(query, update, "spitEntity");
    }

    @Override
    public void addthumbup(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spitEntity");
    }
}
