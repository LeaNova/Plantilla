package com.ulp.plantilla.ui.pagos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Pago;
import com.ulp.plantilla.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<ArrayList<Pago>> mutableLista;

    public PagoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Pago>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerLista(Bundle bContrato) {
        Contrato c = (Contrato)  bContrato.getSerializable("contrato");
        ArrayList<Pago> list = ap.obtenerPagos(c);
        mutableLista.setValue(list);
    }
}