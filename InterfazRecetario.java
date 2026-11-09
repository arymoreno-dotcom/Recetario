// InterfazRecetario.java
import javax.swing.JOptionPane;

public class InterfazRecetario {
	
	// Atributos privados[cite: 10]
	private ControlRecetario control;
	private Usuario usuario;
	
	// Constructor[cite: 10]
	public InterfazRecetario(ControlRecetario control, Usuario usuario) {
		this.control = control;
		this.usuario = usuario;
	}
	
	// Menú principal[cite: 10]
	public void mostrarMenu() {
		int opcion = 0;
		Receta nueva;
		String dato;

		while (opcion != 6) {
			String texto = JOptionPane.showInputDialog("Bienvenido, " + usuario.getNombre() + "\n\n"
					+ "1.- Registrar receta\n"
					+ "2.- Consultar receta\n"
					+ "3.- Buscar receta por nombre\n"
					+ "4.- Eliminar receta\n"
					+ "5.- Mostrar todas las recetas\n"
					+ "6.- Salir\n\n"
					+ "Selecciona una opcion");

			if (texto == null) {
				opcion = 6;
			} else {
				opcion = Integer.parseInt(texto);
			}
			
			if (opcion == 1) {
				usuario.agregarReceta();
				nueva = solicitarDatos();
				if (nueva != null) {
					mostrarResultados(control.registrarReceta(nueva));
				}
			} else if (opcion == 2) {
				usuario.consultarReceta();
				mostrarResultados(control.mostrarRecetas());
				dato = JOptionPane.showInputDialog("Ingresa el número de la receta a consultar");
				if (dato != null) {
					mostrarResultados(control.consultarReceta(Integer.parseInt(dato)));
				}
			} else if (opcion == 3) {
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a buscar");
				if (dato != null) {
					mostrarResultados(control.buscarReceta(dato));
				}
			} else if (opcion == 4) {
				dato = JOptionPane.showInputDialog("Ingresa el nombre de la receta a eliminar");
				if (dato != null) {
					mostrarResultados(control.eliminarReceta(dato));
				}
			} else if (opcion == 5) {
				mostrarResultados(control.mostrarRecetas());
			} else if (opcion == 6) {
				JOptionPane.showMessageDialog(null, "Bye bye");
			} else {
				JOptionPane.showMessageDialog(null, "Opcion no valida");
			}
		}
	}

	// Solicita datos cambiando el orden: ingredientes primero, instrucciones al final
	public Receta solicitarDatos() {
		String texto = JOptionPane.showInputDialog("Tipo de receta:\n1 - Postre\n2 - Platillo\n3 - Bebida");
		if (texto == null) {
			return null;
		}
		
		int tipo = Integer.parseInt(texto);
		if (tipo < 1 || tipo > 3) {
			mostrarResultados("Tipo de receta no válido.");
			return null;
		}

		String nombre = JOptionPane.showInputDialog("Nombre de la receta");
		if (nombre == null) {
			return null;
		}

		texto = JOptionPane.showInputDialog("Tiempo de preparación (minutos)");
		if (texto == null) {
			return null;
		}
		int tiempo = Integer.parseInt(texto);
		if (tiempo <= 0) {
			mostrarResultados("El tiempo debe ser mayor a 0.");
			return null;
		}

		texto = JOptionPane.showInputDialog("Dificultad: \n 1 - Fácil \n 2 - Media \n 3 - Difícil");
		if (texto == null) {
			return null;
		}
		int dif = Integer.parseInt(texto);
		String dificultad = "Facil";
		if (dif == 2) {
			dificultad = "Media";
		} else if (dif == 3) {
			dificultad = "Dificil";
		} else if (dif != 1) {
			mostrarResultados("Dificultad no válida.");
			return null;
		}

		texto = JOptionPane.showInputDialog("Número de porciones");
		if (texto == null) {
			return null;
		}
		int porciones = Integer.parseInt(texto);
		if (porciones <= 0) {
			mostrarResultados("Las porciones deben ser mayores a 0.");
			return null;
		}

		String resena = JOptionPane.showInputDialog("Reseña (opcional, cancela para omitir)");
		float calificacion = 0.0f;
		if (resena == null) {
			resena = "Sin resena";
		} else {
			texto = JOptionPane.showInputDialog("Calificación de 1 a 5");
			if (texto != null) {
				float cal = Float.parseFloat(texto);
				if (cal >= 1 && cal <= 5) {
					calificacion = cal;
				} else {
					mostrarResultados("La calificación debe ser de 1 a 5. Se guardará sin calificación.");
				}
			}
		}

		Receta receta;
		String especifico;
		if (tipo == 1) {
			especifico = JOptionPane.showInputDialog("Tipo de postre (pastel, helado, galleta...)");
			if (especifico == null) {
				return null;
			}
			receta = new Postre(nombre, tiempo, dificultad, porciones, "", resena, calificacion, especifico);
		} else if (tipo == 2) {
			especifico = JOptionPane.showInputDialog("Tipo de platillo (entrada, plato fuerte, sopa...)");
			if (especifico == null) {
				return null;
			}
			receta = new Platillo(nombre, tiempo, dificultad, porciones, "", resena, calificacion, especifico);
		} else {
			especifico = JOptionPane.showInputDialog("Tipo de bebida (fría, caliente, con alcohol...)");
			if (especifico == null) {
				return null;
			}
			receta = new Bebida(nombre, tiempo, dificultad, porciones, "", resena, calificacion, especifico);
		}

		// 1. Captura de Ingredientes
		boolean seguir = true;
		while (seguir) {
			String nombreIng = JOptionPane.showInputDialog("Nombre del ingrediente (cancelar para terminar)");
			if (nombreIng == null) {
				seguir = false;
			} else {
				String cantidadTxt = JOptionPane.showInputDialog("Cantidad de " + nombreIng + " (escribe solo número, ej. 2)");
				String unidad = JOptionPane.showInputDialog("Unidad de medida para " + nombreIng + " (tazas, g, ml, piezas...)");
				
				if (cantidadTxt == null || unidad == null) {
					mostrarResultados("Ingrediente omitido: faltaron datos.");
				} else {
					float cantidad = Float.parseFloat(cantidadTxt);
					if (cantidad <= 0) {
						mostrarResultados("Ingrediente omitido: la cantidad debe ser mayor a 0.");
					} else if (!receta.agregarIngrediente(new Ingrediente(nombreIng, cantidad, unidad))) {
						mostrarResultados("Se alcanzó el límite de ingredientes.");
						seguir = false;
					}
				}
			}
		}

		// 2. Captura de Instrucciones al final
		String instrucciones = JOptionPane.showInputDialog("Instrucciones / Modo de preparación de la receta");
		if (instrucciones == null) {
			instrucciones = "Sin instrucciones registradas.";
		}
		receta.setInstrucciones(instrucciones);

		return receta;
	}

	// Método auxiliar para diálogos emergentes[cite: 10]
	public void mostrarResultados(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	// Método Main dentro de la clase[cite: 10]
	public static void main(String[] args) {
		Usuario u = new Usuario("Ana", "ana_chef");
		ControlRecetario control = new ControlRecetario();
		InterfazRecetario interfaz = new InterfazRecetario(control, u);
		interfaz.mostrarMenu();
	}
}