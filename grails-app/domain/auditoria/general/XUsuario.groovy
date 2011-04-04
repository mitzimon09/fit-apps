package auditoria.general

class XUsuario implements java.io.Serializable {
    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String nombre
    String apellido
    String correo
    String nombreCompleto
    String roles
    Long usuarioId
    Date lastUpdated
    String actividad
    String creador
    
    static transients = ['nombreCompleto']

    static constraints = {
        username blank: false, size:1..64
        password blank: false, size:1..128
        nombre blank: false, size:1..64
        apellido blank: false, size:1..64
        correo nullable: true, size:1..128
        actividad size:1..32
        creador size:1..64
    }

    static mapping = {
        table 'xusuarios'
        password column: '`password`'
    }

    String getNombreCompleto() {
        return "$apellido, $nombre"
    }

    String toString() {
        return "$apellido, $nombre"
    }
}
