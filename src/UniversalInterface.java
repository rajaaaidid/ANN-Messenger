
import java.awt.Component;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.output.CountingOutputStream;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
//import org.ho.yaml.Yaml;


public class UniversalInterface {
	
	Scanner put = new Scanner(System.in);
	Random ran = new Random();
	private String s;
	private int i;
	private double d;
	private boolean b;
	private char c;
	int Version = 30;
        public int pb = 0;
        public String pbs;
        int DSize;
	
	//ArrayGlue Function===================================================================================
	
	public String arrayGlue(String[] array, String text){
		array = arrayConcat(array, text);
		String newline = "";
		for(int counter=0; counter<array.length; counter++){
			newline = newline+array[counter];
		}
		return newline;
	}
	
	//ArrayConcat====================================================================================
	
	public String[] arrayConcat(String[] array, String text){
		String[] newarray = new String[array.length];
		int counter = 0;
		for(String line: array){
			String newline = line.concat(text);
			newarray[counter++] = newline;
		}
		return newarray;
		
	}
	
	//ArrayDelete Function========================================================================================================
	
	public String[] arrayDelete(String[] array, String text){
		int idx = arrayIndex(array, text);
		String[] newarray =  new String[array.length-1];
		for(int counter=0; counter<array.length; counter++){
			if(counter<idx+1){
				newarray[counter] = array[counter];
			}else if(counter>=idx+1){
			newarray[counter-1] = array[counter];
			}
		}
		return newarray;
	}
	
	public String[] arrayDeleteLine(String[] array, int idx){
		String[] newarray =  new String[array.length-1];
		for(int counter=0; counter<array.length; counter++){
			if(counter<idx+1){
				newarray[counter] = array[counter];
			}else if(counter>=idx+1){
			newarray[counter-1] = array[counter];
			}
		}
		return newarray;
	}
	
	//ArrayContains Function======================================================================================================
	
	public boolean arrayContains(String[] array, String text){
		boolean status = false;
		for(String line: array){
			if(line.contains(text)){
				status = true;
			}else{
				nothing();
			}
		}
		return status;
	}
        
        public boolean fileArrayContains(File[] array, String text){
		boolean status = false;
		for(File line: array){
			if((line.getName()+".pxa").contains(text)){
				status = true;
			}else{
				nothing();
			}
		}
		return status;
	}
	
	//ArrayPrint Function=========================================================================================================
	
	public void arrayPrint(String[] array){
		for(String line: array){
			print(line);
		}
	}
        
        	public void arrayPrint(Component[] array){
		for(Component line: array){
			System.out.println(line);
		}
	}
	
	//ArrayAppendAt Function======================================================================================================
	
	public String[] arrayAppendAt(String[] array, String text, int index){
		String[] newarray =  new String[array.length+1];
		for(int counter=0; counter<array.length; counter++){
			if(counter<index){
				newarray[counter] = array[counter];
			}else if(counter==index){
				newarray[counter] = text;
			}else if(counter>index){
				newarray[counter+1] = array[counter];
			}
			newarray[index+1] = array[index];
		}
		return newarray;
	}
	
	//ArrayAppend Function========================================================================================================
	
	public String[] arrayAppend(String[] array, String text){
		String[] newarray = new String[array.length+1];
		for(int counter=0; counter<array.length; counter++){
			String line = array[counter];
			newarray[counter] = line;
		}
		newarray[array.length] = text;
		return newarray;
	}
	
	//ArraySplit Function=========================================================================================================
	
	public String[] arraySplit(String[] array, String text,int splitinto, int index){
		String[] linecut = new String[splitinto];
		//String[] TArray = new String[array.length];
		String[] wanted = new String[array.length];
		for(int counter=0; counter<array.length; counter++){
			String line = array[counter];
			linecut = line.split(":");
			wanted[counter] = linecut[index];
		}
		return wanted;
	}
	
	//ArrayIndex Function==========================================================================================================
	
	public int arrayIndex(String[] array, String text){
		int a = 0;
		for(int counter = 0; counter<=array.length; counter++){
			String temp = array[counter];
			if(temp.equals(text)){
				a = counter;
				break;
			}
		}
		return a;
	}
	
	public int arrayIndex(int[] array, int no){
		int a = 1;
		for(int counter = 0; counter<=array.length; counter++){
			int temp = array[counter];
			if(temp==(no)){
				a = counter;
				break;
			}
		}
		return a;
	}
	
	//CharAssigner===============================================================================================================
	
	public String randChar(){
		String newchar = "";
		int number = ran.nextInt(26);
		if(number==0){
			newchar = "a";
		}else 
		if(number==1){
			newchar = "b";
		}else 
		if(number==2){
			newchar = "c";
		}else 
		if(number==3){
			newchar = "d";
		}else 
		if(number==4){
			newchar = "e";
		}else 
		if(number==5){
			newchar = "f";
		}else 
		if(number==6){
			newchar = "g";
		}else 
		if(number==7){
			newchar = "h";
		}else 
		if(number==8){
			newchar = "i";
		}else 
		if(number==9){
			newchar = "j";
		}else 
		if(number==10){
			newchar = "k";
		}else 
		if(number==11){
			newchar = "l";
		}else 
		if(number==12){
			newchar = "m";
		}else 
		if(number==13){
			newchar = "n";
		}else 
		if(number==14){
			newchar = "o";
		}else 
		if(number==15){
			newchar = "p";
		}else 
		if(number==16){
			newchar = "q";
		}else 
		if(number==17){
			newchar = "r";
		}else 
		if(number==18){
			newchar = "s";
		}else 
		if(number==19){
			newchar = "t";
		}else 
		if(number==20){
			newchar = "u";
		}else 
		if(number==21){
			newchar = "v";
		}else 
		if(number==22){
			newchar = "w";
		}else 
		if(number==23){
			newchar = "x";
		}else 
		if(number==24){
			newchar = "y";
		}else 
		if(number==25){
			newchar = "z";
		}
		return newchar;
	}
	
	//assigner=================================================================================================================
	
	public String assigner(int condition){
		String password = "";
		String code = "2349";
		String a = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String b = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String c = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String d = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String e = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String f = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String g = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String h = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String i = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String j = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String k = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String l = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String m = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String n = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String o = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String p = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String q = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String r = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String s = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String t = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String u = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String v = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String w = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String x = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String y = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String z = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String space = Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)))+Integer.toString((ran.nextInt(10)));
		String disallow = "4301";
		String whitespace = "0000";
		String allow = "0371";
		String conditioni;
		
		if(condition==1){
			conditioni = allow;
		}else if(condition==2){
			conditioni = disallow;
		}else{
			conditioni = disallow;
		}
		String all = a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t+u+v+w+x+y+z+code+space+whitespace+conditioni;
		
		
		
		return all;
	}
	
	//change====================================================================================================================
	
	public void change(String filename, String code) throws IOException{
		String a = code.substring(0*2, 4);
		String b = code.substring(2*2, 8);
		String c = code.substring(4*2, 12);
		String d = code.substring(6*2, 16);
		String e = code.substring(8*2, 20);
		String f = code.substring(10*2, 24);
		String g = code.substring(12*2, 28);
		String h = code.substring(14*2, 32);
		String i = code.substring(16*2, 36);
		String j = code.substring(18*2, 40);
		String k = code.substring(20*2, 44);
		String l = code.substring(22*2, 48);
		String m = code.substring(24*2, 52);
		String n = code.substring(26*2, 56);
		String o = code.substring(28*2, 60);
		String p = code.substring(30*2, 64);
		String q = code.substring(32*2, 68);
		String r = code.substring(34*2, 72);
		String s = code.substring(36*2, 76);
		String t = code.substring(38*2, 80);
		String u = code.substring(40*2, 84);
		String v = code.substring(42*2, 88);
		String w = code.substring(44*2, 92);
		String x = code.substring(46*2, 96);
		String y = code.substring(96, 100);
		String z = code.substring(100, 104);
		String cd = code.substring(104, 105);
		String space = code.substring(108, 112);
		String whitespace = code.substring(112, 116);
		String condition = code.substring(116, 120);
		
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		String[] lines = getRead(filename);
		String[] newarray = new String[lines.length];

		int counter = 0;
		for(String line: lines){
			line = line.replace("a", a);
			line = line.replace("b", b);
			line = line.replace("c", c);
			line = line.replace("d", d);
			line = line.replace("e", e);
			line = line.replace("f", f);
			line = line.replace("g", g);
			line = line.replace("h", h);
			line = line.replace("i", i);
			line = line.replace("j", j);
			line = line.replace("k", k);
			line = line.replace("l", l);
			line = line.replace("m", m);
			line = line.replace("n", n);
			line = line.replace("o", o);
			line = line.replace("p", p);
			line = line.replace("q", q);
			line = line.replace("r", r);
			line = line.replace("s", s);
			line = line.replace("t", t);
			line = line.replace("u", u);
			line = line.replace("v", v);
			line = line.replace("w", w);
			line = line.replace("x", x);
			line = line.replace("y", y);
			line = line.replace("z", z);
			line = line.replace(" ", space);
			//==========================================================Add 00
			String repeatedwhitespace = new String(new char[25]).replace("\0", whitespace);
			line = line.concat(repeatedwhitespace);
			//==========================================================Not my line
			line = line.replaceAll("..", "$0 ");
			//==========================================================Not my line
			newarray[counter++] = line;
		}
		newarray = arrayAppendAt(newarray, code, 0);
		newarray[0] = newarray[0].replaceAll("..", "$0 ");
		rewriteArray(filename, newarray);
                JOptionPane.showMessageDialog(null,"Encrypt Successfully!");
	}
	
	//EncryptTxt==================================================================================================================
	
	public void encrypt(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function		
		change(filename ,assigner(1));
		
	}
	
	public void encryptDisallow(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function		
		change(filename ,assigner(2));
		
	}
	
	//EncryptDisallow======================================================================================
	
	//DeencryptTxt===============================================================================================================
	
	public void deencrypt(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		changeBack(filename);
		
	}
	
	//changeBack=================================================================================================================
	
	public void changeBack(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		String[] file = getRead(filename);
		String[] filebackup = getRead(filename);
		file[0] = file[0].replaceAll("\\s", "");
		String code = file[0];
		
		String a = code.substring(0*2, 4);
		String b = code.substring(2*2, 8);
		String c = code.substring(4*2, 12);
		String d = code.substring(6*2, 16);
		String e = code.substring(8*2, 20);
		String f = code.substring(10*2, 24);
		String g = code.substring(12*2, 28);
		String h = code.substring(14*2, 32);
		String i = code.substring(16*2, 36);
		String j = code.substring(18*2, 40);
		String k = code.substring(20*2, 44);
		String l = code.substring(22*2, 48);
		String m = code.substring(24*2, 52);
		String n = code.substring(26*2, 56);
		String o = code.substring(28*2, 60);
		String p = code.substring(30*2, 64);
		String q = code.substring(32*2, 68);
		String r = code.substring(34*2, 72);
		String s = code.substring(36*2, 76);
		String t = code.substring(38*2, 80);
		String u = code.substring(40*2, 84);
		String v = code.substring(42*2, 88);
		String w = code.substring(44*2, 92);
		String x = code.substring(46*2, 96);
		String y = code.substring(96, 100);
		String z = code.substring(100, 104);
		String cd = code.substring(104, 105);
		//String cd2 = code.substring(107, 108);
		String space = code.substring(108, 112);
		String whitespace = code.substring(112, 116);
		String condition = code.substring(116, 120);
		String password;
		
		//Conditiion test
		int trigger = 0;
		if(condition.equals("0371")){
			nothing();
		}else{
			trigger = 1;
		}
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		String[] lines = getRead(filename);
		lines[0] = lines[0].replaceAll("\\s", "");
		lines = arrayDelete(lines, code);
		String[] newarray = new String[lines.length];
		int counter = 0;
		for(String line: lines){
			line = line.replaceAll("\\s", "");
			line = line.replace(whitespace, "");
			line = line.replace(a, "a");
			line = line.replace(b, "b");
			line = line.replace(c, "c");
			line = line.replace(d, "d");
			line = line.replace(e, "e");
			line = line.replace(f, "f");
			line = line.replace(g, "g");
			line = line.replace(h, "h");
			line = line.replace(i, "i");
			line = line.replace(j, "j");
			line = line.replace(k, "k");
			line = line.replace(l, "l");
			line = line.replace(m, "m");
			line = line.replace(n, "n");
			line = line.replace(o, "o");
			line = line.replace(p, "p");
			line = line.replace(q, "q");
			line = line.replace(r, "r");
			line = line.replace(s, "s");
			line = line.replace(t, "t");
			line = line.replace(u, "u");
			line = line.replace(v, "v");
			line = line.replace(w, "w");
			line = line.replace(x, "x");
			line = line.replace(y, "y");
			line = line.replace(z, "z");
			line = line.replace(space, " ");
			newarray[counter++] = line;
		}
		
		try{
			password = code.substring(121);
			if(input("Enter password:").equals(encryptgetLock(filename))){
				trigger=0;
			}
		}catch(Exception except){
			nothing();
		}
		
		if(trigger==1){
			print("This file cannot be decrypted!");
                        JOptionPane.showMessageDialog(null,"This file cannot be decrypted!","Warning",JOptionPane.WARNING_MESSAGE);
			rewriteArray(filename, filebackup);
		}else{
			rewriteArray(filename, newarray);
                        JOptionPane.showMessageDialog(null,"Decrypt Successfully!");
		}
	}
	
	//DeEncrypt lock=======================================================================================
	
	public void deencryptLock(String filename) throws IOException{
		String[] lines = getRead(filename);
		lines[0] = lines[0].replace("43 01", "03 71");
		rewriteArray(filename, lines);
	}
	
	//encryptPass =========================================================================================
	
	public void encryptLock(String filename, String password) throws IOException{
		String[] lines = getRead(filename);
		String code = lines[0];
		code = code.replaceAll("\\s", "");
		String a = code.substring(0*2, 4);
		String b = code.substring(2*2, 8);
		String c = code.substring(4*2, 12);
		String d = code.substring(6*2, 16);
		String e = code.substring(8*2, 20);
		String f = code.substring(10*2, 24);
		String g = code.substring(12*2, 28);
		String h = code.substring(14*2, 32);
		String i = code.substring(16*2, 36);
		String j = code.substring(18*2, 40);
		String k = code.substring(20*2, 44);
		String l = code.substring(22*2, 48);
		String m = code.substring(24*2, 52);
		String n = code.substring(26*2, 56);
		String o = code.substring(28*2, 60);
		String p = code.substring(30*2, 64);
		String q = code.substring(32*2, 68);
		String r = code.substring(34*2, 72);
		String s = code.substring(36*2, 76);
		String t = code.substring(38*2, 80);
		String u = code.substring(40*2, 84);
		String v = code.substring(42*2, 88);
		String w = code.substring(44*2, 92);
		String x = code.substring(46*2, 96);
		String y = code.substring(96, 100);
		String z = code.substring(100, 104);
		String cd = code.substring(104, 105);
		String space = code.substring(108, 112);
		String whitespace = code.substring(112, 116);
		String condition = code.substring(116, 120);
		
		String line = password;
		
		line = line.replace("a", a);
		line = line.replace("b", b);
		line = line.replace("c", c);
		line = line.replace("d", d);
		line = line.replace("e", e);
		line = line.replace("f", f);
		line = line.replace("g", g);
		line = line.replace("h", h);
		line = line.replace("i", i);
		line = line.replace("j", j);
		line = line.replace("k", k);
		line = line.replace("l", l);
		line = line.replace("m", m);
		line = line.replace("n", n);
		line = line.replace("o", o);
		line = line.replace("p", p);
		line = line.replace("q", q);
		line = line.replace("r", r);
		line = line.replace("s", s);
		line = line.replace("t", t);
		line = line.replace("u", u);
		line = line.replace("v", v);
		line = line.replace("w", w);
		line = line.replace("x", x);
		line = line.replace("y", y);
		line = line.replace("z", z);
		line = line.replace(" ", space);
		line = line.replaceAll("..", "$0 ");
		lines[0] = lines[0].concat(""+line);
		
		rewriteArray(filename, lines);
	}
	
	//EncryptGetPass
	
	public String encryptgetLock(String filename) throws IOException{
		
		String[] file = getRead(filename);
		file[0] = file[0].replaceAll("\\s", "");
		String code = file[0];
		
		String a = code.substring(0*2, 4);
		String b = code.substring(2*2, 8);
		String c = code.substring(4*2, 12);
		String d = code.substring(6*2, 16);
		String e = code.substring(8*2, 20);
		String f = code.substring(10*2, 24);
		String g = code.substring(12*2, 28);
		String h = code.substring(14*2, 32);
		String i = code.substring(16*2, 36);
		String j = code.substring(18*2, 40);
		String k = code.substring(20*2, 44);
		String l = code.substring(22*2, 48);
		String m = code.substring(24*2, 52);
		String n = code.substring(26*2, 56);
		String o = code.substring(28*2, 60);
		String p = code.substring(30*2, 64);
		String q = code.substring(32*2, 68);
		String r = code.substring(34*2, 72);
		String s = code.substring(36*2, 76);
		String t = code.substring(38*2, 80);
		String u = code.substring(40*2, 84);
		String v = code.substring(42*2, 88);
		String w = code.substring(44*2, 92);
		String x = code.substring(46*2, 96);
		String y = code.substring(96, 100);
		String z = code.substring(100, 104);
		String cd = code.substring(104, 105);
		//String cd2 = code.substring(107, 108);
		String space = code.substring(108, 112);
		String whitespace = code.substring(112, 116);
		String condition = code.substring(116, 120);
		
		String line = code.substring(120);
		
		line = line.replace(whitespace, "");
		line = line.replace(a, "a");
		line = line.replace(b, "b");
		line = line.replace(c, "c");
		line = line.replace(d, "d");
		line = line.replace(e, "e");
		line = line.replace(f, "f");
		line = line.replace(g, "g");
		line = line.replace(h, "h");
		line = line.replace(i, "i");
		line = line.replace(j, "j");
		line = line.replace(k, "k");
		line = line.replace(l, "l");
		line = line.replace(m, "m");
		line = line.replace(n, "n");
		line = line.replace(o, "o");
		line = line.replace(p, "p");
		line = line.replace(q, "q");
		line = line.replace(r, "r");
		line = line.replace(s, "s");
		line = line.replace(t, "t");
		line = line.replace(u, "u");
		line = line.replace(v, "v");
		line = line.replace(w, "w");
		line = line.replace(x, "x");
		line = line.replace(y, "y");
		line = line.replace(z, "z");
		line = line.replace(space, " ");
		
		return line;
	}
	
	//GetLines Function===========================================================================================================
	
	public int getLines(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		int counter = 0;
		Scanner read = new Scanner(new FileReader(filename));
		while(read.hasNextLine()){
			read.nextLine();
			counter++;
		}
		read.close();
		return counter;
	}
	
	//Create Function=============================================================================================================
	
	public void create(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		try{
			getRead(filename);
		}catch(FileNotFoundException fe){
			PrintWriter write = new PrintWriter(new FileWriter(filename, false));
			write.println("");
			write.close();
		}
	}
        
        public void create(String filename, boolean txt) throws IOException{
		//Check for .txt extension
		//Function
		try{
			getRead(filename);
		}catch(FileNotFoundException fe){
			PrintWriter write = new PrintWriter(new FileWriter(filename, false));
			write.println("");
			write.close();
		}
	}

	//getRead Functions===========================================================================================================

	public String[] getRead(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		int counter = 0;
		Scanner read = new Scanner(new FileReader(filename));
		while(read.hasNextLine()){
			read.nextLine();
			counter++;
		}
		String[] array = new String[counter];
		Scanner read2 = new Scanner(new FileReader(filename));
		int counter2 = 0;
		while(read2.hasNextLine()){;
			array[counter2] = read2.nextLine();
			counter2++;
		}
		read.close();
		read2.close();
		return array;
	}
        
        public String[] getRead(String filename, boolean bool) throws IOException{
		//Check for .txt extension
                if(bool){
                    if(filename.contains(".txt")){
                            nothing();
                    }else{
                            filename = filename.replace(filename, filename+".txt");
                    }
                }else{}
		//Function
		int counter = 0;
		Scanner read = new Scanner(new FileReader(filename));
		while(read.hasNextLine()){
			read.nextLine();
			counter++;
		}
		String[] array = new String[counter];
		Scanner read2 = new Scanner(new FileReader(filename));
		int counter2 = 0;
		while(read2.hasNextLine()){;
			array[counter2] = read2.nextLine();
			counter2++;
		}
		read.close();
		read2.close();
		return array;
	}
        
        public void readAll(String filename) throws IOException{
            String[] file = getRead(filename);
            
        }
	
	
	//Read Functions==============================================================================================================
	
	public void read(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		if(input("This will read text files. Continue?").toLowerCase().equals("yes")){
			Scanner read = new Scanner(new FileReader(filename));
			while(read.hasNextLine()){
				System.out.println(read.nextLine());
			}
			read.close();
		}else{
			nothing();
		}
	}
        
        /*public String yamlRead(String filename, String get) throws FileNotFoundException{
            //YamlReader reader = new YamlReader(new FileReader("contact.yml"));
            Object object = Yaml.load(new File(filename));
            Map map = (Map)object;
            return (map.get(get).toString());
        }
	
	//Rewriting Functions=========================================================================================================
	
        public void yamlWrite(String key, String value, String filename) throws IOException{
            try{
                Object test = Yaml.load(new File(filename));
            }catch(FileNotFoundException fe){
                //new f().create(filename);
            }
            Map map;
            try{
                Object object = Yaml.load(new File(filename));
                map = (Map)object;
            }catch(org.ho.yaml.exception.YamlException ye){
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("exist", "true");
                File writer = new File(filename);
                Yaml.dump(data, writer);
                Object object = Yaml.load(new File(filename));
                map = (Map)object;
            }
            
            Map<String, Object> data = new HashMap<String, Object>();
            map.put(key, value);
            //data.put("race", "Human");
            //data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });

            Yaml yaml = new Yaml();
            File writer = new File(filename);
            Yaml.dump(map, writer);
        }*/
        
	public void rewrite(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		if(input("This will replace and rewrite everything. Continue?").toLowerCase().equals("yes")){
			PrintWriter write = new PrintWriter(new FileWriter(filename, false));
			int lineno = 1;
			String line = "";
			System.out.println("Type /close to exit");
			while(true){
				System.out.println("Enter line no " + lineno + ":");
				line = put.nextLine();
				lineno++;
				if(line.equals("close")){
					break;
				}else{
					write.println(line);
				}
			}
			write.close();
		}else{
			nothing();
		}
	}
	
	public void rewriteArray(String filename, String[] array) throws IOException{
		//Check for .txt extension
				if(filename.contains(".txt")){
					nothing();
				}else{
					filename = filename.replace(filename, filename+".txt");
				}
				//Function
				PrintWriter write = new PrintWriter(new FileWriter(filename, false));
				for(String line: array){
					write.println(line);
				}
				write.close();		
	}
	
	//Append File Functions=======================================================================================================
	
	public void append(String filename) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		if(input("This will add new lines. Continue?").toLowerCase().equals("yes")){
			PrintWriter write = new PrintWriter(new FileWriter(filename, true));
			int lineno = 1;
			String line = "";
			System.out.println("Type /close to exit");
			while(true){
				System.out.println("Enter line no " + lineno + ":");
				line = put.nextLine();
				lineno++;
				if(line.equals("close")){
					break;
				}else{
					write.println(line);
				}
			}
			write.close();
		}else{
			nothing();
		}
	}
	
	public void appendText(String filename, String text) throws IOException{
		//Check for .txt extension
		if(filename.contains(".txt")){
			nothing();
		}else{
			filename = filename.replace(filename, filename+".txt");
		}
		//Function
		PrintWriter write = new PrintWriter(new FileWriter(filename, true));
		write.println(text);
		write.close();
	}
	
	public void appendArray(String filename, String[] array) throws IOException{
		//Check for .txt extension
				if(filename.contains(".txt")){
					nothing();
				}else{
					filename = filename.replace(filename, filename+".txt");
				}
				//Function
				PrintWriter write = new PrintWriter(new FileWriter(filename, true));
				for(String line: array){
					write.println(line);
				}
				write.close();		
	}
	
	//Input Functions==============================================================================================================
	
	public String input(){
		s = put.nextLine();
		return s;
	}
	
	public String input(String prompt){
		System.out.println(prompt);
		s = put.nextLine();
		return s;
	}
        
	public String input(String prompt, String text){
		System.out.println(prompt);
		s = text;
		return s;
	}
	
	public int inputi(){
		i = put.nextInt();
		return i;
	}
	
	public int inputi(String prompt){
		System.out.println(prompt);
		i = put.nextInt();
		return i;
	}
	
	public double inputd(){
		d = put.nextDouble();
		return d;
	}
	
	public double inputd(String prompt){
		System.out.println(prompt);
		d = put.nextDouble();
		return d;
	}
	
	public boolean inputb(){
		b = put.nextBoolean();
		return b;
	}
	
	public boolean inputb(String prompt){
		System.out.println(prompt);
		b = put.nextBoolean();
		return b;
	}
	
	//_Input Functions============================================================================================================
	
	public String _input(){
		System.out.println("");
		s = put.nextLine();
		return s;
	}
	
	public String _input(String prompt){
		System.out.println("");
		System.out.println(prompt);
		s = put.nextLine();
		return s;
	}
	
	public int _inputi(){
		System.out.println("");
		i = put.nextInt();
		return i;
	}
	
	public int _inputi(String prompt){
		System.out.println("");
		System.out.println(prompt);
		i = put.nextInt();
		return i;
	}
	
	public double _inputd(){
		System.out.println("");
		d = put.nextDouble();
		return d;
	}
	
	public double _inputd(String prompt){
		System.out.println("");
		System.out.println(prompt);
		d = put.nextDouble();
		return d;
	}
	
	public boolean _inputb(){
		System.out.println("");
		b = put.nextBoolean();
		return b;
	}
	
	public boolean _inputb(String prompt){
		System.out.println("");
		System.out.println(prompt);
		b = put.nextBoolean();
		return b;
	}

	//Print Functions==============================================================================================================
	
	public void print(String text){
		System.out.println(text);
	}
	
	public void print(long text){
		System.out.println(text);
	}
	
	public void print(double text){
		System.out.println(text);
	}
	
	public void print(boolean text){
		System.out.println(text);
	}
	
	public void print(char text){
		System.out.println(text);
	}
	
	public void _print(String text){
		System.out.println("");
		System.out.println(text);
	}
	
	public void _print(long text){
		System.out.println("");
		System.out.println(text);
	}
	
	public void _print(double text){
		System.out.println("");
		System.out.println(text);
	}
	
	public void _print(boolean text){
		System.out.println("");
		System.out.println(text);
	}
	
	public void _print(char text){
		System.out.println("");
		System.out.println(text);
	}
	
	public void print_(String text){
		System.out.println(text);
		System.out.println("");
	}
	
	public void print_(long text){
		System.out.println(text);
		System.out.println("");
	}
	
	public void print_(double text){
		System.out.println(text);
		System.out.println("");
	}
	
	public void print_(boolean text){
		System.out.println(text);
		System.out.println("");
	}
	
	public void print_(char text){
		System.out.println(text);
		System.out.println("");
	}
	
	public void _print_(String text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public void _print_(long text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public void _print_(double text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public void _print_(boolean text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public void _print_(char text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public static void _oprint_(Object text){
		System.out.println("");
		System.out.println(text);
		System.out.println("");
	}
	
	public static void _oprint(Object text){
		System.out.println("");
		System.out.println(text);
	}
	
	public static void oprint_(Object text){
		System.out.println(text);
		System.out.println("");
	}
	
	public static void oprint(Object text){
		System.out.println(text);
	}
	
	//Function nothing=============================================================================================================
	
	public void nothing(){
		String nothing;
	}
	
	//Wait function================================================================================================================
	
	public void wait(int seconds) throws InterruptedException{
		seconds = seconds*1000;
		Thread.sleep(seconds);
	}
	
	//String change=========================================================================================
	
	public void stringChange(String string){
		
	}
	
	
	//BELOW ARE NOT CODED BY ME============================================================================
	//ApplicationLauncher==================================================================================
	
	public void applicationLauncher(String path) throws IOException{
		Process process = new ProcessBuilder(path).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		//System.out.printf("Output of running %s is:", Arrays.toString(args));

		while ((line = br.readLine()) != null) {
		  System.out.println(line);
		}
	}
	
	//CommandPrompt========================================================================================
	
    public String commandPrompt(String command) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String returnText = "";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            returnText.concat("\n"+line);
        }
        return returnText;
    }
    
    //ShellExecute=========================================================================================
    
	public void ShellExecute() {
		//a obj = new a();
		 
		String domainName = "google.com";
 
		//in mac oxs
		String command = "test " + domainName;
 
		//in windows
		//String command = "ping -n 3 " + domainName;
 
		//String output = obj.executeCommand(command);
 
		//System.out.println(output);
	}
	
	String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
	}
	
	//Downloader=========================================================================================
	
	public void getRedirect(String link) throws MalformedURLException, IOException{
		HttpURLConnection con = (HttpURLConnection) new URL(
		        link).openConnection();
		    System.out.println("orignal url: " + con.getURL());
		    con.connect();
		    con.setInstanceFollowRedirects(false);
		    int responseCode = con.getResponseCode();
		    if ((responseCode / 100) == 3) {
		        String newLocationHeader = con.getHeaderField("Location");
		        responseCode = con.getResponseCode();
		        System.out.println("Redirected Location " + newLocationHeader);
		        System.out.println(responseCode);
		    }
	}
	
	public void downloadFile(String ilink, String name, String prompt) throws Exception{

		//========================================
		 String fileName = name; 
		 URL link = new URL(ilink); 
		
		 //Code to download
		 InputStream in = new BufferedInputStream(link.openStream());
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 byte[] buf = new byte[1024];
		 int n = 0;
		 while (-1!=(n=in.read(buf)))
		 {
		    out.write(buf, 0, n);
		 }
		 out.close();
		 in.close();
		 byte[] response = out.toByteArray();

		 FileOutputStream fos = new FileOutputStream(fileName);
		 fos.write(response);
		 fos.close();
		 //End download code
		 
		 System.out.println(prompt);
	}
	
	public void downloadFile2(String link, String name, String prompt) throws Exception{
		URL website = new URL(link);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(name);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		print(prompt);
	}
	
	//StringExtract========================================================================================
	
	public String stringExtract(String string, String regex){
		String data = string;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		String value;
		if (matcher.find()){
			value = matcher.group(1);
		}else{
			value = "";
		}
		return value;
	}
	
	//FTP recieve=========================================================================================
	
        public String[] ftpList(String filename){
        String server = "185.27.134.11";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
        FTPFile[] list;
        String[] listStr = null;
        FTPClient ftpClient = new FTPClient();
        try {
            pb = 0;
            pbs = "Connecting...";
            ftpClient.connect(server, port);
            System.out.println(ftpClient.getReplyString());
            
            pb = 20;
            pbs = "Connected...";
            ftpClient.login(user, pass);
            pb = 40;
            pbs = "Entering passive mode...";
            System.out.println("Connected..");
            ftpClient.enterLocalPassiveMode();
            System.out.println("Entering passive mode..");
            pb = 60;
            pbs = "Settings file type...";
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Setting file type..");
            System.out.println("Setting Timeout..");
            ftpClient.setControlKeepAliveTimeout(30);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "htdocs/"+filename;
            //File downloadFile1 = new File(System.getProperty("user.dir")+"\\"+filename);
            pb = 80;
            pbs = "Downloading information...";
            list = ftpClient.listFiles(remoteFile1);
            listStr = new String[list.length];
            int counter = 0;
            for(FTPFile file: list){
                listStr[counter++] = file.getName();
                String str = file.getName();
                System.out.print(str+"\n");
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            list = null;
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    pb = 100;
                    pbs = "Disconnecting...";
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        pb = 0;
        return listStr;
    }
        static private int ing = 0;
	public void ftpD2017(String filename){
	String server = "127.0.0.1";
        int port = 21;
        String user = "Normal";
        String pass = "raja8180";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            pb = 0;
            pbs = "Connecting...";
            ftpClient.connect(server, port);
            System.out.println("Connecting..");
            pb = 20;
            pbs = "Connected...";
            ftpClient.login(user, pass);
            System.out.println("Connected..");
            pb = 40;
            pbs = "Entering passive mode...";
            ftpClient.enterLocalPassiveMode();
            System.out.println("Entering passive mode..");
            pb = 60;
            pbs = "Setting file type...";
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Setting file type..");
            System.out.println("Setting Timeout..");
            ftpClient.setControlKeepAliveTimeout(30);
 
            
            long fileSize = 0;
            final long fs;
            FTPFile[] files = ftpClient.listFiles(filename);
            if (files.length == 1 && files[0].isFile()) {
            fileSize = files[0].getSize();
            }
            fs = fileSize;
            
            Thread hey = new Thread(){
                @Override
                public void run(){
                    while(ing<fs){
                        System.err.println("Downloaded "+ ing + "/" + fs);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(UniversalInterface.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            hey.start();
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = filename;
            //File downloadFile1 = new File(System.getProperty("user.dir")+"\\"+filename);
            File downloadFile1 = new File(filename);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            CountingOutputStream cos = new CountingOutputStream(outputStream1){
                protected void beforeWrite(int n){
                    super.beforeWrite(n);
                    //System.err.println("Downloaded "+ getCount() + "/" + fs);
                    ing = getCount();
                }
            };
            pb = 80;
            pbs = "Retrieving information...";
            boolean success = ftpClient.retrieveFile(remoteFile1, cos);
            outputStream1.close();
 
            if (success) {
                pb = 100;
                pbs = "Information retrieved...";
                System.out.println("File #1 has been downloaded successfully.");
            	nothing();
            }
 
            // APPROACH #2: using InputStream retrieveFileStream(String)
            /**String remoteFile2 = "htdocs/gl.txt";
            File downloadFile2 = new File("C:/Users/IDEAPAD/Desktop/java/test/wadhey.txt");
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            success = ftpClient.completePendingCommand();
            if (success) {
                System.out.println("File #2 has been downloaded successfully.");
            }
            outputStream2.close();
            inputStream.close();*/
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
        
    	public void ftpD(String filename, String directory){
	String server = "185.27.134.11";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            pb = 0;
            pbs = "Connecting...";
            ftpClient.connect(server, port);
            System.out.println("Connecting..");
            pb = 20;
            pbs = "Connected...";
            ftpClient.login(user, pass);
            System.out.println("Connected..");
            pb = 40;
            pbs = "Entering passive mode...";
            ftpClient.enterLocalPassiveMode();
            System.out.println("Entering passive mode..");
            pb = 60;
            pbs = "Setting file type...";
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Setting file type..");
            System.out.println("Setting Timeout..");
            ftpClient.setControlKeepAliveTimeout(30);
 
            long fileSize = 0;
            final long fs;
            FTPFile[] files = ftpClient.listFiles("htdocs/"+filename);
            if (files.length == 1 && files[0].isFile()) {
            fileSize = files[0].getSize();
            }
            fs = fileSize;
            
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "htdocs/"+filename;
            //File downloadFile1 = new File(System.getProperty("user.dir")+"\\"+filename);
            File downloadFile1 = new File(directory);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            CountingOutputStream cos = new CountingOutputStream(outputStream1){
                protected void beforeWrite(int n){
                    super.beforeWrite(n);
                    System.err.println("Downloaded "+ getCount() + "/" + fs);
                }
            };
            pb = 80;
            pbs = "Retrieving information...";
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                pb = 100;
                pbs = "Information retrieved...";
                System.out.println("File #1 has been downloaded successfully.");
            	nothing();
            }
 
            // APPROACH #2: using InputStream retrieveFileStream(String)
            /**String remoteFile2 = "htdocs/gl.txt";
            File downloadFile2 = new File("C:/Users/IDEAPAD/Desktop/java/test/wadhey.txt");
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            success = ftpClient.completePendingCommand();
            if (success) {
                System.out.println("File #2 has been downloaded successfully.");
            }
            outputStream2.close();
            inputStream.close();*/
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
        
        public long ftpSize(String filename){
            long fs = 0;
            String server = "185.27.134.11";
            int port = 21;
            String user = "b17_15673812";
            String pass = "ilikepie";
            
            FTPClient ftpClient = new FTPClient();
            try{
                pb = 0;
                pbs = "Connecting...";
                ftpClient.connect(server, port);
                System.out.println(ftpClient.getReplyString());
                
                pb = 16;
                pbs = "Connected...";
                ftpClient.login(user, pass);
                System.out.println(ftpClient.getReplyString());
                
                pb = 33;
                pbs = "Entering passive mode...";
                ftpClient.enterLocalPassiveMode();
                System.out.println(ftpClient.getReplyString());
                
                pb = 49;
                pbs = "Setting file type...";
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                System.out.println(ftpClient.getReplyString());
                pb = 65;
                pbs = "Setting timeout...";
                ftpClient.setControlKeepAliveTimeout(30);
                System.out.println(ftpClient.getReplyString());
                
                long fileSize = 0;
                pb = 81;
                pbs = "Retrieving information...";
                FTPFile[] files = ftpClient.listFiles("htdocs/"+filename);
                System.out.println(ftpClient.getReplyString());
                if (files.length == 1 && files[0].isFile()) {
                fileSize = files[0].getSize();
                }
                fs = fileSize;
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        pb = 100;
                        pbs = "Disconnecting...";
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            pb = 0;
            return fs;
        }
	
	//FTP Upload==============================================================================
	
	public void ftpU(String filename){
		String server = "185.27.134.11";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            pb = 0;
            pbs = "Connecting...";
            ftpClient.connect(server, port);
            pb = 20;
            pbs = "Connected...";
            ftpClient.login(user, pass);
            pb = 40;
            pbs = "Entering passive mode...";
            ftpClient.enterLocalPassiveMode();
            pb = 60;
            pbs = "Setting file type...";
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Setting Timeout..");
            ftpClient.setControlKeepAliveTimeout(30);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(filename);
 
            String firstRemoteFile = "htdocs/"+filename;
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            pb = 80;
            pbs = "Uploading information...";
            OutputStream outputStream = ftpClient.storeFileStream(firstRemoteFile);
            //CountingOutputStream cos = new CountingOutputStream(outputStream);
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                pb = 100;
                pbs = "Information uploaded...";
                System.out.println("The first file is uploaded successfully.");
            	nothing();
            }
 
            // APPROACH #2: uploads second file using an OutputStream
            /**File secondLocalFile = new File("C:/Users/IDEAPAD/workspace/Test/gl.txt");
            String secondRemoteFile = "htdocs/gl.txt";
            inputStream = new FileInputStream(secondLocalFile);
 
            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;
 
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
 
            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }*/
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
        
        public void ftpU(String filename, String directory){
		String server = "185.27.134.11";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            pb = 0;
            pbs = "Connecting...";
            ftpClient.connect(server, port);
            pb = 20;
            pbs = "Connected...";
            ftpClient.login(user, pass);
            pb = 40;
            pbs = "Entering passive mode...";
            ftpClient.enterLocalPassiveMode();
            pb = 60;
            pbs = "Setting file type...";
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Setting Timeout..");
            ftpClient.setControlKeepAliveTimeout(30);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(directory);
 
            String firstRemoteFile = "htdocs/"+filename;
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            pb = 80;
            pbs = "Uploading information...";
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
            pb = 100;
            pbs = "Information uploaded...";
                System.out.println("The first file is uploaded successfully.");
            	nothing();
            }
 
            // APPROACH #2: uploads second file using an OutputStream
            /**File secondLocalFile = new File("C:/Users/IDEAPAD/workspace/Test/gl.txt");
            String secondRemoteFile = "htdocs/gl.txt";
            inputStream = new FileInputStream(secondLocalFile);
 
            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;
 
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
 
            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }*/
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	//Creating Directory=============================================================================
	
	public void createDirectory(String Directory){
		// Create a directory; all ancestor directories must exist
		boolean success = (new File(Directory)).mkdir();
		if (!success) {
		    // Directory creation failed
		}

		// Create a directory; all non-existent ancestor directories are
		// automatically created
		success = (new File(Directory)).mkdirs();
		if (!success) {
		    // Directory creation failed
		}
	}
        
        public String[] listDirectory(String Directory){
            String[] success = (new File(Directory)).list();
            return success;
        }
        
        public String[] listDirectory(String Directory, String extension){
            String[] directory = (new File(Directory)).list();
            for(String line: directory){
                if(!line.contains(extension)){
                    int idx = arrayIndex(directory, line);
                    directory = arrayDeleteLine(directory, idx);
                }else{
                    nothing();
                }
            }
            return directory;
        }
	
	public void deleteFile(String path){
		try{
    		File file = new File(path);
    		if(file.delete()){
    			//System.out.println(file.getName() + " is deleted!");
    		}else{
    			//System.out.println("Delete operation is failed.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
 
    	}
	}
	
	public void write(String filename, String text){
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		    out.write(text);
		    out.close();
		} catch (IOException e) {
		}
	}
        
	//Move File===========================================================================
	
	public void moveFile(String filename, String NextPath)
    {	
    	try{
 
    	   File afile =new File(filename);
 
    	   if(afile.renameTo(new File(NextPath + afile.getName()))){
    		//System.out.println("File is moved successful!");
    	   }else{
    		System.out.println("File is failed to move!");
    	   }
 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	//ReadFtpFiles==========================================================================
	
	public String readFtp(String filename) throws SocketException, IOException{
		BufferedReader reader = null;
		String firstLine = null;
		
		String server = "ftp.byethost17.com";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlKeepAliveTimeout(300);
		
		    InputStream stream = ftpClient.retrieveFileStream("htdocs/"+filename);
		    reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		    firstLine = reader.readLine();
                    Stream<String> lines = reader.lines();
		} finally {
		    if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
        }
        return firstLine;
	}
        
        public void deleteFolder(String folder){
 
    	File directory = new File(folder);
 
    	//make sure directory exists
    	if(!directory.exists()){
 
           System.out.println("Directory does not exist.");
           System.exit(0);
 
        }else{
 
           try{
 
               delete(directory);
 
           }catch(IOException e){
               e.printStackTrace();
               System.exit(0);
           }
        }
 
    	System.out.println("Done");
    }
 
    public void delete(File file)
    	throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
 
    		   file.delete();
    		   System.out.println("Directory is deleted : " 
                                                 + file.getAbsolutePath());
 
    		}else{
 
    		   //list all the directory contents
        	   String files[] = file.list();
 
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
 
        	      //recursive delete
        	     delete(fileDelete);
        	   }
 
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     System.out.println("Directory is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}
 
    	}else{
    		//if file, then delete it
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
    
     public void compress(String inputFile, String compressedFile) {
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            File inputFileH = new File(inputFile);
            ZipParameters parameters = new ZipParameters();

            // COMP_DEFLATE is for compression
                    // COMp_STORE no compression
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            // DEFLATE_LEVEL_ULTRA = maximum compression
            // DEFLATE_LEVEL_MAXIMUM
            // DEFLATE_LEVEL_NORMAL = normal compression
            // DEFLATE_LEVEL_FAST
            // DEFLATE_LEVEL_FASTEST = fastest compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            // file compressed
            zipFile.addFolder(inputFileH, parameters);
            long uncompressedSize = inputFileH.length();
            File outputFileH = new File(compressedFile);
            long comrpessedSize = outputFileH.length();
            //System.out.println("Size "+uncompressedSize+" vs "+comrpessedSize);
            double ratio = (double)comrpessedSize/(double)uncompressedSize;
            System.out.println("File compressed with compression ratio : "+ ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void compressFiles(String compressedFile, String...files) {
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            List<File> inputf = new ArrayList<File>();
            for(String f: files){
                inputf.add(new File(f));
            }
            ZipParameters parameters = new ZipParameters();

            // COMP_DEFLATE is for compression
                    // COMp_STORE no compression
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            // DEFLATE_LEVEL_ULTRA = maximum compression
            // DEFLATE_LEVEL_MAXIMUM
            // DEFLATE_LEVEL_NORMAL = normal compression
            // DEFLATE_LEVEL_FAST
            // DEFLATE_LEVEL_FASTEST = fastest compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            // file compressed
            long size = 0;
            for(File ff: inputf){
                zipFile.addFile(ff, parameters);
                size += ff.length();
            }
            long uncompressedSize = size;
            File outputFileH = new File(compressedFile);
            long comrpessedSize = outputFileH.length();
            //System.out.println("Size "+uncompressedSize+" vs "+comrpessedSize);
            double ratio = (double)comrpessedSize/(double)uncompressedSize;
            System.out.println("File compressed with compression ratio : "+ ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void compressFolder(String inputFolder, String compressedFile) {
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            File folder = new File(inputFolder);
            List<File> inputf = new ArrayList<File>();
            for(File f: folder.listFiles()){
                if(!folder.getName().equals(f.getName())){
                    inputf.add(f);
                }
            }
            ZipParameters parameters = new ZipParameters();

            // COMP_DEFLATE is for compression
                    // COMp_STORE no compression
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            // DEFLATE_LEVEL_ULTRA = maximum compression
            // DEFLATE_LEVEL_MAXIMUM
            // DEFLATE_LEVEL_NORMAL = normal compression
            // DEFLATE_LEVEL_FAST
            // DEFLATE_LEVEL_FASTEST = fastest compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            // file compressed
            long size = 0;
            for(File ff: inputf){
                if(!ff.isDirectory()){
                    zipFile.addFile(ff, parameters);
                }else{
                    zipFile.addFolder(ff, parameters);
                }
                size += ff.length();
            }
            long uncompressedSize = size;
            File outputFileH = new File(compressedFile);
            long comrpessedSize = outputFileH.length();
            //System.out.println("Size "+uncompressedSize+" vs "+comrpessedSize);
            double ratio = (double)comrpessedSize/(double)uncompressedSize;
            System.out.println("File compressed with compression ratio : "+ ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void decompress(String compressedFile, String destination) {
        try {
           ZipFile zipFile = new ZipFile(compressedFile);
           if (zipFile.isEncrypted()) {
               //zipFile.setPassword(password);
           }
           zipFile.extractAll(destination);
        } catch (ZipException e) {
           e.printStackTrace();
        }

        System.out.println("File Decompressed");
    }
    
    /*public List<String> ftpRead(String filename) throws SocketException, IOException{
		BufferedReader reader = null;
		List<String> listofstring = null;
		
		String server = "ftp.byethost17.com";
        int port = 21;
        String user = "b17_15673812";
        String pass = "ilikepie";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlKeepAliveTimeout(300);
		
		    InputStream stream = ftpClient.retrieveFileStream("htdocs/"+filename);
		    reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                    String firstLine = reader.readLine();
                    Stream<String> lines = reader.lines();
                    listofstring = new ArrayList<String>((Collection<? extends String>) lines);
		} finally {
		    if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
        }
        return listofstring;
    }*/
    
    public String convert(List<String> list){
        String string = "";
        for(String line: list){
            string += line + "\t";
        }
        return string;
    }
    
    public List<InetAddress> listIP() throws SocketException{
        List<InetAddress> ls = new ArrayList<InetAddress>();      
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                if(!i.getHostAddress().contains(":")){
                    ls.add(i);
                }
            }
        }
        return ls;
    }
    
    public ArrayList<String> listIP(boolean external) throws SocketException, Exception{
        List<InetAddress> ls = new ArrayList<InetAddress>();      
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                if(!i.getHostAddress().contains(":")){
                    ls.add(i);
                }
            }
        }
        //External IP
        ArrayList<String> objtostring = new ArrayList<String>();
        if(external==true){
            ArrayList<Object> obj = new ArrayList<Object>(ls);
            obj.add((Object)getExternalIp());
            for(Object o: obj){
                System.out.println(o.toString().replaceAll("/", ""));
                objtostring.add(o.toString().replaceAll("/", ""));
            }
        }else{
            ArrayList<Object> obj = new ArrayList<Object>(ls);
            for(Object o: obj){
                System.out.println(o.toString().replaceAll("/", ""));
                objtostring.add(o.toString().replaceAll("/", ""));
            }
        } 
        //External IP
        return objtostring;
    }
    
    public void server(String file, int port, int timeout) throws IOException {		 
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(timeout);
        Socket socket = serverSocket.accept();
        System.out.println("Accepted connection : " + socket);
        File transferFile = new File (file);
        byte [] bytearray  = new byte [(int)transferFile.length()];
        FileInputStream fin = new FileInputStream(transferFile);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(bytearray,0,bytearray.length);
        OutputStream os = socket.getOutputStream();
        System.out.println("Sending Files...");
        os.write(bytearray,0,bytearray.length);
        os.flush();
        socket.close();
        System.out.println("File transfer complete");
    }
    
    public void client(String fileOutput, String ip, int port) throws IOException {            
        int filesize=1022386; 
        int bytesRead;
        int currentTot = 0;
        Socket socket = new Socket(ip,port);
        byte [] bytearray  = new byte [filesize];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(fileOutput);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray,0,bytearray.length);
        currentTot = bytesRead;

        do {
           bytesRead =
              is.read(bytearray, currentTot, (bytearray.length-currentTot));
           if(bytesRead >= 0) currentTot += bytesRead;
        } while(bytesRead > -1);

        bos.write(bytearray, 0 , currentTot);
        bos.flush();
        bos.close();
        socket.close();
      }
      
    public String getExternalIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        }catch(java.net.UnknownHostException uhe){
            return "Unknown";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void checkIPs(ArrayList<String> ips, int port) throws Exception{ 
        try{
            boolean scanning=true;
            for(String ip: ips)
            {
                try
                {
                    new Socket(ip, port);
                    System.out.println("Connection passed: "+ip);
                    scanning=false;
                }catch(java.net.UnknownHostException uhe)
                {
                    System.out.println("Connection failed: "+ip+":"+port);
                    try
                    {
                        Thread.sleep(100);//2 seconds
                    }
                    catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                catch(java.net.ConnectException e)
                {
                    System.out.println("Connection failed: "+ip+":"+port);
                    try
                    {
                        Thread.sleep(100);//2 seconds
                    }
                    catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                } 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void checkIP(String ip, int port) throws Exception{ 
        try{
            boolean scanning=true;
                try
                {
                    new Socket(ip, port);
                    System.out.println("Connection passed: "+ip);
                    scanning=false;
                }catch(java.net.UnknownHostException uhe)
                {
                    System.out.println("Connection failed: "+ip+":"+port);
                    try
                    {
                        Thread.sleep(100);//2 seconds
                    }
                    catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                catch(java.net.ConnectException e)
                {
                    System.out.println("Connection failed: "+ip+":"+port);
                    try
                    {
                        Thread.sleep(100);//2 seconds
                    }
                    catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //COMPILER
    
    public Class<?> runClass(String classname, String code) throws Exception {
        Path path = Paths.get(".");
        String thisPackage = UniversalInterface.class.getPackage().getName();
        String className = thisPackage + "." + classname; //javaapplication4.Formula
        String body = "package " + thisPackage + ";   "
                    + code;

        compile(className, body, path);
        Class<?> formulaClass = loadClass(className, path);
        
        return formulaClass;
        /*Method lol = formulaClass.getDeclaredMethod("main");
        lol.invoke(formulaClass.newInstance());
        
        Method calculate = formulaClass.getDeclaredMethod("calculate");
        double value = (double) calculate.invoke(formulaClass.newInstance());
        //next line prints 123
        System.out.println("value = " + value);

        Method calculateFails = formulaClass.getDeclaredMethod("calculateFails");
        //next line throws exception:
        double valueFails = (double) calculateFails.invoke(formulaClass.newInstance());
        System.out.println("valueFails = " + valueFails);*/
    }

    public Class<?> loadClass(String className, Path path) throws Exception {
        URLClassLoader loader = new URLClassLoader(new URL[]{path.toUri().toURL()}, UniversalInterface.class.getClassLoader());
        return loader.loadClass(className);
    }

    public void compile(String className, String body, Path path) throws Exception { 
        List<JavaSourceFromString> sourceCode = Arrays.asList(new JavaSourceFromString(className, body));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(path.toFile()));
        boolean ok = compiler.getTask(null, fileManager, null, null, null, sourceCode).call();

        System.out.println("Compilation = " + ok);
    }
    
    public void exec(String command) throws Exception{
        compile("executeclass", "public class executeclass{public void executeclass(){"+command+"}}", Paths.get("."));
        Class<?> loadClass = loadClass("executeclass", Paths.get("."));
        loadClass.getMethod("executeclass").invoke(loadClass.newInstance());
        deleteFile("executeclass.class");
    }
    
    public void compileJava(String className, String pathofjava, Path path) throws Exception {
        String[] s = getRead(pathofjava, false);
        String source = "";
        for(String line: s){
            source += line+"\n"; 
        }
        String body = source;
        
        List<JavaSourceFromString> sourceCode = Arrays.asList(new JavaSourceFromString(className, body));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(path.toFile()));
        boolean ok = compiler.getTask(null, fileManager, null, null, null, sourceCode).call();

        System.out.println("Compilation = " + ok);
    }

    public class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension),
                    JavaFileObject.Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
    
    public String getDate(){
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return (dateFormat.format(cal.getTime())); 
    }
    
    public BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
    int imageWidth  = image.getWidth();
    int imageHeight = image.getHeight();

    double scaleX = (double)width/imageWidth;
    double scaleY = (double)height/imageHeight;
    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

    return bilinearScaleOp.filter(
        image,
        new BufferedImage(width, height, image.getType()));
    }
    
    public BufferedImage getBufferedImage(String path) throws IOException{
        File img = new File(path);
        BufferedImage in = ImageIO.read(img);
        return in;
    }
    
    public void writeBufferedImage(BufferedImage bf ,String path, String extension) throws IOException{
        File outputfile = new File(path);
        ImageIO.write(bf, extension, outputfile);
    }
    
    public void byteFormToFile(String string, String file) throws IOException{
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("image2.jpg"));
        Scanner sc = new Scanner(string);
        while (sc.hasNextInt()) {
        int b = sc.nextInt(2);
        out.write(b);
        }
        out.close();
    }
    
    public String fileToByteForm(String file) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
            for (int b; (b = is.read()) != -1;) {
                String s = "0000000" + Integer.toBinaryString(b);
                s = s.substring(s.length() - 8); 
                sb.append(s).append(' ');
            }
        }
        return sb.toString();
    }
    
    public void bytesToFile(byte[] b, String file) throws IOException{
        Path path = Paths.get(file);
        Files.write(path, b); //creates, overwrites
    }
    
    public String fileToHexForm(String file) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
            for (int b; (b = is.read()) != -1;) {
                String s = Integer.toHexString(b).toUpperCase();
                if (s.length() == 1) {
                    sb.append('0');
                }
                sb.append(s).append(' ');
            }
        }
        return sb.toString();
    }
    
    public byte[] hexFormToByte(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
    return data;
    }
    
    public void hexFormToFile(String hex, String file) throws IOException{
        byte[] bb = hexFormToByte(hex.toString());
        bytesToFile(bb, file);
    }
    
    public String stringCompress(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return str;
        }
        System.out.println("String length : " + str.length());
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("ISO-8859-1"));
        gzip.close();
        String outStr = obj.toString("ISO-8859-1");
        System.out.println("Output String length : " + outStr.length());
        return outStr;
    }
    
    public String stringDecompress(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return str;
        }
        System.out.println("Input String length : " + str.length());
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes("ISO-8859-1")));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "ISO-8859-1"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        System.out.println("Output String lenght : " + outStr.length());
        return outStr;
    }
    
    public String byteToHexForm(byte[] b) {
    return DatatypeConverter.printHexBinary(b);
    }

    public byte[] hexFormToByteBetter(String s) {
    return DatatypeConverter.parseHexBinary(s);
    }
    
    public void copyFolder(File src, File dest)
    	throws IOException{
 
        //File src = new File(ssrc);
        //File dest = new File(sdest);
        
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }
}