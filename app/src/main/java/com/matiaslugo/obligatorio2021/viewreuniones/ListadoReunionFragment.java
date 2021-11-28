package com.matiaslugo.obligatorio2021.viewreuniones;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matiaslugo.obligatorio2021.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListadoReunionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoReunionFragment extends Fragment {


    public ListadoReunionFragment() {
        // Required empty public constructor
    }

    public static ListadoReunionFragment newInstance(String param1, String param2) {
        ListadoReunionFragment fragment = new ListadoReunionFragment();
        Bundle args = new Bundle();
        args.putString("", param1);
        args.putString("", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_reunion, container, false);
    }
}