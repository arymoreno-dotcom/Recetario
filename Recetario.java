//Recetario.java

public class Recetario{
	//variable que no se modifica tiene limite de 10
	final int CANTIDAD = 10;
	
	//atributos
	private Receta recetas[];
	private int totalRecetas;
	
	//constructor vacio
	public Recetario(){
		this.recetas = new Receta[CANTIDAD];
		this.totalRecetas = 0;
	}
	
	//metodos gets & sets
	//recetas y totalRecetas no tienen set, se manejan con agregar y eliminar
	public Receta[] getRecetas(){
		return recetas;
	}

	public int getTotalRecetas(){
		return totalRecetas;
	}

	//Metodos
	//Agrega una receta al final del arreglo. Regresa false si ya esta lleno
	public boolean agregarReceta(Receta r){
		if (totalRecetas >= CANTIDAD){
			return false;
		}
		recetas[totalRecetas++] = r;
		return true;
	}

	//Elimina una receta por nombre y recorre las demas hacia la izquierda
	public boolean eliminarReceta(String nombre){
		for (int i = 0; i < totalRecetas; i++){
			if (recetas[i].getNombre().compareTo(nombre) == 0){
				for (int j = i; j < totalRecetas - 1; j++){
					recetas[j] = recetas[j + 1];
				}
				recetas[totalRecetas - 1] = null;
				totalRecetas--;
				return true;
			}
		}
		return false;
	}

	//Busca una receta por nombre. Regresa null si no existe
	public Receta buscarReceta(String nombre){
		for (int i = 0; i < totalRecetas; i++){
			if (recetas[i].getNombre().compareTo(nombre) == 0){
				return recetas[i];
			}
		}
		return null;
	}

	//Metodo para mostrar la lista de recetas
	public String mostrarRecetas(){
		if (totalRecetas == 0){
			return "No hay recetas registradas.";
		}
		String texto = "Recetas registradas (" + totalRecetas + "):\n";
		for (int i = 0; i < totalRecetas; i++){
			texto += (i + 1) + ". " + recetas[i].getNombre() + " - " + recetas[i].getTiempoPreparacion() + " min - " + recetas[i].getDificultad() + "\n";
		}
		return texto;
	}
}
