package com.angadcheema.bakingproject.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.angadcheema.bakingproject.Activity.MainActivity;
import com.angadcheema.bakingproject.Fragment.StepsDetailFragment;
import com.angadcheema.bakingproject.Model.Steps;
import com.angadcheema.bakingproject.R;
import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

  private Context context;
  private ArrayList<Steps> steps;

  public StepsAdapter(Context context,
      ArrayList<Steps> steps) {
    this.context = context;
    this.steps = steps;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    // Inflate the custom layout
    View stepsView = inflater.inflate(R.layout.steps_detail_view_holder, parent, false);
    // Return a new holder instance
    return new ViewHolder(stepsView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

    holder.stepID.setText(steps.get(position).getId());
    holder.shortDescription.setText(steps.get(position).getShortDescription());
    holder.stepLayout.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Bundle stepsBundle = new Bundle();
        stepsBundle.putSerializable("steps_list", steps);
        stepsBundle.putInt("steplist_postn", position);

        StepsDetailFragment sdFrag = new StepsDetailFragment();
        sdFrag.setArguments(stepsBundle);

        boolean isTwoPane = v.getResources().getBoolean(R.bool.twoPaneMode);

        if (isTwoPane) {
          FragmentManager manager1 = ((MainActivity) context).getSupportFragmentManager();
          manager1.beginTransaction()
              .replace(R.id.detailContainer, sdFrag, "StepsDetailFragment")
              .commit();

        } else {
          FragmentManager manager = ((MainActivity) context).getSupportFragmentManager();
          manager.beginTransaction()
              .replace(R.id.MasterListXml, sdFrag, "StepsDetailFragment")
              .addToBackStack("tag")
              .commit();
        }

      }
    });
  }

  @Override
  public int getItemCount() {
    return steps.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView stepID;
    TextView shortDescription;
    LinearLayout stepLayout;

    ViewHolder(@NonNull View itemView) {
      super(itemView);

      stepID = (TextView) itemView.findViewById(R.id.stepid);
      shortDescription = (TextView) itemView.findViewById(R.id.shortDescription);
      stepLayout = (LinearLayout) itemView.findViewById(R.id.stepsLayout);

    }
  }

}
