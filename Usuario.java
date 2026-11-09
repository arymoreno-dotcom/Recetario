// Usuario.java
// Clase encargada de almacenar la información del usuario del sistema

public class Usuario {
	// Atributos privados
	private String nombre;
	private String usuario;
	
	// Constructor vacío
	public Usuario() {
		this.nombre = "";
		this.usuario = "";
	}
	
	// Constructor con parámetros (ESTE ES EL QUE FALTABA Y CAUSABA EL ERROR)
	public Usuario(String nombre, String usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
	}
	
	// Métodos Gets y Sets corregidos
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	// Métodos informativos
	public void consultarReceta() {
		System.out.println("El usuario " + usuario + " quiere consultar una receta.");
	}
	
	public void agregarReceta() {
		System.out.println("El usuario " + usuario + " quiere agregar una receta.");
	}
}