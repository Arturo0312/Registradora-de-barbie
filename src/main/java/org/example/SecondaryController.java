package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {

    sqlite con = new sqlite();

    @FXML
    private TableView<Producto> tableInv;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TextField txtAname;
    @FXML
    private TextField txtAPrize;
    @FXML
    private Label lblnombre;
    @FXML
    private Label lblprize;
    @FXML
    private Button btnAdd;
    private ObservableList<Producto> productos;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        productos = FXCollections.observableArrayList();
        this.colCode.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.colName.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        con.Inventario();
        productos = con.productos;
        this.tableInv.setItems(productos);



    }

    @FXML
    private void llenarinve(ActionEvent event) throws IOException {
        App.setRoot("addProducto");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        App.setRoot("primary");

    }

    @FXML
    public void borrar(ActionEvent actionEvent) {
        Producto p = this.tableInv.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar algo a eliminar");
            alert.showAndWait();
        }else{
            con.borrarinv(p.getCodigo());
            this.productos.remove(p);
            this.tableInv.refresh();
        }

    }
    @FXML
    public void Modificar(ActionEvent actionEvent) throws IOException {
        Producto p = this.tableInv.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar algo a actualizar");
            alert.showAndWait();
        }else{
            String codigo = p.getCodigo();
            String nombre = txtAname.getText();
            String  preciotxt = txtAPrize.getText();
            float precio = Float.parseFloat(preciotxt);
            con.actualizarinv(nombre, codigo, precio);
            this.productos.remove(p);
            Producto p2 = new Producto(codigo, nombre, precio);
            this.productos.add(p2);
            this.tableInv.refresh();

        }
    }

    @FXML
    public void seleccionar(MouseEvent mouseEvent) {
        Producto p = this.tableInv.getSelectionModel().getSelectedItem();
        if(p!=null){
            this.txtAname.setText(p.getNombre());
            String cadena = Float.toString(p.getPrecio());
            this.txtAPrize.setText(cadena);
        }
    }
}