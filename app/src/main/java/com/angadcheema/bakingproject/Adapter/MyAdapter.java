package com.angadcheema.bakingproject.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.angadcheema.bakingproject.Activity.MainActivity;
import com.angadcheema.bakingproject.Model.IngredientsModel;
import com.angadcheema.bakingproject.Model.Steps;
import com.angadcheema.bakingproject.R;
import com.angadcheema.bakingproject.Model.Recipe;
import com.angadcheema.bakingproject.Fragment.RecipeDetailFragment;
import com.angadcheema.bakingproject.widget.AppWidgetService;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.LineHolder> {

  private Context context;
  private ArrayList<Recipe> RecipeList;

  public MyAdapter(Context context,
      ArrayList<Recipe> recipeList) {
    this.context = context;
    RecipeList = recipeList;
  }

  @NonNull
  @Override
  public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    // Inflate the custom layout
    View recipeView = inflater.inflate(R.layout.master_list_view_holder, parent, false);
    // Return a new holder instance
    return new LineHolder(recipeView);
  }

  @Override
  public void onBindViewHolder(@NonNull LineHolder holder, final int position) {

    final Recipe mCurrent = RecipeList.get(position);

    holder.RecipeName.setText(RecipeList.get(position).getRecipeName());

    Glide.with(context)
        .asBitmap()
        .load(mCurrent.getImage())
        .placeholder(R.drawable.recipe)
        .into(holder.image);

    AppWidgetService.updateWidget(context, RecipeList.get(0));

    holder.parentLayout.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Bundle args = new Bundle();
        args.putString("recipeN", RecipeList.get(position).getRecipeName());
        args.putString("serving", RecipeList.get(position).getServings());
        args.putSerializable("ingredient", RecipeList.get(position).getIngredients());
        args.putSerializable("steps", RecipeList.get(position).getSteps());
        args.putSerializable("RecipeList", RecipeList);
        args.putInt("Postn", position);

        RecipeDetailFragment rFrag = new RecipeDetailFragment();
        rFrag.setArguments(args);
        FragmentManager manager = ((MainActivity) context).getSupportFragmentManager();
        manager.beginTransaction()
            .replace(R.id.MasterListXml, rFrag, "RecipeDetailsFragment")
            .addToBackStack("tag")
            .commit();

      }
    });
  }

  @Override
  public int getItemCount() {
    return RecipeList.size();
  }

  static class LineHolder extends RecyclerView.ViewHolder {

    TextView RecipeName;
    TextView servings;
    ImageView image;
    RelativeLayout parentLayout;

    LineHolder(@NonNull View itemView) {
      super(itemView);
      RecipeName = (TextView) itemView.findViewById(R.id.showRecipeName);
      servings = (TextView) itemView.findViewById(R.id.serving);
      image = (ImageView) itemView.findViewById(R.id.image);
      parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
    }
  }

}
