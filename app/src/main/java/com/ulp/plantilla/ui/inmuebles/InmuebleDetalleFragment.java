package com.ulp.plantilla.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiRetrofit;

public class InmuebleDetalleFragment extends Fragment {
    private InmuebleDetalleViewModel mViewModel;
    private TextView tvCod_inmueble, tvAmbientes, tvDireccion, tvPrecio_inmueble, tvUso, tvTipo, tvCoordenadas;
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
                tvAmbientes.setText(inmueble.getCantAmbientes()+"");
                tvDireccion.setText(inmueble.getDireccion());
                tvPrecio_inmueble.setText("$ " + inmueble.getPrecio());
                tvCoordenadas.setText(inmueble.getCoordenadas());

                int uso = inmueble.getUso() -1;
                tvUso.setText(Inmueble.EnUsos.values()[uso].toString());
                cbDisponible.setChecked(inmueble.isDisponible());
                cbDisponible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        mViewModel.actualizarEstado(inmueble.getIdInmueble());
                    }
                });

                int tipo = inmueble.getTipo() -1;
                tvTipo.setText(Inmueble.EnTipos.values()[tipo].toString());
                Glide.with(getContext())
                        .load(ApiRetrofit.obtenerIP() + inmueble.getFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivDetalle_inmueble);
            }
        });
        mViewModel.obtenerInmueble(getArguments());

        inicializarVista(view);

        return view;
    }

    private void inicializarVista(View view) {
        this.tvCod_inmueble = view.findViewById(R.id.tvCod_inmueble);
        this.cbDisponible = view.findViewById(R.id.cbDisponible);
        this.tvAmbientes = view.findViewById(R.id.tvAmbientes);
        this.tvDireccion = view.findViewById(R.id.tvDireccion);
        this.tvUso = view.findViewById(R.id.tvUso);
        this.tvTipo = view.findViewById(R.id.tvTipo);
        this.tvCoordenadas = view.findViewById(R.id.tvCoordemadas);
        this.tvPrecio_inmueble = view.findViewById(R.id.tvPrecio_inmueble);
        this.ivDetalle_inmueble = view.findViewById(R.id.ivDetalle_inmueble);
    }
}