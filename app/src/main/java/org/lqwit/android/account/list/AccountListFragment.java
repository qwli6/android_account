package org.lqwit.android.account.list;

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
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.account.detail.AccountDetailActivity;
import org.lqwit.android.data.entity.Account;
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

    private boolean isShowBalance = false;

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
                        Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                        Account account = new Account();
                           account.setAccountType(accountType);
                           account.setAccountName(name);
                           account.setTotalAmount(amount);
                           account.setAccountDesc(desc);
                           account.setAccountId(id);
                           accounts.add(account);
                    }
                    e.onNext(accounts);
                    e.onComplete();
                    cursor.close();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Account>>() {
            @Override
            public void accept(final List<Account> accounts) throws Exception {
                accountListView.setAdapter(new AccountListAdapter(accounts));
                accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position > 0) {
                            Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
                            intent.putExtra(AccountDetailActivity.ACCOUNT_ID, accounts.get(position).getAccountId());
                            intent.putExtra(AccountDetailActivity.ACCOUNT_AMOUNT, accounts.get(position).getTotalAmount());
                            startActivity(intent);
                        }
                    }
                });

                accountListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position > 0) {
                            Account account = accounts.get(position);
                            DeleteDialog deleteDialog = new DeleteDialog();
                            deleteDialog.show(getChildFragmentManager(), "account_fragment_delete");
                            return true;
                        }
                        return false;
                    }
                });


                View view = ViewUtils.inflate(R.layout.layout_account_header);
                final TextView avaliableBalance = view.findViewById(R.id.account_avaliable_balance);
                avaliableBalance.setTypeface(Typeface.createFromAsset(getActivity()
                        .getAssets(), "fonts/font_hwzs.ttf"));
                view.findViewById(R.id.check_account_balance).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isShowBalance){
                            isShowBalance = true;
                            avaliableBalance.setText("170,34.12");
                        }else{
                            isShowBalance = false;
                            //不忍直视
                            //财不露白  家财万贯 壕破天际 小康水平 温饱线上 日进斗金 多的可怕 身家过亿 富可敌国
                            avaliableBalance.setText("*壕破天际*");
                        }
                    }
                });
                accountListView.addHeaderView(view);
            }
        });
    }

    private void deleteAccount(final Integer accountId) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                String sql = "delete from user_account where _id = ?";
                sqLiteDatabase.execSQL(sql, new Integer[]{accountId});
                e.onNext("success");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ViewUtils.showCustomToast("删除成功");
            }
        });
    }

    public static AccountListFragment newInstance() {
        return new AccountListFragment();
    }

    @Override
    public void onDelete(Integer accountId) {
//        deleteAccount(accountId);
        Toast.makeText(getActivity(), "accountId = " + accountId, Toast.LENGTH_SHORT).show();
    }


   class AccountListAdapter extends BaseAdapter{

       List<Account> accounts;

       public AccountListAdapter(List<Account> accounts) {
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

           Account account = accounts.get(position);
           View view = LayoutInflater.from(parent.getContext()).
                   inflate(R.layout.layout_account_item, null);
           TextView accountName = view.findViewById(R.id.account_name);
           TextView accountDesc = view.findViewById(R.id.account_desc);
           TextView accountAmount = view.findViewById(R.id.account_amount);
           accountName.setText(account.getAccountName());
           accountDesc.setText(account.getAccountDesc());
           accountAmount.setText(account.getTotalAmount());
           return view;
       }
   }
}
