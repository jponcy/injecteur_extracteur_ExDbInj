package org.i2l.exdbinj;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
	
	private String nameFile = null;
	private String text = null;

	public Files() {
	}

	public Files(String name){
		nameFile = name;
	}

	public Files(String name, String txt){
		nameFile = name;
		text = txt;
	}

	public String getNameFile() throws Exception{
		if(nameFile == null | nameFile == ""){
			System.out.println("NameFile not initialize !");
			throw new Exception();
		}

		return nameFile;
	}

	public void setNameFile(String name){
		nameFile = name; 
	}

	public String getText(){
		if(text == null){
			System.out.println("Text not initialize !");
			return "non initialize";
		}

		return text;
	}

	public void write() throws Exception{
		try{
			FileWriter lu = new FileWriter(getNameFile());
			BufferedWriter out = new BufferedWriter(lu);
			out.write(getText());
			out.close();
		}
		catch (IOException e) { System.out.println(e); }
	}

	public String read(){
		String s = null;
		try{
			FileInputStream fis = new FileInputStream(getNameFile());
			int n; 
			while ((n = fis.available()) > 0){
				byte[] b = new byte[n];
				int result = fis.read(b);
				if (result == -1) break;
				s = new String(b);
			}
			fis.close();
		} catch (Exception e) { System.out.println(e); }
		return s;
	}
}