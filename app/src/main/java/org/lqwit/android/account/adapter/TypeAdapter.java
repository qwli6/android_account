package org.lqwit.android.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.lqwit.android.account.R;
import org.lqwit.android.account.entity.Type;
import org.lqwit.android.account.listenter.OnTypeClickListener;

import java.util.List;


/**
 * Author: liqiwen
 * Date: 2017/9/22
 * Time: 09:38
 * Email: selfassu@gmail.com
 * Desc:
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private List<Type> typeList;
    private OnTypeClickListener onTypeClickListener;

    public void setOnTypeClickListener(OnTypeClickListener onTypeClickListener) {
        this.onTypeClickListener = onTypeClickListener;
    }

    public TypeAdapter(List<Type> typeList) {
        this.typeList = typeList;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_type_item, null);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TypeViewHolder holder, final int position) {
        Type type = typeList.get(position);
        holder.bindData(type);
        if(onTypeClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTypeClickListener.onTypeClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder{
        ImageView typePic;
        TextView typeText;
        public TypeViewHolder(View itemView) {
            super(itemView);
            typePic = itemView.findViewById(R.id.type_pic);
            typeText = itemView.findViewById(R.id.type_name);
        }


        public void bindData(Type type) {
//            typePic.setBackground(ViewUtils.getDrawable(type.getPicId()));
            typeText.setText(type.getName());
        }
    }
}
