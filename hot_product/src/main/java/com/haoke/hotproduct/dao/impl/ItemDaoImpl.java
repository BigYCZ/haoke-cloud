package com.haoke.hotproduct.dao.impl;

import com.haoke.hotproduct.dao.ItemDao;
import com.haoke.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Item> getHotProduct(Query query) {
        return mongoTemplate.find(query, Item.class);
    }
}
