
package edu.eci.arem.springboot;

import java.lang.reflect.Method;

public class MicroJunit {
    public static void main(String...args) throws ClassNotFoundException{
        String classname= args[0];
        Class c = Class.forName(classname);
        Method[] declaredMethods = c.getDeclaredMethods();

        int passed = 0;
        int failed=0;
        for (Method m: declaredMethods) {
            if(m.isAnnotationPresent(Test.class)){
                try{
                    System.out.println("invoke"+m.getName()+"in class"+c.getName());
                    m.invoke(null);
                    passed += 1;
                }catch (Exception e){
                    failed +=1;

                }

            }
        }
        System.out.println("passed " + passed + " failed "+failed);
    }

}