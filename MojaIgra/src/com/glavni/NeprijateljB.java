package com.glavni;

public class NeprijateljB extends Neprijatelj {

	public NeprijateljB(int plocicaX, int plocicaY, PlociceMapa mapa) {
		super(plocicaX, plocicaY, mapa);
		this.postaviTeksturu("neprijateljB");
		this.postaviBrzinu(40);
		this.postaviEnergiju(500);
	}

}
