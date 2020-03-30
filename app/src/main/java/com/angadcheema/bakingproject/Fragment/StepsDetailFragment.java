package com.angadcheema.bakingproject.Fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.angadcheema.bakingproject.Model.Steps;
import com.angadcheema.bakingproject.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Objects;

public class StepsDetailFragment extends Fragment {

  private long position;
  private ArrayList<Steps> steps = new ArrayList<>();
  private int postn;
  private PlayerView playerView;
  private SimpleExoPlayer player;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Objects.requireNonNull(getActivity()).setTitle("Step Detail");

    View inflatedView = inflater.inflate(R.layout.fragment_steps_detail, container, false);
    Bundle args = getArguments();
    if (args == null) {
      Toast.makeText(getActivity(), "arguments is null ", Toast.LENGTH_LONG).show();
    } else {

      steps = (ArrayList<Steps>) args.getSerializable("steps_list");
      postn = args.getInt("steplist_postn");
      TextView description = inflatedView.findViewById(R.id.description);
      description.setText(steps.get(postn).getDescription());

      playerView = inflatedView.findViewById(R.id.playerView);
    }

    return inflatedView;
  }

  @Override
  public void onStart() {
    super.onStart();
Context context;
TrackSelector trackSelector;

    player = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector());
    playerView.setPlayer(player);
    // Produces DataSource instances through which media data is loaded.
    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
        Objects.requireNonNull(getActivity()),
        Util.getUserAgent(getActivity(), getString(R.string.app_name)));
// This is the MediaSource representing the media to be played.

    if (!steps.get(postn).getVideoURL().isEmpty()) {

      MediaSource videoSource =
          new ExtractorMediaSource.Factory(dataSourceFactory)
              .createMediaSource(Uri.parse(steps.get(postn).getVideoURL()));
// Prepare the player with the source.
      player.prepare(videoSource);

    } else if (!steps.get(postn).getThumbnailURL().isEmpty()) {

      MediaSource videoSource =
          new ExtractorMediaSource.Factory(dataSourceFactory)
              .createMediaSource(Uri.parse(steps.get(postn).getThumbnailURL()));
// Prepare the player with the source.
      player.prepare(videoSource);

    } else {

      Toast.makeText(getActivity(), "Sorry! No Video Available", Toast.LENGTH_SHORT).show();
      playerView.setVisibility(View.INVISIBLE);
    }

  }

  @Override
  public void onPause() {
    super.onPause();
    if (player != null) {
      position = player.getCurrentPosition();
      player.stop();
      player.release();
      player = null;

    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (position != 0 && player != null) {
      player.seekTo(position);
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    releasePlayer();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    releasePlayer();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    releasePlayer();
  }

  private void releasePlayer() {
    if (player != null) {
      player.stop();
      player.release();
      player = null;
    }
  }

}