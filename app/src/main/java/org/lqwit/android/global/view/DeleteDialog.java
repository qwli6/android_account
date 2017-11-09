package org.lqwit.android.global.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import org.lqwit.android.R;

public class DeleteDialog extends DialogFragment {



    public interface DeleteListener{
        void onDelete(Integer accountId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        Bundle bundle = getArguments();
//        final int accountId = bundle.getInt("accountId");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定要删除么？");
        builder.setTitle("删除账户");
        builder.setPositiveButton(R.string.confirm_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                DeleteListener listener = (DeleteListener) getParentFragment();
                                listener.onDelete(1);
                            }
                        }).setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

}