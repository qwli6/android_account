package org.lqwit.android.account.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.adapter.TypeAdapter;
import org.lqwit.android.account.base.AppBaseActivity;
import org.lqwit.android.account.db.DataBaseHelper;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.listenter.OnItemClickListener;

import java.util.ArrayList;
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

public class TypeManagerActivity extends AppBaseActivity {

    public static final String TYPE_MANAGER = "type_manager";
    public static final int TYPE_MANAGER_INCOME  = 0;
    public static final int TYPE_MANAGER_EXPEND  = 1;
    @BindView(R.id.type_manager_title)
    TextView typeManagerTitle;
    @BindView(R.id.type_manager_recycler)
    RecyclerView typeManageRecyclerView;
    private int typeManager;
    private TypeAdapter typeAdapter;


    @Override
    public void initView() {
        setContentView(R.layout.activity_type_manager);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        typeManager = intent.getIntExtra(TYPE_MANAGER, 0);
        if(typeManager == TypeManagerActivity.TYPE_MANAGER_EXPEND){
            typeManagerTitle.setText(R.string.expend_type_manager);
        }else if(typeManager == TypeManagerActivity.TYPE_MANAGER_INCOME){
            typeManagerTitle.setText(R.string.income_type_manager);
        }
    }

    @Override
    public void initData() {
        getTypeList(typeManageRecyclerView, typeManager);
    }


    public void getTypeList(final RecyclerView recyclerView, final int typeFlag){
        Observable.create(new ObservableOnSubscribe<List<Type>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Type>> e) throws Exception {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(TypeManagerActivity.this);
                SQLiteDatabase database = dataBaseHelper.openSqlDataBase();
                String sql = "select * from account_type where type = ?";
                Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(typeFlag)});
                if(cursor != null){
                    List<Type> typeList = new ArrayList<>();
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String picName = cursor.getString(cursor.getColumnIndex("pic_name"));
                        Type type = new Type(picName, name);
                        typeList.add(type);
                    }
                    e.onNext(typeList);
                    e.onComplete();
                    cursor.close();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Type>>() {
            @Override
            public void accept(final List<Type> types) throws Exception {
                GridLayoutManager manager = new GridLayoutManager(TypeManagerActivity.this,5);
                recyclerView.setLayoutManager(manager);
                typeAdapter = new TypeAdapter(types);
                recyclerView.setAdapter(typeAdapter);

                typeAdapter.setOnTypeClickListener(new OnItemClickListener() {
                    @Override
                    public void onTypeClick(View view, int postion) {
                       if(postion == types.size() - 1){
                           Toast.makeText(TypeManagerActivity.this, "添加", Toast.LENGTH_SHORT).show();
                       }else{
                           Snackbar snackbar = Snackbar.make(view, "已存在的类型暂时不支持修改操作", Snackbar.LENGTH_LONG);
                           snackbar.show();
                       }
                    }
                });
            }
        });
    }


    @OnClick({R.id.type_manager_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.type_manager_back:
                finish();
                break;
        }
    }
}
