package com.example.bookstore.view.sign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookstore.R;
import com.example.bookstore.uitl.PrefManager;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class signFragment extends Fragment {
    private View viewSign;
    private View viewProfile;

    public signFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign, container, false);
        initUI(view);
        // Inflate the layout for this fragment
        if(PrefManager.retrieveAccessToken(Objects.requireNonNull(getActivity())) !=null)
        {
            viewProfile.setVisibility(View.VISIBLE);
            viewSign.setVisibility(View.GONE);
        }
        else {
            viewProfile.setVisibility(View.GONE);
            viewSign.setVisibility(View.VISIBLE);

        }

        return view;
    }
    private void initUI(View view)
    {
        viewSign=view.findViewById(R.id.layout_sign);
        viewProfile=view.findViewById(R.id.layout_profile);
    }
}
