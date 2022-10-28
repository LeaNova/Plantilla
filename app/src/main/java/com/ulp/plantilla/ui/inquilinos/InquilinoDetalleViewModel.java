package com.ulp.plantilla.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Inquilino;

public class InquilinoDetalleViewModel extends AndroidViewModel {
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
        Inquilino i = (Inquilino) bInquilino.getSerializable("inquilino");
        mutableInquilino.setValue(i);
    }
}