import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// @Author: Ricky Dam
// @Version: June 26, 2016
// @Version: June 27, 2016

// Eclipse IDE used.
// 2 custom JARs are needed to run this program.
// To import custom JARs in Eclipse, Project > Properties > Java Build Path > Add External JARs

// Used to import the main JSON packages
// json-20160212
// http://central.maven.org/maven2/org/json/json/20160212/json-20160212.jar

// Used to import the simple JSON packages
// json-simple-1.1.1
// http://www.java2s.com/Code/JarDownload/json-simple/json-simple-1.1.1.jar.zip

// My console output (June 27, 2016, 4:59am)
// Screenshot 1
// https://i.gyazo.com/df3880affac0efdf1f1f109a4fec7612.png
// Screenshot 2
// https://i.gyazo.com/3d62307917771309e09fbece47f8fc66.png

public class Shopify {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// Computer unique counter
		int compUnique = 0;
		// Computer variant counter
		int compVariant = 0;
		// Keyboard unique counter
		int kbUnique = 0;
		// Keyboard variant counter
		int kbVariant = 0;
		// Total weight of all the computers and keyboards
		Double totalWeight = 0.0;
		// Create a JSON parser to access and parse the data from the file
		JSONParser parser = new JSONParser();
		try {
			// Create a object with all the data from the JSON file
			Object parsedObject = parser.parse(new FileReader("/Users/RickyDam/Desktop/products.json"));
			// Convert the parsed object into a string
			String parsedString = parsedObject.toString();
			// Create a JSON object using the parsed string
			JSONObject jsonObj = new JSONObject(parsedString);
			// Create a JSON array containing all the data
			JSONArray jsonArray = jsonObj.getJSONArray("products");
			for(int i=0; i<jsonArray.length(); i++) {
				// Go through all the products one JSONObject at a time
				JSONObject currentObj = (JSONObject) jsonArray.get(i);
				// Check if the current JSON object is a computer
				if(currentObj.getString("product_type").equals("Computer")) {
					// It is a new a unique computer so increment
					compUnique++;
					// Print out the computer number and it's position on the API products list
					System.out.println("----- Computer " + compUnique + " ----- " + "Product #" + i);
					// In the array of products, each product has its own array called "variants"
					JSONArray variantsArray = currentObj.getJSONArray("variants");
					for(int j=0; j<variantsArray.length(); j++) {
						// Increment the variant count
						compVariant++;
						// Print out the variant number
						System.out.println("Variant " + compVariant + ":");
						// Access each JSON object in the variants array one at a time
						JSONObject anObj = (JSONObject) variantsArray.get(j);
						// Print out the colour of the variant
						String title = anObj.getString("title");
						System.out.println("Colour: " + title);
						// Print out the price of the variant
						String price = anObj.getString("price");
						System.out.println("Price: $" + price);
						// Print out the weight of the variant
						Double grams = anObj.getDouble("grams");
						// Add the weight of the variant to the total weight counter
						totalWeight += grams;
						System.out.println("Weight: " + grams/1000 + " kg");
						System.out.println();
					}
					// There are no more variants for that one so reset the variant counter
					compVariant = 0;
					System.out.println();
				}
				if(currentObj.getString("product_type").equals("Keyboard")) {
					kbUnique++;
					System.out.println("----- Keyboard " + kbUnique + " ----- " + "Product #" + i);
					JSONArray variantsArray = currentObj.getJSONArray("variants");
					for(int j=0; j<variantsArray.length(); j++) {
						kbVariant++;
						// Print out the variant number
						System.out.println("Variant " + kbVariant + ":");
						// Access each JSON object in the variants array one at a time
						JSONObject anObj = (JSONObject) variantsArray.get(j);
						// Print out the colour of the variant
						String title = anObj.getString("title");
						System.out.println("Colour: " + title);
						// Print out the price of the variant
						String price = anObj.getString("price");
						System.out.println("Price: $" + price);
						// Print out the weight of the variant
						Double grams = anObj.getDouble("grams");
						// Add the weight of the variant to the total weight counter
						totalWeight += grams;
						System.out.println("Weight: " + grams/1000 + " kg");
						System.out.println();
					}
					// There are no more variants for that one so reset the variant counter
					kbVariant = 0;
					System.out.println();
				}
			}
			System.out.println("--------------------------------------------------");
			System.out.println();
			System.out.println("Total weight of all the computers and keyboards: " + totalWeight/1000 + " kg");
			System.out.println();
			System.out.println("There is one type of keyboard and only one variant for it.");
			System.out.println("Therefore, to have an even number of computers and keyboards,");
			System.out.println("Alice needs to buy only one computer.");
			System.out.println();
			System.out.println("--------------------------------------------------");
		} catch(JSONException e) {
			// JSON parsing error
			e.printStackTrace();
		}
	}
}
