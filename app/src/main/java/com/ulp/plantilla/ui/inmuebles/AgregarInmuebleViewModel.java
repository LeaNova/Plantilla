package com.ulp.plantilla.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private Context context;
    //private MutableLiveData<Inmueble> mutableInmueble;
    private MutableLiveData<String> mutableOk;
    private MutableLiveData<String> mutableCoordenadas;
    private MutableLiveData<Bitmap> mutableFoto;

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    /*
    public LiveData<Inmueble> getMutableInmueble() {
        if(mutableInmueble == null) {
            mutableInmueble = new MutableLiveData<>();
        }
        return mutableInmueble;
    }
    */

    public LiveData<String> getMutableOk() {
        if(mutableOk == null) {
            mutableOk = new MutableLiveData<>();
        }
        return mutableOk;
    }

    public LiveData<String> getMutableCoordenadas() {
        if(mutableCoordenadas == null) {
            mutableCoordenadas = new MutableLiveData<>();
        }
        return mutableCoordenadas;
    }

    public LiveData<Bitmap> getMutableFoto() {
        if(mutableFoto == null) {
            mutableFoto = new MutableLiveData<>();
        }
        return mutableFoto;
    }

    /*
    public void obtenerInmueble(Bundle bInmueble) {
        if(bInmueble != null) {
            Inmueble i = (Inmueble) bInmueble.getSerializable("inmueble");
            mutableInmueble.setValue(i);
        }
    }
    */

    public void setCoordenadas() {
        SharedPreferences sharedP = context.getSharedPreferences("configuracion", 0);
        String coordenadas = sharedP.getString("coorde", "");
        Log.d("salida", coordenadas);
        if(coordenadas != null) {
            mutableCoordenadas.setValue(coordenadas);
        }
    }

    public void agregarInmueble(String direccion, int uso, int tipo, String strAmbientes, String coordenadas, String strPrecio, String strFoto) {
        int cantAmbientes = 0;
        double precio = 0;

        if(!strAmbientes.equals("")) {
            cantAmbientes = Integer.parseInt(strAmbientes);
        }

        if(!strPrecio.equals("")) {
            precio = Double.parseDouble(strPrecio);
        } else if (strPrecio.contains(".")) {
            precio = Double.parseDouble(strPrecio);
        }

        Log.d("salida", precio+"");

        String token = ApiRetrofit.obtenerToken(context);
        Call<Inmueble> inmueblePromesa = ApiRetrofit.getServiceInmobiliaria().agregarInmueble(direccion, uso, tipo, cantAmbientes, coordenadas, precio, strFoto, token);
        inmueblePromesa.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Inmueble agregado", Toast.LENGTH_SHORT).show();
                    mutableOk.postValue("");
                } else {
                    Toast.makeText(context, "Error en agregar inmueble", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("Salida", t.getMessage());
            }
        });
    }

    /*
    public void actualizarInmueble(int id, String direccion, int uso, int tipo, String strAmbientes, String coordenadas, String strPrecio, String strfoto) {
        int cantAmbientes = 0;
        double precio = 0;

        if(!strAmbientes.equals("")) {
            cantAmbientes = Integer.parseInt(strAmbientes);
        }

        if(!strPrecio.equals("")) {
            precio = Double.parseDouble(strPrecio);
        } else if (strPrecio.contains(".")) {
            precio = Double.parseDouble(strPrecio);
        }

        Log.d("salida", precio+"");

        String token = ApiRetrofit.obtenerToken(context);
        Call<Inmueble> actualizarPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarInformacion(id, direccion, uso, tipo, cantAmbientes, coordenadas, precio, strfoto, token);
        actualizarPromesa.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Inmueble actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar inmueble", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("Salida", t.getMessage());
            }
        });
    }
    */

    public void requestCamera (int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE) {
        Log.d("salida", requestCode + "");
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");

            mutableFoto.setValue(imgBitmap);
        }
    }
}