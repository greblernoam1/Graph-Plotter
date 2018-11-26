/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;


public class ArrayMath {
	
	private ArrayMath(){};
	
	// printing a double array like [1, 2, 3, 4] with a line break after the closing bracket
	// also, static method setDisplayFormat(String format) that formats numerical output given 
	// a format specifier string (e.g. "%.2f" for two decimal places).
	private static String format = "%.3f";
	
	public static void setDisplayFormat(String format){
		ArrayMath.format = format;
	}
	
	public static void print(double[] a){ // I went ahead and made print() and println(). Only the latter was required.
		System.out.print("[");
		for (int  i = 0; i < a.length-1; i++){
			System.out.format(format + ", ", a[i]);
		}
		System.out.format(format + "]", a[a.length-1]);
	}
	
	public static void println(double[] a){
		print(a);
		System.out.print("\n");
	}
	
	
	// generating a double[] of zeros/ones
	public static double[] zeros(int N){
		return new double[N];
	}
	
	public static double[] ones(int N){
		double[] c = zeros(N);
		return add(c,1);
	}
	
	//	generating a double[]of N evenly spaced points between a and b (inclusive),
	public static double[] linspace(double a, double b, int N){
		double segmentLength = (b - a)/(N-1);
		double[] c = new double[N];
		for (int  i = 0; i < N; i++){
			c[i] = a + i*segmentLength;
		}
		return c;
	}
	
	// element-wise double[] addition, subtraction, multiplication, division, and power raising,
	public static double[] add(double[] a, double[] b){
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = a[i] + b[i];
		}
		return c;
	}
	
	public static double[] subtract(double[] a, double[] b){
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = a[i] - b[i];
		}
		return c;
	}
	
	public static double[] multiply(double[] a, double[] b){
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = a[i] * b[i];
		}
		return c;
	}
	
	public static double[] divide(double[] a, double[] b){
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = a[i] / b[i];
		}
		return c;
	}
	
	public static double[] pow(double[] a, double[] b){
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = Math.pow(a[i], b[i]);
		}
		return c;
	}
	
	public static double[] sin(double[] a){	// not required in HW
		double[] c = new double[a.length];
		for (int  i = 0; i < a.length; i++){
			c[i] = Math.sin(a[i]);
		}
		return c;
	}
	
	// finding the minimum/maximum element of a double[], and
	public static double min(double[] a){
		double minVal = a[0];
		for (int  i = 1; i < a.length; i++){
			minVal = Math.min(minVal,a[i]);
		}
		return minVal;
	}
	
	public static double max(double[] a){
		double maxVal = a[0];
		for (int  i = 1; i < a.length; i++){
			maxVal = Math.max(maxVal,a[i]);
		}
		return maxVal;
	}
	
	// finding the index corresponding with a minimum/maximum element of a double[].
	public static int imin(double[] a){
		double minVal = a[0];
		int imin=0;
		for (int  i = 1; i < a.length; i++){
			if (a[i] < minVal){
				imin = i;
				minVal= a[i];
			}
		}
		return imin;
	}
	
	public static int imax(double[] a){
		double maxVal = a[0];
		int imax=0;
		for (int  i = 1; i < a.length; i++){
			if (a[i] > maxVal){
				imax = i;
				maxVal= a[i];
			}
		}
		return imax;
	}
	
	// overload the element-wise array operation methods to accept a double, 
	// rather than a double[], as either input
	public static double[] add(double b, double[] a){
		return add(linspace(b,b,a.length), a);
	}
	
	public static double[] add(double[] a, double b){
		return add(b,a);
	}
	
	public static double[] subtract(double a, double[] b){
		return subtract(linspace(a,a,b.length), b);
	}
	
	public static double[] subtract(double[] a, double b){
		return subtract(a, linspace(b,b,a.length));
	}
	
	public static double[] multiply(double b, double[] a){
		return multiply(linspace(b,b,a.length), a);
	}
	
	public static double[] multiply(double[] a, double b){
		return multiply(b,a);
	}
	
	public static double[] divide(double a, double[] b){
		return divide(linspace(a,a,b.length), b);
	}
	
	public static double[] divide(double[] a, double b){
		return divide(a, linspace(b,b,a.length));
	}
	
	public static double[] pow(double a, double[] b){
		return pow(linspace(a,a,b.length), b);
	}
	
	public static double[] pow(double[] a, double b){
		return pow(a, linspace(b,b,a.length));
	}
}
