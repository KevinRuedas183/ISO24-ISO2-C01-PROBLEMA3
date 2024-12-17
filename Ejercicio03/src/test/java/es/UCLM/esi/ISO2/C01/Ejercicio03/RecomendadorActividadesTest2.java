package es.UCLM.esi.ISO2.C01.Ejercicio03;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RecomendadorActividadesTest2 {

    private RecomendadorActividades recomendador; // Clase a testear
    private Usuario usuario;                      // Usuario de prueba
    private EspacioDeOcio espacio;                // Espacio de ocio
    private PronosticoMeteorologico pronostico;   // Pronóstico meteorológico

    @Before
    public void setUp() throws Exception {
        // Configuración inicial antes de cada prueba
        recomendador = new RecomendadorActividades();
        usuario = new Usuario(00000000, 'A', false, false, false, false);
        espacio = new EspacioDeOcio(0, 0, false, 0, 0, pronostico);
        pronostico = new PronosticoMeteorologico(0, 0, null);
    }
/*
 * TEST MCDC
 */
    /* test 1 (salida a true)*/
    @Test 
    public void testRecomendacionActividadTRUE() throws Exception {
        // Configuración del usuario (usuario válido)
        usuario.setSano(true);
        usuario.setCovidSuperado(true);
        usuario.setTieneCartillaVacunacion(true);

        // Configuración del pronóstico (clima adecuado para senderismo)
        pronostico.setTemperatura(15); // 15 grados Celsius
        pronostico.setPorcentajeHumedadRelativa(50);
        pronostico.setPrecipitaciones(Precipitaciones.nubes);
        espacio.setPronostico(pronostico);

        // Configuración del espacio (aforo disponible para senderismo)
        espacio.setAforoSenderismo(10);

        // Ejecución del método a testear
        String resultado = recomendador.recomendacionActividad(usuario, espacio);

        // Verificación del resultado esperado
        assertEquals("Puede ir a hacer senderismo.", resultado);
    }
    /* test 2 (salida a false)*/
    @Test 
    public void testRecomendacionActividadFALSE() throws Exception {
        /*Configuración del usuario (usuario no válido)
    	no ponemos nada ya que en el before ya pusimos a false todo (lo que
    	justo necesitamos aqui*/

        // segunda decision
        pronostico.setTemperatura(10); // 15 grados Celsius
        pronostico.setPorcentajeHumedadRelativa(25);
        pronostico.setPrecipitaciones(Precipitaciones.nubes);
        espacio.setPronostico(pronostico);
        
        
        // tercera decision 

        // Configuración del espacio (aforo disponible para senderismo)
        espacio.setAforoSenderismo(10);

        // Ejecución del método a testear
        String resultado = recomendador.recomendacionActividad(usuario, espacio);

        // Verificación del resultado esperado
        assertEquals("Puede ir a hacer senderismo.", resultado);
    }
}
