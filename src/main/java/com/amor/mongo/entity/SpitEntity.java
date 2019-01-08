package com.amor.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: LHL
 * @ProjectName: mongodemo
 * @Package: com.aqh.mongo.entity
 * @ClassName: SpitEntity
 * @Description:
 * @Version: 1.0
 */
@Data
public class SpitEntity  implements Serializable {
    /**
     * ID主键
     * */
    @Id
    private String _id;
    /**
     * 吐槽内容
     * */
    private String content;
    /**
     * 发布日期
     * */
    private Date publishtime;
    /**
     * 发布人ID
     * */
    private String userid;
    /**
     * 发布人昵称
     * */
    private String nickname;
    /**
     * 浏览量
     * */
    private Integer visits;
    /**
     * 点赞数
     * */
    private Integer thumbup;
    /**
     * 分享数
     * */
    private Integer share;
    /**
     * 回复数
     * */
    private Integer comment;
    /**
     * 是否可见 0 不可见,1 可见
     * */
    private String state;
    /**
     * 上级ID 如果为0表示文章的顶级评论
     * */
    private String parentid;
}