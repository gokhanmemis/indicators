package indicator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FeederUtil {
	
	static List<Price> prices = new ArrayList<Price>();
	
public static List<Price> getPrices(String symbol)
       throws ParseException, IOException {
   Calendar oneYearAgo = Calendar.getInstance();
   oneYearAgo.add(Calendar.DATE, -366);

   if(prices.size()>0){
	   return prices;
   }

	
	   
	   try {
			FileInputStream fstream = new FileInputStream("isctr.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			
			List<String[]> listt = new ArrayList<String[]>();
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  String[] str = strLine.split(";");
			  listt.add(str);
			}
			int i = 0;
			for (int j = listt.size()-1; j >= 0; j--) {
				String[] str = listt.get(j);
				
				Price prc = new Price(new Date(),Double.parseDouble( str[0] ),Double.parseDouble( str[0] ),Double.parseDouble( str[0] ),Double.parseDouble( str[0] ),Double.parseDouble( str[0] ));
				i++;
				prices.add(prc);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   
	   oneYearAgo.add(Calendar.DATE, 1);
	   //System.out.println(prc);
   
   return prices;
}

}