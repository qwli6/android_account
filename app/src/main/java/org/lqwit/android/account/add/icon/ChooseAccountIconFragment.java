package org.lqwit.android.account.add.icon;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.lqwit.android.R;
import org.lqwit.android.global.base.AppBaseFragment;
import org.lqwit.android.global.utils.ViewUtils;
import org.lqwit.android.type.listenter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseAccountIconFragment extends AppBaseFragment {

    private static final String TAG = "ChooseAccountIconFragme";

    private List<String> iconList;

    @BindView(R.id.choose_account_icon_recycler)
    RecyclerView chooseAccountIcon;

    public static ChooseAccountIconFragment newInstance() {
        return new ChooseAccountIconFragment();
    }

    @Override
    public View createView() {
        View root = View.inflate(mActivity, R.layout.fragment_choose_account_icon, null);
        ButterKnife.bind(this, root);
        initData();
        initListener();
        return root;
    }



    @Override
    public void initListener() {
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 4);
        chooseAccountIcon.setLayoutManager(layoutManager);
        IconAdapter adapter = new IconAdapter(iconList);
        chooseAccountIcon.setAdapter(adapter);
        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onTypeClick(View view, int postion) {
                Intent intent = new Intent();
                intent.putExtra("icon_name", iconList.get(postion));
                (mActivity).setResult(Activity.RESULT_OK,intent);
                mActivity.finish();
            }
        });

    }

    @Override
    public void initData() {
        iconList = new ArrayList<>();
        iconList.add("liu_shui_anjiehuankuan");
        iconList.add("xiang_mu_baobaosheying");
        iconList.add("xiang_mu_caifenglvyou");
        iconList.add("xiang_mu_chucai");
        iconList.add("xiang_mu_cuxiaohuodong");
        iconList.add("xiang_mu_guonian");
        iconList.add("xiang_mu_guonianmaipiao");
        iconList.add("xiang_mu_hongbao");
        iconList.add("xiang_mu_huijiaguonian");
        iconList.add("xiang_mu_hunlihuodong");
        iconList.add("xiang_mu_hunliwupincaigou");
        iconList.add("xiang_mu_jiajucaigou");
        iconList.add("xiang_mu_jichuzhuangxiu");
        iconList.add("xiang_mu_jiejiari");
        iconList.add("xiang_mu_kehuweihu");
        iconList.add("xiang_mu_lvyou");
        iconList.add("xiang_mu_meirongshipin");
        iconList.add("xiang_mu_miyue");
        iconList.add("xiang_mu_nianjian");
        iconList.add("xiang_mu_qinzihuodong");
        iconList.add("xiang_mu_rengong");
        iconList.add("xiang_mu_wenjianjia");
        iconList.add("xiang_mu_youhao");
        iconList.add("xiang_mu_zhuangxiu");
        iconList.add("zhang_hu_fanka");
        iconList.add("zhang_hu_fuzhai_1");
        iconList.add("zhang_hu_fuzhai_2");
        iconList.add("zhang_hu_gongjiaoka");
        iconList.add("zhang_hu_gongjijin");
        iconList.add("zhang_hu_gongsibaoxiao");
        iconList.add("zhang_hu_gupiaozhanghu");
        iconList.add("zhang_hu_jijinzhanghu");
        iconList.add("zhang_hu_jinrong");
        iconList.add("zhang_hu_touzi_bingtu");
        iconList.add("zhang_hu_touzi_diannaoquxian");
        iconList.add("zhang_hu_touzi_zhuzhuangtu");
        iconList.add("zhang_hu_xianjin_2");
        iconList.add("zhang_hu_xianjin_3");
        iconList.add("zhang_hu_xianjin");
        iconList.add("zhang_hu_xinyongka_1");
        iconList.add("zhang_hu_xinyongka_3");
        iconList.add("zhang_hu_xinyongka");
        iconList.add("zhang_hu_xuni_1");
        iconList.add("zhang_hu_xuni_2");
        iconList.add("zhang_hu_xuni");
        iconList.add("zhang_hu_yibaoka");
        iconList.add("zhang_hu_yingfukuanxiang");
        iconList.add("zhang_hu_yinhangka");
        iconList.add("zhang_hu_zhaiquan_1");
        iconList.add("zhang_hu_zhaiquan_2");
        iconList.add("zhang_hu_zhaiquan_3");
    }


    class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder>{
        OnItemClickListener itemClickListener;
        List<String> iconList;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public IconAdapter(List<String> iconList) {
            this.iconList = iconList;
        }

        @Override
        public IconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new IconViewHolder(View.inflate(parent.getContext(),
                    R.layout.layout_icon_item, null));
        }

        @Override
        public void onBindViewHolder(final IconViewHolder holder, final int position) {
            if(itemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onTypeClick(holder.itemView, position);
                    }
                });
            }
            String name = iconList.get(position);
            Log.d(TAG, "iconName:" + iconList.get(position));
            holder.iconImg.setImageBitmap(ViewUtils.decodeBitmap(name));
        }

        @Override
        public int getItemCount() {
            return iconList.size();
        }

        class IconViewHolder extends RecyclerView.ViewHolder{

            ImageView iconImg;
            public IconViewHolder(View itemView) {
                super(itemView);
                iconImg = itemView.findViewById(R.id.account_icon_img);
            }
        }
    }
}
