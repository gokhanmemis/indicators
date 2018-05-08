package talib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class RSI {
	public static final int TOTAL_PERIODS = 380;
	public static int PERIODS_AVERAGE = 14;
	
	public static void main(String[] args) {
		
	    float[] closePrice = new float[TOTAL_PERIODS];
	    float[] highPrice = new float[TOTAL_PERIODS];
	    float[] lowPrice = new float[TOTAL_PERIODS];
	    double[] outRSI = new double[TOTAL_PERIODS];
	    double[] outRSIFill = new double[TOTAL_PERIODS];
	    MInteger begin = new MInteger();
	    MInteger length = new MInteger();
	    
	    try {
			FileInputStream fstream = new FileInputStream("garan.txt");
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
				//highPrice[i] = Float.parseFloat( str[1] );
				//lowPrice[i] = Float.parseFloat( str[2] );
				i++;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    Core c = new Core();
	    RetCode retCode = c.rsi(0, closePrice.length-1, closePrice, PERIODS_AVERAGE, 
	    		begin, length, outRSI );
	    List<Double> alList = new ArrayList<>();
	    List<Double> satList = new ArrayList<>();
	    
	    if (retCode == RetCode.Success  ) {
	      System.out.println("Output Begin:" + begin.value);
	      System.out.println("Output Begin:" + length.value);
	      int rsiAlKriter = 58;
	      int rsiSatKriter = 50;
	      boolean pozisyon = false;
	      double stopLoss = 0D;
	      double izSurenStopLoss = 0D;
	      double izSurenStopLossPrevious = 0D;
	      double stopLossOrani = 0.85D;
	      DecimalFormat df = new DecimalFormat("#");
	      df.setMaximumFractionDigits(8);
	      for (int i = begin.value; i < length.value; i++) {
	    	  double actualRSI = outRSI[i-begin.value];
	    	  double actualRSI_1 = 0.0D;
	    	  double actualRSI_2 = 0.0D;
	    	  if(i-begin.value >2){
	    		  actualRSI_1 = outRSI[i-begin.value-1];
	    		  actualRSI_2 = outRSI[i-begin.value-2];
	    	  }
	    	  
	    	  
	        StringBuilder line = new StringBuilder();
	        line.append("Period #");
	        line.append(i+1);
	        line.append(" close= ");
	        line.append(df.format( closePrice[i]) );
	        line.append(" rsi=");
	        line.append(actualRSI);
	        System.out.println(line.toString());
	        if(!pozisyon && i>2 && actualRSI>rsiAlKriter && actualRSI_1>rsiAlKriter && actualRSI_2>rsiAlKriter && closePrice[i] > closePrice[i-2]){
	        	
	        	pozisyon = true;
	        	stopLoss = closePrice[i] * stopLossOrani;
	        	izSurenStopLoss = closePrice[i] * 1.1D;
	        	
	        	System.err.println("*********************BUUYYYY************************ : "+df.format( closePrice[i]) +" --> "+stopLoss);
	        	alList.add( Double.parseDouble(closePrice[i]+"" ));
	        }
	        else if(!pozisyon && actualRSI>rsiAlKriter && closePrice[i] > closePrice[i-2] && actualRSI > actualRSI_2){

	        }
	        if(pozisyon &&  ( actualRSI<rsiSatKriter || closePrice[i]<stopLoss ) && actualRSI <70){
	        	if(closePrice[i]<stopLoss){
	        		System.err.println("*********************SEELLLLLLLLLLL************************ : "+df.format( stopLoss) );
	        		satList.add( Double.parseDouble(stopLoss+"" ));
	        	}
	        	else{
	        		System.err.println("*********************SEELLLLLLLLLLL************************ : "+df.format( closePrice[i]) );
	        		satList.add( Double.parseDouble(closePrice[i]+"" ));
	        	}
	        	pozisyon = false;
	        }
	        else if(closePrice[i] >= izSurenStopLoss && pozisyon){
	        	stopLoss = closePrice[i] * stopLossOrani;
	        	izSurenStopLossPrevious = izSurenStopLoss;
	        	if(izSurenStopLossPrevious < closePrice[i] * 1.1D){
	        		izSurenStopLoss = closePrice[i] * 1.1D;
	        		System.out.println("*******STOP LOSS CHANGED********** : "+stopLoss);
	        	}
	        }
	    }
	  }
	  else {
	    System.out.println("Error");
	  }
	    System.err.println(" ---------- SUMMARY ------------");
	    for (int j = 0; j < alList.size(); j++) {
			System.out.println("BUY : "+alList.get(j)+" --> "+"SELL : "+satList.get(j)+" PROFIT : %"+ ((satList.get(j)/alList.get(j))-1)*100 );
		}
	    
	    
	}
}
