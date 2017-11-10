package org.lqwit.android.global.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import org.lqwit.android.R;
import org.lqwit.android.global.utils.DateUtils;
import org.lqwit.android.global.utils.ViewUtils;

import java.util.Date;

/**
 * Created by liqiwen on 2017/11/10.
 */

public class DateDialog extends DialogFragment {

    private DatePicker datePicker;

    public interface OnChooseDateCompleteListener{
        void chooseComplete(int year, int month, int day);
        void chooseCancel(String result);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final OnChooseDateCompleteListener listener =
                (OnChooseDateCompleteListener) getParentFragment();
        View view = View.inflate(ViewUtils.getContext(), R.layout.layout_date_picker, null);
        datePicker = view.findViewById(R.id.date_picker);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();

                listener.chooseComplete(year, month, day);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String noYear = DateUtils.formatNoYear(new Date());
                String result = DateUtils.format2(noYear);
                listener.chooseCancel(result);
            }
        });
        return builder.create();

    }
}
