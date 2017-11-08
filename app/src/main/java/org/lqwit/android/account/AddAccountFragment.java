package org.lqwit.android.account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public class AddAccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_account, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    public static AddAccountFragment newInstance() {
        return new AddAccountFragment();
    }
}
