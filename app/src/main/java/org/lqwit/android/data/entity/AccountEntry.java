package org.lqwit.android.data.entity;

/**
 * Created by liqiwen on 2017/10/31.
 */

public class AccountEntry {
    private Integer id;
    private String name;
    private String desc;
    private String amount;
    private String picName;

    public AccountEntry(Integer id, String name, String desc, String amount, String picName) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.amount = amount;
        this.picName = picName;
    }

    public AccountEntry() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}
