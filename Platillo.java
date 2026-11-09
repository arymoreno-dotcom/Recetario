
//clase hija que representa los platillos principales o entradas
public class Platillo extends Receta {
   
    private String tipoPlatillo;

    //constructor vacio
    public Platillo() {
        super();
        this.tipoPlatillo = "";
    }

    // Constructor con parámetros
    public Platillo(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoPlatillo) {
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoPlatillo = tipoPlatillo;
    }

    //Getters y Setters
    public String getTipoPlatillo() { return tipoPlatillo; }
    public void setTipoPlatillo(String t) { this.tipoPlatillo = t; }

    //metodo abstract mostrarReceta
    @Override
    public String mostrarReceta() {
        String info = "--- PLATILLO: " + getNombre() + " ---\n"
                + "Tipo de platillo: " + tipoPlatillo + "\n"
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