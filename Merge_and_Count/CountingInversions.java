package Merge_and_Count;

import java.util.Arrays;

public class CountingInversions {

	public static void main(String[] args) {

		int[]A = {3,5};
		int[]B = {1,20,6,4,5};
		
		//Ciudad[] ruta_off = {
		//		new Ciudad("Lima",1),new Ciudad("Arequipa",2),
		//		new Ciudad("Junin",3),new Ciudad("Puno",4),
		//		new Ciudad("Cuzco",5),
		//		};
		//Ciudad[] ruta_new = {
		//		new Ciudad("Arequipa",2),new Ciudad("Lima",1),
		//		new Ciudad("Junin",3),new Ciudad("Puno",4),
		//		new Ciudad("Cuzco",5),
		//				};
		
		
		//Turista t1 = new Turista("Carlos","Peru",ruta_new);
		//  1 2 4 3 
		//  1 2 3 4 
		//int count = mergeCount(A,B);
		//System.out.print(count);
		int[][]rpta = sortCount(B);
		System.out.println(rpta[0][0]);
		System.out.print(Arrays.toString(rpta[1]));
	}
	
	public static int[][] sortCount(int[] L) {
		int rA = 0,r = 0;
		int rB = 0;
		
		if(L.length == 1) {
			int[][] m = {{0},L};
			return m;
		}else {
			int part1 = L.length/2;
			int[] A = new int[part1];
			int[] B = new int[L.length - part1];
			
			for(int i = 0; i <  part1; i++) {
				A[i] = L[i];  
			}
			for(int i = part1; i < L.length; i++) {
				B[i-part1] = L[i];
			}
			int[][]recepA = sortCount(A);
			rA = recepA[0][0];
			A = recepA[1];
			int[][]recepB = sortCount(B);
			rB = recepB[0][0];
			B = recepB[1];
			int[][]recepC = mergeCount_2(A,B);
			r = recepC[0][0];
			L = recepC[1];
			
		}
		r = r + rA + rB;
		
		int[][] rpta = {{r},L};
		return rpta;
		
	}
	
	public static int[][] mergeCount_2(int[]A,int[]B){
		int[] C = new int[A.length+B.length];
		int i =0;
		int count = 0;
		while(A.length !=0 && B.length != 0) {
			int elem1 = A[0];
			int elem2 = B[0];
			if(elem2 < elem1) {
				C[i] = elem2;
				B = heaDelete(B);
				count++;
			}else {
				C[i] = elem1;
				A = heaDelete(A);
			}
			i++;
		}
		if(A.length != 0) {
			for(int k = 0; k < A.length; k++) {
				C[i] = A[k];
				i++;
			}
		}
		if(B.length != 0) {
			for(int k = 0; k < B.length; k++) {
				C[i] = B[k];
				i++;
			}
		}
		int[][] rpta = {{count},C};
		return rpta;		
	}
	public static int[] heaDelete(int []A) {
		int[] r = new int[A.length-1];
		for(int i = 1; i < A.length; i++) {
			r[i-1] = A[i];
		}
		return r;
	}
	public static int[][] mergeCount(int[]A, int[]B) {
		int[] C = new int[A.length+B.length];
		int pointer1 = 0;
		int pointer2 = 0;
		int count = 0;
		int i = 0;
		int numA = A.length;
		int numB = B.length;
		while(numA != 0 && numB != 0) {
			int elem1 = A[pointer1];
			int elem2 = B[pointer2];
			if(elem2 < elem1) {
				C[i] = elem2;
				count++;
				pointer2++;
				numB--;
			}else {
				C[i] = elem1;
				pointer1++;
				numA--;
			}
			i++;
		}
		if(numA != 0) {
			for(int k = pointer1; k < A.length; k++, i++) {
				C[i] = A[k];
			}
		}
		if(numB != 0) {
			for(int k = pointer2; k < A.length; k++, i++) {
				C[i] = B[k];
			}
		}
		int[][] rpta = {{count},C};
		return rpta;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
