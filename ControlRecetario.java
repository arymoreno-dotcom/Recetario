//ControlRecetario.java

public class ControlRecetario{
	//atributos
	private Recetario recetario;

	//constructor vacio
	public ControlRecetario(){
		this.recetario = new Recetario();
	}

	//constructor
	public ControlRecetario(Recetario recetario){
		this.recetario = recetario;
	}

	//metodos gets & sets
	public Recetario getRecetario(){
		return recetario;
	}

	public void setRecetario(Recetario recetario){
		this.recetario = recetario;
	}

	//metodos
	
	//Todos regresan un texto con el resultado para que la interfaz lo muestre
	public String registrarReceta(Receta r){
		if (recetario.buscarReceta(r.getNombre()) != null){
			return "Ya existe una receta con el nombre " + r.getNombre() + ".";
		}
		if (recetario.agregarReceta(r)){
			return "Receta registrada correctamente.";
		}
		return "Se ha alcanzado el limite de recetas.";
	}

	//Consulta una receta por su numero en la lista (empieza en 1)
	public String consultarReceta(int numero){
		if (numero < 1 || numero > recetario.getTotalRecetas()){
			return "No existe la receta numero " + numero + ".";
		}
		return recetario.getRecetas()[numero - 1].mostrarReceta();
	}

	public String eliminarReceta(String nombre){
		if (recetario.eliminarReceta(nombre)){
			return "Receta " + nombre + " eliminada.";
		}
		return "No se encontro la receta " + nombre + ".";
	}

	public String buscarReceta(String nombre){
		Receta r = recetario.buscarReceta(nombre);
		if (r == null){
			return "No se encontro la receta " + nombre + ".";
		}
		return r.mostrarReceta();
	}

	public String mostrarRecetas(){
		return recetario.mostrarRecetas();
	}
}
