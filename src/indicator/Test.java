package indicator;

import java.io.IOException;
import java.text.ParseException;

import junit.framework.TestCase;

public class Test extends TestCase {
    private String symbolForTest = "GOOG";
    private int nineDaysPeriod = 9;
    private int fourteenDaysPeriod = 14;
    private int twentyFiveDaysPeriod = 25;

    public void testGetPrices() throws Exception {
        assertTrue(FeederUtil.getPrices(symbolForTest).size() > 1);
    }

    public void testRSICalculate() throws Exception {
        try {
            RSI rsi = new RSI(nineDaysPeriod, symbolForTest);
            System.out.println("RSI for a " + rsi.getPeriodLength()
                    + " days period is: " + rsi.calculate());

            rsi = new RSI(fourteenDaysPeriod, symbolForTest);
            System.out.println("RSI for a " + rsi.getPeriodLength()
                    + " days period is: " + rsi.calculate());

            rsi = new RSI(twentyFiveDaysPeriod, symbolForTest);
            System.out.println("RSI for a " + rsi.getPeriodLength()
                    + " days period is: " + rsi.calculate());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}