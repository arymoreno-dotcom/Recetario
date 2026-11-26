//Receta.java
import javax.swing.JOptionPane;
import java.io.Serializable;

// Clase abstracta base de cualquier receta. Implementa Preparable (como Contenido implementa Reproducir)
// y Serializable para poder guardarla en archivo
public abstract class Receta implements Preparable, Serializable {
    // tamaño maximo del arreglo de ingredientes
    private final int CANTIDAD = 10;

    private String nombre;
    private int tiempoPreparacion;
    private String dificultad;
    private int porciones;
    private String instrucciones;
    private String resena;
    private float calificacion;

    // arreglo de tamaño fijo, todo manual (sin memoria dinamica)
    private Ingrediente ingredientes[];
    private int totalIngredientes;

    // volatile: lo cambia detener() y lo lee el hilo de preparar()
    private volatile boolean preparando;

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
        this.preparando = false;
    }

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
        this.preparando = false;
    }

    // metodo abstracto: cada hija (Postre, Platillo, Bebida) lo implementa a su manera
    public abstract String mostrarReceta();

    // regresa los ingredientes en un String (usa el toString de Ingrediente)
    public String getListaIngredientes() {
        String lista = "";
        for (int i = 0; i < totalIngredientes; i++) {
            lista = lista + "- " + ingredientes[i] + "\n";
        }
        return lista;
    }

    // agrega un ingrediente al final del arreglo, regresa false si esta lleno
    public boolean agregarIngrediente(Ingrediente i) {
        if (totalIngredientes >= CANTIDAD) {
            return false;
        }
        ingredientes[totalIngredientes++] = i;
        return true;
    }

    // elimina un ingrediente por nombre y recorre los demas a la izquierda
    public boolean eliminarIngrediente(String nombre) {
        for (int i = 0; i < totalIngredientes; i++) {
            if (ingredientes[i].getNombre().compareTo(nombre) == 0) {
                for (int j = i; j < totalIngredientes - 1; j++) {
                    ingredientes[j] = ingredientes[j + 1];
                }
                ingredientes[totalIngredientes - 1] = null;
                totalIngredientes--;
                return true;
            }
        }
        return false;
    }

    public void mostrarIngredientes() {
        for (int i = 0; i < totalIngredientes; i++) {
            ingredientes[i].mostrarIngrediente();
        }
    }

    public void mostrarResena() {
        System.out.println("Reseña: " + resena + " | Calificación: " + calificacion);
    }

    @Override
    public String toString() {
        return nombre + ", " + tiempoPreparacion + " min, Dificultad: " + dificultad + ", Porciones: " + porciones;
    }

    // metodos de la interface Preparable
    @Override
    public void preparar() {
        JOptionPane.showMessageDialog(null, "Preparando " + nombre + "\n" + instrucciones);
        preparando = true;
        //funcion lambda: hilo que va mostrando en consola un ingrediente a la vez
        Runnable pasos = () -> {
            for (int i = 0; i < totalIngredientes && preparando; i++) {
                System.out.println("Agregando " + ingredientes[i]);
                try {
                    Thread.sleep(3000); //3 segundos
                } catch (InterruptedException e) {}
            }
            preparando = false;
        };
        //crea el hilo
        Thread hilo = new Thread(pasos);
        hilo.start();
    }

    @Override
    public void pausar() {
        JOptionPane.showMessageDialog(null, "Pausando la receta " + nombre);
    }

    @Override
    public void detener() {
        preparando = false;
        JOptionPane.showMessageDialog(null, "Deteniendo la receta " + nombre);
    }

    // Getters y Setters
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

    // Clase ANIDADA: Ingrediente solo tiene sentido dentro de una Receta, por eso se define dentro de ella.
    // Es static para poder crear ingredientes sin necesidad de un objeto Receta: new Receta.Ingrediente(...)
    public static class Ingrediente implements Serializable
    {
        private String nombre;
        private float cantidad;
        private String unidad;

        public Ingrediente() {
            this.nombre = "";
            this.cantidad = 0.0f;
            this.unidad = "";
        }

        public Ingrediente(String nombre, float cantidad, String unidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.unidad = unidad;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public float getCantidad() {
            return cantidad;
        }

        public void setCantidad(float cantidad) {
            this.cantidad = cantidad;
        }

        public String getUnidad() {
            return unidad;
        }

        public void setUnidad(String unidad) {
            this.unidad = unidad;
        }

        @Override
        public String toString() {
            return nombre + ": " + cantidad + " " + unidad;
        }

        public void mostrarIngrediente() {
            System.out.println("- " + this);
        }

        public String modificarCantidad(float cantidad) {
            this.cantidad = cantidad;
            return "Cantidad actualizada de " + nombre + " a " + cantidad + " " + unidad;
        }
    }
}
