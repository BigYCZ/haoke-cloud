package com.haoke.hotproduct.dao;

import com.haoke.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 热销商品中的商品访问数据接口
 * @author yue13
 */
public interface ItemDao {
    //查询热销商品的数据访问方法，根据销量排序，查询条件为城市  查询只要4条数据
    List<Item> getHotProduct(Query query);
}
