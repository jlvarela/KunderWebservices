package com.kundersample.kundersample1;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Configuración de aplicación Web Service.
 * Se utiliza como ruta base la dirección webresources
 * 
 * @author jvarela
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Agrega Resources disponibles en aplicación REST
     * 
     * @param Set resources Recursos REST
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.kundersample.kundersample1.TimeService.class);
        resources.add(com.kundersample.kundersample1.WordService.class);
    }
    
}
