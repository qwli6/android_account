package org.lqwit.android.account.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lqwit.android.account.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, root);
        // Inflate the layout for this fragment
        return root;
    }

    public static SetFragment newInstance() {
        return new SetFragment();
    }
}
