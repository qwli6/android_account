package org.lqwit.android.fundflow.income;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.data.entity.Type;
import org.lqwit.android.data.source.local.DataBaseHelper;
import org.lqwit.android.fundflow.ChooseAccountAdapter;
import org.lqwit.android.fundflow.expend.ExpendFragment;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.CurrencyUtils;
import org.lqwit.android.global.utils.DateUtils;
import org.lqwit.android.global.utils.ViewUtils;
import org.lqwit.android.type.adapter.TypeAdapter;
import org.lqwit.android.type.listenter.OnItemClickListener;
import org.lqwit.android.type.manager.TypeManagerActivity;

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

public class InComeFragment extends AppBaseFragment {
    private static final String TAG = "InComeFragment";
    private static int CONSUME_TYPE = 0; //支出
    private int curPosition = 0; //当前位置


    @BindView(R.id.keep_an_account_income_type_recycleview)
    RecyclerView incomeTypeRecycleView;
    @BindView(R.id.income_pic)
    ImageView  incomePic;
    @BindView(R.id.income_title)
    TextView incomeTitle;
    @BindView(R.id.income_date)
    TextView incomeDate;
    @BindView(R.id.income_type)
    TextView incomeType;

    private StringBuilder content;

    @BindView(R.id.number_key_income_one)
    TextView numberKeyOne;
    @BindView(R.id.number_key_income_two)
    TextView numberKeyTwo;
    @BindView(R.id.number_key_income_three)
    TextView numberKeyThree;
    @BindView(R.id.number_key_income_four)
    TextView numberKeyFour;
    @BindView(R.id.number_key_income_five)
    TextView numberKeyFive;
    @BindView(R.id.number_key_income_six)
    TextView numberKeySix;
    @BindView(R.id.number_key_income_seven)
    TextView numberKeySeven;
    @BindView(R.id.number_key_income_eight)
    TextView numberKeyEight;
    @BindView(R.id.number_key_income_nine)
    TextView numberKeyNine;
    @BindView(R.id.number_key_income_clear)
    TextView numberKeyClear;
    @BindView(R.id.number_key_income_zero)
    TextView numberKeyZero;

    @BindView(R.id.keep_an_account_show_money)
    EditText showMoney;


    private List<Type> typeList;
    private RecyclerView chooseUserAccountRecycler;
    private List<AccountEntry> accounts;
    private SQLiteDatabase dbHelper;
    private TypeAdapter typeAdapter;
    private ChooseAccountAdapter accountAdapter;
    private BottomSheetDialog accountDialog;
    private BottomSheetDialog dateDialog;
    private BottomSheetDialog commentDialog;


    @Override
    public View createView() {

        View view = View.inflate(mActivity, R.layout.fragment_property_income, null);
        ButterKnife.bind(this, view);
        dbHelper = new DataBaseHelper(mActivity).openSqlDataBase();
        content  = new StringBuilder();
        incomeDate.setText(DateUtils.formatNoYear(new Date()));
        return view;
    }

    public void initData(){
        initTypeData();
        initAccountData();
    }


    @OnClick({R.id.number_key_income_one, R.id.number_key_income_two, R.id.number_key_income_three,
            R.id.number_key_income_four,
            R.id.number_key_income_five, R.id.number_key_income_six, R.id.number_key_income_seven,
            R.id.number_key_income_eight, R.id.number_key_income_nine,
            R.id.number_key_income_clear, R.id.number_key_income_zero, R.id.number_key_income_point,
            R.id.number_key_income_delete, R.id.number_key_income_confirm,
            R.id.income_type, R.id.income_date, R.id.income_memo})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.number_key_income_one:
                CurrencyUtils.checkInputMoney(content, numberKeyOne.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_two:
                CurrencyUtils.checkInputMoney(content, numberKeyTwo.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_three:
                CurrencyUtils.checkInputMoney(content, numberKeyThree.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_four:
                CurrencyUtils.checkInputMoney(content, numberKeyFour.getText().toString(), showMoney);

                break;
            case R.id.number_key_income_five:
                CurrencyUtils.checkInputMoney(content, numberKeyFive.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_six:
                CurrencyUtils.checkInputMoney(content, numberKeySix.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_seven:
                CurrencyUtils.checkInputMoney(content, numberKeySeven.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_eight:
                CurrencyUtils.checkInputMoney(content, numberKeyEight.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_nine:
                CurrencyUtils.checkInputMoney(content, numberKeyNine.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_clear:
                showMoney.setText("0");
                content.delete(0, content.length());
                break;
            case R.id.number_key_income_zero:
                CurrencyUtils.checkInputMoney(content, numberKeyZero.getText().toString(), showMoney);
                break;
            case R.id.number_key_income_point:
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
            case R.id.number_key_income_delete:
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
            case R.id.number_key_income_confirm:
                String expendMoney = showMoney.getText().toString();
                if(expendMoney.equals(getString(R.string.num_zero)) || expendMoney.equals("0.") || expendMoney.equals("0.0") || expendMoney.equals("0.00")){
                    ViewUtils.showToastSafe(R.string.money_not_blank);
                } else {
                    //以 . 结尾或者不以 . 结尾
                    if(expendMoney.endsWith(".")){
                        expendMoney = expendMoney.substring(0, expendMoney.length() - 1);
                        Log.d(TAG, "onClickView: " + expendMoney);
                    }else{
                        Log.d(TAG, "onClickView: " + expendMoney);
                    }

                }
                break;
            case R.id.income_type:
                chooseAccount();
                break;
            case R.id.income_date:
                chooseDate();
                break;
            case R.id.income_memo:
                Toast.makeText(mActivity, "xx", Toast.LENGTH_LONG).show();
//                ViewUtils.showToastSafe(R.string.unsupport_comment);
                break;
            default:
                break;
        }
    }

    public static Fragment newInstance() {
        return new InComeFragment();
    }



    private void initAccountData(){
        Observable.create(new ObservableOnSubscribe<List<AccountEntry>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccountEntry>> e) throws Exception {
                accounts = new ArrayList<>();
                String sql = "select * from user_account";
                Cursor cursor = dbHelper.rawQuery(sql, null);
                while (cursor.moveToNext()){
                    Integer accountId = cursor.getInt(
                            cursor.getColumnIndex("_id"));
                    String accountName = cursor.getString(
                            cursor.getColumnIndex("name"));
                    String accountPrice = cursor.getString(
                            cursor.getColumnIndex("amount"));
                    AccountEntry account = new AccountEntry();
                    account.setId(accountId);
                    account.setName(accountName);
                    account.setAmount(accountPrice);
                    accounts.add(account);
                    e.onNext(accounts);
                    e.onComplete();
                }
                cursor.close();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AccountEntry>>() {
                    @Override
                    public void accept(List<AccountEntry> accountEntries) throws Exception {
                        AccountEntry account = accounts.get(0);
                        incomeType.setText(account.getName());
                    }
                });
    }

    /**
     * 添加备注弹出框
     */
    private void completeComment(){
        commentDialog = new BottomSheetDialog(mActivity);
        View memoView = View.inflate(mActivity, R.layout.layout_write_memo, null);
        TextView commentContent = memoView.findViewById(R.id.comment_content);
        TextView commentComplete = memoView.findViewById(R.id.comment_complete);
        commentComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        commentDialog.setContentView(memoView);
        commentDialog.show();
    }


    /**
     * 选择日期弹出框
     */
    private void chooseDate(){
        dateDialog = new BottomSheetDialog(mActivity);
        View dateView = View.inflate(mActivity, R.layout.layout_choose_date, null);
        dateDialog.setContentView(dateView);
        ViewPager viewPager = dateView.findViewById(R.id.date_choose_viewpager);
        dateDialog.show();

    }


    /**
     * 初始化收入消费类型
     */
    private void initTypeData(){
        Observable.create(new ObservableOnSubscribe<List<Type>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Type>> e) throws Exception {
                typeList = new ArrayList<>();
                String sql = "select * from income_expend_type where type = ?";
                Cursor cursor = dbHelper.rawQuery(sql, new String[]{"0"});
                while (cursor.moveToNext()){
                    String name = cursor.getString(
                            cursor.getColumnIndex("income_expend_name"));
                    String imageName = cursor.getString(
                            cursor.getColumnIndex("income_expend_image"));
                    Type type = new Type(imageName, name, CONSUME_TYPE);
                    typeList.add(type);
                }
                Type type = new Type();
                type.setPicName(ExpendFragment.ADD_IMAGE_NAME);
                type.setName(getString(R.string.add));
                type.setConsumeType(CONSUME_TYPE);
                typeList.add(type);
                cursor.close();
                e.onNext(typeList);
                e.onComplete();
            }
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Type>>() {
            @Override
            public void accept(List<Type> types) throws Exception {
                GridLayoutManager manager =
                        new GridLayoutManager(getActivity(),5);
                incomeTypeRecycleView.setLayoutManager(manager);
                typeAdapter = new TypeAdapter(types);
                incomeTypeRecycleView.setAdapter(typeAdapter);

                //初始化默认选中消费类型
                Type type = typeList.get(0);
                incomeTitle.setText(type.getName());
                incomePic.setImageBitmap(ViewUtils.decodeBitmap(type.getPicName()));

                typeAdapter.setOnTypeClickListener(new OnItemClickListener() {
                    @Override
                    public void onTypeClick(View view, int postion) {
                        if (postion != typeList.size() - 1) {
                            Type type = typeList.get(postion);
                            incomePic.setImageBitmap(ViewUtils.decodeBitmap(type.getPicName()));
                            incomeTitle.setText(type.getName());
                            curPosition = postion;
                        } else {
                            Intent intent = new Intent(getActivity(), TypeManagerActivity.class);
                            intent.putExtra(TypeManagerActivity.TYPE_MANAGER,
                                    TypeManagerActivity.TYPE_MANAGER_EXPEND);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }


    /**
     * 弹出底部选择账户框
     */
    private void chooseAccount() {
        accountDialog = new BottomSheetDialog(mActivity);
        View bottomView = View.inflate(mActivity,
                R.layout.layout_choose_user_account, null);
        chooseUserAccountRecycler = bottomView.findViewById(R.id.choose_user_account_recyclerview);
        chooseUserAccountRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        if(accounts != null && accounts.size() > 0) {
            accountAdapter = new ChooseAccountAdapter(accounts);
            chooseUserAccountRecycler.setAdapter(accountAdapter);

            accountAdapter.setItemClickListener(new OnItemClickListener() {
                @Override
                public void onTypeClick(View view, int postion) {
                    accountDialog.dismiss();
                    incomeType.setText(accounts.get(postion).getName());
                }
            });
        }
        accountDialog.setContentView(bottomView);
        accountDialog.show();
    }

}
