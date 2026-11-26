//Mensajes.java

//clase Runnable: hilo que muestra en consola cuantas recetas hay
public class Mensajes implements Runnable{
	private volatile boolean running = true;
	
	ControlRecetario control;
	
	public Mensajes(ControlRecetario control){
		this.control = control;
	}
	
	public void run(){
		while(running){
			System.out.println("Recetas registradas: " + control.getRecetario().getTotalRecetas());
			try{
				Thread.sleep(10000);
			}catch(InterruptedException e) {}
		}
	}
	
	public void parar(){
		running = false;
	}
}
