/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeecia.java.tools;

/**
 *
 * @author alatnet
 */
public class Ticket {
    public static final int tk = 0x140;
    public byte[] data;
    public byte[] titleID;
    public String titleIDStr;
    public byte[] consoleID;
    public String consoleIDStr;
    public int commonkeyIndex;
    public Type type;
    public boolean badTicket;
    
    public enum Type{eShopApp,DownloadPlayChild,Demo,UpdatePatch,DLC,DSiWare,System,DSiSystemApp,DSiSystemDataArchives,Mystery};
    
    Ticket(byte[] data){
        if (data == null) return;
        this.data = data;
        this.commonkeyIndex = data[tk+0xB1];
        
        this.titleID = new byte[8];
        this.consoleID = new byte[4];
        
        for (int i=0x9C,i2=0;i<0xA4;i++,i2++) this.titleID[i2]=this.data[tk+i];
        for (int i=0x98,i2=0;i<0x9C;i++,i2++) this.consoleID[i2]=this.data[tk+i];
        
        this.titleIDStr = Util.toHexString(this.titleID);
        this.consoleIDStr = Util.toHexString(this.consoleID);
        this.type = checkType(this.titleIDStr);
        
        byte[] badCheck = new byte[0x04];
        System.arraycopy(data, 0x00, badCheck, 0x00, 0x04);
        badTicket = !Util.toHexString(badCheck).equals("00010004");
    }
    
    private void patchDemo(){
        byte patch[] = Util.toByteArray("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        for (int i=0x124,i2=0;i<0x164;i++,i2++) this.data[i] = patch[i2];
    }
    
    private void patchDLC(){
        byte patch[] = Util.toByteArray("00010014000000ac000000140001001400000000000000280000000100000084000000840003000000000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        for (int i=0x164,i2=0;i<0x210;i++,i2++) this.data[i] = patch[i2];
    }
    
    public void patch(){
        switch (this.type){
            case DLC:
                this.patchDLC();
                break;
            case Demo:
                this.patchDemo();
                break;
        }
    }
    
    public void blankIDs(){  //personal cia
        byte patch[] = Util.toByteArray("00000000");
        
        for (int i=0x98,i2=0xDC,i3=0;i<0x9C;i++,i2++,i3++){
            data[i]=patch[i3];
            data[i2]=patch[i3];
        }
    }
    
    private static Type checkType(String titleID){
        String titleSub = titleID.substring(4,8);
        if ((Integer.parseInt(titleSub, 16) & 0x10) != 0) return Type.System;
        
        switch(titleSub.toLowerCase()){
            case "0000":
                return Type.eShopApp;
            case "0001":
                return Type.DownloadPlayChild;
            case "0002":
                return Type.Demo;
            case "000e":
                return Type.UpdatePatch;
            case "008c":
                return Type.DLC;
            case "8004":
                return Type.DSiWare;
            case "8005":
                return Type.DSiSystemApp;
            case "800f":
                return Type.DSiSystemDataArchives;
            default:
                return Type.Mystery;
        }
    }
}
