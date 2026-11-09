//Ingrediente.java

public class Ingrediente 
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

    public void mostrarIngrediente() {
        System.out.println("- " + nombre + ": " + cantidad + " " + unidad);
    }

    public String modificarCantidad(float cantidad) {
        this.cantidad = cantidad;
        return "Cantidad actualizada de " + nombre + " a " + cantidad + " " + unidad;
    }
}