package org.lqwit.android.account.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.adapter.TypeAdapter;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.listenter.OnTypeClickListener;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.DateUtils;
import org.lqwit.android.account.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: liqiwen
 * Date: 2017/9/5
 * Time: 20:39
 * Email: selfassu@gmail.com
 * Desc:
 */

public class InComeFragment extends Fragment {
    private static final String TAG = "InComeFragment";
    private static int consume_type = 1; //支出
    private int curPosition = 0; //当前位置

    private List<Type> typeList;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_income, null);

        ButterKnife.bind(this, view);
        content  = new StringBuilder();
        incomeDate.setText(DateUtils.formatNoYear(new Date()));
        initData();
        return view;
    }

    public void initData(){
        typeList = new ArrayList<>();


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
        String sql = "select * from account_type where type = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(0)});
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Integer pic = cursor.getInt(cursor.getColumnIndex("pic"));
            typeList.add(new Type(pic, name));
        }

        GridLayoutManager manager = new GridLayoutManager(getActivity(),5);
        incomeTypeRecycleView.setLayoutManager(manager);
        TypeAdapter adapter = new TypeAdapter(typeList);
        incomeTypeRecycleView.setAdapter(adapter);

        //set default selected account
//        incomePic.setBackgroundResource(typeList.get(0).getPicId());
        incomeTitle.setText(typeList.get(0).getName());


        adapter.setOnTypeClickListener(new OnTypeClickListener() {
            @Override
            public void onTypeClick(View view, int postion) {
                if(postion != typeList.size() - 1) {
                    Type account = typeList.get(postion);
//                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), account.getPicId());
//                    Palette palette = Palette.generate(bitmap);
//                    layoutExpendSetPrice.setBackgroundColor(palette.getVibrantSwatch().getRgb());
//                    incomePic.setBackgroundResource(account.getPicId());
                    incomeTitle.setText(account.getName());
                    curPosition = postion;
                }else{
                    Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                break;
            case R.id.income_date:
                break;
            case R.id.income_memo:
                ViewUtils.showToastSafe(R.string.unsupport_memo);
                break;
            default:
                break;
        }
    }

}
