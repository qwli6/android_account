package org.lqwit.android.global.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.lqwit.android.R;

/**
 * Created by liqiwen on 2017/10/16.
 */

public class PageTitleLayout extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "PageTitleLayout";
    private Context context;


    //标题背景
    LinearLayout titleBarRootLayout;
    private int titleBackgroundColor;

    //标题文字相关
    private String titleText;
    TextView titleTextView;
    private int titleTextColor;

    //左边返回键
    TextView titleBarLeftBack;
    private boolean backVisiable;
    private int leftImgButtonSrc;



    public PageTitleLayout(Context context) {
        this(context, null);
    }

    public PageTitleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PageTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray =context.obtainStyledAttributes(attrs, R.styleable.PageTitleLayout);
        titleBackgroundColor = typedArray.getColor(R.styleable.PageTitleLayout_title_bar_background, 0xffff0000);
        titleText = typedArray.getString(R.styleable.PageTitleLayout_title_bar_text);
        titleTextColor = typedArray.getColor(R.styleable.PageTitleLayout_title_bar_text_color, 0xffff0000);
        backVisiable = typedArray.getBoolean(R.styleable.PageTitleLayout_title_bar_left_button_visiable, true);
        leftImgButtonSrc = typedArray.getResourceId(R.styleable.PageTitleLayout_title_bar_left_button_background, -1);
        typedArray.recycle();
        initView(context);

    }

    public void setTitleBackgroundColor(int titleBackgroundColor) {
        this.titleBackgroundColor = titleBackgroundColor;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this, true);
        titleBarRootLayout = findViewById(R.id.title_bar_root_layout);
        titleBarRootLayout.setBackgroundColor(titleBackgroundColor);
        titleTextView = findViewById(R.id.title_bar_text);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleBarLeftBack = findViewById(R.id.title_bar_left_back);
        if(backVisiable){
            titleBarLeftBack.setVisibility(View.GONE);
        }else{
            titleBarLeftBack.setVisibility(View.VISIBLE);
        }
        if(titleBarLeftBack.getVisibility() == VISIBLE){
            titleBarLeftBack.setBackgroundResource(leftImgButtonSrc);
            titleBarLeftBack.setOnClickListener(this);
        }
    }


    public void setBackVisiable(boolean backVisiable) {
        this.backVisiable = backVisiable;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_bar_left_back:
                ((Activity) context).finish();
                break;
        }
    }
}
