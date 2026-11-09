// Bebida.java

//todo tipo de bebidas
public class Bebida extends Receta {
    //atributo para bebida
    private String tipoBebida;

    //cinstructor vacio
    public Bebida() {
        super();
        this.tipoBebida = "";
    }

    //constructor
    public Bebida(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoBebida) {
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoBebida = tipoBebida;
    }

    //Getters y Setters 
    public String getTipoBebida() { return tipoBebida; }
    public void setTipoBebida(String t) { this.tipoBebida = t; }

    //implementar mostrarReceta
    @Override
    public String mostrarReceta() {
        String info = "--- BEBIDA: " + getNombre() + " ---\n"
                + "Tipo de bebida: " + tipoBebida + "\n"
                + "Tiempo: " + getTiempoPreparacion() + " min | Dificultad: " + getDificultad() + " | Porciones: " + getPorciones() + "\n"
                + "Instrucciones: " + getInstrucciones() + "\n"
                + "Reseña: " + getResena() + " (Calificación: " + getCalificacion() + "/5)\n"
                + "Ingredientes:\n";
        
        Ingrediente[] ing = getIngredientes();
        for (int i = 0; i < getTotalIngredientes(); i++) {
            info += "- " + ing[i].getNombre() + ": " + ing[i].getCantidad() + " " + ing[i].getUnidad() + "\n";
        }
        return info;
    }
}