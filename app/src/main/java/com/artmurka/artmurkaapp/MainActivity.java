package com.artmurka.artmurkaapp;

import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.artmurka.artmurkaapp.Fragments.ShopFragment;
import com.artmurka.artmurkaapp.Retrofit.OneCategory;
import com.artmurka.artmurkaapp.Retrofit.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        RetroClient.getApiService().getOAuthGetRequestToken(
                "murka", RetroClient.oauth_signature_method, RetroClient.oa

        )


//        RetroClient.getApiService().getShopList("artmurka", "CUchqePQNHIuQ6ceMfaziBOLI42sah", "X0S3jMEVlOvxAQSsIZ40zeYzYvOnthchpK.abu1d", "6Gcq2RVe02fL3qUk8hsVaXmri92y5NpHP3RzXA6a")
//                .enqueue(new Callback<List<OneCategory>>() {
//                    @Override
//                    public void onResponse(Call<List<OneCategory>> call, Response<List<OneCategory>> response) {
//
//                        Log.d("Log.d", call.toString() + response.toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<OneCategory>> call, Throwable t) {
//
//                    }
//                });

    }

    private void refreshFragment(){

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
