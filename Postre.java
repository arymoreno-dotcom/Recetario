//Postre.java
//clase hija que hereda atributos y métodos de la superclase Receta
public class Postre extends Receta {
    private String tipoPostre;

    //constructor vacio
    public Postre() {
        super();
        this.tipoPostre = "";
    }

    //constructor con parámetros
    public Postre(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoPostre) {
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoPostre = tipoPostre;
    }

    //Getters y Setters
    public String getTipoPostre() { return tipoPostre; }
    public void setTipoPostre(String t) { this.tipoPostre = t; }

    @Override
    public String toString() {
        return "Postre: " + getNombre() + " (" + tipoPostre + "), " + getTiempoPreparacion() + " min, Dificultad: " + getDificultad();
    }

    //metodo abstracto de Receta
    @Override
    public String mostrarReceta() {
        return "--- POSTRE: " + getNombre() + " ---\n"
                + "Tipo de postre: " + tipoPostre + "\n"
                + "Tiempo: " + getTiempoPreparacion() + " min | Dificultad: " + getDificultad() + " | Porciones: " + getPorciones() + "\n"
                + "Instrucciones: " + getInstrucciones() + "\n"
                + "Reseña: " + getResena() + " (Calificación: " + getCalificacion() + "/5)\n"
                + "Ingredientes:\n" + getListaIngredientes();
    }
}
