package org.example;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.sqlite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addProductoController implements Initializable {

    sqlite con = new sqlite();

    @FXML
    private TextField txtCP;
    @FXML
    private TextField txtNP;
    @FXML
    private TextField txtPP;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    public void Aceptar(ActionEvent actionEvent) {
        String codigo = txtCP.getText();
        String nombre = txtNP.getText();
        String  preciotxt = txtPP.getText();
        float precio = Float.parseFloat(preciotxt);
        con.insertproduct(codigo, nombre, precio);
        txtCP.setText("");
        txtNP.setText("");
        txtPP.setText("");
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        App.setRoot("secondary");
    }
}
