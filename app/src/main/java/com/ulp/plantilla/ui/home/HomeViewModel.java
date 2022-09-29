package com.ulp.plantilla.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<LeerMapa> mutableMapa;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<LeerMapa> getMutableMapa() {
        if(mutableMapa == null) {
            mutableMapa = new MutableLiveData<>();
        }
        return mutableMapa;
    }

    public void leerMapa() {
        mutableMapa.setValue(new LeerMapa(context));
    }
}