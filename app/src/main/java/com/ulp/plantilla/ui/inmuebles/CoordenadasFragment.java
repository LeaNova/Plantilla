package com.ulp.plantilla.ui.inmuebles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.SupportMapFragment;
import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentCoordenadasBinding;

public class CoordenadasFragment extends Fragment {
    private FragmentCoordenadasBinding binding;
    private AgregarInmuebleViewModel aivm;
    private Button btAgregarC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCoordenadasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        aivm = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        aivm.getMutableCoordenadas().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        inicializarActivity(root);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
            .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(new MarcarMapa(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void inicializarActivity(View view) {
        this.btAgregarC = view.findViewById(R.id.btAgregarC);
        this.btAgregarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aivm.setCoordenadas();
                Navigation.findNavController(view).popBackStack();
            }
        });
    }
}