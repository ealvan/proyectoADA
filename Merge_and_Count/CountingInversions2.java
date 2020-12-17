package proyectoADA;
import java.util.ArrayList;
import java.util.Arrays; 
public class CountingInversions2 {
	  
		String[] turisticos = {
				"Plaza de Armas",
				"Monasterio de Santa Catalina de Siena",
				"Catedral de Arequipa y su museo",
				"El valle y el cañón del Colca",
				"Convento de Santa Teresa",
				"Canotaje en el Rio Chili",
				"Iglesia y Claustros de la Compañía",
				"Los Petroglifos de Toro Muerto",
				"Reserva Nacional de Salinas y Aguada Blanca",
				"Sillar de Arequipa",
				"Casa Goyeneche y Casa del Moral",
				"Barrio de San Lázaro – Picanterías",
				"Complejo Arqueológico de Uyo Uyo",
				"Casa Museo Mario Vargas Llosa",
		};
		
		int[] road1 = {0,1,2,3,4,5,6,7,8,9,10,11,13};
		
		int[] road2 = {0,6,7,1,4,10,11,13,2,3,5,8,9 };
		int[] road3 = {0,1,2,3,4,5,6,7,8,9,10,11,13};
		
		int[] road4 = {0,6,7,1,4,10,11,13,2,3,5,8,9 };
		int[] road5 = {10,11,7,1,4,3,8,0,13,2,6,9,5, };
		
		int[][] roadList = {
				road1,
				road2,
				road3,
				road4, 	
				road5
		};
		public ArrayList<Integer> toArrayList(int[] integer){
			ArrayList<Integer> lista = new ArrayList<Integer>();
			for(int k:integer) {
				lista.add(k);
			}
			return lista;
				
		}
	    //*************************************************************************
	    public ArrayList<Integer> main_second(Ciudad[] lugares) {
	    	int items[] = new int[lugares.length];
	    	for(int i = 0; i < items.length; i++) {
	    		items[i] = lugares[i].id;
	    	}
	    	int[] keys = new int[lugares.length];
	    	
	    	int[] inversions = new int[keys.length];
	    	
	    	for(int i = 0; i < roadList.length; i++) {
	    		keys = cortarGen(roadList[i],items);
	    		inversions[i] = mergeSortAndCount(keys,0,keys.length);
	    	}
	    	int items1[] = copy(items);
	    	int isUserInversions = mergeSortAndCount(items,0,items.length);
	    	int idNear = id_list_menor(inversions,isUserInversions);
	    	int[] nearRoad = roadList[idNear];
	    	
	    	ArrayList<Integer> nearR = toArrayList(nearRoad);
	    	ArrayList<Integer> complement = complement(nearR, items1);
	    	return complement;
	    	
	    }
	    public int[] copy(int[]items) {
	    	int[] list = new int [items.length];
	    	for(int i = 0; i< items.length; i++) {
	    		list[i] = items[i];
	    	}
	    	return list;
	    }
	    ///************************************************************************
	    // los lugares, aqui se muestran
	    // se deben cortar
	    public static int[] cortarGen(int origin[], int[] items) {
	    	empaque[] m = fase1_cortar(origin, items);
	    	ordenarIds(m);
	    	int[] keys = new int[m.length];
	    	for(int i = 0; i < m.length; i++) {
	    		keys[i] = m[i].value;
	    	}
	    	return keys;
	    }
	     
	    public static empaque[] fase1_cortar(int[] original, int[] items) {
	    	empaque[] numsCortados = new empaque[items.length];
	    	int j = 0;
	    	for(int key: items)
	    	for(int i = 0; i < original.length; i++) {
	    		if(key == original[i]) {
	    			numsCortados[j] = new empaque(i,key);
	    			j++;
	    		}
	    	}
	    	return numsCortados;
	    }
	    public static void ordenarIds(empaque arr[]){ 
	         int n = arr.length; 
	         for (int i = 0; i < n-1; i++) 
	             for (int j = 0; j < n-i-1; j++) 
	                 if (arr[j].pos > arr[j+1].pos){ 
	                        // swap arr[j+1] and arr[j] 
	                        empaque temp = arr[j]; 
	                        arr[j] = arr[j+1]; 
	                        arr[j+1] = temp; 
	                    } 
	    } 
	   private static int mergeAndCount(int[] arr,int l, int m, int r){ 
	  	  
	        int[] izq = Arrays.copyOfRange(arr, l, m + 1); 
	        int[] der = Arrays.copyOfRange(arr, m + 1, r + 1); 
	  
	        int i = 0, j = 0, k = l, cams = 0; 
	  
	        while (i < izq.length && j < der.length)  
	        { 
	            if (izq[i] <= der[j]) 
	                arr[k++] = izq[i++]; 
	            else { 
	                arr[k++] = der[j++]; 
	                cams += (m + 1) - (l + i); 
	            } 
	        } 
	        return cams; 
	    } 
	  
	    private static int mergeSortAndCount(int[] arr, int l, int r){        
	        int count = 0; 
	        if (l < r) { 
	            int m = (l + r) / 2; 
	            count += mergeSortAndCount(arr, l, m); 
	  
	           
	            count += mergeSortAndCount(arr, m + 1, r); 
	  
	             
	            count += mergeAndCount(arr, l, m, r); 
	        } 
	        return count; 
	    } 
	   
	    public static int id_list_menor(int[]inversions, int user_inv) {
	    	int menor = Math.abs(inversions[0] - user_inv);
	    	int id_list = 0;
	    	for(int i = 0; i < inversions.length; i++) {
	    		if(menor > Math.abs(user_inv-inversions[i]) ) {
	    			menor = Math.abs(user_inv-inversions[i]);
	    			id_list = i;
	    		}
	    	}
	    	return id_list;
	    }
	  
	    public static ArrayList<Integer> complement(ArrayList<Integer> original, int[] quitar) {
	    	
	    	for(int key: quitar) {
	    	    for(int i = 0; i< original.size(); i++) {
	    	    	if(key == original.get(i)) {
	    	    		original.remove(i);
	    	    	}
	    	    }
	    	}
	    	return original;
	    }
	    public String[] retornarLugares(ArrayList<Integer>ids) {
	    	String[] places = new String[ids.size()];
	    	for(int i = 0; i < ids.size(); i++) {
	    		places[i] = turisticos[ids.get(i)];
	    	}
	    	return places;
	    	
	    }
	    public  String[] recomendar(ArrayList<Ciudad> lugares) {
	    	Ciudad[] lugaresI = new Ciudad[lugares.size()];
	    	for(int i = 0; i< lugaresI.length; i++) {
	    		lugaresI[i] = lugares.get(i);
	    	}
	    	ArrayList<Integer> idsRecomendados = main_second(lugaresI);
	    	return retornarLugares(idsRecomendados);
	    }
	    public static void main(String[] args) { 
	    	
	    	Ciudad[] lugares = new Ciudad[7];
	    	lugares[0] = new Ciudad("Place0",0); 
	    	lugares[1] = new Ciudad("Place1",2);
	    	lugares[2] = new Ciudad("Place2",1);
	    	lugares[3] = new Ciudad("Place3",4);
	    	lugares[4] = new Ciudad("Place4",5);
	    	lugares[5] = new Ciudad("Place5",7);
	    	lugares[6] = new Ciudad("Place6",9);
	    	CountingInversions2 m = new CountingInversions2();
	    	ArrayList<Integer> main = m.main_second(lugares);
	    	String[] place = m.retornarLugares(main);
	    	
	    	System.out.println(Arrays.toString(place));
	    	for(Integer i : main) {
	    		System.out.println(i);
	    	}
	    	 
	    }
} 
	

