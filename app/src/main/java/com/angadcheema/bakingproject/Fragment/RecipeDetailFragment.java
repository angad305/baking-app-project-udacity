package com.angadcheema.bakingproject.Fragment;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.angadcheema.bakingproject.Adapter.IngredientsAdapter;
import com.angadcheema.bakingproject.Adapter.StepsAdapter;
import com.angadcheema.bakingproject.Model.IngredientsModel;
import com.angadcheema.bakingproject.Model.Steps;
import com.angadcheema.bakingproject.R;
import com.angadcheema.bakingproject.Model.Recipe;
import com.angadcheema.bakingproject.widget.AppWidgetService;
import java.util.ArrayList;
import java.util.Objects;


public class RecipeDetailFragment extends Fragment {

  private ArrayList<IngredientsModel> ingredients = new ArrayList<>();
  private ArrayList<Steps> steps = new ArrayList<>();
  private ArrayList<Recipe> RecipeList = new ArrayList<>();
  private int Postn;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, final ViewGroup container,
      Bundle savedInstanceState) {
    Objects.requireNonNull(getActivity()).setTitle("Recipe Detail");
    View inflatedView = inflater.inflate(R.layout.recipe_details, container, false);

    Bundle args = getArguments();
    if (args == null) {
      Toast.makeText(getActivity(), "arguments is null ", Toast.LENGTH_LONG).show();
    } else {
      TextView recipeName = (TextView) inflatedView.findViewById(R.id.RecipeName);
      recipeName.setText(args.getString("recipeN"));

      TextView servings = (TextView) inflatedView.findViewById(R.id.serving);
      servings.setText(args.getString("serving"));

      ingredients = (ArrayList<IngredientsModel>) args.getSerializable("ingredient");
      //Toast.makeText(getActivity(), "text " + ingredients.size(), Toast.LENGTH_LONG).show();

      steps = (ArrayList<Steps>) args.getSerializable("steps");
      //Toast.makeText(getActivity(), "Toal Array Size:  " + steps.size(), Toast.LENGTH_LONG).show();

      RecipeList = (ArrayList<Recipe>) args.getSerializable("RecipeList");
      Postn = args.getInt("Postn");
    }

    //Add to Widget Button
    Button button = inflatedView.findViewById(R.id.addToWidget);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Recipe recipe = RecipeList.get(Postn);
        AppWidgetService.updateWidget(getContext(), recipe);
        Toast.makeText(getContext(), "Added to Widget", Toast.LENGTH_SHORT)
            .show();
      }
    });

    //RECYCLERVIEW STARTS INGREDIENTS LIST
    RecyclerView recyclerView = (RecyclerView) inflatedView
        .findViewById(R.id.ingredients_recycler_view);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    RecyclerView.Adapter mAdapter = new IngredientsAdapter(getActivity(), ingredients);

    recyclerView.setAdapter(mAdapter);
    recyclerView
        .addItemDecoration(
            new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    //RECYCLERVIEW ENDS

  //RECYCLERVIEW STARTS STEPS LIST
    recyclerView = (RecyclerView) inflatedView.findViewById(R.id.steps_recycler_view);
    layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new StepsAdapter(getActivity(), steps);
    recyclerView.setAdapter(mAdapter);
    recyclerView
        .addItemDecoration(
            new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    //RECYCLERVIEW ENDS
    mAdapter.notifyDataSetChanged();

    return inflatedView;
  }

}