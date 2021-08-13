package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class sqlite {
     ObservableList<Producto> productos;
    ObservableList<Producto> productos2;
    float preciob = 0f;



    String url = "jdbc:sqlite:registradora_barbie.db";
    Connection conexion;

public sqlite(){
    productos2 = FXCollections.observableArrayList();
    try{
        conexion = DriverManager.getConnection(url);
    }catch (Exception e){

    }
}
    public void Inventario(){

        try {
            productos = FXCollections.observableArrayList();
            String prods = "SELECT * FROM productos";
            Statement stat = conexion.createStatement();
            ResultSet result = stat.executeQuery(prods);
            while(result.next()){

                String codigo = result.getString("codigo");
                String nombre = result.getString("nombre");
                Float precio = result.getFloat("precio");
                Producto add = new Producto(codigo, nombre, precio);
                this.productos.add(add);





            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void LlenarCaja( String code){

        try {
            String prods = "SELECT * FROM productos WHERE codigo = " + "'" + code + "'";
            Statement stat = conexion.createStatement();
            ResultSet result = stat.executeQuery(prods);
            while(result.next()){

                String codigo = result.getString("codigo");
                String nombre = result.getString("nombre");
                Float precio = result.getFloat("precio");
                Producto add = new Producto(codigo, nombre, precio);
                preciob=add.getPrecio();
                    this.productos2.add(add);






            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void eliminar(String code){
    try {
        String prods = "delete from productos where codigo = " + "'" + code + "'";
        Statement stat = conexion.createStatement();
        stat.setQueryTimeout(10);
        stat.execute(prods);
    }catch(Exception e){

    }
    }

    public void insertventa(String valor){
        try {
            String prods = "insert into ventas (total) values (" + valor + ")";
            Statement stat = conexion.createStatement();
            stat.setQueryTimeout(10);
            stat.executeUpdate(prods);
        }catch(Exception e){
System.out.println(e);
        }
    }

    public void insertproduct(String codigo, String Nombre, float precio){
        try {
            String prods = "insert into productos values " + "(" + "'" + codigo  + "', " + "'" + Nombre + "', " + precio + ")" ;
            Statement stat = conexion.createStatement();
            stat.setQueryTimeout(10);
            stat.executeUpdate(prods);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Logrado");
            alert.setContentText("Producto agregado con exito");
            alert.showAndWait();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void borrarinv(String valor){
        try {
            String prods = "delete from productos where codigo = " + "'" + valor + "'";
            Statement stat = conexion.createStatement();
            stat.setQueryTimeout(10);
            stat.executeUpdate(prods);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizarinv(String nombre,String codigo, float precio){
        try {
            String prods = "UPDATE productos SET nombre = " + "'" + nombre +"'," + "precio = " + precio + " " + "where codigo = " + "'" + codigo + "'";
            Statement stat = conexion.createStatement();
            stat.setQueryTimeout(10);
            stat.executeUpdate(prods);
        }catch(Exception e){
            System.out.println(e);
        }
    }


}
