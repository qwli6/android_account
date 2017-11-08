package org.lqwit.android.account.other.setbudget;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.config.Config;
import org.lqwit.android.account.utils.CurrencyUtils;
import org.lqwit.android.account.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetBudgetFragment extends Fragment {

    private StringBuilder content;

    @BindView(R.id.show_budget_money)
    EditText showBudgetMoney;
    @BindView(R.id.number_key_budget_one)
    TextView numberKeyBudgetOne;
    @BindView(R.id.number_key_budget_two)
    TextView numberKeyBudgetTwo;
    @BindView(R.id.number_key_budget_three)
    TextView numberKeyBudgetThree;
    @BindView(R.id.number_key_budget_four)
    TextView numberKeyBudgetFour;
    @BindView(R.id.number_key_budget_five)
    TextView numberKeyBudgetFive;
    @BindView(R.id.number_key_budget_six)
    TextView numberKeyBudgetSix;
    @BindView(R.id.number_key_budget_seven)
    TextView numberKeyBudgetSeven;
    @BindView(R.id.number_key_budget_eight)
    TextView numberKeyBudgetEight;
    @BindView(R.id.number_key_budget_nine)
    TextView numberKeyBudgetNine;
    @BindView(R.id.number_key_budget_clear)
    TextView numberKeyBudgetClear;
    @BindView(R.id.number_key_budget_zero)
    TextView numberKeyBudgetZero;
    @BindView(R.id.number_key_budget_point)
    TextView numberKeyBudgetPoint;
    @BindView(R.id.number_key_budget_delete)
    TextView numberKeyBudgetDelete;
    @BindView(R.id.number_key_budget_confirm)
    TextView numberKeyBudgetConfirm;


    public static SetBudgetFragment newInstance() {
        return new SetBudgetFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_set_budget, container, false);
        ButterKnife.bind(this, root);
        content = new StringBuilder();
        return root;
    }


    @OnClick({R.id.number_key_budget_one, R.id.number_key_budget_two,
            R.id.number_key_budget_three, R.id.number_key_budget_four, R.id.number_key_budget_five,
            R.id.number_key_budget_six, R.id.number_key_budget_seven, R.id.number_key_budget_eight,
            R.id.number_key_budget_nine, R.id.number_key_budget_clear, R.id.number_key_budget_zero,
            R.id.number_key_budget_point, R.id.number_key_budget_delete, R.id.number_key_budget_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.number_key_budget_one:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetOne.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_two:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetTwo.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_three:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetThree.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_four:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetFour.getText().toString(), showBudgetMoney);

                break;
            case R.id.number_key_budget_five:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetFive.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_six:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetSix.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_seven:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetSeven.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_eight:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetEight.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_nine:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetNine.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_clear:
                showBudgetMoney.setText("0");
                content.delete(0, content.length());
                break;
            case R.id.number_key_budget_zero:
                CurrencyUtils.checkInputMoney(content, numberKeyBudgetZero.getText().toString(), showBudgetMoney);
                break;
            case R.id.number_key_budget_point:
                String result_point = showBudgetMoney.getText().toString();
                if ("0".equals(result_point)){
                    if(content.toString().equals("0")) {
                        content.deleteCharAt(0);
                    }
                    content.append("0.");
                    showBudgetMoney.setText(content);
                    return;
                }
                if(result_point.contains(".")){
                    return;
                }else{
                    content.append(".");
                }
                showBudgetMoney.setText(content);
                break;
            case R.id.number_key_budget_delete:
                String result_delete = showBudgetMoney.getText().toString();
                if("0".equals(result_delete)){
                    return;
                }
                if (result_delete.length() == 1){
                    content.delete(0,content.length());
                    showBudgetMoney.setText("0");
                    return;
                } else{
                    showBudgetMoney.setText(content.deleteCharAt(result_delete.length() - 1));
                }

                break;
            case R.id.number_key_budget_confirm:
                String budget = showBudgetMoney.getText().toString();
                if(budget.equals(getString(R.string.num_zero)) || budget.equals("0.0") || budget.equals("0.00")){
                    ViewUtils.showToastSafe(R.string.money_not_blank);
                }
                Intent intent = new Intent();
                intent.putExtra(Config.BUDGET, budget);
//                setResult(RESULT_OK, intent);
//                finish();
//                Toast.makeText(this, "save to database", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
