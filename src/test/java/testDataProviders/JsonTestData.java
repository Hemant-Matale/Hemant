package testDataProviders;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonTestData {
	 @DataProvider(name = "footerLinksTestData")
	    public Object[][] getLinkData() throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        List<Map<String, String>> data = mapper.readValue(
	                new File("src/test/resources/Links.json"),
	                new TypeReference<List<Map<String, String>>>() {}
	        );

	        Object[][] result = new Object[data.size()][1];
	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i);
	        }
	        return result;
	    }

}
