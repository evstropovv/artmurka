package com.artmurka.artmurkaapp.android.views.fragments


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.presenter.SendEmailPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ISendEmailFragment

import android.app.Activity.RESULT_OK
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import kotlinx.android.synthetic.main.fragment_individual.*
import javax.inject.Inject


class IndividualFragment : BaseFragment(), ISendEmailFragment {

    @Inject
    lateinit var presenter: SendEmailPresenter

    var photoUri: String? = null

    override fun getLayout(): Int = R.layout.fragment_individual
    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    override fun onStart() {
        super.onStart()
        etEmail.setText(if (Preferences.email.matches("artmurka.com".toRegex())) "" else Preferences.email)
        btnAddPhoto.setOnClickListener {
            if (fileLoadPermission()) {
                choseFile()
            }
        }

        btnSend.setOnClickListener {
            if (etEmail.text.toString().matches("".toRegex()) || etMsg.text.toString().matches("".toRegex()) || etName.text.toString().matches("".toRegex())) {
                Toast.makeText(context, "Заповніть, будь-ласка, всі поля", Toast.LENGTH_SHORT).show()
            } else {
                if (photoUri == null) {
                    presenter.sendEmail(etEmail.text.toString() + " -email "
                            + etName.text.toString() + " -name. " +
                            "" + etMsg.text.toString())
                } else {
                    presenter.sendEmail(etEmail.text.toString() + " -email "
                            + etName.text.toString() + " -name. " +
                            "" + etMsg.text.toString(), photoUri!!)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choseFile()

            } else { //показываем окно, пока не получим разрешения.
                fileLoadPermission()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //getting image path
        if (requestCode == 10 && resultCode == RESULT_OK && null != data) {
            photoUri = this!!.getPath(data.data)!!
            tvChosingPhoto.text = photoUri
            Toast.makeText(context, photoUri, Toast.LENGTH_LONG).show()
        }
    }

    private fun choseFile() {
        val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.type = "image/*"
        startActivityForResult(intent, 10)
    }

    fun getPath(uri: Uri?): String? {
        var path: String? = null
        val projection = arrayOf(MediaStore.Files.FileColumns.DATA)
        val cursor = activity!!.contentResolver.query(uri!!, projection, null, null, null)
        if (cursor == null) {
            path = uri.path
        } else {
            cursor.moveToFirst()
            val column_index = cursor.getColumnIndexOrThrow(projection[0])
            path = cursor.getString(column_index)
            cursor.close()
        }

        return if (path == null || path.isEmpty()) uri.path else path
    }

    override fun allOk() {
        Toast.makeText(context, "Повідомлення відправлено", Toast.LENGTH_LONG).show()
    }

    override fun error() {
        Toast.makeText(context, "Помилка при відправці повідомлення", Toast.LENGTH_LONG).show()
    }

    override fun clear() {
        etName.setText("")
        etMsg.setText("")
        etEmail.setText("")
        tvChosingPhoto.text = ""
        photoUri = null
    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Індивідуальне замовлення"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }


    private fun fileLoadPermission(): Boolean { //запрос у пользователя разрешений
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    101)
            return true
        } else {
            choseFile()
        }
        return false
    }
}// Required empty public constructor
