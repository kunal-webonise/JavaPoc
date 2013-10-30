package com.test.ftp;
import java.io.*;
import com.lowagie.text.*;
import org.xhtmlrenderer.pdf.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XmlToPdf {
	public static void main(String[] args) 
            throws IOException, DocumentException, TransformerException,TransformerConfigurationException,FileNotFoundException {                  
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource("sample.xsl"));
        System.out.println(transformer);
        transformer.transform(new StreamSource("/home/webonise/Desktop/form/sample.xml"),new StreamResult(new FileOutputStream("/home/webonise/Desktop/form/sample.html")));
        String File_To_Convert = "/home/webonise/Desktop/form/sample.html";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println(""+url);
        String HTML_TO_PDF = "/home/webonise/Desktop/form/ConvertedFile.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);      
        renderer.layout();
        renderer.createPDF(os);        
        os.close();
        
    }
}
