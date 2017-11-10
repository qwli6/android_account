package org.lqwit.android.account.list;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.lqwit.android.R;
import org.lqwit.android.account.detail.AccountDetailActivity;
import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.source.local.DataBaseHelper;
import org.lqwit.android.global.utils.CurrencyUtils;
import org.lqwit.android.global.utils.ViewUtils;
import org.lqwit.android.global.view.DeleteDialog;

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

public class AccountListFragment extends Fragment implements DeleteDialog.DeleteListener{

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
        Observable.create(new ObservableOnSubscribe<List<AccountEntry>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntry>> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                String sql = "select * from user_account";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                if(cursor != null){
                    List<AccountEntry> accounts = new ArrayList<>();
                    while (cursor.moveToNext()){
                           String name = cursor.getString(cursor.getColumnIndex("name"));
                           String desc = cursor.getString(cursor.getColumnIndex("desc"));
                           String amount = CurrencyUtils.formatAmount(cursor.getString(cursor.getColumnIndex("amount")));
                           String picName = cursor.getString(cursor.getColumnIndex("pic_name"));
                        Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                        AccountEntry account = new AccountEntry();
                           account.setPicName(picName);
                           account.setName(name);
                           account.setAmount(amount);
                           account.setDesc(desc);
                           account.setId(id);
                           accounts.add(account);
                    }
                    e.onNext(accounts);
                    e.onComplete();
                    cursor.close();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<AccountEntry>>() {
            @Override
            public void accept(final List<AccountEntry> accounts) throws Exception {
                accountListView.setAdapter(new AccountListAdapter(accounts));
                accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
                        intent.putExtra(AccountDetailActivity.ACCOUNT_ID, accounts.get(position).getId());
                        intent.putExtra(AccountDetailActivity.ACCOUNT_AMOUNT, accounts.get(position).getAmount());
                        startActivity(intent);
                    }
                });

                accountListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position > 0) {
                            AccountEntry account = accounts.get(position);
                            DeleteDialog deleteDialog = new DeleteDialog();
                            deleteDialog.show(getChildFragmentManager(), "account_fragment_delete");
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }


    public static AccountListFragment newInstance() {
        return new AccountListFragment();
    }

    @Override
    public void onDelete(Integer accountId) {

    }


   class AccountListAdapter extends BaseAdapter{

       List<AccountEntry> accounts;

       public AccountListAdapter(List<AccountEntry> accounts) {
           this.accounts = accounts;
       }

       @Override
       public int getCount() {
           return accounts.size();
       }

       @Override
       public Object getItem(int position) {
           return accounts.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {

           AccountEntry account = accounts.get(position);
           View view = LayoutInflater.from(parent.getContext()).
                   inflate(R.layout.layout_account_item, null);
           TextView accountName = view.findViewById(R.id.account_name);
           TextView accountDesc = view.findViewById(R.id.account_desc);
           TextView accountAmount = view.findViewById(R.id.account_amount);
           ImageView accountImg = view.findViewById(R.id.account_image_title);
           accountName.setText(account.getName());
           accountDesc.setText(account.getDesc());
           accountAmount.setText(account.getAmount());
           accountImg.setImageBitmap(ViewUtils.decodeBitmap(account.getPicName()));
           return view;
       }
   }
}
