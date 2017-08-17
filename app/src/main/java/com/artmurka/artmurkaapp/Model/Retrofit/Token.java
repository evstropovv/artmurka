package com.artmurka.artmurkaapp.Model.Retrofit;

import com.artmurka.artmurkaapp.Model.Modules.ApiModule;
import com.artmurka.artmurkaapp.Model.Modules.UcozApiModule;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Token implements IRegisterToken{
    //класс для получения первоначальных токенов

    @Override
    public Call<String> getOAuthGetRequestToken() {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "viewgoods");

        //Getting all token for autorization.
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/request" ,mapForUcozModule);
        confForRequest.put("page", mapForUcozModule.get("page"));

        return ApiModule.getClient().oAuthGetRequestToken(confForRequest);

    }

}
