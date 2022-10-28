package com.ulp.plantilla.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> mutableInmueble;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getMutableInmueble() {
        if(mutableInmueble == null) {
            mutableInmueble = new MutableLiveData<>();
        }
        return mutableInmueble;
    }

    public void obtenerInmueble(Bundle bInmueble) {
        Inmueble i = (Inmueble) bInmueble.getSerializable("inmueble");
        mutableInmueble.setValue(i);
    }

    public void actualizarEstado(int id) {
        String token = ApiRetrofit.obtenerToken(context);
        Call<Inmueble> estadoPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarEstado(id, token);
        estadoPromesa.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}