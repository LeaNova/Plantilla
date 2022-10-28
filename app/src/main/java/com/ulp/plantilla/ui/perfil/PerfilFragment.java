package com.ulp.plantilla.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentPerfilBinding;
import com.ulp.plantilla.modelo.Propietario;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel pvm;
    private ImageView ivUserD;
    private EditText etNombre, etApellido, etDNI, etMail, etTelefono;
    private Button btBoton, btCambiarPass;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicializarVista(root);

        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);
        pvm.getMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                etNombre.setText(p.getNombre());
                etApellido.setText(p.getApellido());
                etDNI.setText(p.getDni()+"");
                etMail.setText(p.getEmail());
                etTelefono.setText(p.getTelefono());

                Glide.with(getContext())
                        .load("http://192.168.0.17:5000/" + p.getFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivUserD);
            }
        });
        pvm.obtenerPropietario();
        pvm.getMutableEdicion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etMail.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
            }
        });
        pvm.getMutableAccion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btBoton.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void inicializarVista(View view) {
        this.ivUserD = view.findViewById(R.id.ivUserD);
        this.etNombre = view.findViewById(R.id.etNombre);
        this.etApellido = view.findViewById(R.id.etApellido);
        this.etDNI = view.findViewById(R.id.etDNI);
        this.etMail = view.findViewById(R.id.etMail);
        this.etTelefono = view.findViewById(R.id.etTelefono);

        this.btBoton = view.findViewById(R.id.btBoton);
        this.btBoton.setText("Editar");
        this.btBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boton = btBoton.getText().toString();

                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String dni = etDNI.getText().toString();
                String telefono = etTelefono.getText().toString();
                String mail = etMail.getText().toString();

                pvm.actualizar(boton, nombre, apellido, dni, telefono, mail);
            }
        });

        this.btCambiarPass = view.findViewById(R.id.btCambiarPass);
        this.btCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cambiarFragment);
            }
        });
    }
}