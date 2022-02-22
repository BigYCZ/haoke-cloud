package com.haoke.hotproduct.service.impl;

import com.haoke.commons.vo.HaokeResult;
import com.haoke.hotproduct.dao.ItemDao;
import com.haoke.hotproduct.service.HotProductService;
import com.haoke.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * 热销商品服务实现
 */
@Service
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private ItemDao itemDao;
    @Value("${haoke.banner.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public HaokeResult getHotProducts(String city) {

        //查询热销商品 需要满足4条数据
        Query query = new Query();
        //根据销量做降序排列  只展示4个数据  并进行分页
        query.with(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "sales")));
        //查询条件     addCriteria:增加查询条件标准   标准是城市是哪个并且是什么城市 -- 根据city名字精准查询
        query.addCriteria(Criteria.where(city).is(city));
        List<Item> items = itemDao.getHotProduct(query);

        //数据不足4条时查询
        if (items.size() < 4) {
            //查询的热销商品数量不足时，需要查询其他城市的热销商品，填补到当前查询结果
            Query otherQuery = new Query();
            //查询条件，查询当前城市以外的其他城市热销商品，避免重复数据
            query.addCriteria(Criteria.where("city").ne(city));
            //排序和分页
            otherQuery.with(PageRequest.of(0, 4 - items.size(),
                    Sort.by(Sort.Direction.DESC, "sales")));
            List<Item> otherItems = itemDao.getHotProduct(otherQuery);
            //将其他城市的热销商品数据，填充到当前城市的热销商品数据集合中，补充4条数据
            items.addAll(otherItems);
        }
        //查询结果items，理论上一定有4条数据，如果不足，可以使用托底数据填充
        if (items.size() < 4) {  //如果所有的热销商品数据总计不足4条，使用托底数据填补，防止页面上显示乱了
            for (int i = items.size(); i <= 4; i++) {
                items.add(fallBackItem());
            }
        }

        //将图片路径，从相对路径转换为绝对路径，增加nginx地址前缀
        items = this.changeImgsUrl(items);

        //从HaokeResult中调取data返回结果
        return HaokeResult.ok(items);
    }

    //将集合中的每个Item类型对象的图片地址，增加前缀
    private List<Item> changeImgsUrl(List<Item> items) {
        for (Item item : items) {
            List<String> newImgs = new ArrayList<>();
            for (String img : item.getImgs()) {
                newImgs.add(nginxPrefix + img);
            }
            item.setImgs(newImgs);
        }
        return items;
    }

    /**
     * 托底数据生成
     *
     * @return
     */
    private Item fallBackItem() {
        Item item = new Item();
        item.setId("62148327bc021a6038d150c3");
        item.setCity("北京");
        item.setHouseType("150 ㎡");
        item.setImgs(
                Arrays.asList(
                        "group1/M00/00/00/wKiIgmIGCQGAWOcYABLGy4zg5nA524.png",
                        "group1/M00/00/00/wKiIgmIGDBiAJwPMAAjIobLVxBY599.png",
                        "group1/M00/00/00/wKiIgmIGDDKADXRbAAro9yfjOVw486.png"

                )
        );
        item.setPrice(15000L);
        item.setRecommendation(true);
        item.setRecoSort((byte) 9);
        item.setRentType("整租");
        item.setSales(100L);
        item.setTitle("北京高档公寓");
        Map<String, String> info = new HashMap<>();
        info.put("years", "2010");
        info.put("type", "3室2厅");
        info.put("level", "10/18");
        info.put("style", "精装修");
        info.put("orientation", "南北通透");
        item.setInfo(info);
        return item;
    }
}
