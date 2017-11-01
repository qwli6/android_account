package org.lqwit.android.account.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.activity.FundFlowDetailActivity;
import org.lqwit.android.account.activity.KeepAnAccountActivity;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.FundFlow;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class FundFlowFragment extends Fragment {

    private static final String TAG = "FundFlowFragment";

    @BindView(R.id.home_button_keep_an_account)
    Button keepAnAccount;
    @BindView(R.id.today_recent_fund_flow)
    TextView todayRecentFundFlow;
    @BindView(R.id.today_total_expend)
    TextView todayTotalExpend;
    @BindView(R.id.today_total_income)
    TextView todayTotalIncome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fund_flow, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
       loadSplashData(getActivity(), DateUtils.formatNoYear(new Date()));

    }

    private void loadSplashData(final Context context, final String date){
        final List<FundFlow> fundFlows = new ArrayList<>();
        Observable.create(new ObservableOnSubscribe<List<FundFlow>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FundFlow>> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                SQLiteDatabase database = dataBaseHelper.openSqlDataBase();
                String sql = "select * from account_fund_flow where riqi = ? order by time desc";
                Cursor cursor = database.rawQuery(sql,new String[]{date});
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    Integer pic = cursor.getInt(cursor.getColumnIndex("pic"));
                    String date = cursor.getString(cursor.getColumnIndex("riqi"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    String memo = cursor.getString(cursor.getColumnIndex("memo"));
                    String payType = cursor.getString(cursor.getColumnIndex("pay_type"));
                    Integer type = cursor.getInt(cursor.getColumnIndex("type"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    FundFlow fundFlow = new FundFlow();
                    fundFlow.setDate(date);
                    fundFlow.setName(name);
                    fundFlow.setPayType(payType);
                    fundFlow.setPrice(price);
                    fundFlow.setType(type);
                    fundFlow.setTime(time);
                    fundFlow.setMemo(memo);
                    fundFlows.add(fundFlow);
                }
                e.onNext(fundFlows);
                e.onComplete();
                cursor.close();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FundFlow>>() {
                    @Override
                    public void accept(List<FundFlow> s) throws Exception {
                        if(fundFlows.size() > 0){
                            FundFlow fundFlow = fundFlows.get(0);
                            todayRecentFundFlow.setText("最近一笔 " + fundFlow.getName() + " " + CurrencyUtils.formatAmount(fundFlow.getPrice()));
                        }
                        Double totalExpend = 0.00;
                        Double totalIncome = 0.00;
                        for (int i = 0; i < fundFlows.size(); i++) {
                            FundFlow fundFlow = fundFlows.get(i);
                            if(fundFlow.getType() == 1){
                                totalExpend += Double.parseDouble(fundFlow.getPrice());
                                todayTotalExpend.setText(CurrencyUtils.formatAmount(totalExpend.toString()));
                            }else if(fundFlow.getType() == 0){
                                //总收入
                                totalIncome += Double.parseDouble(fundFlow.getPrice());
                                todayTotalIncome.setText(totalIncome.toString());
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.today_fund_flow, R.id.week_fund_flow, R.id.month_fund_flow, R.id.home_button_keep_an_account})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.today_fund_flow:
                startActivity(new Intent(getActivity(), FundFlowDetailActivity.class));
                break;
            case R.id.week_fund_flow:
                break;
            case R.id.month_fund_flow:
                break;
            case R.id.home_button_keep_an_account:
                startActivity(new Intent(getActivity(), KeepAnAccountActivity.class));
                break;
                default:
                    break;
        }
    }
}
