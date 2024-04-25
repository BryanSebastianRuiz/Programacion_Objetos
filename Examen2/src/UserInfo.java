public class UserInfo {
    private final String nombreCompleto;
    private final String numeroTelefono;
    private final String correoElectronico;
    private final String pais;

    public UserInfo(String nombreCompleto, String numeroTelefono, String correoElectronico, String pais) {
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.pais = pais;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPais() {
        return pais;
    }
}
