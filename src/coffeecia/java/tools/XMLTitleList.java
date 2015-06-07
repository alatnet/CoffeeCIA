/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeecia.java.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author alatnet
 */
public class XMLTitleList {
    //<titleid,name>
    HashMap<byte[],String> list = new HashMap<>();
    File xFile;
    private boolean isParsed = false;
    
    public XMLTitleList(String xmlFile){ this.xFile = new File(xmlFile); }
    
    public XMLTitleList(File xmlFile){ this.xFile = xmlFile; }
    
    public void open() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        Document doc = db.parse(xFile);
        
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        
        NodeList releases = (NodeList) xpath.evaluate("/releases/release", doc, XPathConstants.NODESET);
        
        for (int i=0;i<releases.getLength();i++){
            Node releaseNode = releases.item(i);
            
            NodeList releaseNodes = releaseNode.getChildNodes();
            
            String titleName = "";
            String titleID = "";
            for(int i2=0; i2<releaseNodes.getLength();i2++){
                Node n = releaseNodes.item(i2);
                
                switch (n.getNodeName()){
                    case "titleid":
                        titleID = n.getNodeValue();
                        System.out.println(titleID);
                        break;
                    case "name":
                        titleName = n.getTextContent();
                        System.out.println(titleName);
                        break;
                }
            }
            
            if ((titleName!=null && !titleName.isEmpty()) && (titleID!=null && !titleID.isEmpty()))
                list.put(Util.toByteArray(titleID),titleName);
        }
        
        this.list.forEach((k,v)->{
            System.out.println(Util.toHexString(k) + "=" + v);
        });
        this.isParsed = true;
    }
    
    public String getTitle(byte[] id){
        if (!this.isParsed) return "";
        return list.getOrDefault(id, "");
    }
}
