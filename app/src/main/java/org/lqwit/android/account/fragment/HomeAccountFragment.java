package org.lqwit.android.account.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.Account;
import org.lqwit.android.account.utils.CurrencyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: liqiwen
 * Date: 2017/9/5
 * Time: 12:41
 * Email: selfassu@gmail.com
 * Desc:
 */

public class HomeAccountFragment extends Fragment {

    @BindView(R.id.account_recycler_view)
    RecyclerView accountRecyclerView;
    @BindView(R.id.account_total_amount)
    TextView accountTotalAmount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_account, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    public void initData() {
        Observable.create(new ObservableOnSubscribe<List<Account>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Account>> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                String sql = "select * from user_account";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                if(cursor != null){
                    List<Account> accounts = new ArrayList<>();
                    while (cursor.moveToNext()){
                           String name = cursor.getString(cursor.getColumnIndex("name"));
                           String desc = cursor.getString(cursor.getColumnIndex("desc"));
                           String amount = CurrencyUtils.formatAmount(cursor.getString(cursor.getColumnIndex("amount")));
                           Integer accountType = cursor.getInt(cursor.getColumnIndex("account_type"));
                           Account account = new Account();
                           account.setAccountType(accountType);
                           account.setAccountName(name);
                           account.setTotalAmount(amount);
                           account.setAccountDesc(desc);
                           accounts.add(account);
                    }
                    e.onNext(accounts);
                    e.onComplete();
                    cursor.close();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Account>>() {
            @Override
            public void accept(List<Account> accounts) throws Exception {
                Double totalAmount = 0.00;
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                accountRecyclerView.setLayoutManager(manager);
                accountRecyclerView.setAdapter(new AccountAdapter(accounts));
                for (int i = 0; i < accounts.size(); i++) {
                    totalAmount += Double.parseDouble(accounts.get(i).getTotalAmount());
                }
                accountTotalAmount.setText(CurrencyUtils.formatAmount(totalAmount.toString()));
            }
        });
    }

    class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{


        List<Account> accounts;

        public AccountAdapter(List<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_item, null);
            return new AccountViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AccountViewHolder holder, int position) {
            Account account = accounts.get(position);
            holder.bindData(account, position);
        }

        @Override
        public int getItemCount() {
            return accounts.size();
        }

        class AccountViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.account_item_layout)
            RelativeLayout accountItemLayout;
            @BindView(R.id.account_amount)
            TextView accountAmount;
            @BindView(R.id.account_desc)
            TextView accountDesc;
            @BindView(R.id.account_name)
            TextView accountName;
            @BindView(R.id.account_image_title)
            TextView accountImageTitle;
            public AccountViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bindData(Account account, int position) {
                accountImageTitle.setText(account.getAccountName().substring(0,1));
                accountAmount.setText(account.getTotalAmount());
                if(account.getAccountType() == 1) {
                    accountName.setText(account.getAccountName() + "(储蓄)");
                }
                if(account.getAccountType() == 0){
                    accountName.setText(account.getAccountName() + "(负债)");
                }
                accountDesc.setText(account.getAccountDesc());
                int curPos = position % 6;
                switch (curPos){
                    case 0:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_one);
                        break;
                    case 1:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_two);
                        break;
                    case 2:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_three);
                        break;
                    case 3:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_four);
                        break;
                    case 4:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_five);
                        break;
                    case 5:
                        accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_six);
                        break;

                     default:
                         break;
                }
            }
        }
    }
}
