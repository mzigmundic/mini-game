package com.glavni;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.sporedni.Vrijeme;

import static com.sporedni.Grafika.*;

import java.util.ArrayList;
public class Igrac {

	private PlociceMapa mapa;
	private TipPlocice[] tipoviPlocica;
	private ValSetup valSetup;
	private ArrayList<Top> listaTopova;
	boolean lijeviMisDolje, desniMisDolje, drziTop;
	private Top trenutniTop;
	public static int Pare, Zivoti;
	
	public Igrac(PlociceMapa mapa, ValSetup valSetup) {
		this.mapa = mapa;
		this.tipoviPlocica = new TipPlocice[3];
		this.tipoviPlocica[0] = TipPlocice.Pijesak;
		this.tipoviPlocica[1] = TipPlocice.Zemlja;
		this.tipoviPlocica[2] = TipPlocice.Voda;
		this.valSetup = valSetup;
		this.listaTopova = new ArrayList<Top>();
		this.lijeviMisDolje = false;
		this.desniMisDolje = false;
		this.drziTop = false;
		this.trenutniTop = null;
		Pare = 0;
		Zivoti = 0;
	}
	
	// Inicijaliziranje para i zivota za igraca
	public void postavi() {
		Pare = 200;
		Zivoti = 10;
	}
	
	public static boolean promijeniPare(int kolicina) {
		if (Pare + kolicina >= 0) {
			Pare += kolicina;
			return true;
		}
		return false;
	}
	
	public static void promijeniZivote(int kolicina) {
		Zivoti += kolicina;
	}
	
	public void osvjezi() {
		
		// Osvjezi drzanje topa
		if (drziTop) {
			trenutniTop.postaviX(dohvatiPlocicuMisa().dohvatiX());
			trenutniTop.postaviY(dohvatiPlocicuMisa().dohvatiY());
			trenutniTop.crtaj();
		}
		
		// Osvjezi sve topove u igri
		for (Top t : listaTopova) {
			t.osvjezi();
			t.crtaj();
			t.osvjeziListuNeprijatelja(valSetup.dohvatiTrenutniVal().dohvatiListuNeprijatelja());
		}
		// Obradi unos misa
		if (Mouse.isButtonDown(0) && !lijeviMisDolje) {
			postaviTop();
		}
		
		if (Mouse.isButtonDown(1) && !desniMisDolje) {
			System.out.println("Desni mis kliknut");
		}
		lijeviMisDolje = Mouse.isButtonDown(0);
		desniMisDolje = Mouse.isButtonDown(1);
		
		// Obradi unos tipkovnice
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Vrijeme.PromijeniMultiplikator(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Vrijeme.PromijeniMultiplikator(-0.2f);
			}
		}
	}
	
	private void postaviTop() {
		Plocica trenutnaPlocica = dohvatiPlocicuMisa();
		if (drziTop) {
			if (!trenutnaPlocica.jeZauzeta() && promijeniPare(-trenutniTop.dohvatiCijenu())) {
				listaTopova.add(trenutniTop);
				trenutnaPlocica.postaviZauzetost(true);
				drziTop = false;
				trenutniTop = null;
			}
		}
	}
	
	public void izaberiTop(Top top) {
		trenutniTop = top;
		drziTop = true;
	}
	
	private Plocica dohvatiPlocicuMisa() {
		return mapa.dohvatiPlocicu(Mouse.getX() / PLOCICA_SIZE, (VISINA - Mouse.getY() - 1) / PLOCICA_SIZE);
	}
	
}







