/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeecia.java;

import coffeecia.java.UI.MainFrame;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import java.util.ArrayList;

/**
 *
 * @author alatnet
 */
public class CoffeeCIA_MAIN {
    //check to see if we are in a unix machine or windows.
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CoffeeCIA_MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*if (File.separatorChar == '/'){
            UNIX = true;
            makecdncia = "make_cdn_cia";
        }*/
    }
    
    public static boolean UNIX=false;
    public static String makecdncia = "make_cdn_cia.exe";
    public final static String titleXML = "3dsreleases.xml";
    public final static String buildDir = "cia_build";
    public final static String ciaDir = "cia";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (File.separatorChar == '/'){
            UNIX = true;
            makecdncia = "make_cdn_cia";
        }
        MainFrame mFrame = new MainFrame();
        mFrame.setVisible(true);
    }
    
}
