package org.lqwit.android.account;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends AppBaseFragment{

    private static final String TAG = "HomeFragment";

    @BindView(R.id.tv_home_set_budget)
    TextView monthBudget;
    @BindView(R.id.tv_home_day_budget)
    TextView dayBudget;
    private SQLiteDatabase dbHelper;


    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_home, null);
        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        findMonthBudget(DateUtils.formatNoDay(new Date()));

        findTodayExpend(DateUtils.formatNoDay(new Date()));
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
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                List<String> amounts = new ArrayList<>();
                String sql = "select * from income_expend_record where " +
                        "income_expend_type = ? and income_expend_date = ?";
                Cursor cursor = dbHelper.rawQuery(sql, new String[]{ExpendFragment.CONSUME_TYPE.toString(),
                        noDay});
                if(cursor != null && cursor.getCount()> 0){
                    String amount = cursor.getString(
                            cursor.getColumnIndex("income_expend_amount"));
                    amounts.add(amount);
                    e.onNext(amounts);
                    e.onComplete();
                    cursor.close();
                }else{
                    e.onError(new Throwable());
                }
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                for (String string :
                        strings) {
                    Log.d(TAG, string);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "今日无交易");
            }

            @Override
            public void onComplete() {

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
                        e.onComplete();
                    }
                    cursor.close();
                }else{
                    e.onNext("0");
                    e.onComplete();
                }
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
