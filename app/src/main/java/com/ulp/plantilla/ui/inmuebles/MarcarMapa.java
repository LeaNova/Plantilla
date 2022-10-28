package com.ulp.plantilla.ui.inmuebles;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ulp.plantilla.modelo.Inmueble;

import java.util.ArrayList;

public class MarcarMapa implements OnMapReadyCallback {
    private GoogleMap map;
    private Context context;
    private float x;
    private float y;

    public MarcarMapa(Context context) {
        this.context = context;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                x = (float) latLng.latitude;
                y = (float) latLng.longitude;

                String coordenadas = x + "," + y;

                Log.d("Salida", coordenadas);
                Toast.makeText(context, "Clickeado: " + coordenadas, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedP = context.getSharedPreferences("configuracion", 0);
                SharedPreferences.Editor editor = sharedP.edit();
                editor.putString("coorde", coordenadas);
                editor.commit();
            }
        });
    }
}
