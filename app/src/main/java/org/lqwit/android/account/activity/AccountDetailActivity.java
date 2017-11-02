package org.lqwit.android.account.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountDetailActivity extends AppBaseActivity {
    private boolean isShowSurplus = true;
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_AMOUNT = "account_amount";

    @BindView(R.id.account_surplus)
    TextView accountSurplus;
    @BindView(R.id.account_exptend)
    TextView accountExptend;
    @BindView(R.id.account_income)
    TextView accountIncome;
    @BindView(R.id.account_flow_expandable_listview)
    ExpandableListView accountFlowListView;

    private int accountId;

    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList = new String[]{"十月", "九月", "八月"};
    private List<String> childrenList1 = new ArrayList<>();
    private List<String> childrenList2 = new ArrayList<>();
    private List<String> childrenList3 = new ArrayList<>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_account_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String accountAmount = intent.getStringExtra(ACCOUNT_AMOUNT);
        accountSurplus.setText(accountAmount);
    }

    @Override
    public void initData() {

        childrenList1.add(parentList[0] + "-");
        childrenList1.add(parentList[0] + "-");
        childrenList1.add(parentList[0] + "-");
        childrenList2.add(parentList[1] + "-");
        childrenList2.add(parentList[1] + "-");
        childrenList2.add(parentList[1] + "-");
        childrenList3.add(parentList[2] + "-");
        childrenList3.add(parentList[2] + "-");
        childrenList3.add(parentList[2] + "-");
        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
        dataset.put(parentList[2], childrenList3);

        accountFlowListView.setChildDivider(getResources().getDrawable(R.color.lineDividerColor));
        accountFlowListView.setAdapter(new AccountFlowAdapter(dataset, parentList));

        accountFlowListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ViewUtils.showCenterToast("点击的位置：" + childPosition + "组位置：" + groupPosition, Toast.LENGTH_SHORT);
                return true;
            }
        });

        /**
        accountId = getIntent().getIntExtra(ACCOUNT_ID, 0);
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AccountDetailActivity.this);
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.openSqlDataBase();
                String sql = "select * from account_fund_flow where pay_type = ? order by date desc";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(accountId)});
                while (cursor.moveToNext()){

                }

                cursor.close();


            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {

            }
        });

         **/
    }

    @OnClick(R.id.account_surplus)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.account_surplus:
                break;
        }
    }

    class AccountFlowAdapter extends BaseExpandableListAdapter{
        Map<String, List<String>> dataset;
        String[] parentList;

        public AccountFlowAdapter(Map<String, List<String>> dataset, String[] parentList) {
            this.dataset = dataset;
            this.parentList = parentList;
        }

        /**
         * 总共有几组数据
         * @return
         */
        @Override
        public int getGroupCount() {
            return dataset.size();
        }

        /**
         * 获得每一个组的孩子的数量
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return dataset.get(parentList[groupPosition]).size();
        }

        /**
         * 获取每一个组的对象
         * @param groupPosition
         * @return
         */
        @Override
        public Object getGroup(int groupPosition) {
            return dataset.get(parentList[groupPosition]);
        }

        /**
         * 获取每一个组的孩子对象
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dataset.get(parentList[groupPosition]).get(childPosition);
        }

        /**
         * 获取每一个组的id
         * @param groupPosition
         * @return
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        /**
         * 获取每一组的孩子的id
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         * 是否具有不变的id，无需改动，直接返回false即可
         * @return
         */
        @Override
        public boolean hasStableIds() {
            return false;
        }

        /**
         * 获取每一组的 view
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) AccountDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.parent_item, null);
            }
            convertView.setTag(R.layout.parent_item, groupPosition);
            convertView.setTag(R.layout.child_item, -1);
            TextView text = convertView.findViewById(R.id.parent_title);
            ImageView indicator = convertView.findViewById(R.id.group_indicator);
            text.setText(parentList[groupPosition]);
            if(isExpanded){
                indicator.setBackgroundResource(R.drawable.ic_arrow_up);
            }else{
                indicator.setBackgroundResource(R.drawable.ic_arrow_down);
            }
            return convertView;
        }



        /**
         * 获取每一组的孩子View
         * @param groupPosition
         * @param childPosition
         * @param isLastChild
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)AccountDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_item, null);
            }
            convertView.setTag(R.layout.parent_item, groupPosition);
            convertView.setTag(R.layout.child_item, groupPosition);
            TextView text = convertView.findViewById(R.id.child_title);
            text.setText(dataset.get(parentList[groupPosition]).get(childPosition));
            return convertView;
        }

        /**
         * 子项是否可以选中
         * 如果需要在子项中设置点击事件，需要返回true
         * 这个地方如果不返回为true的话，子类的分割线将设置无效
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
