package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_welcome);

        useapp = findViewById(R.id.useApp);
        useapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useapp.setTextColor(Color.WHITE);
                useapp.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_signin_btn));
               Intent intent = new Intent(WelcomeActivity.this, FirstRunActivity.class);// delete this and uncomment the code obove to make first run work
                startActivity(intent);
            }
        });




    }
}