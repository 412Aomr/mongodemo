package com.amor.mongo.controller;

import com.amor.mongo.common.PageResult;
import com.amor.mongo.common.Result;
import com.amor.mongo.common.StatusCode;
import com.amor.mongo.entity.SpitEntity;
import com.amor.mongo.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LHL
 * @ProjectName: mongodemo
 * @Package: com.aqh.mongo.controller
 * @ClassName: SpitController
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        Assert.notNull(spitId, "spitId is not null");
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }


    @RequestMapping(value = "/findname", method = RequestMethod.GET)
    public Result findByName(@PathVariable String nickname){
        Assert.notNull(nickname, "nickname is not null");
        return new Result(true, StatusCode.OK, "查询成功", spitService.findByName(nickname));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody SpitEntity spit){
        Assert.notNull(spit, "spit is not null");
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId, @RequestBody SpitEntity spit){
        Assert.notNull(spitId, "spitId is not null");
        Assert.notNull(spit, "spit is not null");
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        Assert.notNull(spitId, "spitId is not null");
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<SpitEntity> pageData = spitService.pageQuery(parentid, page, size);
        Assert.notNull(parentid, "parentid is not null");
        Assert.notNull(page, "page is not null");
        Assert.notNull(size, "size is not null");
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<SpitEntity>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result addthumbup(@PathVariable String spitId){
        Assert.notNull(spitId, "spitId is not null");
        spitService.addthumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    @PutMapping("/share/{spitId}")
    public Result addShare(@PathVariable String spitId) {
        Assert.notNull(spitId, "spitId is not null");
        spitService.addShare(spitId);
        return new Result(true, StatusCode.OK, "分享成功!");
    }
}