package edu.eci.arem.springboot;

public class WebServices {
    @RequestMapping("/hello")
    public static String helloWorld(){
        return "hello world";
    }

    @RequestMapping("/status")
    public static String serverStatus(){
        return "Running";
    }

    @RequestMapping("/index.html")
    public static String serverIndex(){
        return "Mi nombre es Laura";
    }

}
