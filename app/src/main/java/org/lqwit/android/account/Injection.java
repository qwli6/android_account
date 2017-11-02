package org.lqwit.android.account;

import android.content.Context;
import android.support.annotation.NonNull;

import org.lqwit.android.account.db.AccountLocalDataSource;
import org.lqwit.android.account.db.AccountRepository;
import org.lqwit.android.account.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class Injection {

    public static AccountRepository provideAccountRepository(@NonNull Context context){
        ActivityUtils.checkNotNull(context);
        return AccountRepository.getInstance(AccountLocalDataSource.getInstance(context));
    }

    public static AccountLocalDataSource provideAccountLocalDataSource(@NonNull Context context){
        ActivityUtils.checkNotNull(context);
        return AccountLocalDataSource.getInstance(context);
    }
}
