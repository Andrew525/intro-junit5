package guru.springframework;

/**
 * Created by jt on 10/10/18.
 */
public class Greating {

    private static final String HELLO = "Hello";
    private static final String WORLD = "World";
    public static final String ID = "Greeting_ID";

    public String helloWorld(){
        return HELLO + " " + WORLD;
    }

    public String helloWorld(String name){
        return HELLO + " " + name;
    }
}
