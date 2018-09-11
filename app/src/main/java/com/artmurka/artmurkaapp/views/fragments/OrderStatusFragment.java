package com.artmurka.artmurkaapp.views.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.artmurka.artmurkaapp.R;

public class OrderStatusFragment extends DialogFragment implements DialogInterface.OnClickListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String order = bundle.getString("order");
        String message = bundle.getString("message");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.fragment_order_status_order) + order)
                .setMessage(message);
        builder.setPositiveButton(getResources().getString(R.string.fragment_order_status_ok), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dismiss();
    }
}
