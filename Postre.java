//clase hija que hereda atributos y métodos de la superclase Receta
public class Postre extends Receta {
    // Atributo específico para postres[cite: 1]
    private String tipoPostre;

    // Constructor por defecto
    public Postre() {
        super(); // Llama al constructor de la clase padre Receta
        this.tipoPostre = "";
    }

    // Constructor con parámetros
    public Postre(String nombre, int tiempoPreparacion, String dificultad, int porciones, String instrucciones, String resena, float calificacion, String tipoPostre) {
        // Pasa los atributos generales a la clase padre
        super(nombre, tiempoPreparacion, dificultad, porciones, instrucciones, resena, calificacion);
        this.tipoPostre = tipoPostre;
    }

    //Getters y Setters 
    public String getTipoPostre() { return tipoPostre; }
    public void setTipoPostre(String t) { this.tipoPostre = t; }

   //metodo abstract mostrarReceta heredado
    @Override
    public String mostrarReceta() {
        String info = "--- POSTRE: " + getNombre() + " ---\n"
                + "Tipo de postre: " + tipoPostre + "\n"
                + "Tiempo: " + getTiempoPreparacion() + " min | Dificultad: " + getDificultad() + " | Porciones: " + getPorciones() + "\n"
                + "Instrucciones: " + getInstrucciones() + "\n"
                + "Reseña: " + getResena() + " (Calificación: " + getCalificacion() + "/5)\n"
                + "Ingredientes:\n";
        
        //recorre y concatena los ingredientes del arreglo
        Ingrediente[] ing = getIngredientes();
        for (int i = 0; i < getTotalIngredientes(); i++) {
            info += "- " + ing[i].getNombre() + ": " + ing[i].getCantidad() + " " + ing[i].getUnidad() + "\n";
        }
        return info;
    }
}