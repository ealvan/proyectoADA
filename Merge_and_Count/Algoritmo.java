package proyectoADA;
public class Algoritmo {

	//el main puedes cambiarlo a tu gusto bro
	public static void main(String[] args) {
		
		//son 11 lugares turisticos
		
		String [] rutas = new String [5];
		//lista de rutas , pero con ID
		int [] array1 = new int[11];
		int [] array2 = new int[11];
		int [] array3 = new int[11];
		int [] array4 = new int[11];
		int [] array5 = new int[11];
		
		System.out.println("selecciona su ruta:"
				+ "1.-aqui van los lugares turisticos...");// ...compañerp(interfaces)
		int [] seleccionados = new int[5];
		//un for o el otro metodo, para llenar con los ID de los lugares...(compañero)      // ...compañerp(interfaces)
		
		// Devuelve un Array, eligidiendo las rutas(int) que tiene en comun con las rutas(int) del usuario, en cada caso
		int [] r1 = cortar(seleccionados, array1);
		int [] r2 = cortar(seleccionados, array2);
		int [] r3 = cortar(seleccionados, array3);
		int [] r4 = cortar(seleccionados, array4);
		int [] r5 = cortar(seleccionados, array5);
		
		int [] inversiones =  new int[5];
		//contar inversiones y sacar un array con el numero de inversiones de cada ruta(r1,r2,r3,r4,r5)...(compañero)
		
		
		String recomendacion = mejorViaje(inversiones, rutas); //Devuelve la cadena de la ruta recomendada
		System.out.println("La ruta recomendada para visitar los sitios turisticas de Arequipa  es la siguiente: "
				+ "\n"+ recomendacion);// ...compañerp(interfaces)
	}
	
	/*separa en un array diferente, todas las rutas 
	que quiere visitar el usuario y el plan de viajes
	seleccionados: usuario
	array: plan de viaje
	========================
	Ejemplo de los indices de los array
	0: cañon del colca
	1: plaza de armas
	2: ...
	
	*/
	public static int[] cortar(int [] seleccionados, int [] array){
		
		int count = 0;
		int [] nuevo = new int[seleccionados.length];
		
		//como el arreglo no esta ordenado, y merece quedarse asi para saber la secuencia de rutas
		//se opta por una busqueda de fuerza bruta
		for(int i = 0; i < array.length; i++) 
			for(int j = 0; j < seleccionados.length; j++)
				if(seleccionados[i] == array[j]) {
					nuevo[count] = array[i];
					count ++;
				}
		return nuevo; //Array eligidiendo las rutas(int), que tiene en comun con las rutas(int) del usuario
	}
	
	/*
	 * Los inversiones de las rutas y las rutas deben ir de la mano
	 * Ejemplo_
	 * Si la inversion de la ruta N  esta en la posicion 4
	 * la ruta N  determinada debe estar en la posicion 4 
	 * y asi sucesivamente*/
	
	//escoge uno de las 5 rutas predeterminados que tenga el menor numero de inversiones 
	public static String mejorViaje(int [] inversiones, String [] rutas) {
		int ind = indiceBajo(inversiones);
		return rutas[ind]; //plan de viaje elegido
		//rutas[ind] = "Plaza de Armas, Cañon del Colca, ..."
	}
	//escoge el indice de la ruta que tenga el menor numero de inversiones
	private static int indiceBajo(int [] inversiones) {
		int minimo = Integer.MAX_VALUE;
		int indice = -1;
		for(int i = 0; i < inversiones.length; i++)
			if(inversiones[i] < minimo) {
				indice = i;
				minimo = inversiones[i];
			}
		return indice;
	}
}
