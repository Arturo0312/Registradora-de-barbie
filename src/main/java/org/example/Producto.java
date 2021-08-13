package org.example;

public class Producto {
    private String codigo;
    private String Nombre;
    private float precio;

    public Producto(String c, String n, float p){

        codigo = c;
        Nombre = n;
        precio = p;
    }

    public float getPrecio() {
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
