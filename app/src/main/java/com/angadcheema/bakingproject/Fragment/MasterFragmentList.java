package com.angadcheema.bakingproject.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.angadcheema.bakingproject.Adapter.MyAdapter;
import com.angadcheema.bakingproject.Model.IngredientsModel;
import com.angadcheema.bakingproject.Model.Steps;
import com.angadcheema.bakingproject.R;
import com.angadcheema.bakingproject.Model.Recipe;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MasterFragmentList extends Fragment {

  private ArrayList<Recipe> RecipeList = new ArrayList<>();
  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private Object ObjectSerializer;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getData();

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    Objects.requireNonNull(getActivity()).setTitle("Baking Project");
    // Inflate the layout for this fragment
    View inflatedView = inflater.inflate(R.layout.fragment_master_list, container, false);

    //RECYCLERVIEW STARTS
    recyclerView = (RecyclerView) inflatedView.findViewById(R.id.master_recycler_view);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new MyAdapter(getActivity(), RecipeList);
    recyclerView.setAdapter(mAdapter);
    recyclerView
        .addItemDecoration(
            new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    //RECYCLERVIEW ENDS
    return inflatedView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    recyclerView.setAdapter(null);
    recyclerView = null;
  }

  private void getData() {
    RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

          @Override
          public void onResponse(JSONArray response) {

            for (int i = 0; i < response.length(); i++) {
              try {
                JSONObject jsonObject = response.getJSONObject(i);
                Log.d("JSON", "onResponse: " + jsonObject.getString("name"));
                String recipeName = jsonObject.getString("name");
                String servings = jsonObject.getString("servings");
                String image = jsonObject.getString("image");

                Recipe recipe = new Recipe();

                recipe.setRecipeName(recipeName);
                recipe.setServings(servings);
                recipe.setImage(image);
                Log.d("JSON0", "onResponse: " + jsonObject.getString("servings"));

                ArrayList<IngredientsModel> Ingredients = new ArrayList<>();

                JSONArray ingredientsArr = jsonObject.getJSONArray("ingredients");
                for (int j = 0; j < ingredientsArr.length(); j++) {
                  JSONObject ingrObj = ingredientsArr.getJSONObject(j);
                  IngredientsModel model = new IngredientsModel();
                  model.setQuantity(ingrObj.getString("quantity"));
                  model.setMeasure(ingrObj.getString("measure"));
                  model.setIngredient(ingrObj.getString("ingredient"));
                  Ingredients.add(model);
                  recipe.setIngredients(Ingredients);
                  Log.d("JSON1", "onResponse: " + ingrObj.getString("ingredient"));

                }

                ArrayList<Steps> stepsList = new ArrayList<>();

                JSONArray stepsArr = jsonObject.getJSONArray("steps");
                for (int k = 0; k < stepsArr.length(); k++) {
                  JSONObject stepsOBJ = stepsArr.getJSONObject(k);
                  Steps smodel = new Steps();
                  smodel.setId(stepsOBJ.getString("id"));
                  smodel.setShortDescription(stepsOBJ.getString("shortDescription"));
                  smodel.setDescription(stepsOBJ.getString("description"));
                  smodel.setVideoURL(stepsOBJ.getString("videoURL"));
                  smodel.setThumbnailURL(stepsOBJ.getString("thumbnailURL"));
                  stepsList.add(smodel);
                  recipe.setSteps(stepsList);
                  Log.d("JSON2", "onResponse: " + stepsOBJ.getString("id"));

                }

                RecipeList.add(recipe);
                mAdapter.notifyDataSetChanged();
              } catch (JSONException e) {
                e.printStackTrace();
              }

            }
          }
        }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {
            // TODO: Handle error

          }
        });
    requestQueue.add(jsonArrayRequest);
  }


}
