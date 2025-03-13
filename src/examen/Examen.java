package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Examen {

	// Dado un número entero n, implemente una función productoImpares que calcule el producto de 
	// los primeros n números impares. No olvide validar que el parámetro n es positivo y que n > 0.
	public static int ProductoImpares(int n) {
		// Validación de que n es positivo y mayor que cero
        if (n <= 0) {
            throw new IllegalArgumentException("El valor de n debe ser positivo y mayor que cero.");
        }

        int producto = 1;
        int numeroImpar = 1;
        
        for (int i = 0; i < n; i++) {
            producto *= numeroImpar;
            numeroImpar += 2; // Aumentamos de dos en dos para obtener el siguiente número impar
        }

        return producto;
    }

	
	public static double sumaGeometricaAlternada(int k,double a1, double r) {
		if (k <=0) {
			throw new  IllegalArgumentException("𝑘 debe ser mayor que 0, 𝑎1 y 𝑟 deben ser positivos");
		}
		double suma = 0;
		
		for(int n = 1; n < k; n++) {
			suma += Math.pow(-1,n)*a1*Math.pow(r, n-1);
		}
		return suma;
	}
	
	public static int factorial(int n) {
		int producto = 1;
		for(int i = 0; i <n;i++) {
			producto *= n - i;
		}
		return producto;
	}
	
	public static int combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k || n <= 0 || k <= 0) {
            throw new IllegalArgumentException("Los valores deben cumplir n ≥ k y ambos ser positivos");
        }
        
        int numerador = 1, denominador = 1;
        for (int i = 0; i < k; i++) {
            int num = n - i;
            int den = k - i;
            
            if (num % 3 != 0) {
                numerador *= num;
            }
            if (den % 3 != 0) {
                denominador *= den;
            }
        }
        
        return numerador / denominador;
    }
	
	public static List<String> filtrarLineasConsecutivas(String fileName, List<String> palabrasClave){
		List<String> lineasFiltradas = new ArrayList<>();
        Set<String> palabrasSet = new HashSet<>(palabrasClave);
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+"); // Separar por espacios
                for (int i = 0; i < palabras.length - 1; i++) {
                	// Limpiar puntuación
                    String palabra1 = palabras[i].replaceAll("\\W", "").toLowerCase();
                    String palabra2 = palabras[i + 1].replaceAll("\\W", "").toLowerCase();
                    if (palabrasSet.contains(palabra1) && palabrasSet.contains(palabra2)) {
                        lineasFiltradas.add(linea);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return lineasFiltradas;
    }
	
}
