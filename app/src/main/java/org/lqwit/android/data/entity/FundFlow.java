package org.lqwit.android.data.entity;

/**
 * Created by liqiwen on 2017/10/30.
 */

public class FundFlow {
    private Integer id;
    private String name; //收入/支出名称
    private String pic; //收入/支出图片
    private String payType; //支付方式。微信-支付宝-零钱-银行卡
    private String price; //收入/支出金额
    private String date; //日期
    private String time; //时间，精确时间
    private Integer type; //收入/支出类型
    private String memo; //备注

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
