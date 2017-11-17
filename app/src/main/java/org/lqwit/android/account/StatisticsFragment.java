package org.lqwit.android.account;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseFragment;

import butterknife.ButterKnife;

public class StatisticsFragment extends AppBaseFragment {


    public static StatisticsFragment newInstance() {

        Bundle args = new Bundle();
        StatisticsFragment fragment = new StatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View createView() {
        View root =  View.inflate(getActivity(), R.layout.fragment_statistics, null);
        ButterKnife.bind(this, root);
        return root;
    }
}
