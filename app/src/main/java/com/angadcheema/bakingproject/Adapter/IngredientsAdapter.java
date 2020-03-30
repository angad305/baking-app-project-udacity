package com.angadcheema.bakingproject.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.angadcheema.bakingproject.Model.IngredientsModel;
import com.angadcheema.bakingproject.R;
import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

  private Context context;
  private ArrayList<IngredientsModel> ingredient;

  public IngredientsAdapter(Context context,
      ArrayList<IngredientsModel> ingredient) {
    this.context = context;
    this.ingredient = ingredient;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    // Inflate the custom layout
    View recipeView = inflater.inflate(R.layout.recipe_list_view_holder, parent, false);
    // Return a new holder instance
    return new ViewHolder(recipeView);

  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    holder.ingQty.setText(ingredient.get(position).getQuantity());
    holder.ingMea.setText(ingredient.get(position).getMeasure());
    holder.ingr.setText(ingredient.get(position).getIngredient());

  }

  @Override
  public int getItemCount() {
    return ingredient.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView ingQty;
    TextView ingMea;
    TextView ingr;

    ViewHolder(@NonNull View itemView) {
      super(itemView);

      ingQty = (TextView) itemView.findViewById(R.id.Quantity);
      ingMea = (TextView) itemView.findViewById(R.id.Measure);
      ingr = (TextView) itemView.findViewById(R.id.Ingredient);

    }
  }


}
