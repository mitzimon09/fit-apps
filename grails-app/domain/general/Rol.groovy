package general

class Rol implements java.io.Serializable {

	String authority

	static mapping = {
        table 'roles'
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

    String toString() {
        return authority
    }
}
