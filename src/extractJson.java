import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class extractJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException 
	{
		// TODO Auto-generated method stub
		

		ObjectMapper o = new ObjectMapper();
		CustomerDetailsAppium c = o.readValue(new File("D:\\Eclipse workspace\\JsonJava\\customerInfo0.json"), CustomerDetailsAppium.class);
		
		System.out.println(c.getStudentName());
	}

}
