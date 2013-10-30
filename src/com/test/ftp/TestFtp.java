/**
 *
 */
package com.test.ftp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TestFtp {
	public static void main(String[] args) {
		String host = "localhost";
		int    port = 22;
		String user = "webonise";
		String password = "xxxxxxxxxxx";
		String rootDir = "/home/webonise/drf-files/pastperformances/race";
		int readCount;
		Session 	session 	= null;
		Channel 	channel 	= null;
		ChannelSftp channelSftp = null;

		try{
			JSch jsch = new JSch();
			session = jsch.getSession(user,host,port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config); 
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			channelSftp.cd(rootDir);
			Vector filelist = channelSftp.ls(rootDir);
			for(int i=0; i<filelist.size();i++){
//				System.out.println(filelist.get(i).toString());
				System.out.println(filelist.get(i).getClass());
			}
			byte[] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(channelSftp.get("ZIAUSA20130907D12.ser"));
			File newFile = new File("/home/webonise/drf-files/pastperformances/race/ZIAUSA20130907D11.ser");
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			while( (readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();

			channelSftp.exit();
			session.disconnect();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}