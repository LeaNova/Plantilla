package com.ulp.plantilla.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InmuebleViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<ArrayList<Inmueble>> mutableLista;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Inmueble>> getMutableList() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    public void obtenerLista() {
        ArrayList<Inmueble> list = ap.obtnerPropiedades();
        mutableLista.setValue(list);
    }
}