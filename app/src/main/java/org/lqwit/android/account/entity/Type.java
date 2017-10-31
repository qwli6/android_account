package org.lqwit.android.account.entity;

/**
 * Author: liqiwen
 * Date: 2017/9/22
 * Time: 09:23
 * Email: selfassu@gmail.com
 * Desc:
 */

public class Type {
    private String picName;
    private String name;

    public Type(String picName, String name) {
        this.picName = picName;
        this.name = name;
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
}
