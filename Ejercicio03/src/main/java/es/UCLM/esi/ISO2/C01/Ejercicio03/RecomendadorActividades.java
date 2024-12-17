package es.UCLM.esi.ISO2.C01.Ejercicio03;

public class RecomendadorActividades {

    /**
     * Verifica si el usuario está sano y puede realizar actividades
     * parámetro: Usuario que se evalúa
     * devuelve true si el usuario está sano y cumple con las condiciones básicas
     */
    public static boolean usuarioValido(Usuario usuario) {
        boolean res = false;
        if (usuario.isSano() && usuario.isCovidSuperado() && usuario.isTieneCartillaVacunacion()) {
            res = true;
        }
        return res;
    }

    /**
     * Genera una recomendación de actividad para el usuario según las condiciones
     * parámetro: usuario Usuario que se evalúa
     * parámetro: espacio Espacio de ocio donde se evaluará la actividad
     * devuelve la actividad recomendada según las condiciones
     */
    public String recomendacionActividad(Usuario usuario, EspacioDeOcio espacio) {
        // Verifica si el usuario cumple las condiciones básicas
        if (!usuarioValido(usuario)) {
            return "No puede realizar ninguna actividad.";
        }

        // Obtene las condiciones meteorológicas del espacio
        PronosticoMeteorologico pronostico = espacio.getPronostico();

        // Analiza condiciones específicas basadas en el clima
        if (esClimaNevado(pronostico)) {
            return "Quédese en casa.";
        }

        if (esClimaEsquiable(pronostico)) {
            if (espacio.getAforoEsqui() > 0) {
                return "Puede ir a esquiar.";
            } else {
                return "No se puede esquiar, aforo completo.";
            }
        }

        if (esClimaSenderismo(pronostico)) {
            if (espacio.getAforoSenderismo() > 0) {
                return "Puede ir a hacer senderismo.";
            } else {
                return "No puede hacer senderismo, aforo completo.";
            }
        }

        if (esClimaTurismo(pronostico)) {
            if (!espacio.tieneRestricciones()) {
                return "Puede ir a hacer turismo al aire libre.";
            } else {
                return "No puede hacer turismo, hay restricciones de confinamiento.";
            }
        }

        if (esClimaCañas(pronostico)) {
            if (espacio.getAforoEstablecimientos() > 0) {
                return "Puede irse de cañas.";
            } else {
                return "No puede irse de cañas, aforo completo.";
            }
        }

        if (esClimaPlayaPiscina(pronostico)) {
            if (espacio.getAforoPiscina() > 0) {
                return "Puede ir a la playa o a la piscina.";
            } else {
                return "No puede ir a la piscina, aforo completo.";
            }
        }

        return "No hay recomendaciones específicas.";
    }

    
    // Métodos auxiliares para evaluar las condiciones 

    private boolean PrecipitacionesNieveLLuvia(PronosticoMeteorologico pronostico) {
    	boolean resultado = false;
    	if(pronostico.getPrecipitaciones() == Precipitaciones.nieve || pronostico.getPrecipitaciones() == Precipitaciones.lluvia) {
    		resultado = true;
    	}
    	return resultado;
    }
    
    private boolean NoPrecipitacionesNieveLLuvia(PronosticoMeteorologico pronostico) {
    	boolean resultado = false;
    	if(pronostico.getPrecipitaciones() != Precipitaciones.nieve || pronostico.getPrecipitaciones() != Precipitaciones.lluvia) {
    		resultado = true;
    	}
    	return resultado;
    }
    
    private boolean NoPrecipitacionesNubesLLuvia(PronosticoMeteorologico pronostico) {
    	boolean resultado = false;
    	if(pronostico.getPrecipitaciones() != Precipitaciones.nubes || pronostico.getPrecipitaciones() != Precipitaciones.lluvia) {
    		resultado = true;
    	}
    	return resultado;
    }
    
    private boolean TemperaturaTurismo(PronosticoMeteorologico pronostico) {
    	boolean resultado = false;
    	if(pronostico.getTemperatura() >= 15 && pronostico.getTemperatura() < 25) {
    		resultado = true;
    	}
    	return resultado;
    }
    
    boolean esClimaNevado(PronosticoMeteorologico pronostico) {
        boolean resultado = false;
    	if(pronostico.getTemperatura() < 0
                && pronostico.getPorcentajeHumedadRelativa() < 15
                && PrecipitacionesNieveLLuvia(pronostico)){
    		resultado = true;
    	}
    	return resultado;
    }

    boolean esClimaEsquiable(PronosticoMeteorologico pronostico) {
        boolean resultado = false;
    	
    	if(pronostico.getTemperatura() < 0
                && pronostico.getPorcentajeHumedadRelativa() < 15
                && NoPrecipitacionesNieveLLuvia(pronostico)) {
    		resultado = true;
    	}
    	return resultado;
    }
    	
    

     boolean esClimaSenderismo(PronosticoMeteorologico pronostico) {
        boolean resultado = false;
    	if(pronostico.getTemperatura() >= 0 && pronostico.getTemperatura() < 15
                && pronostico.getPrecipitaciones() != Precipitaciones.lluvia) {
    		resultado = true;
    	}
    	return resultado;
    }

    
     boolean esClimaTurismo(PronosticoMeteorologico pronostico) {
       boolean resultado = false;
       if(TemperaturaTurismo(pronostico)
                && (NoPrecipitacionesNubesLLuvia(pronostico))
                && pronostico.getPorcentajeHumedadRelativa() <= 60) {
    	   resultado = true;
       }
       return resultado;
    }

     boolean esClimaCañas(PronosticoMeteorologico pronostico) {
        boolean resultado = false;
        if(pronostico.getTemperatura() >= 25 && pronostico.getTemperatura() < 35
                && pronostico.getPrecipitaciones() != Precipitaciones.lluvia) {
        	resultado = true;
        }
        return resultado;
    }

     boolean esClimaPlayaPiscina(PronosticoMeteorologico pronostico) {
        boolean resultado = false;
    	if(pronostico.getTemperatura() > 30
                && pronostico.getPrecipitaciones() != Precipitaciones.lluvia) {
    		resultado = true;
    	}
    	return resultado;
    }
}
