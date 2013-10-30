package com.test.ftp;
import com.lowagie.text.DocumentException;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class PdfTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 * @throws TransformerException 
	 */
	public static void main(String[] args)  throws IOException, DocumentException, ParserConfigurationException, SAXException, TransformerException {


		TransformerFactory tFactory = TransformerFactory.newInstance();

		Source xslDoc = new StreamSource("catalog.xsl");
		Source xmlDoc = new StreamSource("/home/webonise/Desktop/form/catalog.xml");

		String outputFileName = "/home/webonise/Desktop/form/catalog.html";
		OutputStream htmlFile = new FileOutputStream(outputFileName);

		Transformer transformer = tFactory.newTransformer(xslDoc);
		transformer.transform(xmlDoc, new StreamResult(htmlFile));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		TagNode node = cleaner.clean(new FileInputStream("/home/webonise/Desktop/form/catalog.html"));
		new PrettyXmlSerializer(props).writeToStream(node, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(new String(out.toByteArray()));
		renderer.layout();
		OutputStream outputStream = new FileOutputStream("/home/webonise/Desktop/form/catalog.pdf");
		renderer.createPDF(outputStream);

		renderer.finishPDF();
		out.flush();
		out.close();


	}

}
