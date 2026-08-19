package com.example.belajarpostman_11pplg3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class UserMenuFragment extends Fragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_menu_fragment,
                container,
                false
        );
        return view;
    }
}
