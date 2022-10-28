package com.ulp.plantilla.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Pago;
import com.ulp.plantilla.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Pago>> mutableLista;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerPagos(Bundle bContrato) {
        String token = ApiRetrofit.obtenerToken(context);
        Contrato contrato = (Contrato) bContrato.getSerializable("contrato");
        int id = contrato.getIdContrato();

        Call<ArrayList<Pago>> pagosPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerPagos(id, token);
        pagosPromesa.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Pago> lista = response.body();
                    mutableLista.postValue(lista);
                } else {
                    Toast.makeText(context, "Sin respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Log.d("Salida", t.getMessage());
            }
        });
    }
}