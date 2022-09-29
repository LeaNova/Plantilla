package com.ulp.plantilla.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentPerfilBinding;
import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.request.ApiClient;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel pvm;
    private EditText etId, etDNI, etNombre, etApellido, etMail, etPassPerfil, etTelefono;
    private int avatar;
    private Button btBoton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicializarVista(root);

        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);
        pvm.getMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                etId.setText(p.getId()+"");
                etDNI.setText(p.getDni()+"");
                etNombre.setText(p.getNombre());
                etApellido.setText(p.getApellido());
                etMail.setText(p.getEmail());
                etPassPerfil.setText(p.getContraseña());
                etTelefono.setText(p.getTelefono());
                avatar = p.getAvatar();
            }
        });
        pvm.obtenerPropietario();
        pvm.getMutableEdicion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etMail.setEnabled(aBoolean);
                etPassPerfil.setEnabled(aBoolean);
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
        etId = view.findViewById(R.id.etId);
        etDNI = view.findViewById(R.id.etDNI);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etMail = view.findViewById(R.id.etMail);
        etPassPerfil = view.findViewById(R.id.etPassPerfil);
        etTelefono = view.findViewById(R.id.etTelefono);
        btBoton = view.findViewById(R.id.btBoton);
        btBoton.setText("Editar");

        btBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String boton = btBoton.getText().toString();

                //Armar Propietario;
                int id = Integer.parseInt(etId.getText().toString());
                long dni = Long.parseLong(etDNI.getText().toString());
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String mail = etMail.getText().toString();
                String pass = etPassPerfil.getText().toString();
                String telefono = etTelefono.getText().toString();

                Propietario p = new Propietario(id, dni, nombre, apellido, mail, pass, telefono, avatar);
                pvm.actualizar(boton, p);
            }
        });
    }
}