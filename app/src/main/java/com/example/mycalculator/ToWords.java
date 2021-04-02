package com.example.mycalculator;
import java.text.DecimalFormat;
public class ToWords {

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private ToWords() {}


    public static String convertLessThanOneThousand(int pIntNumber) {
        String soFar;

        if (pIntNumber % 100 < 20){
            soFar = numNames[pIntNumber % 100];
            pIntNumber /= 100;
        }
        else {
            soFar = numNames[pIntNumber % 10];
            pIntNumber /= 10;

            soFar = tensNames[pIntNumber % 10] + soFar;
            pIntNumber /= 10;
        }
        if (pIntNumber == 0) return soFar;
        return numNames[pIntNumber] + " hundred" + soFar;
    }


    public static String convert(long pIntNumber) {
        // 0 to 999 999 999 999
        if (pIntNumber == 0) { return "zero"; }

        String spIntNumber = Long.toString(pIntNumber);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        spIntNumber = df.format(pIntNumber);

        // XXXnnnnnnnnn
        int lIntBllions = Integer.parseInt(spIntNumber.substring(0,3));
        // nnnXXXnnnnnn
        int lIntMllions  = Integer.parseInt(spIntNumber.substring(3,6));
        // nnnnnnXXXnnn
        int lIntHundredThousands = Integer.parseInt(spIntNumber.substring(6,9));
        // nnnnnnnnnXXX
        int lIntThousands = Integer.parseInt(spIntNumber.substring(9,12));

        String lStrTradBillions;
        switch (lIntBllions) {
            case 0:
                lStrTradBillions = "";
                break;
            case 1 :
                lStrTradBillions = convertLessThanOneThousand(lIntBllions)
                        + " billion ";
                break;
            default :
                lStrTradBillions = convertLessThanOneThousand(lIntBllions)
                        + " billion ";
        }
        String result =  lStrTradBillions;

        String tradMillions;
        switch (lIntMllions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(lIntMllions)
                        + " million ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(lIntMllions)
                        + " million ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (lIntHundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "one thousand ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(lIntHundredThousands)
                        + " thousand ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(lIntThousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");}

}
