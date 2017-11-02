package org.lqwit.android.account.entity;

/**
 * Created by liqiwen on 2017/10/31.
 */

public class Account {
    private Integer accountId;
    private String accountName;
    private String totalAmount;
    private String accountDesc;
    private Integer accountType;//资产还是负债

    private boolean isDefault; //是否为默认支付账户

    public Account(Integer accountId, String accountName, String totalAmount, Integer accountType) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.totalAmount = totalAmount;
        this.accountType = accountType;
    }

    public Account() {
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
