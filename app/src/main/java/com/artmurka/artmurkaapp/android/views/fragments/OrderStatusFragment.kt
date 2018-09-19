package com.artmurka.artmurkaapp.android.views.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle

import com.artmurka.artmurkaapp.R

class OrderStatusFragment : DialogFragment(), DialogInterface.OnClickListener {


    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val bundle = arguments
        val order = bundle.getString("order")
        val message = bundle.getString("message")

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(resources.getString(R.string.fragment_order_status_order) + order!!)
                .setMessage(message)
        builder.setPositiveButton(resources.getString(R.string.fragment_order_status_ok)) { dialog, which -> dismiss() }

        val dialog = builder.create()

        return dialog
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        dismiss()
    }
}
