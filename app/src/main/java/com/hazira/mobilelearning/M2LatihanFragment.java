package com.hazira.mobilelearning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class M2LatihanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ((M2Activity) getActivity()).setActionBarTitle(" Materi 2 - Latihan");
        return inflater.inflate(R.layout.fragment_materi2_latihan, container, false);
    }
}
