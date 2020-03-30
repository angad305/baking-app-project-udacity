package com.angadcheema.bakingproject.Activity;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.angadcheema.bakingproject.Fragment.MasterFragmentList;
import com.angadcheema.bakingproject.R;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (isOnline()) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction transaction = fragmentManager.beginTransaction();
      MasterFragmentList ffragment = new MasterFragmentList();
      transaction.add(R.id.MasterListXml, ffragment);
      transaction.commit();
    } else {
      Toast.makeText(getApplicationContext(), "Please Connect to Internet", Toast.LENGTH_LONG)
          .show();
    }

  }

  public boolean isOnline() {
    Runtime runtime = Runtime.getRuntime();
    try {
      Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
      int exitValue = ipProcess.waitFor();
      return (exitValue == 0);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return false;

  }
}