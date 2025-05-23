package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainMenuController {

    @FXML
    private Button btnCerrarOrden;

    @FXML
    private Button btnSalir;

    @FXML
    private void initialize() {
        System.out.println("=== INICIANDO SISTEMA DE GESTIÓN DE ÓRDENES DE INSPECCIÓN ===");
        System.out.println("Sistema: Gestión de Órdenes de Inspección Sismográfica");
        System.out.println("Versión: 2.0 - Con datos precargados");
        System.out.println("Funcionalidad: Cerrar Orden de Inspección");
        System.out.println("================================");
    }

    @FXML
    private void handleCerrarOrden(ActionEvent event) {
        try {
            System.out.println("=== INICIANDO CASO DE USO: CERRAR ORDEN DE INSPECCIÓN ===");

            // Cargar la pantalla de lista de órdenes
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OrdenList.fxml"));
            Parent root = loader.load();

            // Obtener el stage actual
            Stage stage = (Stage) btnCerrarOrden.getScene().getWindow();

            // Cambiar la escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Gestión de Órdenes - Lista de Órdenes");

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error al cargar la pantalla de órdenes", e.getMessage());
        }
    }

    @FXML
    private void handleSalir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Salida");
        alert.setHeaderText("¿Está seguro que desea salir del sistema?");
        alert.setContentText("Se cerrarán todas las ventanas abiertas.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("=== FINALIZANDO SISTEMA ===");
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

