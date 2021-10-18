package com.pastley.util;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class PastleyValidate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	///////////////////////////////////////////////////////
	// Method - Chain
	///////////////////////////////////////////////////////
	/**
	 * Method that allows to validate the strings.
	 * 
	 * @param chain, Represents the string.
	 * @return Boolean true if it meets false if not.
	 */
	public static boolean isChain(String chain) {
		return chain != null && chain.trim().length() > 0;
	}
	
	/**
	 * Method that allows you to convert a string to uppercase.
	 * @param chain, Represents the string.
	 * @return The converted string.
	 */
	public static String uppercase(String chain) {
		if(isChain(chain)) {
			chain = chain.toUpperCase();
		}
		return chain;
	}
	
	///////////////////////////////////////////////////////
	// Method - BigInteger
	///////////////////////////////////////////////////////
	/**
	 * Method that allows verifying if a biginteger is greater than zero.
	 * 
	 * @param a, Represents the biginteger.
	 * @return true if it meets false if not.
	 */
	public static boolean bigIntegerHigherZero(BigInteger a) {
		return (a != null && a.compareTo(BigInteger.ZERO) == 1);
	}

	/**
	 * Method that allows verifying if a biginteger is less than zero.
	 * 
	 * @param a, Represents the biginteger.
	 * @return true if it meets false if not.
	 */
	public static boolean bigIntegerLessZero(BigInteger a) {
		return (a != null && a.compareTo(BigInteger.ZERO) == -1);
	}

	/**
	 * Method that allows verifying if a biginteger is greater than or equal to zero.
	 * 
	 * @param a, Represents the biginteger.
	 * @return true if it meets false if not.
	 */
	public static boolean bigIntegerHigherEqualZero(BigInteger a) {
		return (a != null && a.compareTo(BigInteger.ZERO) >= 0);
	}
}
