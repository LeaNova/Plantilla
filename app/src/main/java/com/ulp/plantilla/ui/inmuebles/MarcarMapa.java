package com.ulp.plantilla.ui.inmuebles;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
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

                Toast.makeText(context, "Clickeado: " + coordenadas, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedP = context.getSharedPreferences("configuracion", 0);
                SharedPreferences.Editor editor = sharedP.edit();
                editor.putString("coorde", coordenadas);
                editor.putFloat("cLat", x);
                editor.putFloat("cLng", y);
                editor.commit();
            }
        });

        obtenerUbicacion();
    }

    private void obtenerUbicacion() {
        FusedLocationProviderClient fl= LocationServices.getFusedLocationProviderClient(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fl.getLastLocation().addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                SharedPreferences sharedP = context.getSharedPreferences("configuracion", 0);
                Float cX = sharedP.getFloat("cLat", 0);
                Float cY = sharedP.getFloat("cLng", 0);

                LatLng miUbicacion = new LatLng(cX, cY);

                CameraPosition camUbicacion = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(15)
                        .bearing(0)
                        .build();
                CameraUpdate camUp = CameraUpdateFactory.newCameraPosition(camUbicacion);
                map.animateCamera(camUp);
            }
        });
    }
}
