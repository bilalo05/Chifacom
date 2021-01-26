package serive.algeria.chifacom.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import serive.algeria.chifacom.IonBackPressed;
import serive.algeria.chifacom.R;


public class FifthStepFragment extends Fragment implements IonBackPressed {



    public FifthStepFragment() {
        // Required empty public constructor
    }


    public static FifthStepFragment newInstance() {
        FifthStepFragment fragment = new FifthStepFragment();

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
        View view =  inflater.inflate(R.layout.fragment_fifth_step, container, false);


        return view;
    }

    @Override
    public boolean onBackPressed() {
        if (true) {
            //action not popBackStack
            Fragment fragment = new ForthStepFragment();
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
}