package com.haoke.banner.service.impl;


import com.haoke.banner.service.dao.BannerDao;
import com.haoke.banner.service.BannerService;
import com.haoke.commons.vo.HaokeResult;
import com.haoke.pojo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner轮播图查询服务实现
 * <p>
 * 微服务应该是一个高内聚，自省性非常高的工程
 * 一个独立的服务，应该有完整的业务链和逻辑链  如果需要使用其他的微服务提供的服务
 * 则通过远程调用 + Hystrix 容灾处理实现服务的应用
 */

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    /**
     * commons 下的application-bannerNginx.yml文件  主要是网站前缀地址
     * 生效的话需要去banner模块下的 application-mongodb.yml 加上 bannerNginx
     */
    @Value("${haoke.banner.nginx.prefix}")
    private String nginxPrefix;

    /**
     * 通过Dao数据访问对象，访问MongoDB，查询轮播图数据
     * 查询规则：根据创建时间的降序排列，查询前4条数据
     */
    @Override
    public HaokeResult getBanners() {

        HaokeResult result = new HaokeResult();

        try {   //定义查询条件
            Query query = new Query();
            //从0开始查询 最多查询4个，并且是降序排序，根据createTime来降序查询
            query.with(
                    PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createTime"))
            );
            List<Banner> banners = bannerDao.selectBanners(query);
            //成功查询 200
            result.setStatus(200);
            //返回查询结果并输出轮播图地址
            List<String> imgResults = new ArrayList<>();  //接收查询的图片地址结果
            //把地址循环加入到banner的URL中
            for (Banner banner : banners) {
                imgResults.add(nginxPrefix + banner.getUrl());
            }
            //从imgResults获取图片地址
            result.setResults(imgResults);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败 500
            result.setStatus(500);
            result.setMsg("轮播图查询失败");
        }
        return result;
    }
}
