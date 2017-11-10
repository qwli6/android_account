package org.lqwit.android.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.entity.FundFlow;
import org.lqwit.android.data.entity.Type;
import org.lqwit.android.data.schema.PersistenceContract;
import org.lqwit.android.data.source.AccountDataSource;
import org.lqwit.android.global.utils.ActivityUtils;

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
 * invoked sqlite database directy
 */
public class AccountLocalDataSource implements AccountDataSource {

    private static AccountLocalDataSource INSTANCE;
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;


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
                            Type type = new Type(picName, name, mTypeFlag);
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
    public void addNewType() {

    }

    @Override
    public void saveAccount(AccountCallback callback, String name,
                            String amount, String desc, String iconName) {
        SQLiteDatabase db = mDbHelper.openSqlDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistenceContract.AccountEntry.COLUMN_NAME_NAME, name);
        contentValues.put(PersistenceContract.AccountEntry.COLUMN_NAME_DESC, desc);
        contentValues.put(PersistenceContract.AccountEntry.COLUMN_NAME_AMOUNT, amount);
        contentValues.put(PersistenceContract.AccountEntry.COLUMN_NAME_PIC_NAME, iconName);
        db.insert(PersistenceContract.AccountEntry.TABLE_NAME, null, contentValues);
        db.close();
        callback.saveSuccess();
    }

    @Override
    public void loadAccountList(final LoadAccountListCallback callback) {
        String tableName = PersistenceContract.AccountEntry.TABLE_NAME;
        final String sql = "select * from " + tableName;
        Observable.create(new ObservableOnSubscribe<List<AccountEntry>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntry>> e) throws Exception {
                db = mDbHelper.openSqlDataBase();
                Cursor cursor = db.rawQuery(sql, null);
                if(cursor != null && cursor.getCount() > 0){
                    List<AccountEntry> accountEntries =new ArrayList<>();
                    while (cursor.moveToNext()){
                        AccountEntry accountEntry = new AccountEntry();
                        accountEntry.setId(cursor.getInt(cursor.getColumnIndex(
                                PersistenceContract.AccountEntry.COLUMN_NAME_ID)));
                        accountEntry.setName(cursor.getString(cursor.getColumnIndex(
                                PersistenceContract.AccountEntry.COLUMN_NAME_NAME)));
                        accountEntry.setAmount(cursor.getString(cursor.getColumnIndex(
                                PersistenceContract.AccountEntry.COLUMN_NAME_AMOUNT)));
                        accountEntry.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(
                                PersistenceContract.AccountEntry.COLUMN_NAME_DESC)));
                        accountEntry.setPicName(cursor.getString(cursor.getColumnIndex(
                                PersistenceContract.AccountEntry.COLUMN_NAME_PIC_NAME)));
                        accountEntries.add(accountEntry);
                    }
                    cursor.close();
                    db.close();
                    e.onNext(accountEntries);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AccountEntry>>() {
            @Override
            public void accept(List<AccountEntry> accountEntries) throws Exception {
                callback.loadSuccess(accountEntries);
            }
        });
    }

    @Override
    public void getFundFlows(@NonNull LoadAccountsCallback callback) {

    }

    /**
    @Override
    public void getFundFlow(@NonNull Integer fundFlowId, @NonNull GetFundFlowCallback callback) {
        SQLiteDatabase db = mDbHelper.openSqlDataBase();
        /**
         * query columns
         */
//        String[] projection = {
//                PersistenceContract.FundFlowEntry.COLUMN_NAME_ENTRY_ID,
//                PersistenceContract.FundFlowEntry.COLUMN_NAME_TITLE
//        };
//        String selection = PersistenceContract.FundFlowEntry.COLUMN_NAME_ENTRY_ID + "LIKE ?";
//        String[] selectionArgs = { String.valueOf(fundFlowId) };
//
//        Cursor c = db.query(PersistenceContract.FundFlowEntry.TABLE_NAME, projection,
//                selection, selectionArgs, null, null, null);
//
//        FundFlow fundFlow = null;
//
//        if(c != null && c.getCount() > 0){
//            c.moveToFirst();
//            Integer itemId = c.getInt(c.getColumnIndexOrThrow(PersistenceContract
//                    .FundFlowEntry.COLUMN_NAME_ENTRY_ID));
//
//            fundFlow = new FundFlow();
//            fundFlow.setId(itemId);
//        }
//        if(c != null){
//            c.close();
//        }
//        db.close();
//
//        if(fundFlow != null){
//            callback.onTaskLoaded(fundFlow);
//        }else{
//            callback.onDataNotAvailable();
//        }
//    }
//
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
