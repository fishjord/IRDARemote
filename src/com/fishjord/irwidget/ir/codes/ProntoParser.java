package com.fishjord.irwidget.ir.codes;

import java.util.Arrays;


public class ProntoParser {

	public static IRCommand parsePronto(String pronto) {
		final String[] lexemes = pronto.split("\\s+");
		int[] codes = new int[lexemes.length];
		for(int index = 0;index < lexemes.length;index++) {
			codes[index] = Integer.parseInt(lexemes[index], 16);
		}
		
		assert(codes[0] == 0);
		return new IRCommand((int)(1000000 / (codes[1] * 0.241246)), Arrays.copyOfRange(codes, 4, codes.length));		
	}
	
	public static void main(String[] args) {
		System.out.println(parsePronto("0000 006d 0000 0022 00ac 00ac 0015 0040 0015 0040 0015 0040 0015 0015 0015 0015 0015 0015 "));
	}
}
