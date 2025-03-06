package entrega1;
import java.util.function.Function;

public class Funciones {

	// Dados los números enteros 𝑛, 𝑘 con 𝑛 > 𝑘 diseñar una función que calcule el producto ∏ (𝑛 − 𝑖 + 1)𝑘 𝑖=0 
	public static int producto_factorial(int n, int k) {
		 if (n <= k) {
	            throw new IllegalArgumentException("n debe ser mayor que k");
	        }
        int producto = 1;
        for (int i = 0; i <= k; i++) {
            producto *= (n - i + 1);
        }
        return producto;
    }
	
	/*Diseña una función que calcule el producto de una secuencia geométrica, donde los términos se definen
	como 𝑎𝑛 = 𝑎1 ∙ 𝑟𝑛−1, con 𝑎1 como el término inicial, 𝑟 la razón, y 𝑛 la posición del término
	correspondiente. La función debe devolver el producto de los primeros 𝑘 términos. Por ejemplo, si 𝑘 =
	2, se debe devolver el producto de 𝑎1 ∙ 𝑎2.*/
	public static int producto_secuencia_geometrica(int a, int r, int k) {
		if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }

        int producto = 1;
        for (int i = 0; i < k; i++) {
            producto *= a * Math.pow(r, i);
        }
        return producto;
	}
	
	 // Función para calcular el factorial de un número
    public static long factorial(int num) {
        long resultado = 1;
        for (int i = 2; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }
	
	//Dados los números enteros 𝑛, 𝑘 con 𝑛 ≥ 𝑘 diseñar una función que calcule el número combinatorio (𝑛/𝑘).
    public static long numero_combinatorio(int n, int k) {
        if (n < k || k < 0) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k y k no puede ser negativo");
        }

        return factorial(n) / (factorial(k) * factorial(n - k));
    }
    
    // Dados los números enteros 𝑛, 𝑘 con 𝑛 ≥ 𝑘 diseñar una función que calcule el número 𝑆(𝑛, 𝑘)
    public static double numero_S(int n, int k) {
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k");
        }

        double sumatoria = 0;

        for (int i = 0; i < k; i++) {
            double combinatorio = numero_combinatorio(k + 1, i + 1);
            double potencia = Math.pow(k - i, n);
            sumatoria += Math.pow(-1, i) * combinatorio * potencia;
        }

        return sumatoria / factorial(k);
    }
    
    /*Dada una función 𝑓(𝑥), su derivada 𝑓′(𝑥𝑛), un valor inicial 𝑎 para 𝑥 y un error 𝜀, ambos de tipo float,
	encontrar 𝑥0 tal que |𝑓(𝑥0)| ≤ 𝜀 usando el método de Newton*/
    public static double metodo_newton(Function<Double, Double> f, Function<Double, Double> df, double a, double epsilon) {
        double x = a;
        int iteracionesMax = 1000; // Evita bucles infinitos
        int iteracion = 0;

        while (Math.abs(f.apply(x)) > epsilon && iteracion < iteracionesMax) {
            double derivada = df.apply(x);
            if (derivada == 0) {
                throw new ArithmeticException("La derivada se anuló, no se puede continuar con Newton-Raphson");
            }
            x = x - f.apply(x) / derivada;
            iteracion++;
        }

        return x;
    }
    
	// Método main para probar la función
    public static void main(String[] args) {
        int n = 4, k = 2;
        System.out.println("El producto de " + n + " y " + k + " es: " + producto_factorial(n, k));
        
        int a1 = 3, r = 5;
        k = 2;
        System.out.println("El producto de la secuencia geométrica con a1 = " + a1 +
                ", r = " + r + " y k = " + k + " es: " + producto_secuencia_geometrica(a1, r, k));
        
        n = 4; 
        k = 2;
        System.out.println("El número combinatorio de " + n + " y " + k + " es: " + numero_combinatorio(n, k));
        
        n = 4;
        k = 2;
        System.out.println("El número S(n, k) siendo n = " + n + " y k = " + k + " es: " + numero_S(n, k));
        
        
        Function<Double, Double> f = x -> 2 * x * x;  // f(x) = 2x^2
        Function<Double, Double> df = x -> 4 * x;      // f'(x) = 4x

        double a = 3;       // Valor inicial
        double epsilon = 0.001;  // Tolerancia

        double raiz = metodo_newton(f, df, a, epsilon);
        System.out.println("Resultado de la función con a = " + a + " y ε = " + epsilon + ": " + raiz);
    }
}
