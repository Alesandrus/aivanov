package ru.job4j;

/**
 * Class for check sub is substring of origin.
 * @author Alexander Ivanov
 * @since 10.01.2016
 * @version 1.0
 */

public class SubOrigin {
	/**
	 * Check sub is substring of origin.
	 * @param origin - original string.
	 * @param sub - string for check.
	 * @return isSub - boolean type for return true if sub is substring of origin.
	 */
	public boolean checksub(String origin, String sub) {
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();
		boolean isSub = false;
		for (int i = 0; i <= originArray.length - subArray.length; i++) {
			if (originArray[i] == subArray[0]) {
				for (int j = 0; j < subArray.length; j++) {
					if (originArray[i + j] == subArray[j]) {
						isSub = true;
					} else {
						isSub = false;
						j = subArray.length;
					}
				}
				if (isSub) {
					i = originArray.length;
				}
			}
		}
		return isSub;
	}
}