package com.test.ftp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.drf.common.dto.pastperformance.RaceDTO;

public class RaceToXml {
	public static void main(String[] args)
	{ 
		try{
		FileInputStream fin = new FileInputStream("/home/webonise/drf-files/pastperformances/race/AJXCAN20130910D04.ser");
		ObjectInputStream ois = new ObjectInputStream(fin);
		RaceDTO address = (RaceDTO) ois.readObject();
		ois.close();
		System.out.println(address);
		
		JAXBContext jc = JAXBContext.newInstance(RaceDTO.class);
		JAXBElement<RaceDTO> je2 = new JAXBElement<RaceDTO>(new QName("raceData"), RaceDTO.class, address);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(je2, new FileOutputStream("/home/webonise/Desktop/form/output.xml"));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
