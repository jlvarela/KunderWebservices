package com.kundersample.kundersample1.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representación para estructura JSON
 * utilizada como variable de entrada para servicio REST WordService
 * 
 * @author jvarela
 */
@XmlRootElement 
public class WordMessage {
    
    /*
     * Valor de la palabra del mensaje
     */
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
