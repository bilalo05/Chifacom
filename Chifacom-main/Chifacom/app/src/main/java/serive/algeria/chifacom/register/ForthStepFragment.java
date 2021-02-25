package serive.algeria.chifacom.register;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import serive.algeria.chifacom.IonBackPressed;
import serive.algeria.chifacom.R;


public class ForthStepFragment extends Fragment implements View.OnClickListener, IonBackPressed, TextWatcher {

    AppCompatButton nextStep ;
    TextView  phone ;
    private Bundle bundle;
    AppCompatButton back;
    EditText c1,c2,c3,c4,c5,c6;

    public ForthStepFragment() {
        // Required empty public constructor
    }


    public static ForthStepFragment newInstance() {
        ForthStepFragment fragment = new ForthStepFragment();

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
        View view =  inflater.inflate(R.layout.fragment_forth_step, container, false);
        bundle=this.getArguments();
        if (bundle != null) {
            final String firstname = bundle.getString("fname", "");
            final String firstnameAR = bundle.getString("fnameAR", "");
            final String lastname = bundle.getString("lname", "");
            final String lastnameAR = bundle.getString("lnameAR", "");
            final String birthday = bundle.getString("birthday","");
            final String birthplace = bundle.getString("birthplace","");
            final String phone = bundle.getString("phone","");
            Log.d("register", firstname + " - " + firstnameAR + " - " + lastname + " - " + lastnameAR
                    + " - " +birthday+ " - " +birthplace+ " - " +phone);
        }

        back = view.findViewById(R.id.changeNumber);
        back.setOnClickListener(this);

        phone = view.findViewById(R.id.phoneNumber);
        if (bundle != null)
        phone.setText(bundle.getString("phone",""));

        //email.setText(bundle.getString("fname","")+", "+bundle.getString("birthplace",""));

        nextStep = view.findViewById(R.id.nextStep4);
        nextStep.setOnClickListener(this);

        //code
        c1 = view.findViewById(R.id.cd1);
        c2 = view.findViewById(R.id.cd2);
        c3 = view.findViewById(R.id.cd3);
        c4 = view.findViewById(R.id.cd4);
        c5 = view.findViewById(R.id.cd5);
        c6 = view.findViewById(R.id.cd6);

        c1.addTextChangedListener(this);
        c2.addTextChangedListener(this);
        c3.addTextChangedListener(this);
        c4.addTextChangedListener(this);
        c5.addTextChangedListener(this);
        c6.addTextChangedListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v==nextStep ){
            bundle=this.getArguments();
            Fragment fragment = new SecurityStepFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 5/7 :");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Nom d'utilisateur et mot de passe");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
            getActivity().findViewById(R.id.step5).setBackgroundColor(getResources().getColor(R.color.blueBack));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        if (v == back){
            back.setTextColor(Color.WHITE);
            back.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_signin_btn));
            Fragment fragment = new ThirdStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 3/7");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Informations Personelles");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
            getActivity().findViewById(R.id.step4).setBackgroundColor(getResources().getColor(R.color.darkGray));
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    int i1=0,i2=0,i3=0,i4=0,i5=0,i6=0;
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (c1.getText().toString().trim().length() == 1)
        {
            c2.requestFocus();
            i1 = 1;
        }
        if (c2.getText().toString().trim().length() == 1)
        {
            c2.clearFocus();
            c3.requestFocus();
            i2 = 1;
        }

        if (c3.getText().toString().trim().length() == 1)
        {
            c3.clearFocus();
            c4.requestFocus();
            i3 = 1;
        }
        if (c4.getText().toString().trim().length() == 1)
        {
            c4.clearFocus();
            c5.requestFocus();
            i4=1;
        }
        if (c5.getText().toString().trim().length() == 1)
        {
            System.out.println("c5 111 ==== "+c5.getText().toString().trim().length());
            c6.requestFocus();

            c5.clearFocus();
            System.out.println("c5  222==== "+c5.getText().toString().trim().length());
            c6.requestFocus();
            System.out.println("c5  333==== "+c5.getText().toString().trim().length());
            i5 =1;
            System.out.println("c5  444==== "+c5.getText().toString().trim().length());
        }


        if (c6.getText().toString().trim().length() != 0 ){
            i6 = 1;
        }

        if (c6.getText().toString().trim().length() == 0 && i6 ==1 ){

            System.out.println("c6  V2 ==== "+c6.getText().toString().trim().length());
            c5.requestFocus();
            c6.clearFocus();
            i6=0;

        }


        if (c5.getText().toString().trim().length() == 0 && i5 ==1 ){
            System.out.println("c5 V2 ==== "+c5.getText().toString().trim().length());
            c4.requestFocus();
            c5.clearFocus();
            i5 = 0;

        }
        if (c4.getText().toString().trim().length() == 0 && i4 == 1){
            c3.requestFocus();
            c4.clearFocus();
            i4 = 0;
        }
        if (c3.getText().toString().trim().length() == 0 && i3 == 1){
            c2.requestFocus();
            c3.clearFocus();
            i3 = 0;
        }
        if (c2.getText().toString().trim().length() == 0 && i2 == 1){
            c1.requestFocus();
            c2.clearFocus();
            i2=0;
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
   


    }
}