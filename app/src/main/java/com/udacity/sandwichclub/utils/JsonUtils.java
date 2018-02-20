package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Log.d("Louco", json);
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject MainJson = new JSONObject(json);
            sandwich = FillName(sandwich, MainJson.getJSONObject("name"));
            sandwich.setPlaceOfOrigin(MainJson.getString("placeOfOrigin"));
            sandwich.setDescription(MainJson.getString("description"));
            sandwich.setImage(MainJson.getString("image"));
            return FillIngredients(sandwich, MainJson);
        } catch (JSONException e) {
            return null;
        }
    }

    private static Sandwich FillName(Sandwich sandwich, JSONObject nameObject) throws JSONException {
        sandwich.setMainName(nameObject.getString("mainName"));
        ArrayList<String> alsoKnownAs =  new ArrayList<>();
        JSONArray array = nameObject.getJSONArray("alsoKnownAs");
        for (int i = 0; i<array.length(); i++){
            alsoKnownAs.add(array.getString(i));
        }
        sandwich.setAlsoKnownAs(alsoKnownAs);
        return sandwich;
    }

    private static Sandwich FillIngredients(Sandwich sandwich, JSONObject nameObject) throws JSONException {
        ArrayList<String> ingredients =  new ArrayList<>();
        JSONArray array = nameObject.getJSONArray("ingredients");
        for (int i = 0; i<array.length(); i++){
            ingredients.add(array.getString(i));
        }
        sandwich.setIngredients(ingredients);
        return sandwich;
    }
}
