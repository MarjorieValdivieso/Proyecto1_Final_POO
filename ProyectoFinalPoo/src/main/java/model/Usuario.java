package model;

public class Usuario {

    private int id;
    private String usuario;
    private String clave;
    private String rol;

    public Usuario() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }


    public String getColor() {
        if (rol == null) return "-fx-background-color: #1a1a1a;";

        switch (rol) {
            case "Administrador":
                return "-fx-background-color: #111111; -fx-border-color: #d4af37; -fx-border-width: 0 0 0 4px;";
            case "Cajero":
                return "-fx-background-color: #1c1c1c; -fx-border-color: #ffffff; -fx-border-width: 0 0 0 4px;";
            case "Reportes":
                return "-fx-background-color: #1f1b14; -fx-border-color: #e5c185; -fx-border-width: 0 0 0 4px;";
            default:
                return "-fx-background-color: #1a1a1a;";
        }
    }
}