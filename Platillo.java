//Platillo.java
//clase hija que hereda atributos y métodos de la superclase Receta
public class Platillo extends Receta {
    private String tipoPlatillo;

    //constructor vacio
    public Platillo() {
        super();
        this.tipoPlatillo = "";
    }

    //constructor con parámetros
    public Platillo(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoPlatillo) {
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoPlatillo = tipoPlatillo;
    }

    //Getters y Setters
    public String getTipoPlatillo() { return tipoPlatillo; }
    public void setTipoPlatillo(String t) { this.tipoPlatillo = t; }

    @Override
    public String toString() {
        return "Platillo: " + getNombre() + " (" + tipoPlatillo + "), " + getTiempoPreparacion() + " min, Dificultad: " + getDificultad();
    }

    //metodo abstracto de Receta
    @Override
    public String mostrarReceta() {
        return "--- PLATILLO: " + getNombre() + " ---\n"
                + "Tipo de platillo: " + tipoPlatillo + "\n"
                + "Tiempo: " + getTiempoPreparacion() + " min | Dificultad: " + getDificultad() + " | Porciones: " + getPorciones() + "\n"
                + "Instrucciones: " + getInstrucciones() + "\n"
                + "Reseña: " + getResena() + " (Calificación: " + getCalificacion() + "/5)\n"
                + "Ingredientes:\n" + getListaIngredientes();
    }
}
