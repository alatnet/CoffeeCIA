/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeecia.java.tools;

import coffeecia.java.CoffeeCIA_MAIN;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alatnet
 */
public class Builder {
    private final Ticket ticket;
    
    public Builder(Ticket ticket){ this.ticket = ticket; }
    
    //build cia from build directory and place cia in output directory.
    public int build(String buildDir, String outputDir) throws IOException, InterruptedException{
        File oDir = new File(outputDir);
        if (!oDir.exists()) oDir.mkdir();
        
        
        Logger.getGlobal().log(Level.INFO, "Building "+ticket.titleIDStr+".cia");
        
        String rawdir = buildDir + File.separatorChar + ticket.titleIDStr;
        String ciadir = outputDir + File.separatorChar + ticket.titleIDStr + ".cia";
        
        ProcessBuilder pb = new ProcessBuilder(CoffeeCIA_MAIN.makecdncia,rawdir,ciadir);
        
        Process p = pb.start();
        
        try (BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String stdoutStr = stdout.readLine();
            while (stdoutStr != null){
                Logger.getGlobal().log(Level.INFO, stdoutStr);
                stdoutStr = stdout.readLine();
            }
        }
        
        p.waitFor();
        
        int exitValue = p.exitValue();
        
        if (exitValue != 0){
            Logger.getGlobal().log(Level.INFO, "Building "+ticket.titleIDStr+".cia errored!");
        }else{
            Logger.getGlobal().log(Level.INFO, "Building "+ticket.titleIDStr+".cia done!", ticket.titleIDStr);
        }
        
        return exitValue;
    }
}
