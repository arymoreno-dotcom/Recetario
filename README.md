# Recetario
Avances del proyecto POO

No usar funciones como .equals, try/catch, .trim, variables interger y en vez de tipo de dato double, usar float.

PROHIBIDO USAR LA FUNCION DE ARRAYLIST ni nada relacionado a memoria dinamica, todo manual. 


---

## Cambios Realizados

1. Implementación de Herencia y Polimorfismo:
   - Se definió Receta como superclase abstracta con métodos generales y arreglos estáticos de ingredientes.
   - Se crearon las tres subclases derivadas: 
   Postre, Platillo y Bebida, implementando sus métodos abstractos de despliegue mostrarReceta

2. Corrección de la Clase Usuario:
   - Se agregó el constructor con parámetros Usuario(String nombre, String usuario) necesario para la inicialización.
   - Se corrigieron los métodos getters y setters adecuando el retorno a void en la modificación de atributos.

3. Optimización del Flujo de Interfaz (InterfazRecetario):
   - Reordenamiento de Captura: Se reordenó el formulario para pedir primero los ingredientes y finalmente las instrucciones.
   - Validación de Datos Numéricos: Se ajustaron los diálogos para advertir la captura exclusiva de valores numéricos en las cantidades de ingredientes, evitando errores de parseo float.
   - Instrucciones Dinámicas Paso a Paso: Se cambió el cuadro de texto único por un ciclo dinámico de captura de instrucciones paso a paso. Ahora muestra el texto ingresado en el **paso anterior** como referencia contextual para evitar confusiones.

---

## Arquitectura y Clases del Proyecto

- `Ingrediente.java`: Modela el nombre, cantidad float y unidad de medida.
- `Receta.java`: Clase abstracta base que administra el arreglo fijo de ingredientes y datos generales.
- `Postre.java`, `Platillo.java`, `Bebida.java`: Clases derivadas que definen el tipo específico de receta.
- `Recetario.java`: Administra el arreglo fijo de recetas (máximo 10) con operaciones de búsqueda y eliminación manual.
- `ControlRecetario.java`: Enlace entre el recetario y la interfaz.
- `Usuario.java`: Almacena las credenciales del usuario activo.
- `InterfazRecetario.java`: Contiene el menú interactivo con JOptionPane y el método ejecutable main.

pendientes:

implementando lo que falta, tmb cambiar lo de instrucciones, 
poder agregar instrucciones como los ingredientes 
y mostrar el paso anterior para no confundir a la persona

Compilación mediante la consola de comandos:
```bash
javac -d bin/ src/*.java 
Ejecución del programa:
java -cp bin/ InterfazRecetario