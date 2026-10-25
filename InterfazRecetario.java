//InterfazRecetario.java
import javax.swing.JOptionPane;

public class InterfazRecetario{
	
	//atributos
	private ControlRecetario control;
	private Usuario usuario;
	
	//constructor
	public InterfazRecetario(ControlRecetario control, Usuario usuario){
		this.control = control;
		this.usuario = usuario;
	}
	
	//metodos
	public void mostrarMenu(){
		int opcion = 0;
		Receta nueva;
		String dato;

		//se repite el menu hasta que el usuario elige salir (opcion 6)
		while (opcion != 6){
			String texto = JOptionPane.showInputDialog("Bienvenido, " + usuario.getNombre() + "\n\n"
					+ "1.- Registrar receta\n"
					+ "2.- Consultar receta\n"
					+ "3.- Buscar receta por nombre\n"
					+ "4.- Eliminar receta\n"
					+ "5.- Mostrar todas las recetas\n"
					+ "6.- Salir\n\n"
					+ "Selecciona una opcion");

			//si cierra o cancela la ventana se sale del menu
			if (texto == null){
				opcion = 6;
			} else {
				opcion = Integer.parseInt(texto);
			}
			
			//opcion 1
			if (opcion == 1){
				usuario.agregarReceta();
				nueva = solicitarDatos();
				if (nueva != null){
					mostrarResultados(control.registrarReceta(nueva));
				}
			
			//opcion 2
			} else if (opcion == 2){
				usuario.consultarReceta();
				mostrarResultados(control.mostrarRecetas());
				dato = JOptionPane.showInputDialog("Ingresa el número de la receta a consultar");
				if (dato != null){
					mostrarResultados(control.consultarReceta(Integer.parseInt(dato)));
				}
			
			//opcion 3
			} else if (opcion == 3){
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a buscar");
				if (dato != null){
					mostrarResultados(control.buscarReceta(dato));
				}
			
			//opcion 4
			} else if (opcion == 4){
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a eliminar");
				if (dato != null){
					mostrarResultados(control.eliminarReceta(dato));
				}
			
			//opcion 5
			} else if (opcion == 5){
				mostrarResultados(control.mostrarRecetas());
			
			//opcion 6
			} else if (opcion == 6){
				JOptionPane.showMessageDialog(null, "Bye bye");
			} else {
				JOptionPane.showMessageDialog(null, "Opcion no valida");
			}
		}
	}

	//Pide todos los datos de una receta. Regresa null si el usuario cancela
	public Receta solicitarDatos(){
		String texto = JOptionPane.showInputDialog("Tipo de receta:\n1 - Postre\n2 - Platillo\n3 - Bebida");
		if (texto == null){
			return null;
		}
		//si el tipo de receta es menor a 1 o si es mayor a 3, regresa nulo
		int tipo = Integer.parseInt(texto);
		if (tipo < 1 || tipo > 3){
			mostrarResultados("Tipo de receta no válido.");
			return null;
		}

		String nombre = JOptionPane.showInputDialog("Nombre de la receta");
		if (nombre == null){
			return null;
		}

		texto = JOptionPane.showInputDialog("Tiempo de preparación (minutos)");
		if (texto == null){
			return null;
		}
		int tiempo = Integer.parseInt(texto);
		if (tiempo <= 0){
			mostrarResultados("El tiempo debe ser mayor a 0.");
			return null;
		}

		texto = JOptionPane.showInputDialog("Dificultad: \n 1 - Fácil \n 2 - Media \n 3 - Difícil");
		if (texto == null){
			return null;
		}
		int dif = Integer.parseInt(texto);
		String dificultad = "Facil";
		if (dif == 2){
			dificultad = "Media";
		} else if (dif == 3){
			dificultad = "Dificil";
		} else if (dif != 1){
			mostrarResultados("Dificultad no válida.");
			return null;
		}

		texto = JOptionPane.showInputDialog("Número de porciones");
		if (texto == null){
			return null;
		}
		int porciones = Integer.parseInt(texto);
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
			texto = JOptionPane.showInputDialog("Calificación de 1 a 5");
			if (texto != null){
				float cal = Float.parseFloat(texto);
				if (cal >= 1 && cal <= 5){
					calificacion = cal;
				} else {
					mostrarResultados("La calificación debe ser de 1 a 5. Se guardará sin calificación.");
				}
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
				String cantidadTxt = JOptionPane.showInputDialog("Cantidad de " + nombreIng);
				String unidad = JOptionPane.showInputDialog("Unidad de " + nombreIng + " (g, ml, piezas...)");
				if (cantidadTxt == null || unidad == null){
					mostrarResultados("Ingrediente omitido: faltaron datos.");
				} else {
					float cantidad = Float.parseFloat(cantidadTxt);
					if (cantidad <= 0){
						mostrarResultados("Ingrediente omitido: la cantidad debe ser mayor a 0.");
					} else if (!receta.agregarIngrediente(new Ingrediente(nombreIng, cantidad, unidad))){
						mostrarResultados("Se alcanzó el límite de ingredientes.");
						seguir = false;
					}
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
