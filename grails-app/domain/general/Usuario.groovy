package general

class Usuario {

    String username
    String password
    boolean enabled = false
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    String nombre
    String apellidos
    String correo
    String nombreCompleto
    
    static transients = ['nombreCompleto']

    static constraints = {
        username blank: false, unique: true, size:1..64
        password blank: false, size:1..64
        nombre blank: false, size:1..64
        apellidos blank: false, size:1..64
        correo nullable: true, size:1..128
    }

    static mapping = {
        table 'usuarios'
        password column: '`password`'
    }

    static namedQueries = {
        listaConFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike('username',filtro)
                ilike('nombre',filtro)
                ilike('apellidos',filtro)
                ilike('correo',filtro)
            }
        }
    }

    Set<Rol> getAuthorities() {
        UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
    }

    String getNombreCompleto() {
        return "$apellidos, $nombre"
    }

    String toString() {
        return "$apellidos, $nombre"
    }
}
