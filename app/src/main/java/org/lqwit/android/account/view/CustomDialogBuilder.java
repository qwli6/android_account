package org.lqwit.android.account.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialogBuilder extends Dialog {
    private static final String TAG = "CustomDialogBuilder";
    private Context context;
    private View itemView;

    private OnClickListener clickListener;


    public CustomDialogBuilder setOnClickListener(OnClickListener listener) {
        this.clickListener = listener;
        return this;
    }

    public CustomDialogBuilder(Context context) {
        this(context, 0);
    }

    public CustomDialogBuilder(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(itemView);

    }

    public CustomDialogBuilder setText(int viewId, String textStr){
         TextView textView = getViewById(viewId);
         textView.setText(textStr);
        return this;
    }

    public CustomDialogBuilder setText(int viewId, String text, final OnClickListener listener){
        TextView textView = getViewById(viewId);
        textView.setText(text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
        return this;
    }

    public CustomDialogBuilder setText(int viewId, int textStrId){
        TextView textView = getViewById(viewId);
        textView.setText(textStrId);
        return this;
    }

    public CustomDialogBuilder setText(int viewId, int textStrId, final OnClickListener listener){
        TextView textView = getViewById(viewId);
        textView.setText(textStrId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
        return this;
    }


    public CustomDialogBuilder setTextColor(int viewId, int colorId){
        TextView textView = getViewById(viewId);
        textView.setTextColor(colorId);
        return this;
    }

    public CustomDialogBuilder setIcon(int viewId, int iconRes){
        ImageView imageView = getViewById(viewId);
        imageView.setBackgroundResource(iconRes);
        return this;
    }

    public CustomDialogBuilder setBackgrondColor(int viewId, int color){
        View view = getViewById(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public CustomDialogBuilder setTextSize(int viewId, float sizeId){
        TextView textView = getViewById(viewId);
        textView.setTextSize(sizeId);
        return this;
    }


    public CustomDialogBuilder setVisiable(int viewId, boolean visiable){
        View view = getViewById(viewId);
        if(visiable){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public CustomDialogBuilder setGone(int viewId, boolean gone){
        View view = getViewById(viewId);
        if(gone){
            view.setVisibility(View.GONE);
        }
        return this;
    }


    public CustomDialogBuilder setView(int viewResId) {
        itemView = LayoutInflater.from(context).inflate(viewResId, null);
        return this;
    }

    public CustomDialogBuilder setView(View view){
        this.itemView = view;
        return this;
    }


    public interface OnClickListener{
        void onClick();
    }

    private <T extends View> T getViewById(int viewId) {
        return (T) itemView.findViewById(viewId);
    }
}
