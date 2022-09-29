package com.ulp.plantilla.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentInmuebleBinding;
import com.ulp.plantilla.databinding.FragmentInquilinoBinding;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.ui.inmuebles.InmuebleAdapter;

import java.util.ArrayList;

public class InquilinoFragment extends Fragment {
    private FragmentInquilinoBinding binding;
    private InquilinoViewModel ivm;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ivm = new ViewModelProvider(this).get(InquilinoViewModel.class);

        inicializarVista(root);
        ivm.getMutableList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                InquilinoAdapter ia = new InquilinoAdapter(getContext(), getLayoutInflater(), inmuebles);
                rv.setAdapter(ia);
            }
        });
        ivm.obtenerLista();

        return root;
    }

    public void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvInquilino);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }
}