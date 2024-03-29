package com.sporedni;

import static com.sporedni.Level.UcitajMapu;

import com.glavni.EditorMape;
import com.glavni.GlavniIzbornik;
import com.glavni.Igra;
import com.glavni.PlociceMapa;

public class MenadzerStanja {
	
	public static enum StanjeIgre {
		GLAVNI_IZBORNIK, IGRA, EDITOR_MAPE
	}
	
	public static StanjeIgre stanjeIgre = StanjeIgre.GLAVNI_IZBORNIK;
	public static GlavniIzbornik glavniIzbornik;
	public static Igra igra;
	public static EditorMape editor;
	public static long sekundaPlusJedan = System.currentTimeMillis() + 1000;
	public static int frejmovaUPrethodnojSekundi = 0;
	public static int frejmovaUTrenutnojSekundi = 0;
	
	static PlociceMapa mapa = UcitajMapu("mojaMapa");
	
	public static void Osvjezi() {
		switch(stanjeIgre) {
		case GLAVNI_IZBORNIK:
			if (glavniIzbornik == null) {
				glavniIzbornik = new GlavniIzbornik();
			}
			glavniIzbornik.osvjezi();
			break;
		case IGRA:
			if (igra == null) {
				igra = new Igra(mapa);
			}
			igra.osvjezi();
			break;
		case EDITOR_MAPE:
			if (editor == null) {
				editor = new EditorMape();
			}
			editor.osvjezi();
			break;
		}
		
		long trenutnoVrijeme = System.currentTimeMillis();
		if (trenutnoVrijeme > sekundaPlusJedan) {
			sekundaPlusJedan += 1000;
			frejmovaUPrethodnojSekundi = frejmovaUTrenutnojSekundi;
			frejmovaUTrenutnojSekundi= 0;
		}
		frejmovaUTrenutnojSekundi++;
	}
	
	public static void PostaviStanje(StanjeIgre novoStanje) {
		stanjeIgre = novoStanje;
	}

}
