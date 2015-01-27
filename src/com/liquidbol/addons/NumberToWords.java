package com.liquidbol.addons;

import java.text.DecimalFormat;

public class NumberToWords {

  private static final String[] tensNames = {
    "",
    " diez",
    " veinte",
    " treinta",
    " cuarenta",
    " cincuenta",
    " sesenta",
    " setenta",
    " ochenta",
    " noventa"
  };

  private static final String[] numNames = {
    "",
    " uno",
    " dos",
    " tres",
    " cuatro",
    " cinco",
    " seis",
    " siete",
    " ocho",
    " nueve",
    " diez",
    " once",
    " doce",
    " trece",
    " catorce",
    " quince",
    " dieciseis",
    " diecisiete",
    " dieciocho",
    " diecinueve"
  };

  private NumberToWords() {}

  private static String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }
    else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    
    return numNames[number] + "cientos" + soFar;
  }


  public static String convert(long number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "cero"; }

    String snumber = Long.toString(number);

    // pad with "0"
    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    // XXXnnnnnnnnn
    int billions = Integer.parseInt(snumber.substring(0,3));
    // nnnXXXnnnnnn
    int millions  = Integer.parseInt(snumber.substring(3,6));
    // nnnnnnXXXnnn
    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
    // nnnnnnnnnXXX
    int thousands = Integer.parseInt(snumber.substring(9,12));

    String tradBillions;
    switch (billions) {
    case 0:
      tradBillions = "";
      break;
    case 1 :
      tradBillions = convertLessThanOneThousand(billions)
      + " billones ";
      break;
    default :
      tradBillions = convertLessThanOneThousand(billions)
      + " billones ";
    }
    String result =  tradBillions;

    String tradMillions;
    switch (millions) {
    case 0:
      tradMillions = "";
      break;
    case 1 :
      tradMillions = convertLessThanOneThousand(millions)
         + " millones ";
      break;
    default :
      tradMillions = convertLessThanOneThousand(millions)
         + " millones ";
    }
    result =  result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
    case 0:
      tradHundredThousands = "";
      break;
    case 1 :
      tradHundredThousands = "un mil ";
      break;
    default :
      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
         + " mil ";
    }
    result =  result + tradHundredThousands;

    String tradThousand;
    tradThousand = convertLessThanOneThousand(thousands);
    result =  result + tradThousand;

    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }
}