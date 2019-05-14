package com.sf.demo.test;

 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
 
 
public class TestRestService {
	public static void main(String[] args) {
		String string = "";
		try {
  
			InputStream inputStream = new FileInputStream("C:\\Users\\madhurji\\Desktop\\testjson.txt");
			InputStreamReader inStreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inStreamReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
 
			JSONObject jsonObject = new JSONObject(string);
			System.out.println(jsonObject);
 
			try {
				URL url = new URL("http://localhost:8080/DummySFRestWS/api/sfRestService");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while (in.readLine() != null) {
				}
				System.out.println("\n SfRestService   Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError in calling SfRestService");
				System.out.println(e);
			}
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}