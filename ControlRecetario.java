//ControlRecetario.java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControlRecetario{
	//atributos
	private Recetario recetario;
	private boolean hiloEjecutandose;

	//constructor vacio
	public ControlRecetario(){
		this.recetario = new Recetario();
		this.hiloEjecutandose = true;
	}

	//constructor
	public ControlRecetario(Recetario recetario){
		this.recetario = recetario;
		this.hiloEjecutandose = true;
	}

	//metodos gets & sets
	public Recetario getRecetario(){
		return recetario;
	}

	public void setRecetario(Recetario recetario){
		this.recetario = recetario;
	}

	public void setHiloEjecutandose(boolean valor){
		hiloEjecutandose = valor;
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

	//Accion sobre la receta numero "numero" (interface Preparable): 1 preparar, 2 pausar, 3 detener
	//Regresa false si la receta o la accion no existen
	public boolean accionReceta(int numero, int accion){
		if (numero < 1 || numero > recetario.getTotalRecetas()){
			return false;
		}
		Preparable p = recetario.getRecetas()[numero - 1];
		if (accion == 1){
			p.preparar();
		} else if (accion == 2){
			p.pausar();
		} else if (accion == 3){
			p.detener();
		} else {
			return false;
		}
		return true;
	}

	//receta al azar (Aleatorizable)
	public String recetaAleatoria(){
		Receta r = recetario.random();
		if (r == null){
			return "No hay recetas registradas.";
		}
		return "Receta aleatoria\n" + r.mostrarReceta();
	}

	//mezcla el orden de las recetas y regresa la lista
	public String mezclarRecetas(){
		recetario.mezclar();
		return "Recetas mezcladas\n" + recetario.mostrarRecetas();
	}

	//agrega tres recetas de prueba (si ya existen no se repiten)
	public void datosPrueba(){
		Postre p = new Postre("Flan napolitano", 60, "Media", 8, "Hacer caramelo, licuar la mezcla y hornear a bano maria", "Cremoso", 4.5f, "flan");
		p.agregarIngrediente(new Receta.Ingrediente("Leche", 500, "ml"));
		p.agregarIngrediente(new Receta.Ingrediente("Huevo", 4, "piezas"));
		p.agregarIngrediente(new Receta.Ingrediente("Azucar", 150, "g"));
		registrarReceta(p);

		Platillo s = new Platillo("Sopa de tortilla", 40, "Facil", 4, "Freir la tortilla, hacer el caldo y servir", "Muy buena", 4, "sopa");
		s.agregarIngrediente(new Receta.Ingrediente("Tortilla", 8, "piezas"));
		s.agregarIngrediente(new Receta.Ingrediente("Jitomate", 3, "piezas"));
		s.agregarIngrediente(new Receta.Ingrediente("Caldo de pollo", 1000, "ml"));
		registrarReceta(s);

		Bebida b = new Bebida("Agua de jamaica", 20, "Facil", 6, "Hervir la jamaica, colar y endulzar", "Refrescante", 5, "fria");
		b.agregarIngrediente(new Receta.Ingrediente("Jamaica", 100, "g"));
		b.agregarIngrediente(new Receta.Ingrediente("Azucar", 100, "g"));
		b.agregarIngrediente(new Receta.Ingrediente("Agua", 1500, "ml"));
		registrarReceta(b);
	}

	//hilo como lambda: muestra en consola cuantos postres, platillos y bebidas hay
	public void mensajeria(){
		Runnable mensaje = () -> {
			while(hiloEjecutandose){
				System.out.println("Postres: " + recetario.getNoPostres() + " | Platillos: " + recetario.getNoPlatillos() + " | Bebidas: " + recetario.getNoBebidas());
				try{
					Thread.sleep(10000);
				}catch(InterruptedException e) {}
			}
		};
		Thread hilo = new Thread(mensaje);
		hilo.start();
	}

	//Persistencia: guarda el arreglo de recetas y el total en el archivo
	public boolean guardaInfo(){
		System.out.println("Guardando");
		try{
			//segundo parametro true agrega, false sobreescribe
			FileOutputStream archivo = new FileOutputStream(new File("Recetario.per"), false);
			ObjectOutputStream oos = new ObjectOutputStream(archivo);

			oos.writeObject(recetario.getRecetas());
			oos.writeInt(recetario.getTotalRecetas());
			oos.close();
			archivo.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error de guardar");
			return false;
		}
	}

	//Persistencia: carga las recetas guardadas. Regresa false si no hay archivo o fallo
	public boolean cargarInfo(){
		File f = new File("Recetario.per");
		//si es la primera vez el archivo no existe
		if (!f.exists()){
			return false;
		}
		try{
			FileInputStream archivo = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(archivo);

			recetario.setRecetas((Receta[]) ois.readObject());
			recetario.setTotalRecetas(ois.readInt());
			ois.close();
			archivo.close();
			return true;
		} catch (IOException | ClassNotFoundException e){
			System.out.println("Error fin de lectura");
			return false;
		}
	}
}
