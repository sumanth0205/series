package com.aem.series.core.serviceImpl;

import com.aem.series.core.service.FibonacciSeries;

public class FibonacciSeriesImpl implements FibonacciSeries{

	
	@Override
	public String fibonacciseries(int resNo, int firstNo, int secondNo, int limit) {
		int firstNom = firstNo;
		int secondNom = secondNo;
		int resNom = resNo;
		for (int i = 0; i < limit; i++) {
		
			resNom = firstNom + secondNom;
			firstNom = secondNom;
			secondNom = resNom;
			return ("," + resNom);
			
			
			
		}
		return null;
	}
	
	
	

}
