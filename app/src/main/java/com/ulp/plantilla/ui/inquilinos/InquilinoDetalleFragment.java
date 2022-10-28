package com.ulp.plantilla.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {
    private InquilinoDetalleViewModel idViewM;
    private TextView tvCodigoInquilino, tvInquilinoNombre, tvInquilinoApellido, tvInquilinoDNI, tvInquilinoMail, tvInquilinoTelefono;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquilino_detalle, container, false);

        idViewM = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        idViewM.getMutableInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvCodigoInquilino.setText(inquilino.getIdInquilino()+"");
                tvInquilinoNombre.setText(inquilino.getNombre());
                tvInquilinoApellido.setText(inquilino.getApellido());
                tvInquilinoDNI.setText(inquilino.getDNI());
                tvInquilinoMail.setText(inquilino.getEmail());
                tvInquilinoTelefono.setText(inquilino.getTelefono());
            }
        });
        idViewM.obtenerInquilino(getArguments());

        inicializarVista(view);

        return view;
    }

    private void inicializarVista(View view) {
        tvCodigoInquilino = view.findViewById(R.id.tvCodigoInquilino);
        tvInquilinoNombre = view.findViewById(R.id.tvInquilinoNombre);
        tvInquilinoApellido = view.findViewById(R.id.tvInquilinoApellido);
        tvInquilinoDNI = view.findViewById(R.id.tvInquilinoDNI);
        tvInquilinoMail = view.findViewById(R.id.tvInquilinoMail);
        tvInquilinoTelefono = view.findViewById(R.id.tvInquilinoTelefono);
    }
}