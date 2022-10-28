package com.ulp.plantilla.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> mutableLista;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerLista() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Inmueble>> listaPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerInmuebles(token);
        listaPromesa.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Inmueble> lista = response.body();
                    mutableLista.postValue(lista);

                } else {
                    Log.d("Salida", "Sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                Log.d("Salida", t.getMessage());
            }
        });
    }
}