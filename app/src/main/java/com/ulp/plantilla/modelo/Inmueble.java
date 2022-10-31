package com.ulp.plantilla.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Inmueble implements Serializable {

    public enum EnUsos {
        Residencial,
        Comercial
    }

    public enum EnTipos {
        Casa,
        Departamento,
        Local,
        Depósito,
        Oficinas,
        Individual
    }

    private int idInmueble;
    private String direccion;
    private int uso;
    private int tipo;
    private int cantAmbientes;
    private String coordenadas;
    private double precio;
    private boolean disponible;
    private int propietarioId;
    private String foto;
    private Propietario duenio;

    public Inmueble() {
    }

    public Inmueble(int idInmueble,
                    String direccion,
                    int uso,
                    int tipo,
                    int cantAmbientes,
                    String coordenadas,
                    double precio,
                    boolean disponible,
                    int propietarioId,
                    String foto,
                    Propietario duenio) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.cantAmbientes = cantAmbientes;
        this.coordenadas = coordenadas;
        this.precio = precio;
        this.disponible = disponible;
        this.propietarioId = propietarioId;
        this.foto = foto;
        this.duenio = duenio;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCantAmbientes() {
        return cantAmbientes;
    }

    public void setCantAmbientes(int cantAmbientes) {
        this.cantAmbientes = cantAmbientes;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public static ArrayList<String> getUsos() {
        ArrayList<String> lista = new ArrayList<>();
        for(EnUsos usos: EnUsos.values()) {
            lista.add(usos.name());
        }
        return lista;
    }

    public static ArrayList<String> getTipos() {
        ArrayList<String> lista = new ArrayList<>();
        for(EnTipos tipos: EnTipos.values()) {
            lista.add(tipos.name());
        }
        return lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return idInmueble == inmueble.idInmueble;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInmueble);
    }


    /*
    public String getFechaInicio() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(desde);

            dia = formato.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }
    */
}
