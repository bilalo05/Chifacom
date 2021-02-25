package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import serive.algeria.chifacom.controle.Language;

public class LoadingActivity extends AppCompatActivity {
    private Language language;

    View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = new Language(this);
        language.setLocate(language.getLocale(),this);
        setContentView(R.layout.activity_loading);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                decorView.setSystemUiVisibility(hideSystemBars());
            }
        });




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=null;
//                if (firstStart) {
//                    SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = prefs.edit();
//                    editor.putBoolean("firstStart",false);
//                    editor.apply();
//                     intent = new Intent(MainActivity.this, FirstRunActivity.class);
//                }else{
//                    intent = new Intent(MainActivity.this, LoginActivity.class);
//                }

                intent = new Intent(LoadingActivity.this, WelcomeActivity.class);// delete this and uncomment the code obove to make first run work
                startActivity(intent);
                finish();
            }
        },3000);
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
}