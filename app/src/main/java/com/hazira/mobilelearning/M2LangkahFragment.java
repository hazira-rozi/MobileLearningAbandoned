package com.hazira.mobilelearning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class M2LangkahFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ((M2Activity) getActivity()).setActionBarTitle(" Materi 2 - Langkah Kerja");
        return inflater.inflate(R.layout.fragment_materi2_langkah, container, false);
    }
}
