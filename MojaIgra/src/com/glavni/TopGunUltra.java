package com.glavni;

import java.util.concurrent.CopyOnWriteArrayList;

public class TopGunUltra extends Top {

	public TopGunUltra(TipTopa tip, Plocica pocetnaPlocica, CopyOnWriteArrayList<Neprijatelj> neprijatelji) {
		super(tip, pocetnaPlocica, neprijatelji);
	}

	@Override
	public void pucaj(Neprijatelj meta) {
		super.projektili.add(new ProjektilCrveni(super.tipTopa.tipProjektila, super.meta, super.dohvatiX(), super.dohvatiY(), 32, 32));
		super.meta.smanjiTrenutnuEnergiju(super.tipTopa.tipProjektila.snagaProjektila);
		
	}

}
