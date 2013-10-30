package com.test.ftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

public class ObjectToXml {

	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Customer.class);

		// StreamSource xml = new StreamSource(new FileInputStream("/home/webonise/Desktop/form/input.xml"));
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		//JAXBElement<Customer> je1 = unmarshaller.unmarshal(xml, Customer.class);
		//Customer customer = je1.getValue();
		Customer customer1 = new Customer();
		customer1.setFirstName("kunal");
		customer1.setLastName("bhatia");
		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
		PhoneNumber ph1= new PhoneNumber();
		ph1.setNumber("123456");
		PhoneNumber ph2= new PhoneNumber();
		ph2.setNumber("123456");
		PhoneNumber ph4= new PhoneNumber();
		ph4.setNumber("123456");
		PhoneNumber ph3= new PhoneNumber();
		ph3.setNumber("123456");
		phones.add(ph1);
		phones.add(ph2);
		phones.add(ph3);
		phones.add(ph4);
		customer1.setPhoneNumbers(phones);
		JAXBElement<Customer> je2 = new JAXBElement<Customer>(new QName("customers"), Customer.class, customer1);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(je2, new FileOutputStream("/home/webonise/Desktop/form/output.xml"));
	}
}
