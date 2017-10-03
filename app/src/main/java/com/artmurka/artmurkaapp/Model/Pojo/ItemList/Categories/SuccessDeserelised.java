package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SuccessDeserelised implements JsonDeserializer<Success>{
    //class need for parsing dinamicly "List<Child>" that can be null or "" or Child...

    @Override
    public Success deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jObj = json.getAsJsonObject();
        JsonElement jElement = jObj.get("childs");
        final String catImg = jObj.get("cat_img").getAsString();
        final String catDescr = jObj.get("cat_descr").getAsString();
        final int goodsCount = jObj.get("goods_count").getAsInt();
        final String catId = jObj.get("cat_id").getAsString();
        final String catName = jObj.get("cat_name").getAsString();
        final String catUrl = jObj.get("cat_url").getAsString();

        List<Child> childs = Collections.emptyList();
        if(jElement.isJsonArray()) {
            childs = context.deserialize(jElement.getAsJsonArray(), new TypeToken<List<Child>>(){}.getType());
        }
        return new Success(catImg, childs, catDescr,goodsCount,catId,catName, catUrl);
    }
}
