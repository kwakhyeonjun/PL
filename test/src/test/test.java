package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class test {

	public static void main(String[] args) {
		FileReader fr;
		List<String> source = new ArrayList<String>(); 
		try {
			fr = new FileReader("as02.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			StringTokenizer tokens = new StringTokenizer(inputString, " ");
			for(int i=0; tokens.hasMoreElements(); i++) {
				source.add(tokens.nextToken());
			}
		}catch(IOException e) {
			e.printStackTrace();		
		}
		System.out.println(source);
	}
}
