import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class oneSingleJson {

	public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "Test@123");
			ArrayList <CustomerDetails> a = new ArrayList <CustomerDetails>();
			JSONArray js = new JSONArray();
			
			//object of statement class will help us to execute queries
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
			while(rs.next())
			{
				CustomerDetails c = new CustomerDetails();
				//System.out.print(rs.getString(1)+"\t \t");
				c.setCourseName(rs.getString(1));
				c.setPurchasedDate(rs.getString(2));
				c.setAmount(rs.getInt(3));
				c.setLocation(rs.getString(4));
				a.add(c);
			}
			
			for(int i=0;i <a.size(); i++)
			{
				ObjectMapper o = new ObjectMapper();
				o.writeValue(new File("D:\\Eclipse workspace-20200724T163020Z-001\\Eclipse workspace\\JsonJava\\customerInfo"+i+".json"), a.get(i));
				Gson g = new Gson();
				String jsonString = g.toJson(a.get(i));
				js.add(jsonString);
			}
			
			
			JSONObject jo = new JSONObject();
			jo.put("data", js);
			
			String unescaped = StringEscapeUtils.unescapeJava(jo.toJSONString());
			//System.out.println(unescaped);
			String s1 = unescaped.replace("\"{", "{");
			String finalString = s1.replace("}\"", "}");
			System.out.println(finalString);
			
			try {
				 
	            // Constructs a FileWriter given a file name, using the platform's default charset
	            FileWriter file = new FileWriter("D:\\Eclipse workspace-20200724T163020Z-001\\Eclipse workspace\\JsonJava\\SingleJson.json");
	            file.write(finalString);
	            file.flush();
	            
	            conn.close();
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
			
	        }
			
			
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

