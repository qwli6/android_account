package org.lqwit.android.account.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.activity.TypeManagerActivity;
import org.lqwit.android.account.adapter.TypeAdapter;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.Account;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.listenter.OnItemClickListener;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.DateUtils;
import org.lqwit.android.account.utils.ViewUtils;

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

/**
 * Author: liqiwen
 * Date: 2017/9/5
 * Time: 20:39
 * Email: selfassu@gmail.com
 * Desc:
 */

public class ExpendFragment extends Fragment {

    private static int consume_type = 1; //支出
    private int curPosition = 0; //当前位置

    private static final String TAG    = "ExpendFragment";
    private StringBuilder content;

    @BindView(R.id.number_key_one)
    TextView numberKeyOne;
    @BindView(R.id.number_key_two)
    TextView numberKeyTwo;
    @BindView(R.id.number_key_three)
    TextView numberKeyThree;
    @BindView(R.id.number_key_four)
    TextView numberKeyFour;
    @BindView(R.id.number_key_five)
    TextView numberKeyFive;
    @BindView(R.id.number_key_six)
    TextView numberKeySix;
    @BindView(R.id.number_key_seven)
    TextView numberKeySeven;
    @BindView(R.id.number_key_eight)
    TextView numberKeyEight;
    @BindView(R.id.number_key_nine)
    TextView numberKeyNine;
    @BindView(R.id.number_key_clear)
    TextView numberKeyClear;
    @BindView(R.id.number_key_zero)
    TextView numberKeyZero;

    @BindView(R.id.keep_an_account_show_money)
    EditText showMoney;

    @BindView(R.id.keep_an_account_expend_type_recycleview)
    RecyclerView expendTypeRecycleView;
    @BindView(R.id.expend_pic)
    ImageView expendPic;
    @BindView(R.id.expend_title)
    TextView expendTitle;
    @BindView(R.id.linear_layout_set_price)
    LinearLayout layoutExpendSetPrice;

    @BindView(R.id.expend_type)
    TextView expendType;
    @BindView(R.id.expend_date)
    TextView expendDate;

    private List<Type> typeList;
    private RecyclerView chooseUserAccountRecycler;
    private List<Account> accounts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_expend, null);
        content  = new StringBuilder();
        ButterKnife.bind(this, view);
        expendDate.setText(DateUtils.formatNoYear(new Date()));
        initData();
        return view;
    }


    /**
     * init expend account data
     */
    public void initData(){
        typeList = new ArrayList<>();
        Observable.create(new ObservableOnSubscribe<List<Account>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Account>> e) throws Exception {
                accounts = new ArrayList<>();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                String sql = "select * from user_account";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()){
                    Integer accountId = cursor.getInt(cursor.getColumnIndex("_id"));
                    String accountName = cursor.getString(cursor.getColumnIndex("name"));
                    String accountPrice = cursor.getString(cursor.getColumnIndex("amount"));
                    String accountDesc = cursor.getString(cursor.getColumnIndex("desc"));
                    Integer accountType = cursor.getInt(cursor.getColumnIndex("account_type"));
                    Account account = new Account();
                    account.setAccountId(accountId);
                    account.setAccountName(accountName);
                    account.setTotalAmount(accountPrice);
                    account.setAccountType(accountType);
                    account.setAccountDesc(accountDesc);
                    accounts.add(account);
                    e.onNext(accounts);
                    e.onComplete();
                }
                cursor.close();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Account>>() {
            @Override
            public void accept(List<Account> accounts) throws Exception {
                Account account = accounts.get(0);
                expendType.setText(account.getAccountName());
            }
        });


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
        String sql = "select * from account_type where type = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(1)});
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String picName = cursor.getString(cursor.getColumnIndex("pic_name"));
            Type type = new Type(picName, name);
            typeList.add(type);
        }

        typeList.add(new Type("icon_zhichu_shouru_type_add", "添加"));
        cursor.close();

        GridLayoutManager manager = new GridLayoutManager(getActivity(),5);
        expendTypeRecycleView.setLayoutManager(manager);
        TypeAdapter adapter = new TypeAdapter(typeList);
        expendTypeRecycleView.setAdapter(adapter);

        //set default selected account
        Type type = typeList.get(0);
        expendTitle.setText(type.getName());
        expendPic.setImageBitmap(ViewUtils.decodeBitmap(type.getPicName()));


        adapter.setOnTypeClickListener(new OnItemClickListener() {
            @Override
            public void onTypeClick(View view, int postion) {
                if(postion != typeList.size() - 1) {
                    Type type = typeList.get(postion);
                    expendPic.setImageBitmap(ViewUtils.decodeBitmap(type.getPicName()));
                    expendTitle.setText(type.getName());
                    curPosition = postion;
                }else{
                    Intent intent = new Intent(getActivity(), TypeManagerActivity.class);
                    intent.putExtra(TypeManagerActivity.TYPE_MANAGER, TypeManagerActivity.TYPE_MANAGER_EXPEND);
                    startActivity(intent);
                }
            }
        });

    }


    @OnClick({R.id.number_key_one, R.id.number_key_two, R.id.number_key_three, R.id.number_key_four,
    R.id.number_key_five, R.id.number_key_six, R.id.number_key_seven, R.id.number_key_eight, R.id.number_key_nine,
    R.id.number_key_clear, R.id.number_key_zero, R.id.number_key_point, R.id.number_key_delete, R.id.number_key_confirm,
    R.id.expend_type, R.id.expend_date, R.id.expend_memo})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.number_key_one:
                CurrencyUtils.checkInputMoney(content, numberKeyOne.getText().toString(), showMoney);
                break;
            case R.id.number_key_two:
                CurrencyUtils.checkInputMoney(content, numberKeyTwo.getText().toString(), showMoney);
                break;
            case R.id.number_key_three:
                CurrencyUtils.checkInputMoney(content, numberKeyThree.getText().toString(), showMoney);
                break;
            case R.id.number_key_four:
                CurrencyUtils.checkInputMoney(content, numberKeyFour.getText().toString(), showMoney);
                break;
            case R.id.number_key_five:
                CurrencyUtils.checkInputMoney(content, numberKeyFive.getText().toString(), showMoney);
                break;
            case R.id.number_key_six:
                CurrencyUtils.checkInputMoney(content, numberKeySix.getText().toString(), showMoney);
                break;
            case R.id.number_key_seven:
                CurrencyUtils.checkInputMoney(content, numberKeySeven.getText().toString(), showMoney);
                break;
            case R.id.number_key_eight:
                CurrencyUtils.checkInputMoney(content, numberKeyEight.getText().toString(), showMoney);
                break;
            case R.id.number_key_nine:
                CurrencyUtils.checkInputMoney(content, numberKeyNine.getText().toString(), showMoney);
                break;
            case R.id.number_key_clear:
                showMoney.setText("0");
                content.delete(0, content.length());
                break;
            case R.id.number_key_zero:
                CurrencyUtils.checkInputMoney(content, numberKeyZero.getText().toString(), showMoney);
                break;
            case R.id.number_key_point:
                String result_point = showMoney.getText().toString();
                if ("0".equals(result_point)){
                    if(content.toString().equals("0")) {
                        content.deleteCharAt(0);
                    }
                    content.append("0.");
                    showMoney.setText(content);
                    return;
                }
                if(result_point.contains(".")){
                    return;
                }else{
                    content.append(".");
                }
                showMoney.setText(content);
                break;
            case R.id.number_key_delete:
                String result_delete = showMoney.getText().toString();
                if("0".equals(result_delete)){
                    return;
                }
                if (result_delete.length() == 1){
                    content.delete(0,content.length());
                    showMoney.setText("0");
                    return;
                } else{
                    showMoney.setText(content.deleteCharAt(result_delete.length() - 1));
                }

                break;
            case R.id.number_key_confirm:
                String expendMoney = showMoney.getText().toString();
                if(expendMoney.equals(getString(R.string.num_zero))
                        || expendMoney.equals("0.")
                        || expendMoney.equals("0.0")
                        || expendMoney.equals("0.00")){
                    ViewUtils.showToastSafe(R.string.money_not_blank);
                }else {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                    Type type = typeList.get(curPosition);
                    String sql = "insert into account_fund_flow(name,pic,pay_type,price,riqi,time,type) values(?,?,?,?,?,?,?)";
                    sqLiteDatabase.execSQL(sql, new Object[]{type.getName(), type.getPicName(), expendType.getText().toString(), expendMoney, DateUtils.formatNoYear(new Date()),DateUtils.format(new Date()), consume_type});
                    Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                    getActivity().finish();

                }
                break;
            case R.id.expend_type:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                View bottomSheetView = View.inflate(getActivity(), R.layout.layout_choose_user_account, null);
                TextView title = bottomSheetView.findViewById(R.id.choose_user_account_title);
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewUtils.showCenterToast("点击title..", Toast.LENGTH_SHORT);
                    }
                });
                chooseUserAccountRecycler = bottomSheetView.findViewById(R.id.choose_user_account_recyclerview);
                chooseUserAccountRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(accounts != null && accounts.size() > 0) {
                    chooseUserAccountRecycler.setAdapter(new ChooseAccountAdapter(accounts));
                }
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ViewUtils.showCenterToast("取消了..", Toast.LENGTH_SHORT);
                    }
                });
                break;
            case R.id.expend_date:
                BottomSheetDialog bottomDateDialog = new BottomSheetDialog(getActivity());
                View dateView = View.inflate(getActivity(), R.layout.layout_choose_date, null);
                bottomDateDialog.setContentView(dateView);
                ViewPager viewPager = view.findViewById(R.id.date_choose_viewpager);


                bottomDateDialog.show();

                break;
            case R.id.expend_memo:
                ViewUtils.showToastSafe(R.string.unsupport_memo);
                break;
                default:
                    break;
        }
    }



    public class ChooseAccountAdapter extends RecyclerView.Adapter<ChooseAccountAdapter.ChooseAccountViewHolder>{

        List<Account> accounts;

        public ChooseAccountAdapter(List<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public ChooseAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChooseAccountViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choose_user_account_item, null));
        }

        @Override
        public void onBindViewHolder(ChooseAccountViewHolder holder, int position) {
            holder.accountTitle.setText(accounts.get(position).getAccountName());
        }

        @Override
        public int getItemCount() {
            return accounts.size();
        }

        class ChooseAccountViewHolder extends RecyclerView.ViewHolder{
            TextView accountTitle;
            public ChooseAccountViewHolder(View itemView) {
                super(itemView);
                accountTitle = itemView.findViewById(R.id.account_title);
            }
        }
    }

}
