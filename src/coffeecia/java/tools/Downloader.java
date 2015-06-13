/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeecia.java.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alatnet
 */
public class Downloader {
    static public boolean downloadCetk = false;
    static private final String baseURL = "http://ccs.cdn.c.shop.nintendowifi.net/ccs/download/";
    private final static byte[] magic = Util.toByteArray("00010004919EBE464AD0F552CD1B72E7884910CF55A9F02E50789641D896683DC005BD0AEA87079D8AC284C675065F74C8BF37C88044409502A022980BB8AD48383F6D28A79DE39626CCB2B22A0F19E41032F094B39FF0133146DEC8F6C1A9D55CD28D9E1C47B3D11F4F5426C2C780135A2775D3CA679BC7E834F0E0FB58E68860A71330FC95791793C8FBA935A7A6908F229DEE2A0CA6B9B23B12D495A6FE19D0D72648216878605A66538DBF376899905D3445FC5C727A0E13E0E2C8971C9CFA6C60678875732A4E75523D2F562F12AABD1573BF06C94054AEFA81A71417AF9A4A066D0FFC5AD64BAB28B1FF60661F4437D49E1E0D9412EB4BCACF4CFD6A3408847982000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000526F6F742D43413030303030303033000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000158533030303030303063000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000137A0894AD505BB6C67E2E5BDD6A3BEC43D910C772E9CC290DA58588B77DCC11680BB3E29F4EABBB26E98C2601985C041BB14378E689181AAD770568E928A2B98167EE3E10D072BEEF1FA22FA2AA3E13F11E1836A92A4281EF70AAF4E462998221C6FBB9BDD017E6AC590494E9CEA9859CEB2D2A4C1766F2C33912C58F14A803E36FCCDCCCDC13FD7AE77C7A78D997E6ACC35557E0D3E9EB64B43C92F4C50D67A602DEB391B06661CD32880BD64912AF1CBCB7162A06F02565D3B0ECE4FCECDDAE8A4934DB8EE67F3017986221155D131C6C3F09AB1945C206AC70C942B36F49A1183BCD78B6E4B47C6C5CAC0F8D62F897C6953DD12F28B70C5B7DF751819A9834652625000100010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010003704138EFBBBDA16A987DD901326D1C9459484C88A2861B91A312587AE70EF6237EC50E1032DC39DDE89A96A8E859D76A98A6E7E36A0CFE352CA893058234FF833FCB3B03811E9F0DC0D9A52F8045B4B2F9411B67A51C44B5EF8CE77BD6D56BA75734A1856DE6D4BED6D3A242C7C8791B3422375E5C779ABF072F7695EFA0F75BCB83789FC30E3FE4CC8392207840638949C7F688565F649B74D63D8D58FFADDA571E9554426B1318FC468983D4C8A5628B06B6FC5D507C13E7A18AC1511EB6D62EA5448F83501447A9AFB3ECC2903C9DD52F922AC9ACDBEF58C6021848D96E208732D3D1D9D9EA440D91621C7A99DB8843C59C1F2E2C7D9B577D512C166D6F7E1AAD4A774A37447E78FE2021E14A95D112A068ADA019F463C7A55685AABB6888B9246483D18B9C806F474918331782344A4B8531334B26303263D9D2EB4F4BB99602B352F6AE4046C69A5E7E8E4A18EF9BC0A2DED61310417012FD824CC116CFB7C4C1F7EC7177A17446CBDE96F3EDD88FCD052F0B888A45FDAF2B631354F40D16E5FA9C2C4EDA98E798D15E6046DC5363F3096B2C607A9D8DD55B1502A6AC7D3CC8D8C575998E7D796910C804C495235057E91ECD2637C9C1845151AC6B9A0490AE3EC6F47740A0DB0BA36D075956CEE7354EA3E9A4F2720B26550C7D394324BC0CB7E9317D8A8661F42191FF10B08256CE3FD25B745E5194906B4D61CB4C2E000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000526F6F7400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001434130303030303030330000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007BE8EF6CB279C9E2EEE121C6EAF44FF639F88F078B4B77ED9F9560B0358281B50E55AB721115A177703C7A30FE3AE9EF1C60BC1D974676B23A68CC04B198525BC968F11DE2DB50E4D9E7F071E562DAE2092233E9D363F61DD7C19FF3A4A91E8F6553D471DD7B84B9F1B8CE7335F0F5540563A1EAB83963E09BE901011F99546361287020E9CC0DAB487F140D6626A1836D27111F2068DE4772149151CF69C61BA60EF9D949A0F71F5499F2D39AD28C7005348293C431FFBD33F6BCA60DC7195EA2BCC56D200BAF6D06D09C41DB8DE9C720154CA4832B69C08C69CD3B073A0063602F462D338061A5EA6C915CD5623579C3EB64CE44EF586D14BAAA8834019B3EEBEED3790001000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    
    private final Ticket ticket;
    
    public enum Status {ERROR,WAITING,PREP_CETK,CETK,PREP_TMD,TMD,PREP_CID,CID,DONE};
    
    private Status status = Status.WAITING;
    private int numcid = -1;
    private int currcid = -1;
    private long fileSize = 0;
    private long downloadSize = 0;
    
    private final Object sync_status=new Object(),sync=new Object();
    
    public Downloader(Ticket ticket){ this.ticket = ticket; }
    
    //download files into specified build directory
    //files are sorted by titleID
    public void download(String buildDir) throws MalformedURLException, IOException {
        Logger.getGlobal().log(Level.INFO, "Downloading "+ticket.titleIDStr+".");
        
        URL url;
        URLConnection connection;
        InputStream input;
        byte[] buffer = new byte[4096];
        
        //create the build directory
        File bDir = new File(buildDir + File.separatorChar + ticket.titleIDStr);
        if (!bDir.exists()) bDir.mkdirs();
        
        synchronized(sync_status) { this.status = Status.PREP_CETK; }
        
        //create the cetk file
        File cetk = new File(buildDir + File.separatorChar + ticket.titleIDStr + File.separatorChar + "cetk");
        if (!cetk.exists()){
            cetk.createNewFile();
        
            synchronized(sync_status) { this.status = Status.CETK; }

            if (!downloadCetk){
                //write the cetk file
                try (FileOutputStream cetkOut = new FileOutputStream(cetk)) {
                    cetkOut.write(ticket.data);
                    cetkOut.write(magic);
                }
            }else{
                Logger.getGlobal().log(Level.INFO, "Downloading "+ticket.titleIDStr+" cetk.");

                try{
                    url = new URL(baseURL+ticket.titleIDStr+"/cetk");
                    connection = url.openConnection();
                    input = connection.getInputStream();
                    try (OutputStream output = new FileOutputStream(cetk)) {
                        int n;
                        while ((n = input.read(buffer)) != -1) output.write(buffer, 0, n);
                    }
                }catch(IOException e){
                    Logger.getGlobal().log(Level.WARNING, "Downloading "+ticket.titleIDStr+" cetk failed. Creating cetk manually.\n" + e.toString());
                    //write the cetk file
                    try (FileOutputStream cetkOut = new FileOutputStream(cetk)) {
                        cetkOut.write(ticket.data);
                        cetkOut.write(magic);
                    }
                }
            }
        }
        
        synchronized(sync_status) { this.status = Status.PREP_TMD; }
        
        //create the tmd file
        File tmd = new File(buildDir + File.separatorChar + ticket.titleIDStr + File.separatorChar + "tmd");
        if (!tmd.exists()){
            tmd.createNewFile();
        
            synchronized(sync_status) { this.status = Status.TMD; }

            Logger.getGlobal().log(Level.INFO, "Downloading "+ticket.titleIDStr+" tmd.");
            //grab the TMD
            //for (int i=0;i<numAttepmts;i++){
                url = new URL(baseURL+ticket.titleIDStr+"/tmd");
                connection = url.openConnection();
                input = connection.getInputStream();
                try (OutputStream output = new FileOutputStream(tmd)) {
                    int n;
                    while ((n = input.read(buffer)) != -1) output.write(buffer, 0, n);
                    //break;
                }
            //}
        }
            
        Logger.getGlobal().log(Level.INFO, ticket.titleIDStr+" tmd downloaded.");
        
        synchronized(sync_status) { this.status = Status.PREP_CID; }
        
        byte tmdBuff[] = new byte[(int)tmd.length()]; //get the length of the tmd file.
        try (FileInputStream tmdFS = new FileInputStream(tmd)) {
            tmdFS.read(tmdBuff); //load the entire tmd into memory.  makes it faster, and possibly easier, to get cid's.
        }
        
        byte tmdContentCount[] = new byte[0xA0-0x9E];
        System.arraycopy(tmdBuff, Ticket.tk+0x9E, tmdContentCount, 0, 0xA0-0x9E);
        int contentCount = Integer.parseInt(Util.toHexString(tmdContentCount), 16);
        
        synchronized(sync) { this.numcid = contentCount; }
        
        Logger.getGlobal().log(Level.INFO, "Downloading "+ticket.titleIDStr+" cIDs. Number of cIDS: "+contentCount);
        
        synchronized(sync_status) { this.status = Status.CID; }
        
        byte cID[] = new byte[0x04];
        byte cIDLenByte[] = new byte[0x10-0x08];
        
        for (int i=0;i<contentCount;i++){
            synchronized(sync) { this.currcid = i; }
            
            int cOffs = 0xB04+(0x30*i);
            System.arraycopy(tmdBuff, cOffs, cID, 0, 0x04);  //copy the cid.
            System.arraycopy(tmdBuff, cOffs+0x08, cIDLenByte, 0, 0x10-0x08); //copy the cid size.
            int cIDLen = Integer.parseInt(Util.toHexString(cIDLenByte), 16);
            
            synchronized(sync){
                this.fileSize = 0;
                this.downloadSize = 0;
            }
            
            //create cID file
            File FcID = new File(buildDir + File.separatorChar + ticket.titleIDStr + File.separatorChar + Util.toHexString(cID));
            if (!FcID.exists()){  //if it doesnt exist, create it.
                FcID.createNewFile();
                url = new URL(baseURL+ticket.titleIDStr+"/"+Util.toHexString(cID));
                connection = url.openConnection();
                
                synchronized(sync){
                    this.fileSize = Util.getFileSize(connection);
                    if (this.fileSize == -1) this.fileSize = cIDLen;
                }
                
                input = connection.getInputStream();
                try (OutputStream output = new FileOutputStream(FcID)) {
                    int n;
                    while ((n = input.read(buffer)) != -1){
                        output.write(buffer, 0, n);
                        synchronized(sync) { this.downloadSize+=n; }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (cIDLen == FcID.length()){ //check if content download is correct size.
                    //break;
                }else{
                    Logger.getGlobal().log(Level.INFO, ticket.titleIDStr + " - " + Util.toHexString(cID) + "size incorrect!");
                }
            }else if (cIDLen != FcID.length()){  //if it exists, check to see if it's the right size.
                FcID.delete();
                FcID.createNewFile();
                
                url = new URL(baseURL+ticket.titleIDStr+"/"+Util.toHexString(cID));
                connection = url.openConnection();
                
                synchronized(sync){
                    this.fileSize = Util.getFileSize(connection);
                    if (this.fileSize == -1) this.fileSize = cIDLen;
                }
                
                input = connection.getInputStream();
                try (OutputStream output = new FileOutputStream(FcID)) {
                    int n;
                    while ((n = input.read(buffer)) != -1){
                        output.write(buffer, 0, n);
                        synchronized(sync) { this.downloadSize+=n; }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (cIDLen == FcID.length()){ //check if content download is correct size.
                    //break;
                }else{
                    Logger.getGlobal().log(Level.INFO, ticket.titleIDStr + " - " + Util.toHexString(cID) + "size incorrect!");
                }
            }
        }
        
        Logger.getGlobal().log(Level.INFO, ticket.titleIDStr+" cID's downloaded.");
        Logger.getGlobal().log(Level.INFO, "Downloading "+ticket.titleIDStr+" done.");
        
        synchronized(sync_status) { this.status = Status.DONE; }
        synchronized(sync) { 
            this.numcid = -1;
            this.currcid = -1;
            this.downloadSize = -1;
        }
    }
    
    public int numCID(){ synchronized(sync) { return this.numcid; } }  //number of cid's.
    public int currCID() { synchronized(sync) { return this.currcid; } }  //current cid downloading.
    
    //current cid file size.
    public String fsCID(){
        synchronized(sync) {
            if (this.fileSize == -1) return "?";
            return Util.toFileSize(this.fileSize);
        }
    }
    public long fsCIDbytes() { synchronized(sync){ return this.fileSize; } } //current cid file size in bytes.
    public String downCID(){ synchronized(sync) { return Util.toFileSize(this.downloadSize); } } //current cid downloaded size.
    
    public long downCIDbytes() { synchronized(sync){ return this.downloadSize; } } //current cid downloaded size in bytes.
    
    public Status currStatus(){ synchronized(sync_status) { return this.status; } } //current status of the downloader.
}
