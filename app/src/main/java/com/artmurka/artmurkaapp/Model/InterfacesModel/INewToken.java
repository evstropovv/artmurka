package com.artmurka.artmurkaapp.Model.InterfacesModel;


import retrofit2.Call;

public interface INewToken {
     Call<String> oAuthGetRequestToken();
}
