/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeecia.java.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alatnet
 */
public class TicketDB_File {
    private final ArrayList<Ticket> tickets;
    private long size=-1;
    private long currpos=0;
    private final String filestr;
    private final File file;
    private final boolean useFile;
    private final Object sync = new Object();
    private final Object sync2 = new Object();
    
    public TicketDB_File(String filestr){
        this.filestr = filestr;
        this.useFile = false;
        this.file = null;
        this.tickets = new ArrayList<>();
    }
    
    public TicketDB_File(File file){
        this.file = file;
        this.filestr = "";
        this.useFile = true;
        this.tickets = new ArrayList<>();
    }
    
    public void open(){
        File f = this.file;
        if (!this.useFile) f = new File(this.filestr);
        synchronized(sync2){ this.size = f.length(); }
        try (FileInputStream fis = new FileInputStream(f)) {
            
            byte b[] = new byte[1];
            byte ticketRoot[] = new byte[26];
            
            while (fis.read(b)!=-1){
                synchronized(sync){ this.currpos++; }
                if (b[0] == 'R'){ //found something. check if it's a ticket!
                    fis.skip(-1);
                    fis.read(ticketRoot);
                    String ticketRootString = new String(ticketRoot);
                    fis.skip(-26);
                    
                    if (ticketRootString.contains("Root-CA00000003-XS0000000c")){ //it's something! let's see if it's a ticket.
                        fis.skip(0xB1-0x01);
                        fis.read(b);
                        int commonKeyIndex = b[0];
                        fis.skip(-(0xB1-0x01));
                        if (commonKeyIndex > 5) continue; //not a ticket
                        
                        fis.skip(0x7C-0x01);
                        fis.read(b);
                        fis.skip(-(0x7C-0x01));
                        if (b[0] != 0x1) continue; //not a ticket
                        
                        byte ticketData[] = new byte[0x210-(-0x140)];
                       
                        fis.skip(-(0x140+0x02));
                        fis.read(ticketData);
                        synchronized(sync){ this.currpos+=(0x210-(-0x140)); }
                        
                        synchronized(sync2){ this.tickets.add(new Ticket(ticketData)); }
                    }else fis.skip(1);
                }
            }
            fis.close();
        }catch (FileNotFoundException e){
            Logger.getGlobal().log(Level.WARNING, "Cannot find file.\n" + e.toString());
        }catch (IOException e){
            Logger.getGlobal().log(Level.WARNING, "IO Error!\n" + e.toString());
        }
    }
    
    public ArrayList<Ticket> getTickets(){ synchronized(sync2){ return this.tickets; } }
    public int numTickets(){ synchronized(sync){ return this.tickets.size(); } }
    
    public long getPos() { synchronized(sync){return this.currpos; } }
    
    public final long getSize() { synchronized(sync2){ return this.size; } }
}
