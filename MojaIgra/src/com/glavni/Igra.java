package com.glavni;

import static com.sporedni.Grafika.CrtajQTeksturu;
import static com.sporedni.Grafika.UcitajTeksturuBrzo;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import com.sporedni.MenadzerStanja;
import com.sucelje.KoristnickoSucelje;
import com.sucelje.KoristnickoSucelje.Menu;

public class Igra {
	
	private PlociceMapa mapa;
	private Igrac igrac;
	private ValSetup valSetup;
	private KoristnickoSucelje igraSucelje;
	private Menu igraMenu;
	private Texture igraPozadina;
	private Neprijatelj[] listaTipovaNeprijatelja;
	
	public Igra(PlociceMapa mapa) {
		this.mapa = mapa;
		this.listaTipovaNeprijatelja = new Neprijatelj[2];
		this.listaTipovaNeprijatelja[0] = new NeprijateljA(2, 0, mapa);
		this.listaTipovaNeprijatelja[1] = new NeprijateljB(2, 0, mapa);
		this.valSetup = new ValSetup(listaTipovaNeprijatelja, 3, 6);
		this.igrac = new Igrac(mapa, valSetup);
		this.igrac.postavi();
		this.igraPozadina = UcitajTeksturuBrzo("pozadinaIgra");
		postaviSucelje();
	}
	
	private void postaviSucelje() {
		this.igraSucelje = new KoristnickoSucelje();
		this.igraSucelje.napraviMenu("IzborTopova", 1088, 100, 192, 960, 2, 0);
		this.igraMenu = igraSucelje.dohvatiMenu("IzborTopova");
		this.igraMenu.dodajGumbBrzo("TopNarancasti", "topGunNarancasti");
		this.igraMenu.dodajGumbBrzo("TopLed", "topGunLed");
		this.igraMenu.dodajGumbBrzo("TopUltra", "topGunUltra");
	}
	
	private void osvjeziSucelje() {
		this.igraSucelje.crtajGumbove();
		this.igraSucelje.ispisStringa(1140, 470, "Val: " + valSetup.dohvatiRedniBrojVala());
		this.igraSucelje.ispisStringa(1140, 570, "Zivoti: " + Igrac.Zivoti);
		this.igraSucelje.ispisStringa(1140, 640, "Pare: " + Igrac.Pare);
		this.igraSucelje.ispisStringa(0, 0, MenadzerStanja.frejmovaUPrethodnojSekundi + " fps");
		
		if (Mouse.next()) {
			boolean misKliknut = Mouse.isButtonDown(0);
			if (misKliknut) {
				if (igraMenu.gumbKliknut("TopNarancasti")) {
					igrac.izaberiTop(new TopGunNarancasti(TipTopa.TopNarancasti, mapa.dohvatiPlocicu(0, 0), valSetup.dohvatiTrenutniVal().dohvatiListuNeprijatelja()));
				}
				if (igraMenu.gumbKliknut("TopLed")) {
					igrac.izaberiTop(new TopGunLed(TipTopa.TopLed, mapa.dohvatiPlocicu(0, 0), valSetup.dohvatiTrenutniVal().dohvatiListuNeprijatelja()));
				}
				if (igraMenu.gumbKliknut("TopUltra")) {
					igrac.izaberiTop(new TopGunUltra(TipTopa.TopUltra, mapa.dohvatiPlocicu(0, 0), valSetup.dohvatiTrenutniVal().dohvatiListuNeprijatelja()));
				}
			}
		}
	}
	
	public void osvjezi() {
		CrtajQTeksturu(igraPozadina, 1088, 0, 192, 768);
		this.mapa.crtajMapu();
		this.valSetup.osvjezi();
		this.igrac.osvjezi();
		osvjeziSucelje();
	}

}
