package com.ulp.plantilla.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Inmueble;

public class InmuebleDetalleFragment extends Fragment {
    private InmuebleDetalleViewModel mViewModel;
    private TextView tvCod_inmueble, tvAmbientes, tvDireccion, tvPrecio_inmueble, tvUso, tvTipo;
    private CheckBox cbDisponible;
    private ImageView ivDetalle_inmueble;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmueble_detalle, container, false);

        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        mViewModel.getMutableInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvCod_inmueble.setText(inmueble.getIdInmueble()+"");
                tvAmbientes.setText(inmueble.getAmbientes()+"");
                tvDireccion.setText(inmueble.getDireccion());
                tvPrecio_inmueble.setText("$ " + inmueble.getPrecio());
                tvUso.setText(inmueble.getUso());
                cbDisponible.setChecked(inmueble.isEstado());
                cbDisponible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        inmueble.setEstado(cbDisponible.isChecked());
                        mViewModel.actualizar(inmueble);
                    }
                });
                tvTipo.setText(inmueble.getTipo());
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivDetalle_inmueble);
            }
        });
        mViewModel.obtenerInmueble(getArguments());

        inicializarVista(view);

        return view;
    }

    private void inicializarVista(View view) {
        tvCod_inmueble = view.findViewById(R.id.tvCod_inmueble);
        tvAmbientes = view.findViewById(R.id.tvAmbientes);
        tvDireccion = view.findViewById(R.id.tvDireccion);
        tvPrecio_inmueble = view.findViewById(R.id.tvPrecio_inmueble);
        tvUso = view.findViewById(R.id.tvUso);
        cbDisponible = view.findViewById(R.id.cbDisponible);
        tvTipo = view.findViewById(R.id.tvTipo);
        ivDetalle_inmueble = view.findViewById(R.id.ivDetalle_inmueble);
    }
}