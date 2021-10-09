package com.service.mutantdetector.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class MutantDetectorServiceImpl {
	
	private String[][] matriz;

	public boolean isMutant(String[] dna) {

		if (validateDate(dna)) {

			matriz = getMatriz(dna);

			if(dnaAnalice(dna) > 2 ) {
				return true;
			}else {
				return false;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param dna
	 * @return
	 */
	private boolean validateDate(String[] dna) {

		String regex = "[^ACTG]";
		Pattern pat = Pattern.compile(regex);
		for (String string : dna) {
			Matcher mat = pat.matcher(string);
			if (mat.find() || string.length() != dna.length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param dna
	 * @return
	 */
	private String[][] getMatriz(String[] dna) {

		int size = dna.length;
		String[][] matriz = new String[size][size];

		for (int x = 0; x < dna.length; x++) {
			for (int y = 0; y < dna[x].length(); y++) {
				matriz[x][y] = "" + dna[x].charAt(y);
			}
		}
		return matriz;
	}

	/**
	 * 
	 * @param dna
	 */
	private int dnaAnalice(String[] dna) {

		int countH = 0;
		int countV = 0;
		int countD = 0;
		for (int x = 0; x < dna.length; x++) {
			countH += countSequence(dna[x]);
			countV += verticalSearch(x);
		}
		countD = diagonalSearch();
	  return (countH + countV + countD);
	}

	/**
	 * 
	 * @param dna
	 * @return
	 */
	private int countSequence(String string) {

		String regex = "AAAA|CCCC|TTTT|GGGG";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(string);
		int count = 0;
		while (mat.find()) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 * @param dna
	 * @return
	 */
	private int verticalSearch(int column) {
		String string = "";

		for (int x = 0; x < matriz[0].length; x++) {
			string += matriz[x][column];
		}

		return countSequence(string);
	}

	/***
	 * 
	 * @return
	 */
	private int diagonalSearch() {
		int count = 0;
		for (int i = matriz.length - 1; i > 0; i--) {
			String temp = "";
			for (int j = 0, x = i; x <= matriz.length - 1; j++, x++) {
				temp = temp + matriz[x][j];

			}			
			count += countSequence(temp);
		}

		for (int i = 0; i <= matriz.length - 1; i++) {
			String temp = "";
			for (int j = 0, y = i; y <= matriz.length - 1; j++, y++) {
				temp = temp + matriz[j][y];
			}			
			count += countSequence(temp);
		}

		return count;
	}

}
