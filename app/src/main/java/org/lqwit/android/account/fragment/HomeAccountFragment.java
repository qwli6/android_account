package org.lqwit.android.account.fragment;

import android.content.Intent;
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
import org.lqwit.android.account.activity.AccountDetailActivity;
import org.lqwit.android.account.data.source.local.DataBaseHelper;
import org.lqwit.android.account.entity.Account;
import org.lqwit.android.account.listenter.OnItemClickListener;
import org.lqwit.android.account.listenter.OnItemLongClikcListener;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.ViewUtils;
import org.lqwit.android.account.view.CustomDialogBuilder;

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
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                accountRecyclerView.setLayoutManager(manager);
                final AccountAdapter adapter = new AccountAdapter(accounts);
                accountRecyclerView.setAdapter(adapter);

                adapter.setItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onTypeClick(View view, int postion) {
                        Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
                        intent.putExtra(AccountDetailActivity.ACCOUNT_ID, accounts.get(postion).getAccountId());
                        intent.putExtra(AccountDetailActivity.ACCOUNT_AMOUNT, accounts.get(postion).getTotalAmount());
                        startActivity(intent);
                    }
                });

                adapter.setItemLongClikcListener(new OnItemLongClikcListener() {
                    @Override
                    public void itemLongClick(View view, final int position) {
                        final CustomDialogBuilder customDialogBuilder = new CustomDialogBuilder(getActivity());
                        customDialogBuilder.setView(R.layout.layout_custom_dialog).setText(R.id.dialog_message,
                                "您确定要删除该账户吗？删除该账户后，该账户的数据将不会保存，请慎重执行该操作!")
                                .setText(R.id.dialog_confirm, "确定", new CustomDialogBuilder.OnClickListener() {
                            @Override
                            public void onClick() {
                                ViewUtils.showCustomToast("正在删除中，请稍后...");
                                customDialogBuilder.dismiss();
                                Account account = accounts.get(position);
                                deleteAccount(account.getAccountId());
                                accounts.remove(position);
                                adapter.notifyItemRemoved(position);
                            }
                        }).setText(R.id.dialog_cancel, "取消", new CustomDialogBuilder.OnClickListener() {
                            @Override
                            public void onClick() {
                                ViewUtils.showCustomToast("正在取消中，请稍后...");
                                customDialogBuilder.dismiss();
                            }
                        }).show();
                    }
                });
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

    public static HomeAccountFragment newInstance() {
        return new HomeAccountFragment();
    }

    class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<Account> accounts;
        private OnItemClickListener itemClickListener;
        private OnItemLongClikcListener itemLongClikcListener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setItemLongClikcListener(OnItemLongClikcListener itemLongClikcListener) {
            this.itemLongClikcListener = itemLongClikcListener;
        }

        public AccountAdapter(List<Account> accounts) {
            this.accounts = accounts;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_item, null);
            return new AccountViewHolder(view);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            Account account = accounts.get(position);
            if (holder instanceof AccountViewHolder) {
                ((AccountViewHolder) holder).bindData(account, position);
                ((AccountViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(itemClickListener != null){
                            itemClickListener.onTypeClick(v, position);
                        }
                    }
                });

                ((AccountViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(itemLongClikcListener != null){
                            itemLongClikcListener.itemLongClick(v, position);
                        }
                        return true;
                    }
                });
            }
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

        class NormalViewHolder extends RecyclerView.ViewHolder{

            public NormalViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
