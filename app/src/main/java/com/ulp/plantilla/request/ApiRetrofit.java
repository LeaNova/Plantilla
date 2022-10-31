package com.ulp.plantilla.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Inmueble;
import com.ulp.plantilla.modelo.Pago;
import com.ulp.plantilla.modelo.Propietario;
import com.ulp.plantilla.modelo.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiRetrofit {
    private static final String PATH = "http://192.168.0.17:5000/";
    //private static final String PATH = "https://192.168.0.17:5001/";
    private static ServiceInmobiliaria serviceInmobiliaria;

    public static ServiceInmobiliaria getServiceInmobiliaria() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serviceInmobiliaria = retrofit.create(ServiceInmobiliaria.class);

        return serviceInmobiliaria;
    }

    public static String obtenerToken(Context context) {
        SharedPreferences sharedP = context.getSharedPreferences("configuracion", 0);
        String token = sharedP.getString("token", "Sin definir");
        return token;
    }

    public static String obtenerIP() {
        return PATH;
    }

    public interface ServiceInmobiliaria {
        @FormUrlEncoded
        @POST("Propietario/login")
        Call<String> login(
                @Field("user") String user,
                @Field("pass") String pass
        );

        @FormUrlEncoded
        @POST("Propietario/pass_change")
        Call<Propietario> olvideContraseña(@Field("email") String email);

        @GET("Propietario")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @FormUrlEncoded
        @POST("Propietario/actualizar/perfil")
        Call<Propietario> actualizarPerfil(
                @Field("nombre") String nombre,
                @Field("apellido") String apellido,
                @Field("DNI") String DNI,
                @Field("telefono") String telefono,
                @Field("Email") String Email,
                @Header("Authorization") String token
        );

        @FormUrlEncoded
        @POST("Propietario/actualizar/contraseña")
        Call<Propietario> actualizarContraseña(
                @Field("passOld") String passOld,
                @Field("passNew") String passNew,
                @Field("passNewR") String passNewR,
                @Header("Authorization") String token
        );

        @GET("Inmueble")
        Call<ArrayList<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @FormUrlEncoded
        @POST("Inmueble/agregar")
        Call<Inmueble> agregarInmueble(
                @Field("direccion") String direccion,
                @Field("uso") int uso,
                @Field("tipo") int tipo,
                @Field("cantAmbientes") int cantAmbientes,
                @Field("coordenadas") String coordenadas,
                @Field("precio") double precio,
                @Field("foto") String foto,
                @Header("Authorization") String token
        );

        @POST("Inmueble/actualizar/estado/{id}")
        Call<Inmueble> actualizarEstado(
                @Path("id") int id,
                @Header("Authorization") String token
        );

        @GET("Contrato")
        Call<ArrayList<Contrato>> obtenerContratos(@Header("Authorization") String token);

        @GET("Pago/{id}")
        Call<ArrayList<Pago>> obtenerPagos(
                @Path("id") int id,
                @Header("Authorization") String token
        );
    }
}
