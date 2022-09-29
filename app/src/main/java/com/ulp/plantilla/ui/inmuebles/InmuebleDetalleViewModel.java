package com.ulp.plantilla.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.api.Api;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiClient;

import java.util.ArrayList;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<Inmueble> mutableInmueble;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
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

    public void actualizar(Inmueble i) {
        ap.actualizarInmueble(i);
    }
}