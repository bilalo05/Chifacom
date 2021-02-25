package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import serive.algeria.chifacom.controle.Language;

public class FirstRunActivity extends AppCompatActivity {
    TextView textView ;
    AppCompatButton submit,ar,fr;
    View decorView;

    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = new Language(this);
        language.setLocate(language.getLocale(),this);
        setContentView(R.layout.activity_first_run);

        //hide status bar
        decorView = getWindow().getDecorView();

        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

// forward to login form after submitting
        ar = findViewById(R.id.Ar);
        fr = findViewById(R.id.Fr);

        //ARABIC
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("prefs", MODE_PRIVATE).edit().putBoolean("firstStart", false).commit();

                language.setLocate("ar",getApplicationContext());

//              ar.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_signin_btn));
                ar.setTextColor(Color.WHITE);
                ar.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.language_2));


                Intent intent = new Intent(FirstRunActivity.this, LoginActivity.class);// delete this and uncomment the code obove to make first run work
              startActivity(intent);
            }
        });

        //FRENCH
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("prefs", MODE_PRIVATE).edit().putBoolean("firstStart", false).commit();

                language.setLocate("fr",getApplicationContext());

                fr.setTextColor(Color.WHITE);
                fr.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.language_2));
                Intent intent = new Intent(FirstRunActivity.this, LoginActivity.class);// delete this and uncomment the code obove to make first run work
                startActivity(intent);
            }
        });





    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    public int  hideSystemBars(){


        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                ;
    }

    public void setFr(View view) {
        findViewById(R.id.Fr).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
        findViewById(R.id.Ar).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.inselect));

    }

    public void setAr(View view) {
        findViewById(R.id.Ar).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
        findViewById(R.id.Fr).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.inselect));

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
        super.onBackPressed();
    }
}