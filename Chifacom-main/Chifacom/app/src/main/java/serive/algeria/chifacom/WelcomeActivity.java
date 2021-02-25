package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import serive.algeria.chifacom.controle.Language;

public class WelcomeActivity extends AppCompatActivity {

    private Language language;

    AppCompatButton useapp;

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = new Language(this);
        language.setLocate(language.getLocale(),this);
        setContentView(R.layout.activity_welcome);

        boolean firstStart = getSharedPreferences("prefs", MODE_PRIVATE).getBoolean("firstStart", true);

        useapp = findViewById(R.id.useApp);
        useapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useapp.setTextColor(Color.WHITE);
                useapp.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_signin_btn));

                Intent intent;// delete this and uncomment the code obove to make first run work
                if(firstStart){
                    intent = new Intent(WelcomeActivity.this, FirstRunActivity.class);
                }else{
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        });




    }
}