package com.kundersample.kundersample1;

import com.kundersample.kundersample1.entity.WordMessage;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 * 
 * Word Service
 * 
 * Retorna palabra de parámetro de entrada en letras mayúsculas (UpperCase).
 *
 * @author jvarela
 */
@Path("word")
public class WordService {

    @Context
    private UriInfo context;
    
    // Tamaño de mensaje de entrada
    public static final int MESSAGE_LENGTH= 4;

    public WordService() {
    }


    /**
     * Obtiene palabra en formato JSON. Devuelve palabra en mayúsculas.
     * Método POST
     * Consume entrada en formato JSON como mensaje POST
     * Produce resultado en formato JSON
     * 
     * @param WordMessage content Estructura JSON de entrada
     * @return Response Respuesta de webservice en formato JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(WordMessage content) {
        String data = "";               // Valor de salida. Palabra en mayúsculas
        String code;                    // Código de salida
        String desc;                    // Descripción de salida
        Status status;                 // Código de HTTP Status Code
        String resp;
        
        // Validaciones
        
        // Determina si palabra contiene sólo letras
        if(!isAlpha(content.getData())){
            code = "01";
            desc = "NOT TEXT";
            status = Status.BAD_REQUEST;
        }
        // Valida largo de la palabra de entrada
        else if (!hasRightLength(content.getData())){
            code = "02";
            desc = "NOT SIZE ".concat(String.valueOf(MESSAGE_LENGTH));
            status = Status.BAD_REQUEST;
        }
        // Caso correcto
        else {
            status = Status.OK;
            code = "00";
            desc = "OK";
            data = content.getData().toUpperCase();
        }
        
        // Salida en formato JSON
        resp = "{\"code\":\""+code+"\",\"description\":\""+desc+"\",\"data\":\""+data+"\"}";
        
        return Response.ok(resp, MediaType.APPLICATION_JSON).status(status).build();
    }
    
    /*
     * Valida si String contiene sólo letras
     *
     * @param String message Palabra a validar
     * @return boolean Resultado de la validación
     */
    public boolean isAlpha(String message) {
        return message.matches("[a-zA-Z]+");
    }
    
    /*
     * Valida correcto largo de una palabra
     *
     * @param String message Palabra a validar
     * @return boolean Resultado de la validación
     */
    public boolean hasRightLength(String message){
        return message.length()==MESSAGE_LENGTH;
        
    }
}
