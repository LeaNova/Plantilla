package com.ulp.plantilla.ui.pagos;

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
import com.ulp.plantilla.databinding.FragmentPagoBinding;
import com.ulp.plantilla.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {
    private FragmentPagoBinding binding;
    private PagoViewModel pvm;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pvm = new ViewModelProvider(this).get(PagoViewModel.class);

        inicializarVista(root);
        pvm.getMutableList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                PagoAdapter pa = new PagoAdapter(getContext(), getLayoutInflater(), pagos);
                rv.setAdapter(pa);
            }
        });
        pvm.obtenerPagos(getArguments());

        return root;
    }

    public void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvPago);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }
}