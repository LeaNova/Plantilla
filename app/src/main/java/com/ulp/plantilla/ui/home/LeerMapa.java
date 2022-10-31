package com.ulp.plantilla.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.contentcapture.ContentCaptureCondition;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class LeerMapa implements OnMapReadyCallback {
    static final LatLng INMOBILIARIA = new LatLng(-33.302134, -66.336876);
    private Context context;
    private GoogleMap map;

    public LeerMapa(Context context) {
        this.context = context;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA))
                .setTitle("Inmobiliaria H's");

        obtenerUltimaUbicacion();
    }

    private void obtenerUltimaUbicacion() {
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
                if (location != null) {
                    LatLng miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
                    map.addMarker(new MarkerOptions().position(miUbicacion))
                            .setTitle("Mi ubicacion");

                    CameraPosition camUbicacion= new CameraPosition.Builder()
                            .target(miUbicacion)
                            .zoom(17)
                            .bearing(0)
                            .build();
                    CameraUpdate camUp= CameraUpdateFactory.newCameraPosition(camUbicacion);
                    map.animateCamera(camUp);
                } else {
                    CameraPosition camUbicacion= new CameraPosition.Builder()
                            .target(INMOBILIARIA)
                            .zoom(17)
                            .bearing(0)
                            .build();
                    CameraUpdate camUp= CameraUpdateFactory.newCameraPosition(camUbicacion);
                    map.animateCamera(camUp);
                }
            }
        });
    }
}
