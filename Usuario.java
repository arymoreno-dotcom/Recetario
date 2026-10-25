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
	
	//metodos gets/set
	
	public String getNombre(){
		return nombre;
	}
	
	public String setNombre(String nombre){
		return nombre;
	}
	
	public String getUsuario(String usuario){
		return usuario;
	}
	
	public String setUsuario(){
		return usuario;
	}
	
	//metodos
	
	public void consultarReceta(){
		System.out.println("El usuario " + usuario + " quiere consultar una receta.");
	}
	
	public void agregarReceta(){
		System.out.println("El usuario " + usuario + " quiere agregar una receta.");
	}
}
