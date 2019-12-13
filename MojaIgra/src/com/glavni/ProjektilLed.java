package com.glavni;

public class ProjektilLed extends Projektil {

	public ProjektilLed(TipProjektila tip, Neprijatelj meta, float x, float y, int sirina, int visina) {
		super(tip, meta, x, y, sirina, visina);
	}
	
	@Override
	public void nanesiStetu() {
		super.getTarget().postaviBrzinu(30f);
		super.nanesiStetu();
	}

}
