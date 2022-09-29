package com.ulp.plantilla.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Contrato;

public class ContratoDetalleFragment extends Fragment {
    private ContratoDetalleViewModel cdViewM;
    private TextView tvContratoCodigo, tvContratoFechaI, tvContratoFechaF, tvContratoAlquiler, tvContratoInquilino, tvContratoInmueble;
    private Button btPagos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contrato_detalle, container, false);

        cdViewM = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        cdViewM.getMutableContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                tvContratoCodigo.setText(contrato.getIdContrato()+"");
                tvContratoFechaI.setText(contrato.getFechaInicio());
                tvContratoFechaF.setText(contrato.getFechaFin());
                tvContratoAlquiler.setText("$ " + contrato.getMontoAlquiler());
                tvContratoInquilino.setText(contrato.getInquilino().getNombre() + ", " + contrato.getInquilino().getNombre());
                tvContratoInmueble.setText(contrato.getInmueble().getDireccion());
                btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagoFragment, bundle);
                    }
                });
            }
        });
        cdViewM.obtenerContrato(getArguments());

        inicializarVista(view);

        return view;
    }

    public void inicializarVista(View view) {
        tvContratoCodigo = view.findViewById(R.id.tvContratoCodigo);
        tvContratoFechaI = view.findViewById(R.id.tvContratoFechaI);
        tvContratoFechaF = view.findViewById(R.id.tvContratoFechaF);
        tvContratoAlquiler = view.findViewById(R.id.tvContratoAlquiler);
        tvContratoInquilino = view.findViewById(R.id.tvContratoInquilino);
        tvContratoInmueble = view.findViewById(R.id.tvContratoInmueble);
        btPagos = view.findViewById(R.id.btPagos);
    }
}