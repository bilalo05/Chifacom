package serive.algeria.chifacom.register;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
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


public class SecurityStepFragment extends Fragment  implements View.OnClickListener, IonBackPressed {
    private Bundle bundle;
    EditText username , password ;
    AppCompatButton nextStep ;
    ImageView back;

    public SecurityStepFragment() {
        // Required empty public constructor
    }


    public static SecurityStepFragment newInstance(String param1, String param2) {
        SecurityStepFragment fragment = new SecurityStepFragment();


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
        View view= inflater.inflate(R.layout.fragment_security_step, container, false);
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

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

      back = view.findViewById(R.id.backtoforth);
        back.setOnClickListener(this);

        nextStep = view.findViewById(R.id.nextStepSecurity);
        nextStep.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View v) {
   if (v == nextStep ){
       bundle=this.getArguments();
       Fragment fragment = new SixthStepFragment();
       if (bundle != null) {
           bundle.putString("username", username.getText().toString().trim());
           bundle.putString("password", password.getText().toString().trim());
       }

       fragment.setArguments(bundle);
       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 6/7 :");
       TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Specialite et Mail electronique");
       fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
       getActivity().findViewById(R.id.step6).setBackgroundColor(getResources().getColor(R.color.blueBack));
       fragmentTransaction.replace(R.id.fragment_container, fragment);
       fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
   }

        if (v == back){
            Fragment fragment = new ForthStepFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TextView txt =  getActivity().findViewById(R.id.step); txt.setText("Etap 4/7");
            TextView txt2 = getActivity().findViewById(R.id.stepLabel); txt2.setText("Confirmation du Numero de telephone");
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
            getActivity().findViewById(R.id.step5).setBackgroundColor(getResources().getColor(R.color.darkGray));
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