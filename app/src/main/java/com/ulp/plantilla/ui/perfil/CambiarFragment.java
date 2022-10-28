package com.ulp.plantilla.ui.perfil;

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
import android.widget.EditText;

import com.ulp.plantilla.R;
import com.ulp.plantilla.databinding.FragmentCambiarBinding;

public class CambiarFragment extends Fragment {
    private FragmentCambiarBinding binding;
    private CambiarViewModel cvm;
    private EditText etPOld, etPNew, etPNewRep;
    private Button btCambiar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCambiarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicializarVista(root);

        cvm = new ViewModelProvider(this).get(CambiarViewModel.class);
        cvm.getMutableOk().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etPOld.setText(s);
                etPNew.setText(s);
                etPNewRep.setText(s);
                Navigation.findNavController(root).popBackStack();
            }
        });

        return root;
    }

    private void inicializarVista(View view) {
        this.etPOld = view.findViewById(R.id.etPOld);
        this.etPNew = view.findViewById(R.id.etPnew);
        this.etPNewRep = view.findViewById(R.id.etPNewRep);

        this.btCambiar = view.findViewById(R.id.btCambiar);
        this.btCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = etPOld.getText().toString();
                String newPass = etPNew.getText().toString();
                String newPassR = etPNewRep.getText().toString();

                cvm.cambiarContraseña(old, newPass, newPassR);
            }
        });
    }
}