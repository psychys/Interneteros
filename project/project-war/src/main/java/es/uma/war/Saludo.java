package es.uma.war;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Saludo {
    public String getSaludo(){
        return "Hola mundo";
    }
}
