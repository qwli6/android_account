package org.lqwit.android.type.add;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.global.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddEditTypeFragment extends Fragment implements AddEditTypeContract.View {

    @BindView(R.id.type_name)
    EditText typeName;
    @BindView(R.id.update_type_icon)
    ImageView updateTypeIcon;

    private AddEditTypeContract.Presenter mPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_type, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    public static AddEditTypeFragment newInstance() {
        return new AddEditTypeFragment();
    }

    @Override
    public void setPresenter(@NonNull AddEditTypeContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @OnClick({R.id.update_type_icon_layout, R.id.save_type})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.update_type_icon_layout:
                Snackbar.make(view, "暂不支持自定义类型图标", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.save_type:
                String name = typeName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ViewUtils.showCenterToast("类型名称不能为空", Toast.LENGTH_SHORT);
                    return;
                }

                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_type_done);
        fab.setImageResource(R.drawable.ic_done);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveType();
            }
        });
    }

    @Override
    public void showEmptyTypeError() {
        Snackbar.make(typeName, "类型名称不能为空", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setName(@NonNull String name) {
        ActivityUtils.checkNotNull(name);
        typeName.setText(name);
    }

    @Override
    public void setBgResource(@NonNull String picName) {
        ActivityUtils.checkNotNull(picName);
        Bitmap bitmap = ViewUtils.decodeBitmap(picName);
        updateTypeIcon.setImageBitmap(bitmap);
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
