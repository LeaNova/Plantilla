package com.ulp.plantilla.ui.inmuebles;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentAgregarInmuebleBinding;
import com.ulp.plantilla.modelo.Inmueble;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;

public class AgregarInmuebleFragment extends Fragment {
    private FragmentAgregarInmuebleBinding binding;
    private AgregarInmuebleViewModel aivm;
    private TextView textAgregar;
    private EditText etDireccionA, etCoordenadasA, etCantAmbientesA, etPrecioA;
    private Spinner spUsosA, spTiposA;
    private ImageView ivFotoA;
    private String strFoto;
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private Button btBuscarA, btGuardarInmueble;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        aivm = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        aivm.getMutableFoto().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                ivFotoA.setImageBitmap(bitmap);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte [] b = baos.toByteArray();

                String encoded = Base64.getEncoder().encodeToString(b);
                strFoto = encoded;

                btGuardarInmueble.setEnabled(true);
            }
        });

        aivm.getMutableCoordenadas().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etCoordenadasA.setText(s);
            }
        });

        aivm.getMutableOk().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etDireccionA.setText(s);
                etCoordenadasA.setText(s);
                etCantAmbientesA.setText(s);
                etPrecioA.setText(s);
                ivFotoA.setImageResource(R.drawable.default_img);
                btGuardarInmueble.setEnabled(false);
            }
        });

        inicializarVista(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        aivm.setCoordenadas();
    }

    private void inicializarVista(View view) {
        this.spUsosA = view.findViewById(R.id.spUsosA);
        ArrayList<String> listaUsos = Inmueble.getUsos();
        ArrayAdapter<String> spinnerUsos = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaUsos);
        this.spUsosA.setAdapter(spinnerUsos);

        this.spTiposA = view.findViewById(R.id.spTiposA);
        ArrayList<String> listaTipos = Inmueble.getTipos();
        ArrayAdapter<String> spinnerTipos = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaTipos);
        this.spTiposA.setAdapter(spinnerTipos);

        this.textAgregar = view.findViewById(R.id.textAgregar);
        this.etDireccionA = view.findViewById(R.id.etDireccionA);
        this.etCoordenadasA = view.findViewById(R.id.etCoordenadasA);
        this.etCantAmbientesA = view.findViewById(R.id.etCantAmbientesA);
        this.etPrecioA = view.findViewById(R.id.etPrecioA);

        this.ivFotoA = view.findViewById(R.id.ivFotoA);
        this.ivFotoA.setImageResource(R.drawable.default_img);
        this.ivFotoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        this.btBuscarA = view.findViewById(R.id.btBuscarA);
        this.btBuscarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.coordenadasFragment);
            }
        });

        this.btGuardarInmueble = view.findViewById(R.id.btGuardarInmueble);
        this.btGuardarInmueble.setEnabled(false);
        this.btGuardarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccion = etDireccionA.getText().toString();
                int uso = spUsosA.getSelectedItemPosition() +1;
                int tipo = spTiposA.getSelectedItemPosition() +1;
                String srtAmbientes = etCantAmbientesA.getText().toString();
                String coordenadas = etCoordenadasA.getText().toString();
                String srtPrecio = etPrecioA.getText().toString();

                aivm.agregarInmueble(direccion, uso, tipo, srtAmbientes, coordenadas, srtPrecio, strFoto);
                aivm.setToEmpty();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        aivm.requestCamera(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);
    }
}