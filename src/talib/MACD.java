package talib;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class MACD {

	public static final int TOTAL_PERIODS = 104;
	public static final int PERIODS_AVERAGE = 30;
	
	public static void main(String[] args) {
	    double[] closePrice = new double[TOTAL_PERIODS];
	    double[] out = new double[TOTAL_PERIODS];
	    double[] out2 = new double[TOTAL_PERIODS];
	    double[] out3 = new double[TOTAL_PERIODS];
	    MInteger begin = new MInteger();
	    MInteger length = new MInteger();
	    int fastperiod = 12;  
	    int slowperiod = 26 ; 
	    int signalperiod = 9;

	    closePrice[	0	]	=	7.69	;
	    closePrice[	1	]	=	7.53	;
	    closePrice[	2	]	=	7.35	;
	    closePrice[	3	]	=	7.39	;
	    closePrice[	4	]	=	7.44	;
	    closePrice[	5	]	=	7.57	;
	    closePrice[	6	]	=	7.7	;
	    closePrice[	7	]	=	7.75	;
	    closePrice[	8	]	=	7.74	;
	    closePrice[	9	]	=	7.82	;
	    closePrice[	10	]	=	7.79	;
	    closePrice[	11	]	=	7.85	;
	    closePrice[	12	]	=	7.86	;
	    closePrice[	13	]	=	7.96	;
	    closePrice[	14	]	=	7.81	;
	    closePrice[	15	]	=	7.87	;
	    closePrice[	16	]	=	7.68	;
	    closePrice[	17	]	=	7.65	;
	    closePrice[	18	]	=	7.75	;
	    closePrice[	19	]	=	7.77	;
	    closePrice[	20	]	=	7.53	;
	    closePrice[	21	]	=	7.42	;
	    closePrice[	22	]	=	7.48	;
	    closePrice[	23	]	=	7.47	;
	    closePrice[	24	]	=	7.47	;
	    closePrice[	25	]	=	7.43	;
	    closePrice[	26	]	=	7.45	;
	    closePrice[	27	]	=	7.41	;
	    closePrice[	28	]	=	7.37	;
	    closePrice[	29	]	=	7.26	;
	    closePrice[	30	]	=	7.25	;
	    closePrice[	31	]	=	7.22	;
	    closePrice[	32	]	=	7.25	;
	    closePrice[	33	]	=	7.24	;
	    closePrice[	34	]	=	7.14	;
	    closePrice[	35	]	=	7.15	;
	    closePrice[	36	]	=	7.23	;
	    closePrice[	37	]	=	7.11	;
	    closePrice[	38	]	=	7.19	;
	    closePrice[	39	]	=	7.1	;
	    closePrice[	40	]	=	7.03	;
	    closePrice[	41	]	=	7.05	;
	    closePrice[	42	]	=	7.05	;
	    closePrice[	43	]	=	7.07	;
	    closePrice[	44	]	=	7.09	;
	    closePrice[	45	]	=	6.97	;
	    closePrice[	46	]	=	7.01	;
	    closePrice[	47	]	=	6.93	;
	    closePrice[	48	]	=	7.03	;
	    closePrice[	49	]	=	7.01	;
	    closePrice[	50	]	=	7.06	;
	    closePrice[	51	]	=	7.14	;
	    closePrice[	52	]	=	7.16	;
	    closePrice[	53	]	=	7.07	;
	    closePrice[	54	]	=	6.98	;
	    closePrice[	55	]	=	7.05	;
	    closePrice[	56	]	=	7.07	;
	    closePrice[	57	]	=	7.02	;
	    closePrice[	58	]	=	6.94	;
	    closePrice[	59	]	=	6.91	;
	    closePrice[	60	]	=	7.02	;
	    closePrice[	61	]	=	7.02	;
	    closePrice[	62	]	=	6.94	;
	    closePrice[	63	]	=	6.97	;
	    closePrice[	64	]	=	6.86	;
	    closePrice[	65	]	=	6.89	;
	    closePrice[	66	]	=	6.96	;
	    closePrice[	67	]	=	7.01	;
	    closePrice[	68	]	=	6.94	;
	    closePrice[	69	]	=	6.98	;
	    closePrice[	70	]	=	7.05	;
	    closePrice[	71	]	=	7.08	;
	    closePrice[	72	]	=	6.86	;
	    closePrice[	73	]	=	6.85	;
	    closePrice[	74	]	=	6.77	;
	    closePrice[	75	]	=	6.89	;
	    closePrice[	76	]	=	6.77	;
	    closePrice[	77	]	=	6.67	;
	    closePrice[	78	]	=	6.67	;
	    closePrice[	79	]	=	6.83	;
	    closePrice[	80	]	=	6.89	;
	    closePrice[	81	]	=	7.02	;
	    closePrice[	83	]	=	6.56	;
	    closePrice[	84	]	=	6.51	;
	    closePrice[	85	]	=	6.52	;
	    closePrice[	86	]	=	6.39	;
	    closePrice[	87	]	=	6.43	;
	    closePrice[	88	]	=	6.37	;
	    closePrice[	89	]	=	6.43	;
	    closePrice[	90	]	=	6.43	;
	    closePrice[	91	]	=	6.43	;
	    closePrice[	92	]	=	6.41	;
	    closePrice[	93	]	=	6.44	;
	    closePrice[	94	]	=	6.29	;
	    closePrice[	95	]	=	6.33	;
	    closePrice[	96	]	=	6.33	;
	    closePrice[	97	]	=	6.38	;
	    closePrice[	98	]	=	6.36	;
	    closePrice[	99	]	=	6.35	;
	    closePrice[	100	]	=	6.33	;
	    closePrice[	101	]	=	6.23	;
	    closePrice[	102	]	=	6.28	;
	    closePrice[	103	]	=	6.35	;


	    Core c = new Core();
	    RetCode retCode = c.macd(0, closePrice.length-1, closePrice, fastperiod, slowperiod, signalperiod, 
	    		begin, length, out, out2, out3);
	    		

	    if (retCode == RetCode.Success) {
	      System.out.println("Output Begin:" + begin.value);
	      System.out.println("Output Begin:" + length.value);

	      for (int i = begin.value; i < length.value; i++) {
	        StringBuilder line = new StringBuilder();
	        line.append("Period #");
	        line.append(i+1);
	        line.append(" close= ");
	        line.append(closePrice[i]);
	        line.append(" macd=");
	        line.append(out[i]);
	        System.out.println(line.toString());
	    }
	  }
	  else {
	    System.out.println("Error");
	  }
	}
	
}
