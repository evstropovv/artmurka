package com.artmurka.artmurkaapp;

import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.artmurka.artmurkaapp.Fragments.ShopFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    ShopFragment shopFragment;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btnTest);
        shopFragment = new ShopFragment();
        setButtonEnable();
    }

    private void setButtonEnable() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingInfo();
            }
        });
    }

    private void loadingInfo() {
        //loading info from api

    }

    private void refreshFragment(){
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("array", new ArrayList<Parcelable>());
        shopFragment.setArguments(bundle);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, shopFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
