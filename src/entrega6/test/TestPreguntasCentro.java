package entrega6.test;


import java.util.List;
import java.util.Map;
import entrega6.preguntas.*;

public class TestPreguntasCentro {

    public static void testPromedioProfesores() {
        try {
            System.out.println("Test Promedio Profesores:");
            System.out.println("Funcional: " + PreguntasCentro.promedioProfesoresFuncional("86283165E"));
            System.out.println("Imperativo: " + PreguntasCentro.promedioProfesoresImperativo("86283165E"));

            // Error esperado
            PreguntasCentro.promedioProfesoresFuncional("00000000X");
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testGrupoMayorDiversidadEdad() {
        System.out.println("Test Grupo Mayor Diversidad Edad:");
        System.out.println("Funcional: " + PreguntasCentro.grupoMayorDiversidadEdadFuncional());
        System.out.println("Imperativo: " + PreguntasCentro.grupoMayorDiversidadEdadImperativo());
        System.out.println();
    }

    public static void testAlumnoMasMatriculas() {
        System.out.println("Test Alumno Más Matrículas:");
        System.out.println("Funcional: " + PreguntasCentro.alumnoMasMatriculasFuncional());
        System.out.println("Imperativo: " + PreguntasCentro.alumnoMasMatriculasImperativo());
        System.out.println();
    }

    public static void testRangosEdadPorAlumno() {
        System.out.println("Test Rangos Edad por Alumno:");
        String rangos = "18-20, 21-25, 26-30";
        Map<String, List<us.lsi.centro.Alumno>> mapa1 = PreguntasCentro.rangosEdadPorAlumnoFuncional(rangos);
        Map<String, List<us.lsi.centro.Alumno>> mapa2 = PreguntasCentro.rangosEdadPorAlumnoImperativo(rangos);

        System.out.println("Funcional:");
        mapa1.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("Imperativo:");
        mapa2.forEach((k, v) -> System.out.println(k + ": " + v));

        try {
            PreguntasCentro.parsearRangos("18-22,21-30");
        } catch (Exception e) {
            System.out.println("Error esperado por solapamiento: " + e.getMessage());
        }

        try {
            PreguntasCentro.parsearRangos("18-a");
        } catch (Exception e) {
            System.out.println("Error esperado por formato: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testNombreProfesorMasGrupos() {
        System.out.println("Test Nombre Profesor Más Grupos:");
        System.out.println("Funcional: " + PreguntasCentro.nombreProfesorMasGruposFuncional(30, 60));
        System.out.println("Imperativo: " + PreguntasCentro.nombreProfesorMasGruposImperativo(30, 60));

        try {
            PreguntasCentro.nombreProfesorMasGruposFuncional(20, 50);
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testNombresAlumnosMayorNota() {
        System.out.println("Test Nombres Alumnos Mayor Nota:");
        System.out.println("Funcional: " + PreguntasCentro.nombresAlumnosMayorNotaFuncional(5, 2000));
        System.out.println("Imperativo: " + PreguntasCentro.nombresAlumnosMayorNotaImperativo(5, 1980));

        try {
            PreguntasCentro.nombresAlumnosMayorNotaFuncional(0, 2005);
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            PreguntasCentro.nombresAlumnosMayorNotaFuncional(null, 2005);
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        testPromedioProfesores();
        testGrupoMayorDiversidadEdad();
        testAlumnoMasMatriculas();
        testRangosEdadPorAlumno();
        testNombreProfesorMasGrupos();
        testNombresAlumnosMayorNota();
    }
}
