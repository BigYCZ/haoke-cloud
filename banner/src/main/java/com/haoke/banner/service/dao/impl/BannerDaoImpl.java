package com.haoke.banner.service.dao.impl;


import com.haoke.banner.service.dao.BannerDao;
import com.haoke.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Banner 数据访问对象
 * 连接MongoDB ，访问banner集合 实现数据的查询
 */
@Repository
public class BannerDaoImpl implements BannerDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> selectBanners(Query query) {
        //条件 查询数据库中的banner名
        List<Banner> result = mongoTemplate.find(query, Banner.class);
        return result;
    }


}
