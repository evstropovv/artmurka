package com.artmurka.artmurkaapp.Presenter.Adapters;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.SizePhoto;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.Success;
import com.google.android.gms.common.images.Size;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class PojoPhotoAdapter implements JsonDeserializer<Success> {

    @Override
    public Success deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObj = json.getAsJsonObject();
        JsonElement jElement = jObj.get("others_photo");
        Map<String, SizePhoto> tags = Collections.emptyMap();
        if(jElement.isJsonObject()) { // isJsonArray ???
            tags = context.deserialize(jElement.getAsJsonObject(), new TypeToken<Map<String, SizePhoto>>(){}.getType());
        }
        //assuming there is an appropriate constructor
        return new Success(jObj.getAsJsonPrimitive("others_photo").getAsString(), tags);
    }
}
