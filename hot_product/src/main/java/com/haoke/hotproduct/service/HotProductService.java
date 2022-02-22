package com.haoke.hotproduct.service;

import com.haoke.commons.vo.HaokeResult;

/**
 * @author yue13
 * 热销商品服务接口
 */
public interface HotProductService {
    /**
     * 查询热销商品方法
     * 查询的返回结果，热销商品的数量必须是4
     * 查询条件所在城市的热销商品数量大于4的时候，只查询商品排序的前4位商品
     * 若查询条件所在城市的热销商品数量小于4的时候，
     * 从其他的城市热销商品中查询销量排序靠前的补上去
     *
     */
    HaokeResult getHotProducts(String city);

}
