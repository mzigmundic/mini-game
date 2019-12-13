package com.sporedni;

import org.lwjgl.Sys;

public class Vrijeme {
	
	private static boolean pauzirano = false;
	public static long prethodniFrejm, ukupnoVrijeme;
	public static float d = 0, multiplikator = 1;
	
	public static long DohvatiVrijeme() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float DohvatiDeltu() {
		long trenutnoVrijeme = DohvatiVrijeme();
		int delta = (int) (trenutnoVrijeme - prethodniFrejm);
		prethodniFrejm = DohvatiVrijeme();
		if (delta + 0.001f > 0.05f) {
			return 0.05f;
		}
		return delta * 0.001f;
	}
	
	public static float Delta() {
		if (pauzirano) {
			return 0;
		} else {
			return d * multiplikator;
		}
	}
	
	public static float UkupnoVrijeme() {
		return ukupnoVrijeme;
	}
	
	public static float DohvatiMultiplikator() {
		return multiplikator;
	}
	
	public static void Osvjezi() {
		d = DohvatiDeltu();
		ukupnoVrijeme += d;
	}
	
	public static void PromijeniMultiplikator(float iznos) {
		if (multiplikator + iznos < -1 && multiplikator + iznos > 7) {
			
		} else {
			multiplikator += iznos;
		}
	}
	
	public static void Pauziraj() {
		if (pauzirano) {
			pauzirano = false;
		} else {
			pauzirano = true;
		}
	}
	
}















