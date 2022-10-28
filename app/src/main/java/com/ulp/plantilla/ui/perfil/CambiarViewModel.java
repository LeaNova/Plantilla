package com.ulp.plantilla.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mutableOk;
    
    public CambiarViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getMutableOk() {
        if(mutableOk == null) {
            mutableOk = new MutableLiveData<>();
        }
        return mutableOk;
    }

    public void cambiarContraseña(String old, String newPass, String newPassR) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Propietario> okPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarContraseña(old, newPass, newPassR, token);
        okPromesa.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                    mutableOk.postValue("");
                } else {
                    Toast.makeText(context, "Error en cambiar contraseña", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}