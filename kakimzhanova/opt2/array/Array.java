package Opt12.kakimzhanova.opt2.array;
import java.util.Scanner;

public class Array implements Cloneable{
	private int[] arr;
	private int[] lengthArr;

	public Array(final int N){	//constructor
		arr = new int[N];
		lengthArr = new int[N];
	}
	public void setArr(int i, int elem){ // also calculates number of digits
		arr[i] = elem;
		lengthArr[i] = String.valueOf(Math.abs(elem)).length();
	}
	public int getArr(int i){
		return arr[i];
	}

	public void readArray(Scanner scan){
		System.out.println("Please type array elements and press <Enter>");
		for (int i = 0; i < arr.length; i++){
			setArr(i, scan.nextInt());
		}
	}
	public void printArray(){ // also prints length in brackets
		for (int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+"("+lengthArr[i]+")"+" ");
		}
		System.out.println();
	}

	public void printShortest(){	//prints the sortest number in array
		int minLength = lengthArr[0];
		int shortest = arr[0];
		for (int i = 1; i < arr.length; i++){
			if (lengthArr[i] < minLength){
				minLength = lengthArr[i];
				shortest = arr[i];
			}
		}
		System.out.println("Shortest: " + shortest + "(" + minLength + ")");
	}
	public void printLongest(){ // //prints the longest number in array
		int maxLength = lengthArr[0];
		int longest = arr[0];
		for (int i = 1; i < arr.length; i++){
			if (lengthArr[i] > maxLength){
				maxLength = lengthArr[i];
				longest = arr[i];
			}
		}
		System.out.println("Longest: " + longest + "(" + maxLength + ")");
	}
	
	public void printSortedByLength(){ // sorts array by length of numbers and prints
		int tmp;
		boolean isNotSorted = true;
		while (isNotSorted){
			isNotSorted = false;
			for (int i = 1; i < arr.length; i++){
				if (lengthArr[i-1] > lengthArr[i]){
					isNotSorted = true;
					tmp = arr[i-1];
					setArr(i-1, arr[i]);
					setArr(i, tmp);
				}
			}
		}
		System.out.println("Sorted by length");
		printArray();
	}

	public void printShorterThanAverage(){	//calculates average length and prints all shorter numbers
		int lengthSum = 0;
		for (int i = 0; i < arr.length; i++){
			lengthSum += lengthArr[i];
		}
		double avg = (double)(lengthSum)/arr.length;
		System.out.println("Average length =" + avg + "\nPrinting shorter than average elements");
		for (int i = 0; i < arr.length; i++){
			if ((double)(lengthArr[i]) < avg){
				System.out.print(arr[i]+"("+lengthArr[i]+")"+" ");
			}
		}
		System.out.println();
	}

	public int[] getIntDigitsArray(int a){	//calculates number of digits in a
		int length = String.valueOf(Math.abs(a)).length();
	 	int[] digits = new int[length];
	 	for (int l = length - 1; l >= 0; l--){
	 		digits[l] = a % 10;
	 		a /= 10;
	 	}
	 	return digits;
	}
	public void printIncreasingDigits(){ //prints the first number in array with increasing digits sequence
		boolean isDecreasing;
		for (int i = 0; i < arr.length; i++){
			if (lengthArr[i] > 1){
				int[] digits = getIntDigitsArray(arr[i]);
				isDecreasing = false;
				for (int j = 1; j < digits.length; j++){	
					if (digits[j] <= digits[j-1]){
						isDecreasing = true;
						break;
					}
				}
				if (!isDecreasing){
					System.out.println("Number with increasing digits: "+ arr[i]);
					return;
				}
			}
		}
		System.out.println("Element with increasing numbers not found");		

	}

	public Object clone(){ //for clonable interface
		Array copy = null;
		try{
			copy = (Array)super.clone();
			copy.arr = (int [])arr.clone();
			copy.lengthArr = (int [])lengthArr.clone();
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}
	public static void main(String[] args){
		System.out.println("Please type number of array elements and press <Enter>");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Array numbers = new Array(n);
		numbers.readArray(scan);
		scan.close();

		numbers.printShortest();
		numbers.printLongest();

		Array copy = (Array)numbers.clone();
		copy.printSortedByLength();
		numbers.printShorterThanAverage();
		numbers.printIncreasingDigits();
		
	}
}