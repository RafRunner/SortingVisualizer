package main;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

public class ArrayHandler {

	private static int interval;

	public static void shuffle(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		interval = 8;
		
		Random rng = new Random();
		
		for(int i = 0; i < Values.length; i++) {
			int index = rng.nextInt(i + 1);
			int aux = Values[index];
			Values[index] = Values[i];
			Values[i] = aux;
			
			colorSwap(index, i, panel);
			panel.repaint();
			Thread.sleep(interval);
		}
		panel.repaint();
	}
	
	public static void colorSwap(int indexA, int indexB, ArrayDrawer panel) 
	{
		panel.addRecolor(indexA, indexA, Color.RED);
		panel.addRecolor(indexB, indexB, Color.RED);
	}
	
	public static void finish(int numberOfValues, ArrayDrawer panel) throws InterruptedException
	{
		interval = 8;
		
		for(int i = 0; i < numberOfValues - 1; i++) {
			panel.addRecolor(0, i + 1, Color.GREEN);
			panel.repaint();
			Thread.sleep(interval);
		}
		panel.repaint();
	}
	
	public static void selectionSort(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();

        for (int i = 0; i < Values.length-1; i++){
            int min_idx = i;
            for (int j = i+1; j < Values.length; j++)
                if (Values[j] < Values[min_idx])
                    min_idx = j;

            int temp = Values[min_idx];
            Values[min_idx] = Values[i];
            Values[i] = temp;
            
            colorSwap(min_idx, i, panel);
			panel.repaint();
			Thread.sleep(interval);
        }
        finish(Values.length, panel);
        panel.repaint();
	}

	public static void bubbleSort(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();
		int i, j, temp;
		boolean swapped;
		for (i = 0; i < Values.length - 1; i++) {
			swapped = false;
			for (j = 0; j < Values.length - i - 1; j++) {
				if (Values[j] > Values[j + 1]) {

					temp = Values[j];
					Values[j] = Values[j + 1];
					Values[j + 1] = temp;
					swapped = true;
					
					colorSwap(j, i, panel);
					panel.repaint();
					Thread.sleep(interval);
				}
			}

			if (!swapped) {
				break;
			}
    	}
		finish(Values.length, panel);
        panel.repaint();
    }
	
	private static void merge(int arr[], int l, int m, int r, ArrayDrawer panel) throws InterruptedException 
    { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
        int i = 0, j = 0; 
  
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } 
            else{ 
                arr[k] = R[j]; 
                j++; 
            } 
            k++;
            
            colorSwap(k, j, panel);
			panel.repaint();
			Thread.sleep(interval);
        } 
  
        while (i < n1) { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }
	
	public static void mergeSort(int[] Values, int l, int r, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();
		if (l < r) { 
            int m = (l+r)/2; 

            mergeSort(Values, l, m, panel); 
            mergeSort(Values, m+1, r, panel);
 
            merge(Values, l, m, r, panel); 
        }
	}
	
	public static void insertionSort(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();
        int n = Values.length;
        for (int i=1; i<n; ++i){
        	
            int key = Values[i];
            int j = i-1;
 
            while (j>=0 && Values[j] > key)
            {
            	Values[j+1] = Values[j];
                j = j-1;
                
                colorSwap(j, i, panel);
    			panel.repaint();
    			Thread.sleep(interval);
            }
            Values[j+1] = key;
        }
        finish(Values.length, panel);
        panel.repaint();
	}
	
	private static int partition(int[] arr, int low, int high, ArrayDrawer panel) throws InterruptedException
    { 
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr[j] <= pivot) { 
               
            	i++;  
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp;
                
                colorSwap(j, i, panel);
				panel.repaint();
				Thread.sleep(interval);
            } 
        } 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    }
	
	public static void quickSort(int[] Values, int low, int high, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();
		if (low < high) {
            int pi = partition(Values, low, high, panel); 
  
            quickSort(Values, low, pi-1, panel); 
            quickSort(Values, pi+1, high, panel); 
        } 
	}
	
	private static void heapify(int arr[], int n, int i, ArrayDrawer panel) throws InterruptedException
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        if (largest != i) {
        	
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            colorSwap(0, i, panel);
			panel.repaint();
			Thread.sleep(interval);
 
            heapify(arr, n, largest, panel);
        }
    }
	
	public static void heapSort(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		interval = MainWindow.getInterval();
		int n = Values.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(Values, n, i, panel);

        for (int i=n-1; i>=0; i--) {

            int temp = Values[0];
            Values[0] = Values[i];
            Values[i] = temp;
            
            colorSwap(0, i, panel);
			panel.repaint();
			Thread.sleep(interval);

            heapify(Values, i, 0, panel);
        }
        finish(Values.length, panel);
        panel.repaint();
	}
	
	private static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
 
	 private static void countSort(int arr[], int n, int exp, ArrayDrawer panel) throws InterruptedException
	    {
	        int output[] = new int[n]; // output array
	        int i;
	        int count[] = new int[10];
	        Arrays.fill(count,0);
	 
	        for (i = 0; i < n; i++)
	            count[ (arr[i]/exp)%10 ]++;

	        for (i = 1; i < 10; i++)
	            count[i] += count[i - 1];
	 
	        for (i = n - 1; i >= 0; i--) {
	            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
	            count[ (arr[i]/exp)%10 ]--;
	        }
	        for (i = 0; i < n; i++) {
	            arr[i] = output[i];
	            
	            colorSwap(0, i, panel);
				panel.repaint();
				Thread.sleep(interval);
	        }
	    }
	 
	 public static void radixSort(int[] Values, int n, ArrayDrawer panel) throws InterruptedException
	    {
			interval = MainWindow.getInterval();
			int m = getMax(Values, n);

			for (int exp = 1; m/exp > 0; exp *= 10) {
				countSort(Values, n, exp, panel);
			}

			finish(Values.length, panel);
			panel.repaint();
	    }
	 
	 public static void shellSort(int[] Values, ArrayDrawer panel) throws InterruptedException
	    {
	    	interval = MainWindow.getInterval();
	        int n = Values.length;

	        for (int gap = n/2; gap > 0; gap /= 2) {

	            for (int i = gap; i < n; i += 1) {
	                int temp = Values[i];

	                int j;
	                for (j = i; j >= gap && Values[j - gap] > temp; j -= gap) {
	                	Values[j] = Values[j - gap];
	                	
	                	colorSwap(j, j - gap, panel);
	    				panel.repaint();
	    				Thread.sleep(interval);
	                }

	                Values[j] = temp;
	            }
	        }
	        finish(Values.length, panel);
	        panel.repaint();
	    }
}
