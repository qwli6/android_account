package org.lqwit.android.account.addtype;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;
import org.lqwit.android.account.utils.ActivityUtils;

import butterknife.ButterKnife;


public class AddEditTypeFragment extends Fragment implements AddEditTypeContract.View {

    private AddEditTypeContract.Persenter mPersenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_type, container, false);
        ButterKnife.bind(this, root);
        // Inflate the layout for this fragment
        return root;
    }

    public static AddEditTypeFragment newInstance() {
        return new AddEditTypeFragment();
    }

    @Override
    public void setPresenter(@NonNull AddEditTypeContract.Persenter presenter) {
        mPersenter = ActivityUtils.checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPersenter.start();
    }
}
