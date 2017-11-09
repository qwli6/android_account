package org.lqwit.android.other.about;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.lqwit.android.account.R;
import org.lqwit.android.global.utils.ActivityUtils;
import org.lqwit.android.global.utils.VersionUtils;
import org.lqwit.android.global.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutFragment extends Fragment implements AboutContract.View {

    private AboutContract.Presenter mPresenter;

    @BindView(R.id.show_version_name)
    TextView versionName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);;
        ButterKnife.bind(this, root);
        // Inflate the layout for this fragment
        return root;
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void setPresenter(@Nullable AboutContract.Presenter presenter) {
        mPresenter = ActivityUtils.checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        versionName.setText(VersionUtils.getLocalVersionName(getActivity()));
        mPresenter.start();
    }


    @OnClick(R.id.check_version)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.check_version:
                ViewUtils.showCenterToast("检测更新", Toast.LENGTH_SHORT);
                break;
        }
    }
}
