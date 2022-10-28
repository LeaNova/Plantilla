package com.ulp.plantilla.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;

public class ContratoDetalleViewModel extends AndroidViewModel {
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
        Contrato c = (Contrato) bContrato.getSerializable("contrato");
        mutableContrato.setValue(c);
    }
}