package com.ulp.plantilla.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Contrato>> mutableLista;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerListaContratos() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Contrato>> listaPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerContratos(token);
        listaPromesa.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Contrato> lista = response.body();
                    mutableLista.postValue(lista);
                    //obtenerInmuebles(lista);
                    //listaContratos = response.body();
                } else {
                    Log.d("Salida", "Sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {
                Log.d("Salida", t.toString());
            }
        });
    }
}