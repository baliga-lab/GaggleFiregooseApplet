import netscape.javascript.JSObject;

import javax.swing.*;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created with IntelliJ IDEA.
 * User: Ning Jiang
 * Date: 2/6/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class GaggleFiregooseApplet extends JApplet {
    JSObject browser = null;
    FireGoose goose;

    public void init(){
        browser = JSObject.getWindow(this);
    }

    // Stop and destroy
    public void stop(){

    }

    public void destroy(){

    }


    public FireGoose getGoose()
    {
        return goose;
    }


    // Main
    // Note: This method loops over and over to handle requests becuase only
    //       this thread gets the elevated security policy.  Java == stupid.
    public void start(){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    System.out.println("Loading FireGoose...");
                    goose = new FireGoose();

                    System.out.println("Calling plugin...");
                    browser.call("appletloaded", null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        });
    }
 }
