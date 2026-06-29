package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<String> cmbRol;
    @FXML private Button btnIngresar;

    @FXML
    public void initialize() {
        if (cmbRol != null) {
            cmbRol.setItems(FXCollections.observableArrayList("Administrador", "Cajero", "Reportes"));
        }
    }

    @FXML
    public void handleAcceder() {
        String username = txtUsuario.getText();
        String password = txtContrasena.getText();
        String rolSeleccionado = cmbRol.getValue();

        //Validación de Campos vacíos
        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty() ||
                rolSeleccionado == null) {

            mostrarAlerta("Campos Incompletos", "Por favor, llene todos los campos y seleccione un rol.", Alert.AlertType.WARNING);
            return;
        }

        //Mínimo de caracteres Contraseña >= 6
        if (password.length() < 6) {
            mostrarAlerta("Contraseña Débil", "La contraseña debe tener al menos 6 caracteres.", Alert.AlertType.ERROR);
            return;
        }

        // Validación de Usuarios
        boolean accesoValido = false;

        if (rolSeleccionado.equals("Administrador") && username.equals("admin") && password.equals("admin123")) {
            accesoValido = true;
        } else if (rolSeleccionado.equals("Cajero") && username.equals("cajero") && password.equals("cajero123")) {
            accesoValido = true;
        } else if (rolSeleccionado.equals("Reportes") && username.equals("reportes") && password.equals("reportes123")) {
            accesoValido = true;
        }


        if (!accesoValido) {
            mostrarAlerta("Credenciales Incorrectas", "El usuario, la contraseña o el rol no coinciden.", Alert.AlertType.ERROR);
            return;
        }


        Usuario usuarioLogueado = new Usuario();
        usuarioLogueado.setUsuario(username);
        usuarioLogueado.setClave(password);
        usuarioLogueado.setRol(rolSeleccionado);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinalpoo/Dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardCtrl = loader.getController();
            dashboardCtrl.inicializarDashboard(usuarioLogueado);

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Belleza Elegante - Panel de Control");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error del Sistema", "No se pudo cargar la pantalla principal del Dashboard. Verifica que renombraste la carpeta a 'resources'.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}