package org.lqwit.android.account.list;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lqwit.android.R;
import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.source.local.DataBaseHelper;
import org.lqwit.android.global.base.AppBaseFragment;
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

public class AccountListFragment extends AppBaseFragment implements DeleteDialog.DeleteListener{

    @BindView(R.id.account_list)
    RecyclerView accountList;


    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_home_account, null);
        ButterKnife.bind(this, root);
        initData();
        return root;
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
                           String amount = CurrencyUtils.formatAmount(cursor.getString(cursor.getColumnIndex("amount")));
                            Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                            AccountEntry account = new AccountEntry();
                           account.setName(name);
                           account.setAmount(amount);
                           account.setId(id);
                           accounts.add(account);
                    }
                    e.onNext(accounts);
                    e.onComplete();
                    cursor.close();
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AccountEntry>>() {
            @Override
            public void accept(final List<AccountEntry> accounts) throws Exception {
                for (int i = 0; i < accounts.size(); i++) {
                    System.out.println(accounts.get(i).getName());
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                accountList.setLayoutManager(layoutManager);
                accountList.setAdapter(new AccountListAdapter(accounts));
            }
        });

    }


    public static AccountListFragment newInstance() {
        return new AccountListFragment();
    }

    @Override
    public void onDelete(Integer accountId) {

    }


   class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>{

       List<AccountEntry> accounts;

       public AccountListAdapter(List<AccountEntry> accounts) {
           this.accounts = accounts;
       }

       @Override
       public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View item = ViewUtils.inflate(R.layout.layout_account_item);
           return new AccountViewHolder(item);
       }

       @Override
       public void onBindViewHolder(AccountViewHolder holder, int position) {
            holder.bindData(accounts.get(position));
       }

       @Override
       public int getItemCount() {
           return accounts.size();
       }

       class AccountViewHolder extends RecyclerView.ViewHolder{
           TextView accountName;
           TextView accountAmount;
           public AccountViewHolder(View itemView) {
               super(itemView);
               accountName = itemView.findViewById(R.id.account_name);
               accountAmount = itemView.findViewById(R.id.account_amount);
           }

           public void bindData(AccountEntry accountEntry){
               accountName.setText(accountEntry.getName());
               accountAmount.setText(accountEntry.getAmount());
           }
       }
   }
}
