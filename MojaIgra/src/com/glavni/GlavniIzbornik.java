package com.glavni;

import static com.sporedni.Grafika.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import com.sporedni.MenadzerStanja;
import com.sporedni.MenadzerStanja.StanjeIgre;
import com.sucelje.KoristnickoSucelje;

public class GlavniIzbornik {
	
	private Texture glavnaPozadina;
	private KoristnickoSucelje glavniIzbornikSucelje;
	
	public GlavniIzbornik() {
		glavnaPozadina = UcitajTeksturuBrzo("glavniIzbornik");
		glavniIzbornikSucelje = new KoristnickoSucelje();
		glavniIzbornikSucelje.dodajGumb("gIgraj", "gumbIgraj", SIRINA / 2 - 128, (int) (VISINA *0.45f) );
		glavniIzbornikSucelje.dodajGumb("gEditor", "gumbEditor", SIRINA / 2 - 128, (int) (VISINA *0.55f) );
		glavniIzbornikSucelje.dodajGumb("gIzlaz", "gumbIzlaz", SIRINA / 2 - 128, (int) (VISINA *0.65f) );
	}
	
	// Provjeri jel koristnik kliknuo koji gumb pa promjeni stanje
	private void osvjeziGumbove() {
		if (Mouse.isButtonDown(0)) {
			if (glavniIzbornikSucelje.gumbKliknut("gIgraj")) {
				MenadzerStanja.PostaviStanje(StanjeIgre.IGRA);
			}
			if (glavniIzbornikSucelje.gumbKliknut("gEditor")) {
				MenadzerStanja.PostaviStanje(StanjeIgre.EDITOR_MAPE);
			}
			if (glavniIzbornikSucelje.gumbKliknut("gIzlaz")) {
				System.exit(0);
			}
		}
	}
	
	public void osvjezi() {
		CrtajQTeksturu(glavnaPozadina, 0, 0, 2048, 1024);
		glavniIzbornikSucelje.crtajGumbove();
		osvjeziGumbove();
	}
	

}
