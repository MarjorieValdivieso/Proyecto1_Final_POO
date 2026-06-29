package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;
import java.io.IOException;

public class DashboardController {

    @FXML private VBox panelLateralMenu;
    @FXML private VBox contenedorCentral;
    @FXML private Label lblNombreUsuario;
    @FXML private Label lblRolActivo;

    @FXML private Button btnCrudPrincipal;
    @FXML private Button btnGestionUsuarios;
    @FXML private Button btnModuloReportes;
    @FXML private Button btnConfiguracion;
    @FXML private Button btnCerrarSesion;

    public void inicializarDashboard(Usuario usuario) {
        // Validación segura
        if (lblNombreUsuario != null) {
            lblNombreUsuario.setText("Usuario: " + usuario.getUsuario());
        }

        if (lblRolActivo != null) {
            lblRolActivo.setText(usuario.getRol().toUpperCase());
        }

        if (panelLateralMenu != null) {
            panelLateralMenu.setStyle(usuario.getColor());
        }

        // Validación de permisos
        switch (usuario.getRol()) {
            case "Administrador":
                if (btnCrudPrincipal != null) btnCrudPrincipal.setVisible(true);
                if (btnGestionUsuarios != null) btnGestionUsuarios.setVisible(true);
                if (btnModuloReportes != null) btnModuloReportes.setVisible(true);
                if (btnConfiguracion != null) btnConfiguracion.setVisible(true);
                break;

            case "Cajero":
                if (btnCrudPrincipal != null) btnCrudPrincipal.setVisible(true);
                if (btnGestionUsuarios != null) btnGestionUsuarios.setVisible(false);
                if (btnModuloReportes != null) btnModuloReportes.setVisible(false);
                if (btnConfiguracion != null) btnConfiguracion.setVisible(false);
                break;

            case "Reportes":
                if (btnCrudPrincipal != null) btnCrudPrincipal.setVisible(false);
                if (btnGestionUsuarios != null) btnGestionUsuarios.setVisible(false);
                if (btnModuloReportes != null) btnModuloReportes.setVisible(true);
                if (btnConfiguracion != null) btnConfiguracion.setVisible(false);
                break;

            default:
                if (btnCrudPrincipal != null) btnCrudPrincipal.setVisible(false);
                if (btnGestionUsuarios != null) btnGestionUsuarios.setVisible(false);
                if (btnModuloReportes != null) btnModuloReportes.setVisible(false);
                if (btnConfiguracion != null) btnConfiguracion.setVisible(false);
                break;
        }
    }

    @FXML
    public void handleCerrarSesion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/proyectofinalpoo/Login.fxml"));
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Belleza Elegante - Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}