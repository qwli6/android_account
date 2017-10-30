package org.lqwit.android.account.entity;

/**
 * Author: liqiwen
 * Date: 2017/9/22
 * Time: 09:23
 * Email: selfassu@gmail.com
 * Desc:
 */

public class Type {
    private int picId;
    private String name;

    public Type(int picId, String name) {
        this.picId = picId;
        this.name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
