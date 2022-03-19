package com.nk;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(romanToInt("LVIII"));

	}

	public static int romanToInt(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'I') {
				if (i != s.length() - 1) {
					if (s.charAt(i + 1) == 'V') {
						res = res + 4;
						i++;
					} else if (s.charAt(i + 1) == 'X') {
						res = res + 9;
						i++;
					} else {
						res = res + 1;
					}
				} else {
					res = res + 1;
				}
			}
			else if (s.charAt(i) == 'X') {
				if (i != s.length() - 1) {
					if (s.charAt(i + 1) == 'L') {
						res = res + 40;
						i++;
					} else if (s.charAt(i + 1) == 'C') {
						res = res + 90;
						i++;
					} else {
						res = res + 10;
					}
				} else {
					res = res + 10;
				}
			}
			else if (s.charAt(i) == 'C') {
				if (i != s.length() - 1) {
					if (s.charAt(i + 1) == 'D') {
						res = res + 400;
						i++;
					} else if (s.charAt(i + 1) == 'M') {
						res = res + 900;
						i++;
					} else {
						res = res + 100;
					}
				} else {
					res = res + 100;
				}
			}
			else if (s.charAt(i) == 'V')
				res = res + 5;
			else if (s.charAt(i) == 'L')
				res = res + 50;
			else if (s.charAt(i) == 'D')
				res = res + 500;
			else
				res = res + 1000;

		}

		return res;
	}

}
