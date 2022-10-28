package com.ulp.plantilla.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> mutablePropietario;
    private MutableLiveData<Boolean> mutableEdicion;
    private MutableLiveData<String> mutableAccion;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
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
        String token = ApiRetrofit.obtenerToken(context);

        Call<Propietario> propietarioPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(token);
        propietarioPromesa.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    Propietario p = response.body();
                    mutablePropietario.postValue(p);

                } else {
                    Log.d("Salida", "Sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("Salida", t.getMessage());
            }
        });
    }

    public void actualizar(String action, String nombre, String apellido, String dni, String telefono, String mail) {
        String caso = action.toLowerCase();

        if(caso.equals("editar")) {
            mutableEdicion.setValue(true);
            mutableAccion.setValue("Actualizar");
        } else {
            String token = ApiRetrofit.obtenerToken(context);
            Call<Propietario> propietarioPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarPerfil(nombre, apellido, dni, telefono, mail, token);
            propietarioPromesa.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()) {
                        mutableEdicion.setValue(false);
                        mutableAccion.setValue("Editar");
                        Toast.makeText(context, "Informacion actualizada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error en actualizar perfil", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {

                }
            });
        }
    }
}