package com.artmurka.artmurkaapp.Views.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OrderStatusFragment extends DialogFragment implements DialogInterface.OnClickListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String order = bundle.getString("order");
        String message = bundle.getString("message");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Заказ: " + order)
                .setMessage(message);
        builder.setPositiveButton("Добре", new DialogInterface.OnClickListener(){
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
