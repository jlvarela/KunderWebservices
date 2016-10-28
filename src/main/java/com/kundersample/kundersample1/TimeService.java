package com.kundersample.kundersample1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 * 
 * Time Service
 * 
 * Retorna fecha y hora en formato UTC ISO en base
 * a hora ingresada por parámetro.
 *
 * @author jvarela
 */
@Path("time")
public class TimeService {

    @Context
    private UriInfo context;
    
    /*
     * Formateador de hora de entrada
     */
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        
    /*
     * Formateador de fecha de salida
     */
    SimpleDateFormat dfOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sZ");
    
    public TimeService() {
    }

    /**
     * Obtiene valor value como parámetro URL. Retorna fecha/hora en formato UTC.
     * Método GET
     * Produce resultado en formato JSON
     * 
     * @param String value Valor de entrada
     * @return Response Respuesta de webservice en formato JSON
     */
    @GET
    @Produces("application/json")
    public Response getString(@QueryParam("value") String value) {

        String resp;
        String data = "";           // Data de salida
        String code = "00";         // Código de salida
        String desc = "OK";         // Descripción de salida
        Status status;             // Código de HTTP Status Code
        try {
            Calendar now = new GregorianCalendar();                                      // Fecha actual
            Calendar paramDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));    // Fecha de parámetro
            
            now.setTime(new Date());                        // Obteniendo fecha actual
            paramDate.setTime(df.parse(value));             // Parseo de parámetro de entrada
            
            // Seteando fecha y hora de salida
            paramDate.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
            
            // Fecha/hora de salida en string
            data = dfOut.format(paramDate.getTime());
            
            // Status de salida
            status = Status.OK;
            
        } catch (Exception ex) {
            // Logueo en caso de error
            Logger.getLogger(TimeService.class.getName()).log(Level.SEVERE, null, ex);
            
            code = "01";                            // Código de error
            desc = "NOT FORMAT HH:mm:ss";           // Descripción de error
            status = Status.BAD_REQUEST;          // Status de error. 400 Bad-request
        }
        
        // Salida en formato JSON
        resp = "{\"code\":\""+code+"\",\"description\":\""+desc+"\",\"data\":\""+data+"\"}";
        
        return Response.ok(resp, MediaType.APPLICATION_JSON).status(status).build();
    }

}
