package encoder;

import java.math.BigInteger;
import java.util.Base64;
import javax.swing.JOptionPane;

/**
* String encoder.
* 
* @author jussellymoreno
* 
* @version 1.0
*/
public class StringEncoder {

  /**
  * String to Base64 converter.
  * 
  * @param string String to encode.
  * 
  * @return Base64 string.
  */
  public String stringToBase64(String string) {

    return Base64.getEncoder().encodeToString(string.getBytes());

  }

  /**
  * String to Rot13 converter.
  * 
  * @param string String to encode.
  * 
  * @return encoded Encoded string to Rot13.
  */
  public String stringToRot13(String string) {

    StringBuilder encoded = new StringBuilder();

    for (int i = 0; i < string.length(); i++) {

      char character = string.charAt(i);

      if ((string.codePointAt(i) > 90 && string.codePointAt(i) < 97) || string.codePointAt(i) < 65
          || string.codePointAt(i) > 122) {

        character = string.charAt(i);

      } else if (string.codePointAt(i) > 109 || (string.codePointAt(i) > 77 
              && string.codePointAt(i) <= 90)) {

        character -= 13;

      } else {

        character += 13;

      }

      encoded.append(character);

    }

    return encoded.toString();

  }

  /**
  * String to Hexadecimal converter.
  * 
  * @param string String to encode.
  * 
  * @return Hexadecimal string.
  */
  public String stringToHexadecimal(String string) {

    return String.format("%03x", new BigInteger(1, string.getBytes()));

  }

  /**
  * String to Binary converter.
  * 
  * @param string String to encode.
  * 
  * @return encoded Binary string.
  */
  public String stringToBinary(String string) {

    String encoded = " ";

    for (int i = 0; i < string.length(); i++) {

      encoded += String.format("%8s", Integer.toBinaryString(string.charAt(i))).replace(' ', '0');

    }

    return encoded;

  }

  /**
  * Encoding options.
  * 
  * @param option Encoding option.
  * 
  * @param string String to encode.
  * 
  * @return encodedString encoding result.
  */
  public String stringEncodingOpt(Integer option, String string) {

    String encodedString = null;

    switch (option) {

      case 0:

        encodedString = stringToBase64(string);
        break;

      case 1:

        encodedString = stringToRot13(string);
        break;

      case 2:

        encodedString = stringToHexadecimal(string);
        break;

      case 3:

        encodedString = stringToBinary(string);
        break;

    }

    return encodedString;

  }

  /**
  * Input handler.
  * 
  * @return Encoded result.
  */
  public String inputHandler() {

    Integer option = null;

    boolean isNumeric;
    boolean isValidOpt = false;

    String string;
    String optStr;

    do {

      optStr = JOptionPane.showInputDialog(null,
        "Enter your encoding choice: \n\n0) Base64 "
      + "\n1) ROT13 \n2) Hexadecimal \n3) Binary \n\n");

      if (null == optStr) {

        System.exit(0);

      }

      isNumeric = isNumericValue(optStr);

      if (isNumeric == true) {

        option = Integer.parseInt(optStr);

        if (option < 0 || option > 3) {

          JOptionPane.showMessageDialog(null, "Non existen option. Try again.");

        } else {

          isValidOpt = true;

        }

      }

    } while (!isNumeric || !isValidOpt);

    do {

      string = JOptionPane.showInputDialog(null, "Enter the string you want to encode.");

      if (string.isEmpty()) {

        JOptionPane.showMessageDialog(null, "You must enter at least "
            + "one character to encode. Try again.");

      }

    } while (string.isEmpty());

    return stringEncodingOpt(option, string);

  }

  /**
  * Valid if it is a numeric entry.
  * 
  * @param input Option to validate.
  * 
  * @return boolean.
  */
  public boolean isNumericValue(String input) {

    try {

      Integer.parseInt(input);

      return true;

    } catch (NumberFormatException e) {

      return false;

    }

  }


  public static void main(String[] args) {

    StringEncoder strEncoder = new StringEncoder();

    JOptionPane.showMessageDialog(null, "Encode Result:\n" + strEncoder.inputHandler());

  }

}