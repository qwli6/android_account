package org.lqwit.android.account.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: liqiwen
 * Date: 2017/9/5
 * Time: 12:41
 * Email: selfassu@gmail.com
 * Desc:
 */

public class HomeAccountFragment extends Fragment {

    private RecyclerView.LayoutManager manager;

    @BindView(R.id.recyclerview_account)
    RecyclerView recyclerViewAccount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_account, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    public void initData(){
        manager = new LinearLayoutManager(getActivity());
        recyclerViewAccount.setLayoutManager(manager);
        recyclerViewAccount.setAdapter(new AccountAdapter());
    }


    public class AccountAdapter extends  RecyclerView.Adapter<AccountViewHolder>{

        @Override
        public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = ViewUtils.inflate(R.layout.layout_account_item);
            return new AccountViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AccountViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView accountImage;
        public AccountViewHolder(View itemView) {
            super(itemView);
        }
    }
}
