package serive.algeria.chifacom.register;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import serive.algeria.chifacom.IonBackPressed;
import serive.algeria.chifacom.R;


public class SixthStepFragment extends Fragment implements View.OnClickListener, IonBackPressed {

    AppCompatButton nextStep ;
    private Bundle bundle;
    TextView firstletter;
    ImageView back;

    EditText speciality,email;

    public SixthStepFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SixthStepFragment newInstance() {
        SixthStepFragment fragment = new SixthStepFragment();
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
        View view =  inflater.inflate(R.layout.fragment_sixth_step, container, false);
        bundle = this.getArguments();
        if (bundle != null) {
            final String firstname = bundle.getString("fname", "");
            final String firstnameAR = bundle.getString("fnameAR", "");
            final String lastname = bundle.getString("lname", "");
            final String lastnameAR = bundle.getString("lnameAR", "");
            final String birthday = bundle.getString("birthday","");
            final String birthplace = bundle.getString("birthplace","");
            final String phone = bundle.getString("phone","");
            final String username = bundle.getString("username","");
            final String password =bundle.getString("password","");
            Log.d("register", firstname + " - " + firstnameAR + " - " + lastname + " - " + lastnameAR
                    + " - " +birthday+ " - " +birthplace+ " - " +phone
                    + " - " +username+ " - " +password);
        }

        speciality = view.findViewById(R.id.speciality);
        email = view.findViewById(R.id.email);


//        textView.setText(bundle.getString("fname","")+", "+bundle.getString("birthplace",""));

        back = view.findViewById(R.id.backtosecurity);
        back.setOnClickListener(this);

        nextStep = view.findViewById(R.id.nextStep6);
        nextStep.setOnClickListener(this);


        firstletter = view.findViewById(R.id.firstLetter);

        if (bundle != null && bundle.getString("fname", "").length() > 0) {
            char lt = bundle.getString("fname", "").charAt(0);
            firstletter.setText(Character.toString(lt));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v==nextStep){
            Fragment fragment = new FifthStepFragment();
            if (bundle != null) {
                bundle.putString("email", email.getText().toString().trim());
                bundle.putString("speciality", speciality.getText().toString().trim());
            }
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 7/7 :");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Address et Localisation ");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
            getActivity().findViewById(R.id.step7).setBackgroundColor(getResources().getColor(R.color.blueBack));
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        if (v == back){
            Fragment fragment = new SecurityStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 5/7");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Nom d'utilisateur et Mot de passe");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
            getActivity().findViewById(R.id.step6).setBackgroundColor(getResources().getColor(R.color.darkGray));
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