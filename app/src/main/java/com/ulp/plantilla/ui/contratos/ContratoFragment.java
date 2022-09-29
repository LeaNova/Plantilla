package com.ulp.plantilla.ui.contratos;

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
import com.ulp.plantilla.databinding.FragmentContratoBinding;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.ui.inmuebles.InmuebleAdapter;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {
    private FragmentContratoBinding binding;
    private ContratoViewModel cvm;
    private RecyclerView rv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cvm = new ViewModelProvider(this).get(ContratoViewModel.class);

        inicializarVista(root);
        cvm.getMutableList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                ContratoAdapter ca = new ContratoAdapter(getContext(), getLayoutInflater(), inmuebles);
                rv.setAdapter(ca);
            }
        });
        cvm.obtenerLista();

        return root;
    }

    public void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvContrato);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }
}