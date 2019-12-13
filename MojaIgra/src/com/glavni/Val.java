package com.glavni;

import static com.sporedni.Grafika.PLOCICA_SIZE;
import static com.sporedni.Vrijeme.*;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Val {

	private float vrijemeOdPosljednjegSpawna, vrijemeIzmedjuSpawnanja;
	private Neprijatelj[] tipoviNeprijatelja;
	private CopyOnWriteArrayList<Neprijatelj> listaNeprijatelja;
	private int brojNeprijateljaPoValu, brojSpawnanihNeprijatelja;
	private boolean valZavrsen;

	public Val(Neprijatelj[] tipovi, float vrijemeIzmedjuSpawnanja, int brojNeprijateljaPoValu) {
		this.tipoviNeprijatelja = tipovi;
		this.vrijemeIzmedjuSpawnanja = vrijemeIzmedjuSpawnanja;
		this.brojNeprijateljaPoValu = brojNeprijateljaPoValu;
		this.brojSpawnanihNeprijatelja = 0;
		this.vrijemeOdPosljednjegSpawna = 0;
		this.listaNeprijatelja = new CopyOnWriteArrayList<Neprijatelj>();
		this.valZavrsen = false;
		
		spawnaj();
	}

	public void osvjezi() {
		// Pretpostavi da su svi neprijatelji mrtvi, dok petlja ne pokaze drugacije
		boolean sviNeprijateljiMrtvi = true;
		if (brojSpawnanihNeprijatelja < brojNeprijateljaPoValu) {
			vrijemeOdPosljednjegSpawna += Delta();
			if (vrijemeOdPosljednjegSpawna > vrijemeIzmedjuSpawnanja) {
				spawnaj();
				vrijemeOdPosljednjegSpawna = 0;
			}
		}
		for (Neprijatelj nep : listaNeprijatelja) {
			if (nep.jeZiv()) {
				sviNeprijateljiMrtvi = false;
				nep.osvjezi();
				nep.crtaj();
			} else {
				listaNeprijatelja.remove(nep);
			}
		}
		if (sviNeprijateljiMrtvi) {
			valZavrsen = true;
		}
	}

	public void spawnaj() {
		int izabraniNeprijatelj = 0;
		Random random = new Random();
		izabraniNeprijatelj= random.nextInt(tipoviNeprijatelja.length);
		listaNeprijatelja.add(new Neprijatelj(tipoviNeprijatelja[izabraniNeprijatelj].dohvatiTeksturu(), tipoviNeprijatelja[izabraniNeprijatelj].dohvatiPocetnuPlocicu(),
				tipoviNeprijatelja[izabraniNeprijatelj].dohvatiMapuPlocica(), PLOCICA_SIZE, PLOCICA_SIZE, tipoviNeprijatelja[izabraniNeprijatelj].dohvatiBrzinu(), tipoviNeprijatelja[izabraniNeprijatelj].dohvatiEnergiju()));
		brojSpawnanihNeprijatelja++;
	}
	
	public boolean jeValZavrsen() {
		return valZavrsen;
	}
	
	public CopyOnWriteArrayList<Neprijatelj> dohvatiListuNeprijatelja() {
		return listaNeprijatelja;
	}

}






