//Recetario.java

public class Recetario implements Aleatorizable{
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
	public Receta[] getRecetas(){
		return recetas;
	}

	public int getTotalRecetas(){
		return totalRecetas;
	}

	//se usan al cargar el archivo
	public void setRecetas(Receta[] recetas){
		this.recetas = recetas;
	}

	public void setTotalRecetas(int totalRecetas){
		this.totalRecetas = totalRecetas;
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

	//regresa la lista de recetas (usa el toString de cada receta)
	public String mostrarRecetas(){
		if (totalRecetas == 0){
			return "No hay recetas registradas.";
		}
		String texto = "Recetas registradas (" + totalRecetas + "):\n";
		for (int i = 0; i < totalRecetas; i++){
			texto += (i + 1) + ". " + recetas[i] + "\n";
		}
		return texto;
	}

	//cuenta cuantos postres hay: instanceof revisa si la receta es un Postre
	public int getNoPostres(){
		int cuenta = 0;
		for (int i = 0; i < totalRecetas; i++){
			if (recetas[i] instanceof Postre){
				cuenta++;
			}
		}
		return cuenta;
	}

	public int getNoPlatillos(){
		int cuenta = 0;
		for (int i = 0; i < totalRecetas; i++){
			if (recetas[i] instanceof Platillo){
				cuenta++;
			}
		}
		return cuenta;
	}

	public int getNoBebidas(){
		int cuenta = 0;
		for (int i = 0; i < totalRecetas; i++){
			if (recetas[i] instanceof Bebida){
				cuenta++;
			}
		}
		return cuenta;
	}

	//metodos de la interface Aleatorizable
	@Override
	public Receta random(){
		if (totalRecetas == 0){
			return null;
		}
		// Math.random() da un numero entre 0 y 1 (sin llegar a 1); se multiplica por el numero
		// de recetas y se convierte a int para obtener una posicion valida del arreglo
		int pos = (int) (Math.random() * totalRecetas);
		return recetas[pos];
	}

	@Override
	public void mezclar(){
		// recorre el arreglo de atras hacia adelante y cambia cada receta con otra en una posicion al azar
		for (int i = totalRecetas - 1; i > 0; i--){
			int j = (int) (Math.random() * (i + 1));
			Receta temp = recetas[i];
			recetas[i] = recetas[j];
			recetas[j] = temp;
		}
	}
}
