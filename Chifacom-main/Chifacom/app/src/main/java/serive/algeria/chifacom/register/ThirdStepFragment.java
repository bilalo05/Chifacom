package serive.algeria.chifacom.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import serive.algeria.chifacom.IonBackPressed;

import serive.algeria.chifacom.R;


public class ThirdStepFragment extends Fragment implements View.OnClickListener, IonBackPressed {

AppCompatButton nextStep ;
EditText phone, birthplace ;
Bundle bundle;
    LinearLayout birthdate;
    TextView bday,bmonth,byear;

    ImageView back;

    private DatePickerDialog.OnDateSetListener setListener;
    String birthday = "";

    public ThirdStepFragment() {
        // Required empty public constructor
    }

    public static ThirdStepFragment newInstance() {
        ThirdStepFragment fragment = new ThirdStepFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_third_step, container, false);
        bundle = this.getArguments();
        if (bundle != null) {
            final String firstname = bundle.getString("fname", "");
            final String firstnameAR = bundle.getString("fnameAR", "");
            final String lastname = bundle.getString("lname", "");
            final String lastnameAR = bundle.getString("lnameAR", "");
            Log.d("register", firstname + " - " + firstnameAR + " - " + lastname + " - " + lastnameAR);
        }

        //get views


        nextStep = view.findViewById(R.id.nextStep3);
        nextStep.setOnClickListener(this);

       back = view.findViewById(R.id.backtosecond);
        back.setOnClickListener(this);

        phone = view.findViewById(R.id.phone);
        birthdate = view.findViewById(R.id.birthdate);
        birthplace = view.findViewById(R.id.birthplace);

        bday = view.findViewById(R.id.birthday);
        bmonth = view.findViewById(R.id.birthmonth);
        byear = view.findViewById(R.id.birthyear);



        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = 1998;
                int month = 0;
                int day = 01;

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Mettez votre date de naissance");

                dialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10) {
                    birthday = year + " - 0" + month + " - " + dayOfMonth;
                } else {
                    birthday = year + "-" + month + "-" + dayOfMonth;
                }

                //birthdate.setText(birthday);
                bday.setText(""+dayOfMonth);
                bmonth.setText(""+month);
                byear.setText(""+year);


            }
        };

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v==nextStep){
            Fragment fragment = new ForthStepFragment();
            if (bundle != null){

            bundle.putString("phone",phone.getText().toString().trim());
            bundle.putString("birthday",birthday.trim());
            bundle.putString("birthplace",birthplace.getText().toString().trim());
            fragment.setArguments(bundle);}

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 4/7 :");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Confirmation de numero de telephone");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
            getActivity().findViewById(R.id.step4).setBackgroundColor(getResources().getColor(R.color.blueBack));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();



        }
        if (v == back){
            Fragment fragment = new SecondStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 2/7");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Nom et Prenom");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
            getActivity().findViewById(R.id.step3).setBackgroundColor(getResources().getColor(R.color.darkGray));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }



    }

    @Override
    public boolean onBackPressed() {
        if (true) {

            return true;
        } else {
            return false;
        }
    }
}