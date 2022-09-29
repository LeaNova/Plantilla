package com.ulp.plantilla.ui.contratos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<ArrayList<Inmueble>> mutableLista;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Inmueble>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerLista() {
        ArrayList<Inmueble> list = ap.obtenerPropiedadesAlquiladas();
        mutableLista.setValue(list);
    }
}