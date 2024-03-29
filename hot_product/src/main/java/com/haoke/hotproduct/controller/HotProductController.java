package com.haoke.hotproduct.controller;

import com.haoke.commons.vo.HaokeResult;
import com.haoke.hotproduct.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 热销商品控制器
 * @author yue13
 */
@RestController
@CrossOrigin
public class HotProductController {

    @Autowired
    private HotProductService hotProductService;

    /**
     * 查询热销商品数据    查询条件  城市
     *
     * @param city
     * @return
     */
    @GetMapping("/hotProduct")
    public HaokeResult getHotProduct(String city) {
        return hotProductService.getHotProducts(city);
    }
}
