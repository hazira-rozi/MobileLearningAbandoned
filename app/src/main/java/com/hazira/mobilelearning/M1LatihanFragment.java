package com.hazira.mobilelearning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class M1LatihanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ((M1Activity) getActivity()).setActionBarTitle(" Materi 1 - Latihan");
        return inflater.inflate(R.layout.fragment_materi1_latihan, container, false);
    }
}
