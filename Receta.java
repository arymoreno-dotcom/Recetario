// Clase abstracta base que representa la estructura general de cualquier receta
public abstract class Receta {
    // Constante para definir el tamaño máximo del arreglo de ingredientes[cite: 1, 4]
    private final int CANTIDAD = 10;
    
    // Atributos privados de la receta según el diagrama UML
    private String nombre;
    private int tiempoPreparacion;
    private String dificultad;
    private int porciones;
    private String instrucciones;
    private String resena;
    private float calificacion;
    
    // Arreglo de tamaño fijo para almacenar los ingredientes sin usar memoria dinámica[cite: 1, 4]
    private Ingrediente ingredientes[];
    // Contador para saber cuántos ingredientes se han guardado actualmente
    private int totalIngredientes;

    // Constructor vacio
    public Receta() {
        this.nombre = "";
        this.tiempoPreparacion = 0;
        this.dificultad = "";
        this.porciones = 0;
        this.instrucciones = "";
        this.resena = "";
        this.calificacion = 0.0f; 
        this.ingredientes = new Ingrediente[CANTIDAD]; 
        this.totalIngredientes = 0;
    }

    // Constructor para inicializar los atributos de receta
    public Receta(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.dificultad = dificultad;
        this.porciones = porciones;
        this.instrucciones = instrucciones;
        this.resena = resena;
        this.calificacion = calificacion;
        this.ingredientes = new Ingrediente[CANTIDAD];
        this.totalIngredientes = 0;
    }

    // Método abstracto que deberán implementar las clases hijas (Postre, Platillo, Bebida)[cite: 1]
    public abstract String mostrarReceta();

    // Agrega un ingrediente al final del arreglo si aún hay espacio[cite: 1]
    public boolean agregarIngrediente(Ingrediente i) {
        // Verifica si se alcanzó el límite máximo de ingredientes[cite: 1]
        if (totalIngredientes >= CANTIDAD) {
            return false; // Arreglo lleno
        }
        ingredientes[totalIngredientes++] = i; // Guarda el ingrediente e incrementa el contador
        return true; // Agregado con éxito
    }

    // Elimina un ingrediente por su nombre y recorre los demás a la izquierda[cite: 1]
    public boolean eliminarIngrediente(String nombre) {
        for (int i = 0; i < totalIngredientes; i++) {
            // Uso de compareTo en lugar de .equals() según las restricciones[cite: 4]
            if (ingredientes[i].getNombre().compareTo(nombre) == 0) {
                // Desplaza los elementos para no dejar huecos vacíos
                for (int j = i; j < totalIngredientes - 1; j++) {
                    ingredientes[j] = ingredientes[j + 1];
                }
                ingredientes[totalIngredientes - 1] = null; // Limpia la última posición
                totalIngredientes--; 
                return true; 
            }
        }
        return false; //no se encontro ingrediente
    }

    // Muestra en consola la lista de ingredientes registrados[cite: 1]
    public void mostrarIngredientes() {
        for (int i = 0; i < totalIngredientes; i++) {
            ingredientes[i].mostrarIngrediente();
        }
    }

    //muestra en consola la reseña y calificación
    public void mostrarResena() {
        System.out.println("Reseña: " + resena + " | Calificación: " + calificacion);
    }

    // Métodos Getters y Setters para acceder y modificar atributos privados[cite: 1]
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public void setTiempoPreparacion(int tiempoPreparacion) { this.tiempoPreparacion = tiempoPreparacion; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    public int getPorciones() { return porciones; }
    public void setPorciones(int porciones) { this.porciones = porciones; }

    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }

    public String getResena() { return resena; }
    public void setResena(String resena) { this.resena = resena; }

    public float getCalificacion() { return calificacion; }
    public void setCalificacion(float calificacion) { this.calificacion = calificacion; }

    public Ingrediente[] getIngredientes() { return ingredientes; }
    public int getTotalIngredientes() { return totalIngredientes; }
}