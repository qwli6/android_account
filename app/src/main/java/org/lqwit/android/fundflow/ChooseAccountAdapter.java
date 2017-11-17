package org.lqwit.android.fundflow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.lqwit.android.R;
import org.lqwit.android.data.entity.AccountEntry;
import org.lqwit.android.type.listenter.OnItemClickListener;

import java.util.List;

public class ChooseAccountAdapter extends RecyclerView.
            Adapter<ChooseAccountAdapter.ChooseAccountViewHolder>{

        OnItemClickListener itemClickListener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        List<AccountEntry> accounts;

        public ChooseAccountAdapter(List<AccountEntry> accounts) {
            this.accounts = accounts;
        }

        @Override
        public ChooseAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChooseAccountViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_choose_user_account_item, null));
        }

        @Override
        public void onBindViewHolder(final ChooseAccountViewHolder holder, int position) {
            final AccountEntry account = accounts.get(position);
            holder.accountTitle.setText(account.getName());
            holder.amount.setText(account.getAmount());
            if(itemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onTypeClick(holder.itemView, holder.getAdapterPosition());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return accounts.size();
        }

        class ChooseAccountViewHolder extends RecyclerView.ViewHolder{
            TextView accountTitle;
            TextView amount;
            CheckBox defaultPayAccount;
            public ChooseAccountViewHolder(View itemView) {
                super(itemView);
                accountTitle = itemView.findViewById(R.id.account_title);
                amount = itemView.findViewById(R.id.amount);
                defaultPayAccount = itemView.findViewById(R.id.checkbox_choose_account);
            }
        }
    }