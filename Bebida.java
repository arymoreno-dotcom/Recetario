//Bebida.java
//clase hija que hereda atributos y métodos de la superclase Receta
public class Bebida extends Receta {
    private String tipoBebida;

    //constructor vacio
    public Bebida() {
        super();
        this.tipoBebida = "";
    }

    //constructor con parámetros
    public Bebida(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoBebida) {
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoBebida = tipoBebida;
    }

    //Getters y Setters
    public String getTipoBebida() { return tipoBebida; }
    public void setTipoBebida(String t) { this.tipoBebida = t; }

    @Override
    public String toString() {
        return "Bebida: " + getNombre() + " (" + tipoBebida + "), " + getTiempoPreparacion() + " min, Dificultad: " + getDificultad();
    }

    //metodo abstracto de Receta
    @Override
    public String mostrarReceta() {
        return "--- BEBIDA: " + getNombre() + " ---\n"
                + "Tipo de bebida: " + tipoBebida + "\n"
                + "Tiempo: " + getTiempoPreparacion() + " min | Dificultad: " + getDificultad() + " | Porciones: " + getPorciones() + "\n"
                + "Instrucciones: " + getInstrucciones() + "\n"
                + "Reseña: " + getResena() + " (Calificación: " + getCalificacion() + "/5)\n"
                + "Ingredientes:\n" + getListaIngredientes();
    }
}
