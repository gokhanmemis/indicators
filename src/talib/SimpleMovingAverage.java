package talib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class SimpleMovingAverage {

	public static final int TOTAL_PERIODS = 103;
	public static final int PERIODS_AVERAGE = 30;

	
	public static void main(String[] args) {
	    double[] closePrice = new double[TOTAL_PERIODS];
	    double[] out = new double[TOTAL_PERIODS];
	    MInteger begin = new MInteger();
	    MInteger length = new MInteger();

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
				closePrice[i] = Float.parseFloat( str[0] );
				i++;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    Core c = new Core();
	    RetCode retCode = c.sma(0, closePrice.length - 1, closePrice, PERIODS_AVERAGE, begin, length, out);
	    		

	    if (retCode == RetCode.Success) {
	      System.out.println("Output Begin:" + begin.value);
	      System.out.println("Output Begin:" + length.value);

	      for (int i = begin.value; i < length.value; i++) {
	        StringBuilder line = new StringBuilder();
	        line.append("Period #");
	        line.append(i+1);
	        line.append(" close= ");
	        line.append(closePrice[i]);
	        line.append(" mov avg=");
	        line.append(out[i]);
	        System.out.println(line.toString());
	    }
	  }
	  else {
	    System.out.println("Error");
	  }
	}
	
}
