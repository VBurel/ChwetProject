package com.example.vincentburel.chwet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PrefsData";
    private static final String PREF_USEREMAIL = "user_email";
    private static final String PREF_PASSWORD = "user_password";

    final String EXTRA_EMAIL = "user_email";
    final String EXTRA_PASSWORD = "user_password";


    Button btLogin;
    EditText userEmailText;
    EditText userPasswordText;
    CheckBox rememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}


        /* // Change ActionBar Title and Color
        String appName = "Chwet";
        SpannableString s = new SpannableString(appName );
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, appName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);*/

        setContentView(R.layout.activity_main);

        btLogin = (Button) findViewById(R.id.loginBtLogin);
        userEmailText = (EditText) findViewById(R.id.loginEmail);
        userPasswordText = (EditText) findViewById(R.id.loginPassword);
        rememberMe = (CheckBox) findViewById(R.id.loginCheckbox);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String savedUserEmail = pref.getString(PREF_USEREMAIL, null);
        String savedUserPassword = pref.getString(PREF_PASSWORD, null);

        rememberMe.setChecked(true);

        // if UserData saved
        if (savedUserEmail != null)
            userEmailText.setText(savedUserEmail);
        if (savedUserPassword!= null)
            userPasswordText.setText(savedUserPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String userEmail = userEmailText.getText().toString();
                final String userPassword = userPasswordText.getText().toString();

                // Check if Email and Password aren't empty
                if (userEmail.equals("") || userPassword.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.email_or_password_empty,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if Email as an email format
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(userEmail);
                if (!m.matches()) {
                    Toast.makeText(MainActivity.this, R.string.email_format_error,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // @TODO Condition Email and Password are good

                // save Email and Password in cache
                if (rememberMe.isChecked()) {
                    getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                            .putString(PREF_USEREMAIL, userEmail)
                            .putString(PREF_PASSWORD, userPassword)
                            .apply();
                } else {
                    getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                            .remove(PREF_USEREMAIL)
                            .remove(PREF_PASSWORD)
                            .apply();
                }


                // Send Email, Password into next Activity
                Intent home = new Intent(MainActivity.this,
                        HomeActivity.class);
                home.putExtra(EXTRA_EMAIL, userEmail);
                home.putExtra(EXTRA_PASSWORD, userPassword);

                // Start next Activity
                startActivity(home);

            }
        });
        //       this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}


