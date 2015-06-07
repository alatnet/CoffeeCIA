/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeecia.java.tools;

import java.net.URLConnection;
import java.text.DecimalFormat;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author alatnet
 */
public class Util {
    public static String toHexString(byte[] array) { return DatatypeConverter.printHexBinary(array); }
    public static byte[] toByteArray(String s) { return DatatypeConverter.parseHexBinary(s); }
    
    private static final DecimalFormat dForm = new DecimalFormat("#.####");
    
    public static String toFileSize(long size){
        String[] fSizes = {" B"," KB"," MB"," GB"," TB"};
        float c = size;
        
        int currSize = 0;
        while (c >= 1024){
            c/=1024;
            currSize++;
        }
        
        return dForm.format(c) + fSizes[currSize];
    }
    
    public static long getFileSize(URLConnection c){
        return c.getContentLengthLong();
        /*List values = c.getHeaderFields().get("content-Length");
        
        if (values != null && !values.isEmpty()){
            String sLength = (String) values.get(0);
            long ret = Long.parseLong(sLength);
            return ret;
        }
        
        return -1;*/
    }
}
