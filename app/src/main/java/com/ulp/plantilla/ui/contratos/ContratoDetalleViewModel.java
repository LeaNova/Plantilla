package com.ulp.plantilla.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiClient;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<Contrato> mutableContrato;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getMutableContrato() {
        if(mutableContrato == null) {
            mutableContrato = new MutableLiveData<>();
        }
        return mutableContrato;
    }

    public void obtenerContrato(Bundle bContrato) {
        Inmueble i = (Inmueble) bContrato.getSerializable("inmueble2");
        Contrato c = ap.obtenerContratoVigente(i);
        mutableContrato.setValue(c);
    }
}