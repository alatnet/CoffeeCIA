/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeecia.java.UI;

import coffeecia.java.CoffeeCIA_MAIN;
import coffeecia.java.tools.Builder;
import coffeecia.java.tools.Downloader;
import coffeecia.java.tools.Ticket;
import coffeecia.java.tools.XMLTitleList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexander
 */
public class TitlesTableModel extends AbstractTableModel {
    final private String[] columnNames = { "TitleID", "ConsoleID", "Type", "Preinstalled", "Build", "Personal", "Patch", "Ignore", "Download", "Status" };
    final private String[] columnNamesXML = { "Title", "TitleID", "ConsoleID", "Type", "Preinstalled", "Build", "Personal", "Patch", "Ignore", "Download", "Status" };
    
    private XMLTitleList xmlTitles = null;
    private boolean usingXML = false;
    
    public TitlesTableModel(XMLTitleList xmlTitles){ this.setXMLTitles(xmlTitles); }
    
    public TitlesTableModel(){}
    
    private class Entry{
        public Ticket ticket;
        public boolean build=true,preinstalled=false,personal=false,patch=false,ignore=false,download=true;
        public String status="";
        public String title = "";
        
        public int getBitmask(){
            int ret = 0b000000;
            if (build) ret |= 0b000001;
            if (preinstalled) ret |= 0b000010;
            if (personal) ret |= 0b000100;
            if (patch) ret |= 0b001000;
            if (ignore) ret |= 0b010000;
            if (download) ret |= 0b100000;
            return ret;
        }
    }
    
    private final ArrayList<Entry> entries = new ArrayList<>();
    
    public void setXMLTitles(XMLTitleList xmlTitles){
        this.usingXML = true;
        this.xmlTitles = xmlTitles;
        
        entries.forEach((e)->{ e.title = xmlTitles.getTitle(e.ticket.titleID); });
        
        this.fireTableDataChanged();
    }
    
    public void addEntry(Ticket entry){
        Entry e = new Entry();
        e.ticket = entry;
        
        e.status = "Ready";
        
        if (entry.consoleIDStr.equals("00000000")) e.preinstalled = true;
        
        switch (entry.type){
            case System:
            case DSiSystemApp:
            case DSiSystemDataArchives:
            case Mystery:
                e.ignore = true;
                e.build = false;
                e.download = false;
                break;
            case Demo:
            case DLC:
                e.patch = true;
        }
        
        if (entry.badTicket){
            e.ignore = true;
            e.build = false;
            e.download = false;
            e.status = "Bad Ticket. (Ignored)";
        }
        
        if (this.usingXML){
            e.title = xmlTitles.getTitle(entry.titleID);
            //System.out.println(e.title + "=" + entry.titleIDStr);
        }
        
        entries.add(e);
        fireTableRowsInserted(entries.size() - 1, entries.size() - 1);
    }
    
    public void clear(){
        entries.clear();
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() { return entries.size(); }

    @Override
    public int getColumnCount() {
        if (usingXML) return columnNamesXML.length;
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (usingXML){
            switch (columnIndex){
                case 0:  //title
                    return this.entries.get(rowIndex).title;
                case 1:  //titleid
                    return this.entries.get(rowIndex).ticket.titleIDStr;
                case 2:  //consoleid
                    return this.entries.get(rowIndex).ticket.consoleIDStr;
                case 3:  //type
                    return this.entries.get(rowIndex).ticket.type;
                case 4:  //preinstalled
                    return this.entries.get(rowIndex).preinstalled;
                case 5:  //build
                    return this.entries.get(rowIndex).build;
                case 6:  //personal
                    return this.entries.get(rowIndex).personal;
                case 7:  //patch
                    return this.entries.get(rowIndex).patch;
                case 8:  //ignore
                    return this.entries.get(rowIndex).ignore;
                case 9:  //download
                    return this.entries.get(rowIndex).download;
                case 10:  //status
                    return this.entries.get(rowIndex).status;
            }
            return null;
        }
        switch (columnIndex){
            case 0:  //titleid
                return this.entries.get(rowIndex).ticket.titleIDStr;
            case 1:  //consoleid
                return this.entries.get(rowIndex).ticket.consoleIDStr;
            case 2:  //type
                return this.entries.get(rowIndex).ticket.type;
            case 3:  //preinstalled
                return this.entries.get(rowIndex).preinstalled;
            case 4:  //build
                return this.entries.get(rowIndex).build;
            case 5:  //personal
                return this.entries.get(rowIndex).personal;
            case 6:  //patch
                return this.entries.get(rowIndex).patch;
            case 7:  //ignore
                return this.entries.get(rowIndex).ignore;
            case 8:  //download
                return this.entries.get(rowIndex).download;
            case 9:  //status
                return this.entries.get(rowIndex).status;
        }
        return null;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if (usingXML){
            if (col < 5) return false;
            if (col == 10) return false;
            return true;
        }
        if (col < 4) return false;
        if (col == 9) return false;
        return true;
    }
    
    @Override
    public String getColumnName(int col) {
        if (usingXML) return columnNamesXML[col];
        return columnNames[col];
    }
    
    @Override
    public Class getColumnClass(int c) {
        if (usingXML){
            switch (c){
                case 0:  //Title
                    return String.class;
                case 1:  //TitleID
                    return String.class;
                case 2:  //ConsoleID
                    return String.class;
                case 3:  //Type
                    return String.class;
                case 4:  //preinstalled
                    return Boolean.class;
                case 5:  //build
                    return Boolean.class;
                case 6:  //personal
                    return Boolean.class;
                case 7:  //patch
                    return Boolean.class;
                case 8:  //ignore
                    return Boolean.class;
                case 9:  //download
                    return Boolean.class;
                case 10:  //status
                    return String.class;
            }
            return null;
        }
        switch (c){
            case 0:  //TitleID
                return String.class;
            case 1:  //ConsoleID
                return String.class;
            case 2:  //Type
                return String.class;
            case 3:  //preinstalled
                return Boolean.class;
            case 4:  //build
                return Boolean.class;
            case 5:  //personal
                return Boolean.class;
            case 6:  //patch
                return Boolean.class;
            case 7:  //ignore
                return Boolean.class;
            case 8:  //download
                return Boolean.class;
            case 9:  //status
                return String.class;
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        Entry e = entries.get(row);
        if (usingXML){
            switch (col){
                case 5:  //build
                    e.build=(boolean) value;
                    break;
                case 6:  //personal
                    e.personal=(boolean) value;
                    break;
                case 7:  //patch
                    e.patch=(boolean) value;
                    break;
                case 8:  //ignore
                    e.ignore=(boolean) value;
                    e.download=!(boolean) value;
                    this.fireTableCellUpdated(row, col+1);
                    break;
                case 9:  //download
                    e.download=(boolean) value;
                    e.ignore=!(boolean) value;
                    this.fireTableCellUpdated(row, col-1);
                    break;
                case 10:  //status
                    e.status=(String) value;
                    break;
            }
        }else{
            switch (col){
                case 4:  //build
                    e.build=(boolean) value;
                    break;
                case 5:  //personal
                    e.personal=(boolean) value;
                    break;
                case 6:  //patch
                    e.patch=(boolean) value;
                    break;
                case 7:  //ignore
                    e.ignore=(boolean) value;
                    e.download=!(boolean) value;
                    this.fireTableCellUpdated(row, col+1);
                    break;
                case 8:  //download
                    e.download=(boolean) value;
                    e.ignore=!(boolean) value;
                    this.fireTableCellUpdated(row, col-1);
                    break;
                case 9:  //status
                    e.status=(String) value;
                    break;
            }
        }
        this.fireTableCellUpdated(row, col);
        entries.set(row, e);
    }
    
    private boolean running=false;
    private final int runningSize = 5;
    private final static Object notifyObj = new Object();
    public void execute(){
        if (this.running) return;
        
        this.running = true;
        
        Logger.getGlobal().log(Level.INFO, "Beginning download.");
        
        //manager thread - separate it from ui.
        new Thread(() -> {
            ArrayList<Entry> toExecute = new ArrayList<>();
            ArrayList<workerThread> runningWorkers = new ArrayList<>();
            entries.forEach((e)->{toExecute.add(e);});
            
            //dispatcher thread
            Thread dispatch = new Thread(() -> {
                while (!toExecute.isEmpty()){
                    while (runningWorkers.size()-runningSize<runningSize){
                        Entry eT = toExecute.remove(0);
                        workerThread wT = new workerThread(eT,runningWorkers);
                        synchronized (notifyObj) {
                            runningWorkers.add(wT);
                            wT.start();
                        }
                    }
                    try { synchronized(notifyObj) { notifyObj.wait(); } } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
                    //runningWorkers.forEach((wT)->{ if (!wT.running) runningWorkers.remove(wT); }); //remove any working threads that are done.
                }
                
                //wating for the remaining workers to finish.
                while(!runningWorkers.isEmpty()){
                    //runningWorkers.forEach((wT)->{ if (!wT.running) runningWorkers.remove(wT); }); //remove any working threads that are done.
                    //try { synchronized(notifyObj) { notifyObj.wait(); } } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
                    try { Thread.sleep(50); } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
                }
                
                Logger.getGlobal().log(Level.INFO, "Download finished.");
            });
            
            //start the dispatch thread and wait for it to finish.
            dispatch.start();
            try {
                dispatch.join();
            } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
            running = false;
        }).start();
    }
    
    private class workerThread extends Thread {
        Entry e;
        public boolean running = true;
        public boolean error = false;
        private ArrayList a;
        
        public workerThread(Entry e, ArrayList a){
            this.e = e;
            this.a = a;
        }
        
        private void updateStatus(String status){
            e.status = status;
            fireTableDataChanged();
        }
        
        @Override
        public void run(){
            if (e.ignore){
                updateStatus("Ignored");
                running = false;
                synchronized(notifyObj){
                    a.remove(this);
                    notifyObj.notifyAll();
                }
                return;
            }
            
            if (e.ticket.badTicket){
                updateStatus("Bad Ticket. (Ignored)");
                running = false;
                synchronized(notifyObj){ 
                    a.remove(this);
                    notifyObj.notifyAll();
                }
                return;
            }
            
            if (e.patch && (e.ticket.type == Ticket.Type.DLC || e.ticket.type == Ticket.Type.Demo)) e.ticket.patch();
            if (e.personal) e.ticket.blankIDs();
            
            if (e.download){
                updateStatus("Prepping download.");
                Downloader d = new Downloader(e.ticket);
                
                //Download thread
                Thread dT = new Thread(() -> {
                    try {
                        d.download(CoffeeCIA_MAIN.buildDir);
                    } catch (IOException ex) {
                        Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        error = true;
                    }
                });
                dT.start();
                
                //update status
                boolean dStatus = false;
                while(!dStatus){
                    switch(d.currStatus()){
                        case WAITING:
                            updateStatus("Waiting to download.");
                            break;
                        case PREP_CETK:
                            updateStatus("Preparing cetk...");
                            break;
                        case CETK:
                            updateStatus("Downloading cetk...");
                            break;
                        case PREP_TMD:
                            updateStatus("Preparing tmd...");
                            break;
                        case TMD:
                            updateStatus("Downloading tmd...");
                            break;
                        case PREP_CID:
                            updateStatus("Preparing to download cID's...");
                            break;
                        case CID:
                            updateStatus("Downloading " + (d.currCID()+1) + " of " + d.numCID() + " cID's.  " + d.downCID() + " of " + d.fsCID());
                            break;
                        case DONE:
                        case ERROR:
                        default:
                            dStatus = true;
                    }
                    try { Thread.sleep(50); } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
                }
                
                if (d.currStatus() == Downloader.Status.ERROR) this.error = true;
                
                try { dT.join(); } catch (InterruptedException ex) { Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex); }
            }
            
            if (e.build && !this.error){
                updateStatus("Building...");
                Builder b = new Builder(e.ticket);
                try {
                    b.build(CoffeeCIA_MAIN.buildDir, CoffeeCIA_MAIN.ciaDir);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(TitlesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                    this.error = true;
                }
            }
            
            if (!this.error) updateStatus("DONE!");
            else updateStatus("Error!");
            running = false;
            synchronized(notifyObj){
                notifyObj.notifyAll();
                a.remove(this);
            }
        }
    }
    
    private enum FilterState {NONE,WHERE,SET,IGNORE};
    public void filter(String str){
        Filter f = new Filter();
        FilterState fs = FilterState.NONE;
        
        //get arguments
        // <editor-fold defaultstate="collapsed" desc="setup filter">  
        Scanner s = new Scanner(str).useDelimiter(" ");
        
        String first = s.next();
        switch (first){
            case "WHERE":
                fs = FilterState.WHERE;
                break;
            case "SET":
                fs = FilterState.SET;
                break;
            case "IGNORE":
                String i = s.next();
                if (i.toLowerCase().equals("all")) f.ignoreALL = true;
                else f.ignore = Pattern.compile(i);
                break;
            default:
                if (first.toLowerCase().equals("all")) f.titleALL = true;
                else f.find.titleid = Pattern.compile(first);
                break;
        }
        
        while (s.hasNext()){
            String e = s.next();
            switch(e){
                case "WHERE":
                    fs = FilterState.WHERE;
                    break;
                case "SET":
                    fs = FilterState.SET;
                    break;
                case "IGNORE":
                    String i = s.next();
                    if (i.toLowerCase().equals("all")) f.ignoreALL = true;
                    else f.ignore = Pattern.compile(i);
                    break;
                default:
                    switch(fs){
                        case WHERE:
                            if (e.contains("type")){
                                f.matchType = true;
                                Scanner type1 = new Scanner(e).useDelimiter("=");
                                type1.next();
                                Scanner type2 = new Scanner(type1.next()).useDelimiter(",");
                                
                                while (type2.hasNext()){
                                    switch(type2.next().toLowerCase()){
                                        case "eshopapp":
                                            f.find.type |= 0b0000000001;
                                            break;
                                        case "downloadplaychild":
                                            f.find.type |= 0b0000000010;
                                            break;
                                        case "demo":
                                            f.find.type |= 0b0000000100;
                                            break;
                                        case "updatepatch":
                                            f.find.type |= 0b0000001000;
                                            break;
                                        case "dlc":
                                            f.find.type |= 0b0000010000;
                                            break;
                                        case "dsiware":
                                            f.find.type |= 0b0000100000;
                                            break;
                                        case "system":
                                            f.find.type |= 0b0001000000;
                                            break;
                                        case "dsisystemapp":
                                            f.find.type |= 0b0010000000;
                                            break;
                                        case "dsisystemdataarchives":
                                            f.find.type |= 0b0100000000;
                                            break;
                                        case "mystery":
                                            f.find.type |= 0b1000000000;
                                            break;
                                    }
                                }
                            }else if(e.contains("cid")){
                                f.matchCID = true;
                                Scanner cid = new Scanner(e).useDelimiter("=");
                                cid.next();
                                //f.find.cid = Util.toByteArray(cid.next());
                                f.find.cid = Pattern.compile(cid.next());
                            }else{
                                switch(e){
                                    case "preinstalled":
                                        f.matchBitmask = true;
                                        f.find.preinstalled = true;
                                        break;
                                    case "build":
                                        f.matchBitmask = true;
                                        f.find.build = true;
                                        break;
                                    case "personal":
                                        f.matchBitmask = true;
                                        f.find.personal = true;
                                        break;
                                    case "patch":
                                        f.matchBitmask = true;
                                        f.find.patch = true;
                                        break;
                                    case "ignore":
                                        f.matchBitmask = true;
                                        f.find.ignore = true;
                                        break;
                                    case "download":
                                        f.matchBitmask = true;
                                        f.find.download = true;
                                        break;
                                }
                            }
                            break;
                        case SET:
                            //preinstalled build personal patch ignore download
                            switch(e){
                                case "build":
                                    f.set.build = true;
                                    break;
                                case "personal":
                                    f.set.personal = true;
                                    break;
                                case "patch":
                                    f.set.patch = true;
                                    break;
                                case "ignore":
                                    f.set.ignore = true;
                                    f.set.download = false;
                                    break;
                                case "download":
                                    f.set.download = true;
                                    f.set.ignore = false;
                                    break;
                                }
                            break;
                    }
                    break;
            }
        }
        // </editor-fold>
        
        this.entries.forEach((e)->{
            //check if it's something to ignore
            if (f.ignoreALL || (f.ignore!=null && f.ignore.matcher(e.ticket.titleIDStr).matches())){
                e.build = false;
                e.ignore = true;
                e.download = false;
                e.patch = false;
                e.personal = false;
                fireTableDataChanged();
            }else{
                boolean mTitle = false, mType = true, mBMask = true, mCID = true;
                if (f.titleALL || (f.find.titleid!=null && f.find.titleid.matcher(e.ticket.titleIDStr).matches())) mTitle = true; //check the titleid
                
                if (f.matchType){ //are we looking for a type?
                    mType = (f.find.type > 0 && f.typeCheck(e.ticket.type)); //check the type
                }else{
                    mType=true;
                }
                
                //doesnt work right for some reason... TT.TT
                //just look for something that's preinstalled
                /*if (f.matchBitmask){ //are we looking for a bitmask?
                    //int b1 = f.find.getBitmask();
                    //int b2 = e.getBitmask();
                    //mBMask = (b1 > 0 && ((b1 & b2) > 0)); //check the bitmask
                    
                    mBMask = f.find.preinstalled && e.preinstalled;
                }else{
                    mBMask = true;
                }*/
                
                if (f.matchCID){ //are we looking for a cid?
                    mCID = (f.find.cid != null && f.find.cid.matcher(e.ticket.consoleIDStr).matches()); //check the cid
                }else{
                    mBMask = true;
                }
                
                if (mTitle && mType && mBMask && mCID){ //passed checks
                    e.build = f.set.build;
                    if (f.set.download){
                        e.download = true;
                        e.ignore = false;
                    }
                    if (f.set.ignore){
                        e.ignore = true;
                        e.download = false;
                    }
                    e.patch = f.set.patch;
                    e.personal = f.set.personal;
                    fireTableDataChanged();
                }
            }
        });
    }
    //current command options
    //titleid/regex/all WHERE type=type cid=regex SET build personal patch ignore download IGNORE titleid/regex/all
    //ideal command options
    //regex/all WHERE type=type cid=cid preinstalled build personal patch ignore download SET build personal patch ignore download IGNORE regex/all
    
    private class Filter{
        public Pattern ignore = null;
        public boolean titleALL = false;
        public boolean ignoreALL = false;
        public FIND find=new FIND();
        public SET set=new SET();
        
        public boolean matchType = false;
        public boolean matchBitmask = false;
        public boolean matchCID = false;
        
        public class FIND {
            public Pattern titleid = null;
            public Pattern cid=null;
            public int type = 0b000000000;
            boolean build=false,preinstalled=false,personal=false,patch=false,ignore=false,download=false;
            
            public int getBitmask(){
                int ret = 0b000000;
                if (build) ret |= 0b000001;
                if (preinstalled) ret |= 0b000010;
                if (personal) ret |= 0b000100;
                if (patch) ret |= 0b001000;
                if (ignore) ret |= 0b010000;
                if (download) ret |= 0b100000;
                return ret;
            }
        }
        public class SET {
            boolean build=false;
            boolean personal=false;
            boolean patch=false;
            boolean ignore=false;
            boolean download=false;
        }
        
        public boolean typeCheck(Ticket.Type e){
            switch(e){
                case eShopApp:
                    if ((this.find.type & 0b000000001)>0) return true;
                    break;
                case DownloadPlayChild:
                    if ((this.find.type & 0b000000010)>0) return true;
                    break;
                case Demo:
                    if ((this.find.type & 0b000000100)>0) return true;
                    break;
                case UpdatePatch:
                    if ((this.find.type & 0b000001000)>0) return true;
                    break;
                case DLC:
                    if ((this.find.type & 0b000010000)>0) return true;
                    break;
                case DSiWare:
                    if ((this.find.type & 0b000100000)>0) return true;
                    break;
                case System:
                    if ((this.find.type & 0b001000000)>0) return true;
                    break;
                case DSiSystemApp:
                    if ((this.find.type & 0b001000000)>0) return true;
                    break;
                case DSiSystemDataArchives:
                    if ((this.find.type & 0b010000000)>0) return true;
                    break;
                case Mystery:
                    if ((this.find.type & 0b100000000)>0) return true;
                    break;
            }
            return false;
        }
    }
}
