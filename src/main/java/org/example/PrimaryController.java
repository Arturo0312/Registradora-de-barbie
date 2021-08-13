package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

public class PrimaryController implements Initializable {

    Float total = 0f;

    sqlite con = new sqlite();

    @FXML
    private TableView<Producto> tabProducto;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private Button btnInventario;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnVenta;
    @FXML
    private TextField txtcode;
    @FXML
    private ComboBox<String> combo;

    private ObservableList<Producto> productos;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        productos = FXCollections.observableArrayList();
        this.colCode.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));


        combo.getItems().add("2x1");
        combo.getItems().add("3x2");
        combo.getItems().add("10%");
        combo.getItems().add("20%");
        combo.getItems().add("30%");
        combo.getItems().add("50%");

    }
    @FXML
    private void agregar(ActionEvent event){



        String codigob = txtcode.getText();

        con.LlenarCaja(codigob);
        productos = con.productos2;
        this.tabProducto.setItems(productos);
        total += con.preciob;

        txtcode.setText("");

    }


    @FXML
    private void setBtnInventario(ActionEvent event) throws IOException {

        App.setRoot("secondary");
    }

    @FXML
    public void seleccionar(javafx.scene.input.MouseEvent mouseEvent) {
        Producto p = this.tabProducto.getSelectionModel().getSelectedItem();
        if(p!=null){
            this.txtcode.setText(p.getCodigo());
        }
    }

    @FXML
    public void eliminartb(ActionEvent actionEvent) {

        Producto p = this.tabProducto.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar algo a eliminar");
            alert.showAndWait();
        }else{
            this.productos.remove(p);
            total = total - p.getPrecio();
            this.tabProducto.refresh();
        }

    }

    @FXML
    public void cerrar (ActionEvent event){
        String seleccion = combo.getSelectionModel().getSelectedItem();
        System.out.println(seleccion);
        switch (seleccion){
            case "2x1":
                total = total*.50f;
                break;
            case "3x2":
                total = total*.66f;
                break;
            case "10%":
                total = total*.90f;
                break;
            case "20%":
                total = total*.80f;
                break;
            case "30%":
                total = total*.70f;
                break;
            case "50%":
                total = total*.50f;
                break;
            default:
                total = total;
                break;

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Total");
        alert.setContentText("El total es: " + total + "$");
        alert.showAndWait();
        this.tabProducto.getItems().clear();
        con.insertventa(total.toString());
        total = 0f;
    }
}
