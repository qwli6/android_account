package org.lqwit.android.data.entity;

import android.text.TextUtils;

/**
 * Author: liqiwen
 * Date: 2017/9/22
 * Time: 09:23
 * Email: selfassu@gmail.com
 * Desc:
 */

public class Type {
    private String typeId;
    private String picName;
    private String name;
    private Integer consumeType;

    public Type() {
    }

    public Type(String picName, String name, Integer consumeType) {
        this.picName = picName;
        this.name = name;
        this.consumeType = consumeType;
    }

    public Type(String typeId, String picName, String name, Integer consumeType) {
        this.typeId = typeId;
        this.picName = picName;
        this.name = name;
        this.consumeType = consumeType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public boolean isEmpty(){

        return TextUtils.isEmpty(name) && TextUtils.isEmpty(picName);
    }
}
