package org.lqwit.android.account.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.lqwit.android.account.entity.FundFlow;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liqiwen on 2017/11/2.
 */

public class AccountLocalDataSource implements AccountDataSource {

    private static AccountLocalDataSource INSTANCE;
    private DataBaseHelper mDbHelper;

    /**
     * Prevent direct instantiation.
     * @param context
     */
    private AccountLocalDataSource(@NonNull Context context){
        ActivityUtils.checkNotNull(context);
        mDbHelper = new DataBaseHelper(context);
    }

    public static AccountLocalDataSource getInstance(@NonNull Context context){
        if(INSTANCE == null){
            INSTANCE = new AccountLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getTypes(@NonNull final Integer mTypeFlag, @NonNull final GetTypesCallback callback) {
            Observable.create(new ObservableOnSubscribe<List<Type>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Type>> e) throws Exception {
                   SQLiteDatabase db = mDbHelper.openSqlDataBase();
                   String tableName = PersistenceContract.TypeEntry.TABLE_NAME;
                   String selectionArgs = PersistenceContract.TypeEntry.COLUMN_NAME_TYPE + " = ?";
                    String sql = "select * from " + tableName + " where " + selectionArgs;
                    Cursor c = db.rawQuery(sql, new String[]{ String.valueOf(mTypeFlag) });
                    if(c != null && c.getCount() > 0){
                        List<Type> typeList = new ArrayList<>();
                        while(c.moveToNext()){
                            String name = c.getString(c.getColumnIndex(
                                    PersistenceContract.TypeEntry.COLUMN_NAME_NAME));
                            String picName = c.getString(c.getColumnIndex(
                                    PersistenceContract.TypeEntry.COLUMN_NAME_PIC_NAME));
                            Type type = new Type(picName, name);
                            typeList.add(type);
                        }
                        e.onNext(typeList);
                        e.onComplete();
                        c.close();
                    }
                }
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Type>>() {
                @Override
                public void accept(final List<Type> types) throws Exception {
                    if(types.size() > 0){
                        callback.onTypesLoaded(types);
                    }else{
                        callback.onDataNotAvailable();
                    }
                }
            });

    }

    @Override
    public void getFundFlows(@NonNull LoadAccountsCallback callback) {

    }

    @Override
    public void getFundFlow(@NonNull Integer fundFlowId, @NonNull GetFundFlowCallback callback) {
        SQLiteDatabase db = mDbHelper.openSqlDataBase();
        /**
         * query columns
         */
        String[] projection = {
                PersistenceContract.FundFlowEntry.COLUMN_NAME_ENTRY_ID,
                PersistenceContract.FundFlowEntry.COLUMN_NAME_TITLE
        };
        String selection = PersistenceContract.FundFlowEntry.COLUMN_NAME_ENTRY_ID + "LIKE ?";
        String[] selectionArgs = { String.valueOf(fundFlowId) };

        Cursor c = db.query(PersistenceContract.FundFlowEntry.TABLE_NAME, projection,
                selection, selectionArgs, null, null, null);

        FundFlow fundFlow = null;

        if(c != null && c.getCount() > 0){
            c.moveToFirst();
            Integer itemId = c.getInt(c.getColumnIndexOrThrow(PersistenceContract
                    .FundFlowEntry.COLUMN_NAME_ENTRY_ID));

            fundFlow = new FundFlow();
            fundFlow.setId(itemId);
        }
        if(c != null){
            c.close();
        }
        db.close();

        if(fundFlow != null){
            callback.onTaskLoaded(fundFlow);
        }else{
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void saveFundFlow(@NonNull FundFlow fundFlow) {
        ActivityUtils.checkNotNull(fundFlow);
        SQLiteDatabase db = mDbHelper.openSqlDataBase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistenceContract.FundFlowEntry.COLUMN_NAME_TITLE,
                fundFlow.getName());

        db.insert(PersistenceContract.FundFlowEntry.TABLE_NAME, null, contentValues);
        db.close();
    }
}
