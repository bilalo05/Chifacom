package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Locale;

import serive.algeria.chifacom.controle.Language;

public class MainActivity extends AppCompatActivity {

    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = new Language(this);
        if(language.getLocale().equals("")){
            language.setLocate(Locale.getDefault().getLanguage(),this);
        }else{
            language.loadLocale();
        }
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        //save the first run to skip it next time
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        startActivity(new Intent(this,LoadingActivity.class));
        finish();

    }
}