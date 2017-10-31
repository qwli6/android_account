package org.lqwit.android.account.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.activity.AccountDetailActivity;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.Account;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.ViewUtils;

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

    @BindView(R.id.account_list_view)
    ListView accountListView;


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
                  accountListView.setAdapter(new AccountAdapter(accounts));
                  accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
                          startActivity(intent);
                      }
                  });
            }
        });
    }


    public class AccountAdapter extends BaseAdapter {

        List<Account> accountList;

        public AccountAdapter(List<Account> accountList) {
            this.accountList = accountList;
        }

        @Override
        public int getCount() {
            return accountList.size();
        }

        @Override
        public Object getItem(int position) {
            return accountList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Account account = accountList.get(position);
            View view = null;
            ViewHolder viewHolder;
            if(convertView == null){
                view = ViewUtils.inflate(R.layout.layout_account_item);
                viewHolder = new ViewHolder(view);
                viewHolder.accountImageTitle.setText(account.getAccountName().substring(0,1));
                viewHolder.accountImageTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/font_fzsongjianti.ttf"));
                viewHolder.accountName.setText(account.getAccountName());
                viewHolder.accountName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/font_fzsongjianti.ttf"));
                viewHolder.accountDesc.setText(account.getAccountDesc());
                viewHolder.accountDesc.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/font_fzsongjianti.ttf"));
                viewHolder.accountAmount.setText(account.getTotalAmount());
                viewHolder.accountAmount.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/font_fzsongjianti.ttf"));
                view.setTag(viewHolder);
            }else{
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            int cus = position % 6;
            switch (cus){
                case 0:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_one);
                    break;
                case 1:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_two);
                    break;
                case 2:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_three);
                    break;
                case 3:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_four);
                    break;
                case 4:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_five);
                    break;
                case 5:
                    viewHolder.accountItemLayout.setBackgroundResource(R.drawable.account_rect_border_six);
                    break;
                default:
                    break;
            }
            return view;
        }

         class ViewHolder {
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

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
