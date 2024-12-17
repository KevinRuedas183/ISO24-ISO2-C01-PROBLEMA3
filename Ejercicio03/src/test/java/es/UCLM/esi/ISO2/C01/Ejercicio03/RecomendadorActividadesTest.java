package es.UCLM.esi.ISO2.C01.Ejercicio03;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;


public class RecomendadorActividadesTest {
	

	private RecomendadorActividades recomendador; // Clase a testear
	private Usuario usuario; // Usuario de prueba
	private EspacioDeOcio espacio; // Espacio de ocio
	private PronosticoMeteorologico pronostico; // Pronóstico meteorológico

	@Before
	public void setUp() throws Exception {
		// Configuración inicial antes de cada prueba
		
		/*
		 * usuario = new Usuario(00000000, 'A', false, false, false, false);
		 * Esto es por si lo ponemos, si lo dejamos no hay que 
		 * crear un objeto usuario  luego, solo usar sets para dar valores
		 * Si no lo dejamos, hay que crear y poner valores a usuario.
		 * 
		 * Para mi, dejo eso si los boolean, el objeto recomendador,
		 * espacio y pronostico
		 */
		espacio = new EspacioDeOcio(0, 0, false, 0, 0, pronostico);
		pronostico = new PronosticoMeteorologico(0, 0, null);
		recomendador = new RecomendadorActividades();
		
		boolean climaNevado = false;
		boolean climaEsquiable = false;
		boolean climaSenderismo = false;
		boolean climaTurismo = false;
		boolean climaCañas = false;
		boolean climaPiscina = false;
	}
	
	

 //Primer método  usuarioValido 
    @Test
    public void UsuarioValidoFtest() throws Exception {
    	Usuario usuario=new Usuario (10504000, 'A', false, false, false, false);
    	boolean actual=RecomendadorActividades.usuarioValido(usuario);
    	boolean expected= false;
    	assertEquals(actual, expected);
    }
    @Test
    public void UsuarioValidoTtest() throws Exception {
    	Usuario usuario=new Usuario (10504000, 'A', true, true, true, true);
    	boolean actual=RecomendadorActividades.usuarioValido(usuario);
    	boolean expected= true;
    	assertEquals(actual, expected);
    }
    /*
	 * TEST MCDC
	 */
	/* test 2 (salida a true) */
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

	/* test 2 (salida a false) */
	@Test
	public void testRecomendacionActividadFALSE() throws Exception {
		/*
		 * Configuración del usuario (usuario no válido) no ponemos nada ya que en el
		 * before ya pusimos a false todo (lo que justo necesitamos aqui
		 */

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

	/* test 7 (salida a false) */
	@Test 
	public void esClimaNevadoTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(-10); 
		pronostico.setPorcentajeHumedadRelativa(10);
		pronostico.setPrecipitaciones(Precipitaciones.nubes); // nubes = no precipitaciones
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaNevado(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);
	}

	/* test 8 (salida a true) */
	@Test
	public void esClimaEsquiableTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(10); 
		pronostico.setPorcentajeHumedadRelativa(25);
		pronostico.setPrecipitaciones(Precipitaciones.nubes); // nubes = no precipitaciones
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaEsquiable(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);

		
	}

	/* test 9 (salida a true) */
	@Test
	public void esClimaSenderismoTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(-1); 
		// no hace falta humedad relativa ya que lo tenemos en el before inicializado
		pronostico.setPrecipitaciones(Precipitaciones.lluvia); // nubes = no precipitaciones
		
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaSenderismo(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);
	
		
	}

	/* test 10 (salida a true) 
	 * 
	 * AQUI FALTAN DATOS DE SUS TABLAS DE PRUEBA
	 * 
	 * */
	@Test
	public void esClimaTurismoTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(-10); 
		pronostico.setPorcentajeHumedadRelativa(10);
		pronostico.setPrecipitaciones(Precipitaciones.nieve); // nubes = no precipitaciones
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaTurismo(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);
	
		
	}

	/* test 11 (salida a true)
	 * 
	 *  AQUI Y EN EL TEST DE esClimaPiscinaTRUE SE SUPONE QUE 
	 *  PONIENDO NIEVE DA LO MISMO QUE PONIENDO 
	 *  NUBES, ME PARECE RARO PERO ES LO QUE PONE EN LAS TABLAS
	 *  
	 *  */
	@Test
	public void esClimaCañasTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(30); 
		pronostico.setPrecipitaciones(Precipitaciones.nieve);
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaCañas(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);
	
		
	}

	/* test 12 (salida a true) */
	@Test
	public void esClimaPiscinaTRUE() throws Exception {
	// Configuración del pronóstico
		pronostico.setTemperatura(35); 
		pronostico.setPrecipitaciones(Precipitaciones.nieve); 
		espacio.setPronostico(pronostico);
		
		boolean actual = recomendador.esClimaPlayaPiscina(pronostico);
		boolean expected = true;
		assertEquals(actual, expected);
	
		
	}
}
