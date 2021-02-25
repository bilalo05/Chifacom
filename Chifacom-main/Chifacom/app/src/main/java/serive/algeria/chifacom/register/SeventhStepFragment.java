package serive.algeria.chifacom.register;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import serive.algeria.chifacom.Constants;
import serive.algeria.chifacom.IonBackPressed;
import serive.algeria.chifacom.R;


public class SeventhStepFragment extends Fragment implements IonBackPressed, View.OnClickListener {

    private Bundle bundle;
    private TextView doctorfull,back;
    private AppCompatButton createUser;
    private AppCompatButton inserer_photo;

    private ProgressDialog progressDialog;

    public SeventhStepFragment() {
        // Required empty public constructor
    }

    public static SeventhStepFragment newInstance() {
        SeventhStepFragment fragment = new SeventhStepFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_seventh_step, container, false);
        bundle = this.getArguments();

        doctorfull = view.findViewById(R.id.doctorfull);
        doctorfull.setText(bundle.getString("fname","")+" "+bundle.getString("lname",""));

        back = view.findViewById(R.id.back);
        back.setOnClickListener(this);

        progressDialog = new ProgressDialog(getContext());

        createUser = view.findViewById(R.id.submitRegistration);
        createUser.setOnClickListener(this);

        inserer_photo = view.findViewById(R.id.inserer_photo);
        inserer_photo.setOnClickListener(this);



        return view;
    }

    @Override
    public boolean onBackPressed() {
        if (true) {
            //action not popBackStack
            Fragment fragment = new SixthStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            getActivity().findViewById(R.id.step5).setBackgroundColor(getResources().getColor(R.color.darkGray));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {

        if (v== inserer_photo){
            insertPhoto();
        }
        if (v== createUser){
            createNewDoctor();
        }

        if (v == back){
            Fragment fragment = new SixthStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            getActivity().findViewById(R.id.step5).setBackgroundColor(getResources().getColor(R.color.darkGray));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void insertPhoto() {

    }

    public void createNewDoctor(){
        final String firstname = bundle.getString("fname","");
        final String firstnameAR = bundle.getString("fnameAR","");
        final String lastname = bundle.getString("lname","");
        final String lastnameAR = bundle.getString("lnameAR","");
        final String speciality = bundle.getString("speciality","");
        final String birthday = bundle.getString("birthday","");
        final String birthplace = bundle.getString("birthplace","");
        final String email = bundle.getString("email","");
        final String phone = bundle.getString("phone","");
        final String username = bundle.getString("username","");
        final String password =bundle.getString("password","");
        final String type = "doctor";
        final String location = "cite djenaine";
        final String province = "sidi merouane";
        final String state = "mila";
        final String link = "https://www.google.com/maps/place/Constantine+Province/@36.5227989,6.262338,497m/data=!3m1!1e3!4m5!3m4!1s0x12f10cb470246509:0x5e2ae1969309f737!8m2!3d36.3373911!4d6.663812";
        final String lat = "36.52306169788467";
        final String longt = " 6.26314185637305";
        //  final int debitC = Integer.parseInt(debit);
        //int store_id= Integer.parseInt(storeId.getText().toString().trim());

        progressDialog.setMessage(getString(R.string.attendez));
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ROGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                              // success message

                                     Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                if (error.getMessage() != null) {
                    // errror message
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
                //        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("doctor_firstname",firstname);
                params.put("doctor_lastname",lastname);
                params.put("doctor_firstname_AR",firstnameAR);
                params.put("doctor_lastname_AR",lastnameAR);
                params.put("doctor_speciality",speciality);
                params.put("birthdate",birthday);
                params.put("birthplace",birthplace);
                params.put("phone",phone);
                params.put("office_mail",email);
                params.put("office_username",username);
                params.put("office_pass",password);
                params.put("office_type",type);
                params.put("office_location",location);
                params.put("province",province);
                params.put("state",state);
                params.put("address_link",link);
                params.put("address_lat",lat);
                params.put("address_long",longt);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}