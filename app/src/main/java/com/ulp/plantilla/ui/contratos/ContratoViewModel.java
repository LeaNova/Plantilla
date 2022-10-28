package com.ulp.plantilla.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Contrato>> mutableContratos;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getMutableContratos() {
        if(mutableContratos == null) {
            mutableContratos = new MutableLiveData<>();
        }
        return mutableContratos;
    }

    public void obtenerListaContratos() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Contrato>> listaPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerContratos(token);
        listaPromesa.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Contrato> lista = response.body();
                    mutableContratos.postValue(lista);
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