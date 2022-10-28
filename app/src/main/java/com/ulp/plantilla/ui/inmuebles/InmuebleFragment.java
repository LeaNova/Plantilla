package com.ulp.plantilla.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentInmuebleBinding;
import com.ulp.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class InmuebleFragment extends Fragment {
    private FragmentInmuebleBinding binding;
    private InmuebleViewModel ivm;
    private RecyclerView rv;
    private Button btAgregarInmueble;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ivm = new ViewModelProvider(this).get(InmuebleViewModel.class);

        inicializarVista(root);
        ivm.getMutableList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                InmuebleAdapter ia = new InmuebleAdapter(getContext(), getLayoutInflater(), inmuebles);
                rv.setAdapter(ia);
            }
        });
        ivm.obtenerLista();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void inicializarVista(View view) {
        this.rv = view.findViewById(R.id.rvInmueble);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        this.rv.setLayoutManager(gridLayoutManager);

        this.btAgregarInmueble = view.findViewById(R.id.btAgregarInmueble);
        this.btAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.agregarInmuebleFragment);
            }
        });
    }
}