package com.ulp.plantilla.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.request.ApiClient;

import java.util.Locale;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> mutablePropietario;
    private MutableLiveData<Boolean> mutableEdicion;
    private MutableLiveData<String> mutableAccion;
    private ApiClient ap = ApiClient.getApi();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getMutablePropietario() {
        if(mutablePropietario == null) {
            mutablePropietario = new MutableLiveData<>();
        }
        return mutablePropietario;
    }

    public LiveData<Boolean> getMutableEdicion() {
        if(mutableEdicion == null) {
            mutableEdicion = new MutableLiveData<>();
        }
        return mutableEdicion;
    }

    public LiveData<String> getMutableAccion() {
        if(mutableAccion == null) {
            mutableAccion = new MutableLiveData<>();
        }
        return mutableAccion;
    }

    public void obtenerPropietario() {
        Propietario p = ap.obtenerUsuarioActual();
        mutablePropietario.setValue(p);
    }

    public void actualizar(String action, Propietario p) {
        String caso = action.toLowerCase();

        if(caso.equals("editar")) {
            mutableEdicion.setValue(true);
            mutableAccion.setValue("Actualizar");
        } else {
            ap.actualizarPerfil(p);
            mutableEdicion.setValue(false);
            mutableAccion.setValue("Editar");
        }
    }
}