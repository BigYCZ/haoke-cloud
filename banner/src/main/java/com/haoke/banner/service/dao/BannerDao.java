package com.haoke.banner.service.dao;


import com.haoke.pojo.Banner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author yue13
 * <p>
 * Banner 轮播图的数据访问对象，实现数据查询
 */
public interface BannerDao {

    List<Banner> selectBanners(Query query);
}
