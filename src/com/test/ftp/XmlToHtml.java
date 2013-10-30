package com.test.ftp;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XmlToHtml {

    public static void main(String[] args)
    {
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("catalog.xsl");
            Source xmlDoc = new StreamSource("/home/webonise/Desktop/form/catalog.xml");

            String outputFileName = "/home/webonise/Desktop/form/catalog.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
