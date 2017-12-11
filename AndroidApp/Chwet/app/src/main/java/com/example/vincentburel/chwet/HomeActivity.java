package com.example.vincentburel.chwet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by vincent.burel on 01/12/2017.
 */

public class HomeActivity extends AppCompatActivity {

    final String EXTRA_EMAIL = "user_email";
    final String EXTRA_PASSWORD = "user_password";

    private ListView eventListView;
    private String[] events = new String[] {
      "Jungle Juice - 10 ans", "Splash - 12TH Planet", "Animalz"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String appName = "Chwet";
        SpannableString s = new SpannableString(appName );
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, appName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        setContentView(R.layout.activity_home);

        eventListView = (ListView) findViewById(R.id.eventList);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_list_item_1, events);
        eventListView.setAdapter(adapter);
    }
}
