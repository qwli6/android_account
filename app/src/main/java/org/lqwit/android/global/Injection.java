package org.lqwit.android.global;

import android.content.Context;
import android.support.annotation.NonNull;

import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.data.source.local.AccountLocalDataSource;
import org.lqwit.android.data.source.AccountRepository;
import org.lqwit.android.global.utils.ActivityUtils;

/**
 * Created by liqiwen on 2017/11/2.
 * Injection a Repository for persistence data
 */
public class Injection {

    /**
     * provide a Repository
     * @param context
     * @return
     */
    public static AccountDataSource provideAccountRepository(@NonNull Context context){
        ActivityUtils.checkNotNull(context);
        return AccountRepository.getInstance(AccountLocalDataSource.getInstance(context));
    }
}
