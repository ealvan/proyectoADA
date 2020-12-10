package proyectoADA;
import java.util.ArrayList;
import java.util.Arrays; 
public class CountingInversions2 {
	  
		String[] turisticos = {
				"lugar0",
				"lugar1",
				"lugar2",
				"lugar3",
				"lugar4",
				"lugar5",
				"lugar6",
				"lugar7",
				"lugar8",
				"lugar9",
				"lugar10",
				"lugar11",
				"lugar12",
				"lugar13"
		};
		int[] road1 = {0,1,2,3,4,5,6,7,8,9,10,11,13};
		
		int[] road2 = {0,6,7,1,4,10,11,13,2,3,5,8,9 };
		int[] road3 = {0,1,2,3,4,5,6,7,8,9,10,11,13};
		
		int[] road4 = {0,6,7,1,4,10,11,13,2,3,5,8,9 };
		int[][] roadList = {
				road1,
				road2,
				road3,
				road4, 	
		};
	 
		// Function to count the number of inversions 
		
	    // during the merge process 
	    
	   
	   
	     
	 
	   /*
	    *  public static int[] ordenarIds(empaque[] nums) {
	    	for(int i = 1; i < nums.length; i++) {
	    		int pos = nums[i].pos;
	    		int j = i-1;
	    		while(j >= 0 && nums[j].pos > pos ) {
	    			nums[j+1] = nums[j];
	    			j --;
	    		}
	    		nums[j+1]= nums[i];
	    	}
	    	int[] list = new int[nums.length];
	    	int k  =0;
	    	for(empaque key: nums) {
	    		list[k] = key.value; 
	    		k++;
	    	}
	    	return list;
	    }
	    * */
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
	    // debo cortarlos
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
	   // *************************************************
	    private static int mergeAndCount(int[] arr,int l, int m, int r){ 
	  	  
	        // Left subarray 
	        int[] left = Arrays.copyOfRange(arr, l, m + 1); 
	  
	        // Right subarray 
	        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1); 
	  
	        int i = 0, j = 0, k = l, swaps = 0; 
	  
	        while (i < left.length && j < right.length)  
	        { 
	            if (left[i] <= right[j]) 
	                arr[k++] = left[i++]; 
	            else { 
	                arr[k++] = right[j++]; 
	                swaps += (m + 1) - (l + i); 
	            } 
	        } 
	        return swaps; 
	    } 
	  
	    // Merge sort function 
	    private static int mergeSortAndCount(int[] arr, int l, int r){ 
	  
	        // Keeps track of the inversion count at a 
	        // particular node of the recursion tree 
	        int count = 0; 
	  
	        if (l < r) { 
	            int m = (l + r) / 2; 
	  
	            // Total inversion count = left subarray count 
	            // + right subarray count + merge count 
	  
	            // Left subarray count 
	            count += mergeSortAndCount(arr, l, m); 
	  
	            // Right subarray count 
	            count += mergeSortAndCount(arr, m + 1, r); 
	  
	            // Merge count 
	            count += mergeAndCount(arr, l, m, r); 
	        } 
	  
	        return count; 
	    } 
	    //***************************************************************
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
	   // *****************************************************************
	    
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
	

