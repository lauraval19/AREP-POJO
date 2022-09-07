/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package edu.eci.arem.springboot;

import java.lang.reflect.Method;

/**
 *
 * @author laura.alvarado-g
 */
public class Springboot {

    public static void main(String...args) throws ClassNotFoundException{
        String classname= args[0];
        Class c = Class.forName(classname);
        Method[] declaredMethods = c.getDeclaredMethods();

        int numservices = 0;

        for (Method m: declaredMethods) {
            if(m.isAnnotationPresent(RequestMapping.class)){
               String path = m.getAnnotation(RequestMapping.class).value();
               Services.datos.put(path,m);
               numservices += 1;

            }
        }
        System.out.println("Numero de servicios pulicados:  " + numservices);
    }
}
