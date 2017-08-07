package com.artmurka.artmurkaapp.Model.Retrofit;


import retrofit2.Call;

public interface IRegisterToken {
    public Call<String> getOAuthGetRequestToken();
}
