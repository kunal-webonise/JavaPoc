package com.test.ftp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.drf.common.dto.pastperformance.RaceDTO;
import com.lowagie.text.DocumentException;

public class RaceToPdf {
	public static void main(String[] args)  throws IOException, DocumentException, ParserConfigurationException, SAXException, TransformerException {

        getXml();
		TransformerFactory tFactory = TransformerFactory.newInstance();

		Source xslDoc = new StreamSource("race.xsl");
		Source xmlDoc = new StreamSource("/home/webonise/Desktop/form/race/race.xml");

		String outputFileName = "/home/webonise/Desktop/form/race/race.html";
		OutputStream htmlFile = new FileOutputStream(outputFileName);

		Transformer transformer = tFactory.newTransformer(xslDoc);
		transformer.transform(xmlDoc, new StreamResult(htmlFile));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		TagNode node = cleaner.clean(new FileInputStream("/home/webonise/Desktop/form/race/race.html"));
		new PrettyXmlSerializer(props).writeToStream(node, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(new String(out.toByteArray()));
		renderer.layout();
		OutputStream outputStream = new FileOutputStream("/home/webonise/Desktop/form/race/race.pdf");
		renderer.createPDF(outputStream);

		renderer.finishPDF();
		out.flush();
		out.close();
}

	private static void getXml() {
		// TODO Auto-generated method stub
		try{
			FileInputStream fin = new FileInputStream("/home/webonise/drf-files/pastperformances/race/AJXCAN20130910D01.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			RaceDTO address = (RaceDTO) ois.readObject();
			ois.close();
			System.out.println(address);
			
			JAXBContext jc = JAXBContext.newInstance(RaceDTO.class);
			JAXBElement<RaceDTO> je2 = new JAXBElement<RaceDTO>(new QName("raceData"), RaceDTO.class, address);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(je2, new FileOutputStream("/home/webonise/Desktop/form/race/race.xml"));
			}
			catch(Exception e){
				System.out.println(e);
			}
		
	}
}
