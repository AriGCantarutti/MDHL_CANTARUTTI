package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.example.Interfaz.InterfazEmail;
import org.example.Interfaz.InterfazPantalla;
import org.example.controlador.GestorOrden;
import org.example.models.*;
import org.example.util.OrdenInspeccionVisualDTO;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;


// CLASE QUE CONTIENE TODA LA LOGICA DE JAVA FX
public abstract class PantallaOrdenController implements Initializable {

    // === ELEMENTOS DE LA INTERFAZ GRÁFICA ===
    @FXML protected Label lblSistemaInfo;
    @FXML protected Label lblVersion;
    @FXML protected Button btnCerrarOrden;
    @FXML protected Button btnSalir;
    @FXML protected Button btnAgregarBloque;

    @FXML protected TableView<OrdenInspeccionVisualDTO> tableOrdenes;
    @FXML protected TableColumn<OrdenInspeccionVisualDTO, Integer> colNumero;
    @FXML protected TableColumn<OrdenInspeccionVisualDTO, String> colEstado;
    @FXML protected TableColumn<OrdenInspeccionVisualDTO, String> colFecha;

    @FXML protected VBox panelCierre;
    @FXML protected TextField txtNumeroOrden;
    @FXML protected TextArea txtObservacion;
    @FXML protected ComboBox<String> cmbMotivos;
    @FXML protected TextArea txtComentarios;
    @FXML protected Button btnConfirmarCierre;
    @FXML protected Button btnCancelar;
    VBox contenedorBloques = new VBox(10);

    private Scanner scanner;
    private ObservableList<OrdenInspeccionVisualDTO> ordenesData = FXCollections.observableArrayList();
    private ObservableList<String> motivosData = FXCollections.observableArrayList();
    protected GestorOrden gestor;
    private List<MotivoTipo> listaMotivos;

    protected PantallaOrdenController() {
        this.scanner = new Scanner(System.in);
        inicializarSistema();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configurarInterfaz();
        mostrarInformacionSistema();

        if (panelCierre != null) {
            panelCierre.setVisible(false);
        }

        // Añadimos listeners para habilitar controles progresivamente
        if (txtNumeroOrden != null) {
            txtNumeroOrden.textProperty().addListener((obs, oldVal, newVal) -> validarPaso1());
        }
        if (txtObservacion != null) {
            txtObservacion.textProperty().addListener((obs, oldVal, newVal) -> validarPaso2());
        }
        if (cmbMotivos != null) {
            cmbMotivos.valueProperty().addListener((obs, oldVal, newVal) -> validarPaso3());
        }

        // Inicialmente deshabilitar controles relacionados al cierre
        if (txtObservacion != null) txtObservacion.setDisable(true);
        if (cmbMotivos != null) cmbMotivos.setDisable(true);
        if (txtComentarios != null) txtComentarios.setDisable(true);
        if (btnConfirmarCierre != null) btnConfirmarCierre.setDisable(true);
    }

    private void validarPaso1() {
        boolean valido = !txtNumeroOrden.getText().trim().isEmpty();
        txtObservacion.setDisable(!valido);

        if (!valido) {
            cmbMotivos.setDisable(true);
            txtComentarios.setDisable(true);
            btnConfirmarCierre.setDisable(true);
            cmbMotivos.getSelectionModel().clearSelection();
            txtObservacion.clear();
            txtComentarios.clear();
        }
    }

    private void validarPaso2() {
        boolean valido = !txtObservacion.getText().trim().isEmpty();
        cmbMotivos.setDisable(!valido);
        txtComentarios.setDisable(!valido);

        if (!valido) {
            btnConfirmarCierre.setDisable(true);
            cmbMotivos.getSelectionModel().clearSelection();
            txtComentarios.clear();
        }
    }

    private void validarPaso3() {
        boolean motivoSeleccionado = cmbMotivos.getValue() != null && !cmbMotivos.getValue().trim().isEmpty();
        btnConfirmarCierre.setDisable(!motivoSeleccionado);
    }

    private void inicializarSistema() {

        //Rol
        Rol rolResponsable = new Rol("Responsable de Inspecciones", "Encargado de gestionar inspecciones");
        Rol rolTecnico = new Rol("Técnico", "Técnico de campo");

        //Empleado
        Empleado empleado = new Empleado("Juan Pérez", "juanperez@email.com");
        empleado.agregarRol(rolResponsable);
        Empleado empleado2 = new Empleado("María García", "maria.garcia@empresa.com");
        empleado2.agregarRol(rolTecnico);
        List<Empleado> empleados = List.of(empleado, empleado2);

        //usuario logeado
        Usuario usuario = new Usuario("admin", "admin123", empleado);

        //sesion actual
        Sesion sesion = new Sesion("2025-01-01 8:00", "2025-01-01 17:00", usuario);

        // Estacion sismografica
        EstacionSismologica est1 = new EstacionSismologica(101, "01CD", "2020-02-01", "-34.6118", "-58.3960", "Estación Central", 21458);
        Sismografo sismo1 = new Sismografo(101, "2020-10-21", 2000);
        est1.setSismografo(sismo1);
        EstacionSismologica est2 = new EstacionSismologica(101, "01CD", "2020-05-10", "35.6118", "-55.3960", "Estación Norte", 15874);
        Sismografo sismo2 = new Sismografo(102, "2021-08-20", 2001);
        est2.setSismografo(sismo2);
        EstacionSismologica est3 = new EstacionSismologica(101, "01CD", "2018-06-10", "33.6118", "-58.3960", "Estación Sur", 78541);
        Sismografo sismo3 = new Sismografo(103, "2023-01-10", 2010);

        //Estado
        Estado estado = Estado.REALIZADA;
        List<Estado> estados = Estado.obtenerEstados();

        //ordenes
        OrdenInspeccion orden1 = new OrdenInspeccion(1, "2025-01-01 10:00", "2025-01-01 11:00", "2025-01-01", "Observacion ciere 1", est1, empleado,estado);
        OrdenInspeccion orden2 = new OrdenInspeccion(2, "2025-01-01 10:00", "2025-01-01 11:00", "2025-01-01", "Observacion ciere 2", est2, empleado, estado);
        OrdenInspeccion orden3 = new OrdenInspeccion(3, "2025-01-01 10:00", "2025-01-01 11:00", "2025-01-01", "Observacion ciere 3", est1, empleado, estado);
        List<OrdenInspeccion> ordenes = List.of(orden1, orden2, orden3);

        //interfaz email
        InterfazEmail interfazEmail = new InterfazEmail("");

        //Interfaz pantalla
        InterfazPantalla interfazPantalla = new InterfazPantalla("");
        // gestor
        gestor = new GestorOrden(sesion, ordenes, estados, empleados, interfazEmail, interfazPantalla);


    }

    private void mostrarInformacionSistema() {
        if (lblSistemaInfo != null) {
            lblSistemaInfo.setText("Sistema: Gestión de Órdenes de Inspección Sismográfica");
        }
        if (lblVersion != null) {
            lblVersion.setText("Versión: 2.0 - Con datos precargados | Funcionalidad: Cerrar Orden de Inspección");
        }

        System.out.println("\n=== INFORMACIÓN DEL SISTEMA ===");
        System.out.println("Sistema: Gestión de Órdenes de Inspección Sismográfica");
        System.out.println("Versión: 2.0 - Con datos precargados");
        System.out.println("Funcionalidad: Cerrar Orden de Inspección");
        System.out.println("================================");
    }

    private void configurarInterfaz() {
        tableOrdenes.setItems(ordenesData);
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroDeOrden"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("EstadoActual"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaGeneracion"));
    }

    @FXML
    private void onCerrarOrdenClick() {
        System.out.println("boton apretado");
        opsCerrarOrden();
    }

    protected abstract void opsCerrarOrden();


    protected void mostrarDatoEnTabla(int numeroOrden, String estado, String fecha) {
        ordenesData.add(new OrdenInspeccionVisualDTO(numeroOrden, estado, fecha));
        mostrarPanelSeleccionOrden();
    }

    private void mostrarPanelSeleccionOrden() {
        if (panelCierre != null) {
            panelCierre.setVisible(true);
        }


        if (txtNumeroOrden != null) txtNumeroOrden.clear();
        if (txtObservacion != null) {
            txtObservacion.clear();
            txtObservacion.setDisable(true);
        }
        if (cmbMotivos != null) {
            cmbMotivos.getSelectionModel().clearSelection();
            cmbMotivos.setDisable(true);
        }
        if (txtComentarios != null) {
            txtComentarios.clear();
            txtComentarios.setDisable(true);
        }
        if (btnConfirmarCierre != null) btnConfirmarCierre.setDisable(true);
    }

    protected void mostrarMotivosEnPantalla(List<MotivoTipo> motivos) {
        motivosData.addAll(motivos.stream().map(MotivoTipo::toString).toList());
        cmbMotivos.setItems(motivosData);
    }

    @FXML
    private void onConfirmarCierreClick() {
        boolean valido = validarCamposCierreOrden();
        if (!valido) {
            System.out.println(">>> Campos de cierre de orden inválidos.");
            return;
        }

        int numeroOrden = Integer.parseInt(txtNumeroOrden.getText().trim());
        String observacion = txtObservacion.getText().trim();
        String motivoSeleccionado = cmbMotivos.getValue();
        String comentario = txtComentarios.getText().trim();

        //pantalla
        boolean ordenValida = gestor.tomarOrdenPorNumero(numeroOrden);
        if (!ordenValida) return;
        gestor.tomarObservacion(observacion);
        gestor.tomarMotivosYComentarios(motivoSeleccionado, comentario);

        pedirConfirmacionCierreOrden(numeroOrden, motivoSeleccionado);
    }

    private boolean validarCamposCierreOrden() {
        // Validacion numero de orden
        String nroOrdenText = txtNumeroOrden.getText().trim();
        if (nroOrdenText.isEmpty()) {
            mostrarAdvertencia("Campo requerido", "Debe ingresar el número de orden.");
            return false;
        }

        int nroOrden;
        try {
            nroOrden = Integer.parseInt(nroOrdenText);
        } catch (NumberFormatException e) {
            mostrarError("Número inválido", "El número de orden debe ser un número entero.");
            return false;
        }

        if (nroOrden <= 0) {
            mostrarError("Número inválido", "El número de orden debe ser positivo.");
            return false;
        }

        // Validacion observacion
        String observacion = txtObservacion.getText().trim();
        if (observacion.isEmpty()) {
            mostrarAdvertencia("Campo requerido", "Debe ingresar una observación.");
            txtObservacion.requestFocus();
            txtObservacion.setStyle("-fx-border-color: red;");
            return false;
        }

        // Validacion motivo
        String motivoSeleccionado = cmbMotivos.getValue();
        if (motivoSeleccionado == null || motivoSeleccionado.trim().isEmpty()) {
            mostrarAdvertencia("Campo requerido", "Debe seleccionar un motivo.");
            return false;
        }

        return true;
    }

    private void pedirConfirmacionCierreOrden(int nroOrden, String motivoSeleccionado) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Cierre");
        confirmacion.setHeaderText("Cierre de Orden de Inspección");
        confirmacion.setContentText("¿Confirma el cierre de la orden #" + nroOrden + "?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        boolean confirmado = resultado.isPresent() && resultado.get() == ButtonType.OK;

        if (confirmado) {
            mostrarExito("Orden cerrada", "La orden #" + nroOrden + " ha sido cerrada exitosamente.");
            ocultarPanelCierre();
            tomarConfirmacion();

        } else {
            mostrarInformacion("Operación cancelada", "El cierre de la orden ha sido cancelado.");
            System.out.println(">>> Cierre de orden cancelado por el usuario.");
        }
    }

    protected abstract void tomarConfirmacion();

    @FXML
    private void onCancelarClick() {
        ocultarPanelCierre();
        System.out.println(">>> Proceso de cierre cancelado por el usuario.");
    }

    private void ocultarPanelCierre() {
        if (panelCierre != null) {
            panelCierre.setVisible(false);
        }
    }

    @FXML
    private void onSalirClick() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Salir");
        confirmacion.setHeaderText("Salir del Sistema");
        confirmacion.setContentText("¿Está seguro que desea salir del sistema?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            cerrarSistema();
            System.exit(0);
        }
    }

    private void cerrarSistema() {
        try {
            this.scanner.close();
            System.out.println("\n=== SISTEMA FINALIZADO ===");
        } catch (Exception e) {
            System.err.println("Error al cerrar sistema: " + e.getMessage());
        }
    }

    protected void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    protected void limpiarPantalla() {
        // Limpiar tabla de ordenes
        ordenesData.clear();
    }

}
