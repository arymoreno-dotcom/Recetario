//Validacion.java
import javax.swing.JOptionPane;

public class Validacion{

	//pide un entero hasta que sea valido. Regresa -1 si el usuario cancela
	public static int validaInt(String mensaje)
	{
		while(true)
		{
			String texto = JOptionPane.showInputDialog(mensaje);
			if (texto == null)
			{
				return -1;
			}
			try
			{
				int valor = Integer.parseInt(texto);
				return valor;
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Ingresa un valor entero valido");
			}
		}
	}

	//pide un float hasta que sea valido. Regresa -1 si el usuario cancela
	public static float validaFloat(String mensaje)
	{
		while(true)
		{
			String texto = JOptionPane.showInputDialog(mensaje);
			if (texto == null)
			{
				return -1;
			}
			try
			{
				float valor = Float.parseFloat(texto);
				return valor;
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido");
			}
		}
	}
}
