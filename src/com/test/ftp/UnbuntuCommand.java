package com.test.ftp;

import java.io.IOException;

public class UnbuntuCommand {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Process p = Runtime.getRuntime().exec("wkhtmltopdf /home/webonise/Desktop/form/race/race.html  /home/webonise/Desktop/form/race/race.pdf");

	}

}
