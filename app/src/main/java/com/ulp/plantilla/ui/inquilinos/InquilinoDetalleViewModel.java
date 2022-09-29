package com.ulp.plantilla.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.modelo.Inquilino;
import com.ulp.plantilla.request.ApiClient;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<Inquilino> mutableInquilino;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getMutableInquilino() {
        if(mutableInquilino == null) {
            mutableInquilino = new MutableLiveData<>();
        }
        return mutableInquilino;
    }

    public void obtenerInquilino(Bundle bInquilino) {
        Inmueble i = (Inmueble) bInquilino.getSerializable("inmueble2");
        Inquilino inq = ap.obtenerInquilino(i);
        mutableInquilino.setValue(inq);
    }
}