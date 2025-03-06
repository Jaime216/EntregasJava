package entrega1;


public class TestFunciones {
    public static void main(String[] args) {
        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 1:");
        System.out.println("El producto de 4 y 2 es: " + Funciones.producto_factorial(4, 2));

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 2:");
        System.out.println("El producto de la secuencia geométrica con a1 = 3, r = 5 y k = 2 es: " 
                           + Funciones.producto_secuencia_geometrica(3, 5, 2));

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 3:");
        System.out.println("El número combinatorio de 4 y 2 es: " + Funciones.numero_combinatorio(4, 2));

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 4:");
        System.out.println("El número S(n, k) siendo n = 4 y k = 2 es: " + Funciones.numero_S(4, 2));

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 5:");
        java.util.function.Function<Double, Double> f = x -> 2 * x * x;  // f(x) = 2x^2
        java.util.function.Function<Double, Double> df = x -> 4 * x;      // f'(x) = 4x
        double a = 3;
        double epsilon = 0.001;
        System.out.println("Resultado de la función 5 con a = " + a + " y e = " + epsilon 
                           + ", f(x) = 2x^2 y f'(x) = 4x: " + Funciones.metodo_newton(f, df, a, epsilon));
    }
}
