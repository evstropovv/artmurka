package com.artmurka.artmurkaapp.Views.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ISendEmailPresenter;
import com.artmurka.artmurkaapp.Presenter.SendEmailPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ISendEmailFragment;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import static android.app.Activity.RESULT_OK;


public class IndividualFragment extends Fragment implements ISendEmailFragment {
    Button btnAddPhoto, btnSend;
    EditText etName, etEmail, etMsg;
    TextView tvChosingPhoto;
    String photoUri = null;
    ISendEmailPresenter presenter;

    public IndividualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual, container, false);
        presenter = new SendEmailPresenter(this, getContext());
        btnAddPhoto = (Button) view.findViewById(R.id.btnAddPhoto);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        etName = (EditText) view.findViewById(R.id.etName);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etEmail.setText(Preferences.getEmail().matches("artmurka.com")?"":Preferences.getEmail());
        etMsg = (EditText) view.findViewById(R.id.etMsg);
        tvChosingPhoto = (TextView) view.findViewById(R.id.tvChosingFile);

        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileLoadPermission()) {
                    choseFile();
                }

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().matches("")||etMsg.getText().toString().matches("")||etName.getText().toString().matches("")){
                    Toast.makeText(getContext(), "Заповніть, будь-ласка, всі поля", Toast.LENGTH_SHORT).show();
                }else {
                    if (photoUri == null) {
                        presenter.sendEmail(etEmail.getText().toString() + " -email "
                                + etName.getText().toString() + " -name. " +
                                "" + etMsg.getText().toString());
                    } else {

                        presenter.sendEmail(etEmail.getText().toString() + " -email "
                                + etName.getText().toString() + " -name. " +
                                "" + etMsg.getText().toString(), photoUri);
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choseFile();

            } else { //показываем окно, пока не получим разрешения.
                fileLoadPermission();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //getting image path
        if (requestCode == 10 && resultCode == RESULT_OK && null != data) {
            photoUri = getPath(data.getData());
            tvChosingPhoto.setText(photoUri);
            Toast.makeText(getContext(), photoUri, Toast.LENGTH_LONG).show();
        }
    }

    private void choseFile() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    public String getPath(Uri uri) {
        String path = null;
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            path = uri.getPath();
        } else {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }

    @Override
    public void allOk() {
        Toast.makeText(getContext(), "Повідомлення відправлено", Toast.LENGTH_LONG).show();
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Помилка при відправці повідомлення", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clear() {
        etName.setText("");
        etMsg.setText("");
        etEmail.setText("");
        tvChosingPhoto.setText("");
        photoUri =null;
    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Індивідуальне замовлення");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }



    private boolean fileLoadPermission() { //запрос у пользователя разрешений
        if (Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    101);
            return true;
        }else {
            choseFile();
        }
        return false;
    }
}
