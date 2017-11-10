package org.lqwit.android.global.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import org.lqwit.android.R;
import org.lqwit.android.global.utils.ViewUtils;

public class AccountDialog extends DialogFragment {



    public interface DeleteListener{
        void onDelete(Integer accountId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.create();
    }

}