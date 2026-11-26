//Usuario.java

public class Usuario{
	//atributos
	private String nombre;
	private String usuario;
	
	//constructor vacio
	public Usuario(){
		this.nombre = "";
		this.usuario = "";
	}

	//constructor con datos
	public Usuario(String nombre, String usuario){
		this.nombre = nombre;
		this.usuario = usuario;
	}
	
	//metodos gets/set
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getUsuario(){
		return usuario;
	}
	
	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	
	//metodos
	public void consultarReceta(){
		System.out.println("El usuario " + usuario + " quiere consultar una receta.");
	}
	
	public void agregarReceta(){
		System.out.println("El usuario " + usuario + " quiere agregar una receta.");
	}
}
