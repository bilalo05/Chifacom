package serive.algeria.chifacom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import serive.algeria.chifacom.controle.Language;
import serive.algeria.chifacom.doctor.DoctorHomeActivity;
import serive.algeria.chifacom.doctor.DoctorMainActivity;
import serive.algeria.chifacom.forgetPass.ForgetPassActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private  long backPressedTime;
    AppCompatButton register ,forgetPass,login;
    EditText email,password;
    View decorView;

    ProgressDialog progressDialog;

    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = new Language(this);
        language.setLocate(language.getLocale(),this);
        setContentView(R.layout.activity_login2);

        progressDialog = new ProgressDialog(this);
        decorView = getWindow().getDecorView();

        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                decorView.setSystemUiVisibility(hideSystemBars());
            }
        });


        //get information from view
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);

        register = findViewById(R.id.registerNav);
        forgetPass = findViewById(R.id.forgetPass);
        login = findViewById(R.id.login);

        register.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case EditorInfo.IME_ACTION_GO:
                    Toast.makeText(LoginActivity.this, "Login !..", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    };
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }



    @Override
    public void onClick(View v) {

        if (v==register) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        }
        if (v == forgetPass){
            Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
            startActivity(intent);
        }
        if (v==login){

            Login();
            /*
            Intent intent = new Intent(LoginActivity.this, DoctorMainActivity.class);
            startActivity(intent);
            */
            //userLogin();
        }
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

    private void Login() {

        final String username = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray values =new JSONArray(response);
                    if(values.length() == 0){
                        Log.d("login"," 0 ");
                        progressDialog.dismiss();
                    }else {

                            JSONObject object = values.getJSONObject(0);

                            String error = object.getString("error");

                            int office_id = object.getInt("office_id");
                            String office_mail = object.getString("office_mail");
                            String office_type = object.getString("office_type");
                            String phone = object.getString("phone");
                            String office_createdAt = object.getString("office_createdAt");
                            String doctor_firstname = object.getString("doctor_firstname");
                            String doctor_lastname = object.getString("doctor_lastname");
                            String doctor_address = object.getString("doctor_address");
                            String doctor_speciality = object.getString("doctor_speciality");
                            String birthdate = object.getString("birthdate");

                            DoctorPrefManager.getInstance(getApplicationContext()).userLogin(
                                            office_id,
                                            office_mail,
                                            doctor_firstname,
                                            doctor_lastname,
                                            office_createdAt
                                    );

                            Log.d("login",error+" - "+office_id+" - "+office_mail
                                    +" - "+office_type+" - "+phone+" - "+office_createdAt
                                    +" - "+doctor_firstname+" - "+doctor_lastname+" - "+doctor_address
                                    +" - "+doctor_speciality+" - "+birthdate);

                            startActivity(new Intent(getApplicationContext(),DoctorMainActivity.class));
                            finish();

                            progressDialog.dismiss();

                    }
                } catch (JSONException e) {
                    Log.d("login"," JSONException ");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("login"," VolleyError ");
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params =new HashMap<>();
                params.put("office_mail",username);
                params.put("office_pass",pass);
                return params;
            }
        } ;
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void userLogin(){
        final String username = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){

                                DoctorPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("firstname"),
                                                obj.getString("lastname"),
                                                obj.getString("created_at")
                                        );

                                startActivity(new Intent(getApplicationContext(),DoctorMainActivity.class));
                                finish();

                            }else{
//                                Toast.makeText(
//                                        getApplicationContext(),
//                                        obj.getString("message"),
//                                        Toast.LENGTH_LONG
//                                ).show();

                                if (obj.getString("message") != null) {

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
//                        Toast.makeText(
//                                getApplicationContext(),
//                                error.getMessage(),
//                                Toast.LENGTH_LONG
//                        ).show();
                        if (error.getMessage() != null) {
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("office_mail",username);
                params.put("office_pass",pass);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}