package com.ulp.plantilla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel lvm;
    private EditText etUser, etPass;
    private Button btEntrar;
    private TextView tvAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        inicializarAcitivity();
        pedirPermisos();
        lvm.shake();

        lvm.getMutableAccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAccess.setText(s);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        etPass.setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        lvm.stopShake();
    }

    private void inicializarAcitivity() {
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

        btEntrar = findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
                lvm.iniciarSesion(user, pass);
            }
        });

        tvAccess = findViewById(R.id.tvAccess);
    }

    private void pedirPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.CALL_PHONE}, 1000);
        }
    }
}