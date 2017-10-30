package org.lqwit.android.account.utils;

import android.widget.EditText;

import org.lqwit.android.account.config.Config;

import java.text.DecimalFormat;

/**
 * Author: liqiwen
 * Date: 2017/9/21
 * Time: 16:54
 * Email: selfassu@gmail.com
 * Desc:
 */

public class CurrencyUtils {
    public static void checkInputMoney(StringBuilder content, String str, EditText showMoney){
        if(content.length() > 0 && content.toString().contains(".")){
            if(content.substring(content.lastIndexOf(".")).length() > 2){
                return;
            }
        }
        if(content.toString().equals("0")){
            content.deleteCharAt(0);
        }

        if(showMoney.getText().toString().equals("0")){
            showMoney.setText("0");
        }
        if(content.length() <= Config.MAX_MONEY){
            content.append(str);
        }
        showMoney.setText(content);
    }

    /**
     * 格式化金额，用逗号分隔
     * @param sourceAmount 原目标格式的金额
     * @return
     */
    public static String formatAmount(String sourceAmount){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(Double.parseDouble(sourceAmount));
    }
}
