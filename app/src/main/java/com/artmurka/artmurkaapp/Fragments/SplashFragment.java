package com.artmurka.artmurkaapp.Fragments;

import android.app.Fragment;
import android.icu.util.TimeUnit;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.R;


public class SplashFragment extends Fragment {
    public SplashFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SplashTask st = new SplashTask();
        st.execute();
    }

    private class SplashTask extends AsyncTask<Void, Void, Void> {

        //showing SPLASH SCREEN for 1.5 seconds

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //slep 1.5 seconds
                Thread.sleep(1500);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (getActivity()!=null){
                getActivity().getFragmentManager().popBackStack();
            }
            return null;
        }
    }
}
