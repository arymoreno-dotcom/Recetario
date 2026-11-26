//Moreno Garcia Ary Naim 2220398
//Codigo para compilar
//Guardar archivos en src
//abrir el cmd en la carpeta del proyecto
//javac -d bin/ src/*.java //Compilar
//java -cp bin/ InterfazRecetario //Ejecutar

//InterfazRecetario.java
import javax.swing.JOptionPane;

public class InterfazRecetario{
	
	//atributos
	private ControlRecetario control;
	private Usuario usuario;
	
	//constructor
	public InterfazRecetario(){
		this.control = new ControlRecetario();
		this.usuario = new Usuario();
	}
	
	//metodos

	//main: solo crea la interfaz y arranca el programa
	public static void main(String args[]){
		InterfazRecetario interfaz = new InterfazRecetario();
		interfaz.iniciar();
	}

	//Arranca todo el programa
	public void iniciar(){
		String nombre = JOptionPane.showInputDialog("Nombre del usuario");
		if (nombre == null){
			nombre = "Invitado";
		}
		usuario.setNombre(nombre);
		usuario.setUsuario(nombre);

		//Persistencia: carga las recetas guardadas; si es la primera vez, agrega datos de prueba
		if (!control.cargarInfo()){
			control.datosPrueba();
		}

		//hilo como lambda dentro de control
		control.mensajeria();

		//clase Mensajes (hilo)
		Mensajes m = new Mensajes(control);
		Thread hilo = new Thread(m);
		hilo.start();

		mostrarMenu();

		//al salir se guarda y se paran los hilos
		control.guardaInfo();
		control.setHiloEjecutandose(false);
		m.parar();
	}

	public void mostrarMenu(){
		int opcion = 0;
		Receta nueva;
		String dato;
		int numero;
		int accion;

		//se repite el menu hasta que el usuario elige salir (opcion 6)
		while (opcion != 6){
			opcion = Validacion.validaInt("Bienvenido, " + usuario.getNombre() + "\n\n"
					+ "1.- Registrar receta\n"
					+ "2.- Consultar receta\n"
					+ "3.- Buscar receta por nombre\n"
					+ "4.- Eliminar receta\n"
					+ "5.- Mostrar todas las recetas\n"
					+ "6.- Salir\n\n"
					+ "Selecciona una opcion");

			//si cierra o cancela la ventana (-1) se sale del menu
			if (opcion == -1){
				opcion = 6;
			}
			
			if (opcion == 1){
				usuario.agregarReceta();
				nueva = solicitarDatos();
				if (nueva != null){
					mostrarResultados(control.registrarReceta(nueva));
				}
			} else if (opcion == 2){
				usuario.consultarReceta();
				mostrarResultados(control.mostrarRecetas());
				numero = Validacion.validaInt("Ingresa el número de la receta a consultar");
				if (numero != -1){
					mostrarResultados(control.consultarReceta(numero));
					//si la receta existe se puede preparar, pausar o detener (interface Preparable)
					if (numero >= 1 && numero <= control.getRecetario().getTotalRecetas()){
						accion = Validacion.validaInt("1.- Preparar\n2.- Pausar\n3.- Detener\n4.- Volver");
						if (accion >= 1 && accion <= 3){
							control.accionReceta(numero, accion);
						}
					}
				}
			} else if (opcion == 3){
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a buscar");
				if (dato != null){
					mostrarResultados(control.buscarReceta(dato));
				}
			} else if (opcion == 4){
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a eliminar");
				if (dato != null){
					mostrarResultados(control.eliminarReceta(dato));
				}
			} else if (opcion == 5){
				mostrarResultados(control.mostrarRecetas());
				//opciones extra del catalogo (interface Aleatorizable)
				accion = Validacion.validaInt("1.- Mezclar recetas\n2.- Receta aleatoria\n3.- Volver");
				if (accion == 1){
					mostrarResultados(control.mezclarRecetas());
				} else if (accion == 2){
					mostrarResultados(control.recetaAleatoria());
				}
			} else if (opcion == 6){
				mostrarResultados("Bye bye");
			} else {
				mostrarResultados("Opcion no valida");
			}
		}
	}

	//Pide todos los datos de una receta. Regresa null si el usuario cancela
	public Receta solicitarDatos(){
		int tipo = Validacion.validaInt("Tipo de receta:\n1 - Postre\n2 - Platillo\n3 - Bebida");
		if (tipo == -1){
			return null;
		}
		if (tipo < 1 || tipo > 3){
			mostrarResultados("Tipo de receta no válido.");
			return null;
		}

		String nombre = JOptionPane.showInputDialog("Nombre de la receta");
		if (nombre == null){
			return null;
		}

		int tiempo = Validacion.validaInt("Tiempo de preparación (minutos)");
		if (tiempo == -1){
			return null;
		}
		if (tiempo <= 0){
			mostrarResultados("El tiempo debe ser mayor a 0.");
			return null;
		}

		int dif = Validacion.validaInt("Dificultad: \n 1 - Fácil \n 2 - Media \n 3 - Difícil");
		if (dif == -1){
			return null;
		}
		String dificultad = "Facil";
		if (dif == 2){
			dificultad = "Media";
		} else if (dif == 3){
			dificultad = "Dificil";
		} else if (dif != 1){
			mostrarResultados("Dificultad no válida.");
			return null;
		}

		int porciones = Validacion.validaInt("Número de porciones");
		if (porciones == -1){
			return null;
		}
		if (porciones <= 0){
			mostrarResultados("Las porciones deben ser mayores a 0.");
			return null;
		}

		String instrucciones = JOptionPane.showInputDialog("Instrucciones");
		if (instrucciones == null){
			return null;
		}

		//Reseña y calificacion son opcionales
		String resena = JOptionPane.showInputDialog("Reseña (opcional, cancela para omitir)");
		float calificacion = 0;
		if (resena == null){
			resena = "Sin resena";
		} else {
			float cal = Validacion.validaFloat("Calificación de 1 a 5");
			if (cal >= 1 && cal <= 5){
				calificacion = cal;
			} else if (cal != -1){
				mostrarResultados("La calificación debe ser de 1 a 5. Se guardará sin calificación.");
			}
		}

		//Segun el tipo se crea Postre, Platillo o Bebida
		Receta receta;
		String especifico;
		if (tipo == 1){
			especifico = JOptionPane.showInputDialog("Tipo de postre (pastel, helado, galleta...)");
			if (especifico == null){
				return null;
			}
			receta = new Postre(nombre, tiempo, dificultad, porciones, instrucciones, resena, calificacion, especifico);
		} else if (tipo == 2){
			especifico = JOptionPane.showInputDialog("Tipo de platillo (entrada, plato fuerte, sopa...)");
			if (especifico == null){
				return null;
			}
			receta = new Platillo(nombre, tiempo, dificultad, porciones, instrucciones, resena, calificacion, especifico);
		} else {
			especifico = JOptionPane.showInputDialog("Tipo de bebida (fría, caliente, con alcohol...)");
			if (especifico == null){
				return null;
			}
			receta = new Bebida(nombre, tiempo, dificultad, porciones, instrucciones, resena, calificacion, especifico);
		}

		//Ingredientes: se piden hasta que el usuario cancele
		boolean seguir = true;
		while (seguir){
			String nombreIng = JOptionPane.showInputDialog("Nombre del ingrediente (cancelar para terminar)");
			if (nombreIng == null){
				seguir = false;
			} else {
				float cantidad = Validacion.validaFloat("Cantidad de " + nombreIng);
				String unidad = JOptionPane.showInputDialog("Unidad de " + nombreIng + " (g, ml, piezas...)");
				if (cantidad == -1 || unidad == null){
					mostrarResultados("Ingrediente omitido: faltaron datos.");
				} else if (cantidad <= 0){
					mostrarResultados("Ingrediente omitido: la cantidad debe ser mayor a 0.");
				} else if (!receta.agregarIngrediente(new Receta.Ingrediente(nombreIng, cantidad, unidad))){
					mostrarResultados("Se alcanzó el límite de ingredientes.");
					seguir = false;
				}
			}
		}
		return receta;
	}

	//Muestra un texto al usuario
	public void mostrarResultados(String texto){
		JOptionPane.showMessageDialog(null, texto);
	}
}
