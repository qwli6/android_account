package org.lqwit.android.account.entity;

/**
 * Created by liqiwen on 2017/10/31.
 */

public class Account {
    private String accountId;
    private String accountName;
    private String totalAmount;
    private String accountDesc;
    private Integer accountType;//资产还是负债

    public Account(String accountId, String accountName, String totalAmount, Integer accountType) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.totalAmount = totalAmount;
        this.accountType = accountType;
    }

    public Account() {
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
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
