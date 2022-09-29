package com.ulp.plantilla;

import static android.content.Context.SENSOR_SERVICE;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.request.ApiClient;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient ap;
    private MutableLiveData<String> mutableAccess;

    private SensorManager sm;
    private LeerSensor leerSensor;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        ap = ApiClient.getApi();
    }

    public LiveData<String> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    public void iniciarSesion(String user, String pass) {
        Propietario p = ap.login(user, pass);
        if (!(p == null)) {
            mutableAccess.setValue("");

            Intent i = new Intent(context, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
            Toast.makeText(context, "Sesión iniciada", Toast.LENGTH_SHORT).show();
        } else {
            mutableAccess.setValue("Usuario o contraseña incorrecta");
        }
    }

    public void shake() {
        leerSensor = new LeerSensor();
        sm = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(lista.size() > 0) {
            sm.registerListener(leerSensor, lista.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void stopShake() {
        sm.unregisterListener(leerSensor);
    }

    private class LeerSensor implements SensorEventListener {
        private float varX, varY,varZ;
        private final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
        private final int SHAKE_SLOP_TIME_MS = 500;
        private final int SHAKE_COUNT_RESET_TIME_MS = 1000;
        private long mShakeTimestamp;
        private int mShakeCount;

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            varX = sensorEvent.values[0];
            varY = sensorEvent.values[1];
            varZ = sensorEvent.values[2];

            float gX = varX / sm.GRAVITY_EARTH;
            float gY = varY / sm.GRAVITY_EARTH;
            float gZ = varZ / sm.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();

                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                if(mShakeCount > 1) {
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:2664896870"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                    mShakeCount = 0;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            //Hehe te nanda yo!
        }
    }
}
