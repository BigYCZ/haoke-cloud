package com.haoke.commons.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yue13
 * <p>
 * 项目的返回结果类型
 * 根据开发项目进度逐渐增加需要的属性
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HaokeResult {
    /**
     * 需要什么写什么，后期再添加属性
     */

    //响应状态编码 ： 成功200  失败500
    private int status;

    // 返回结果数据：Banner轮播图使用，用于保存图片地址集合
    private Object results;

    //提示消息： Banner轮播图使用，用于查询错误时返回提示消息
    private String msg;

    //返回的数据：热销商品使用，返回查询的热销商品集合   热门推荐使用
    private Object data;

    //返回成功结果 200
    public static HaokeResult ok() {
        HaokeResult result = new HaokeResult();
        result.setResults(200);
        return result;
    }

    //结果返回成功，传入data数据
    public static HaokeResult ok(Object data) {
        HaokeResult result = new HaokeResult();
        result.setResults(200);
        result.setData(data);
        return result;
    }

    //查询失败 返回500
    public static HaokeResult error() {
        HaokeResult result = new HaokeResult();
        result.setResults(500);
        return result;
    }

    //查询失败之后  返回500  并显示失败信息msg
    public static HaokeResult error(String msg) {
        HaokeResult result = new HaokeResult();
        result.setResults(500);
        result.setMsg(msg);
        return result;
    }

}
