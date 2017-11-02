package org.lqwit.android.account.typemanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.account.adapter.TypeAdapter;
import org.lqwit.android.account.addtype.AddEditTypeActivity;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.listenter.OnItemClickListener;
import org.lqwit.android.account.utils.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Main UI for the type manager screen.
 * p level
 */
public class TypeManagerFragment extends Fragment implements TypeManagerContract.View{

    private TypeAdapter mTypeAdapter;


    @BindView(R.id.type_manager_recycler)
    RecyclerView mTypeManagerRecyclerView;

    private TypeManagerContract.Presenter mPresenter;

    public static TypeManagerFragment newInstance(){
        return new TypeManagerFragment();
    }

    @Override
    public void setPresenter(@NonNull TypeManagerContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_type_manager, container, false);
        ButterKnife.bind(this, root);

        //Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_type);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewType();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }



    @Override
    public void showTypeList(final List<Type> types) {
        mTypeAdapter = new TypeAdapter(types);
        GridLayoutManager manager = new GridLayoutManager(getContext(),5);
        mTypeManagerRecyclerView.setLayoutManager(manager);
        mTypeManagerRecyclerView.setAdapter(mTypeAdapter);

        mTypeAdapter.setOnTypeClickListener(new OnItemClickListener() {
            @Override
            public void onTypeClick(View view, int postion) {
                if(postion == types.size() - 1){
                    Toast.makeText(getActivity(), "添加", Toast.LENGTH_SHORT).show();
                }else{
                    Snackbar snackbar = Snackbar.make(view, "已存在的类型暂时不支持修改操作", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    public void showAddType() {
        Intent intent = new Intent(getActivity(), AddEditTypeActivity.class);
        startActivityForResult(intent, AddEditTypeActivity.REQUEST_ADD_TYPE);
    }

    //是否显示在屏幕上
    @Override
    public boolean isActive() {
        return isAdded();
    }
}
