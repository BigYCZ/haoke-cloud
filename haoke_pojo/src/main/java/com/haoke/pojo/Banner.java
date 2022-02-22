package com.haoke.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 轮播图实体
 * 可选属性很多
 * 如：
 * 1、轮播图生效时间，当系统时间大于等于生效时间时，轮播图生效，可被查询
 * 2、有效时长/失效时间    当系统时间大于等于失效时间时，轮播图不可查询
 * 3、优先级 根据具体情况，排序查询
 * 4、随机算法， 代码逻辑实现
 */
@Data //get  set
@NoArgsConstructor  //无参构造方法
@EqualsAndHashCode
@ToString
public class Banner {
    //主键
    private String id;
    //图片地址
    private String url;
    //创建时间
    private Date createTime;
}
