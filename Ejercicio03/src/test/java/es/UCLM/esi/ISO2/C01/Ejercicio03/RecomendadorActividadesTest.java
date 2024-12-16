package es.UCLM.esi.ISO2.C01.Ejercicio03;

import org.junit.Test;
import static org.junit.Assert.*;


public class RecomendadorActividadesTest {

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
    
    //segundo metodo recomendacionActividad
    
    

}
