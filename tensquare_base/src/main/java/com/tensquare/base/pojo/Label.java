package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xiaokuan
 * 2020/11/2 18:34 星期一
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {
    @Id
    private String id;
    private String labelname;
    private String state;//状态
    private Long count;//使用数量
    private String recommend;//是否推荐
    private Long fans; //关注数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }
}