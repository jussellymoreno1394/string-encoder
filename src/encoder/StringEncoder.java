package encoder;

import java.math.BigInteger;
import java.util.Base64;

import javax.swing.JOptionPane;

/**
 * @author jussellymoreno
 * 
 * @version 1.0
 */
public class StringEncoder {

	/**
	 * String to Base64 converter.
	 * 
	 * @param string String to encode.
	 * @return encoded Encoded string to Base64.
	 */
	public String stringToBase64(String string) {

		String encoded = Base64.getEncoder().encodeToString(string.getBytes());

		return encoded;

	}

	/**
	 * String to Rot13 converter.
	 * 
	 * @param string String to encode.
	 * @return encoded Encoded string to Rot13.
	 */
	public String stringToRot13(String string) {

		StringBuilder encoded = new StringBuilder();

		for (int i = 0; i < string.length(); i++) {

			char character = string.charAt(i);

			if ((string.codePointAt(i) > 90 && string.codePointAt(i) < 97) || string.codePointAt(i) < 65
					|| string.codePointAt(i) > 122) {

				character = string.charAt(i);

			} else if (string.codePointAt(i) > 109 || (string.codePointAt(i) > 77 && string.codePointAt(i) <= 90)) {

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
	 * @return encoded Encoded string to Hexadecimal.
	 */
	public String stringToHexadecimal(String string) {

		String encoded = String.format("%03x", new BigInteger(1, string.getBytes()));

		return encoded;

	}

	/**
	 * String to Binary converter.
	 * 
	 * @param string String to encode.
	 * @return encoded Encoded string to Binary.
	 */
	public String stringToBinary(String string) {

		String encoded = " ";

		for (int i = 0; i < string.length(); i++) {

			encoded += String.format("%8s", Integer.toBinaryString(string.charAt(i))).replace(' ', '0');

		}

		return encoded;

	}

	/**
	 * String encoding options.
	 * 
	 * @param option Encoding option.
	 * @param string String to encode.
	 * @return encodedString - encoding result.
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

	public String inputHandler() {

		Integer option = null;

		boolean isNumeric, isValidOpt = false;

		String string, optStr;

		do {

			optStr = JOptionPane.showInputDialog(null,
					"Enter your encoding choice: \n\n0) Base64 " + "\n1) ROT13 \n2) Hexadecimal \n3) Binary \n\n");

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

				JOptionPane.showMessageDialog(null, "You must enter at least one character to encode. Try again.");

			}

		} while (string.isEmpty());

		return stringEncodingOpt(option, string);

	}

	public boolean isNumericValue(String numero) {

		try {
			
			Integer.parseInt(numero);
			
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