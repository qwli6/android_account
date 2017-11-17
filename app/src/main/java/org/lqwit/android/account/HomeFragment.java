package org.lqwit.android.account;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.data.source.local.DataBaseHelper;
import org.lqwit.android.fundflow.KeepAnAccountActivity;
import org.lqwit.android.fundflow.expend.ExpendFragment;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.DateUtils;
import org.lqwit.android.other.budget.SetBudgetActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends AppBaseFragment{

    private static final String TAG = "HomeFragment";

    @BindView(R.id.tv_home_set_budget)
    TextView monthBudget;
    @BindView(R.id.tv_home_day_budget)
    TextView dayBudget;
    @BindView(R.id.account_total_amount)
    TextView accountTotalAmount;
    @BindView(R.id.today_expend)
    TextView todayExpend;
    @BindView(R.id.month_expend)
    TextView monthExpend;
    private SQLiteDatabase dbHelper;


    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_home, null);
        ButterKnife.bind(this, root);
        dbHelper = new DataBaseHelper(mActivity).openSqlDataBase();
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        findMonthBudget(DateUtils.formatNoDay(new Date()));
        findTodayExpend(DateUtils.formatNoYear(new Date()));
        findTotalAmount();
        findMonthExpend(DateUtils.formatNoYear(new Date()));
    }

    /**
     * 查询本月消费
     * @param yearMonth 年月
     */
    private void findMonthExpend(final String yearMonth) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String sql = "select income_expend_amount from income_expend_record where " +
                        "income_expend_type = ? and income_expend_date like ?";
                Cursor cursor = dbHelper.rawQuery(sql, new String[]{ExpendFragment.CONSUME_TYPE.toString(),
                        yearMonth +"%"});
                if(cursor != null && cursor.getCount()> 0){
                    Double todayExpendAmount = 0.00;
                    while (cursor.moveToNext()) {
                        String amount = cursor.getString(
                                cursor.getColumnIndex("income_expend_amount"));
                        todayExpendAmount += Double.parseDouble(amount);
                    }
                    e.onNext(String.valueOf(todayExpendAmount));
                    cursor.close();
                }else{
                    e.onNext("0");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        monthExpend.setText("本月已消费 " + s + " 元");
                    }
                });
    }

    private void findTotalAmount() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String sql = "select amount from user_account";
                Cursor cursor = dbHelper.rawQuery(sql, null);
                Double totalAmount = 0.00;
                if(cursor != null && cursor.getCount() > 0){
                    while (cursor.moveToNext()) {
                        String amount = cursor.getString(cursor.getColumnIndex("amount"));
                        totalAmount = totalAmount + Double.parseDouble(amount);
                    }
                    e.onNext(String.valueOf(totalAmount));
                    e.onComplete();
                    cursor.close();
                }
            }
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                accountTotalAmount.setText("￥ " + s);
            }
        });
    }


    @OnClick({R.id.home_button_keep_an_account, R.id.tv_home_set_budget})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.home_button_keep_an_account:
                startActivity(new Intent(getActivity(), KeepAnAccountActivity.class));
                break;
            case R.id.tv_home_set_budget:
                startActivity(new Intent(mActivity, SetBudgetActivity.class));
                break;
                default:
                    break;
        }
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    /**
     * 查找当日消费
     * @param noDay
     */
    private void findTodayExpend(final String noDay){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String sql = "select income_expend_amount from income_expend_record where " +
                        "income_expend_type = ? and income_expend_date = ?";
                Cursor cursor = dbHelper.rawQuery(sql, new String[]{ExpendFragment.CONSUME_TYPE.toString(),
                        noDay});
                if(cursor != null && cursor.getCount()> 0){
                    Double todayExpendAmount = 0.00;
                    while (cursor.moveToNext()) {
                        String amount = cursor.getString(
                                cursor.getColumnIndex("income_expend_amount"));
                        todayExpendAmount += Double.parseDouble(amount);
                    }
                    e.onNext(String.valueOf(todayExpendAmount));
                    cursor.close();
                }else{
                    e.onNext("0");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                todayExpend.setText("今日已消费 " + s + " 元");
            }
        });
    }

    private void findMonthBudget(final String noDay){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String sql = "select * from month_budget where budget_date = ?";
                dbHelper = new DataBaseHelper(mActivity).openSqlDataBase();
                Cursor cursor = dbHelper.rawQuery(sql, new String[]{noDay});
                if(cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String budget = cursor.getString(
                                cursor.getColumnIndex("budget_amount"));
                        e.onNext(budget);
                    }
                    cursor.close();
                }else{
                    e.onNext("0");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if(Integer.parseInt(s) == 0){
                    Toast.makeText(mActivity, "点击设置预算，良好控制支出", Toast.LENGTH_SHORT).show();
                }else{
                    monthBudget.setText(s);
                    dayBudget.setText(String.valueOf(Integer.parseInt(s)/30));
                }
            }
        });
    }
}
