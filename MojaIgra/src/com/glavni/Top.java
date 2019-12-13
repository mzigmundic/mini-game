package com.glavni;

import org.newdawn.slick.opengl.Texture;

import static com.sporedni.Grafika.CrtajQTeksturu;
import static com.sporedni.Grafika.CrtajQTeksturuRot;
import static com.sporedni.Vrijeme.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Top implements Jedinka {
	
	private float x, y, vrijemeOdPosljednjegPucnja, ucestalostPucanja, kut;
	private int sirina, visina, domet, cijena;
	public Neprijatelj meta;
	private Texture[] teksture;
	private CopyOnWriteArrayList<Neprijatelj> neprijatelji;
	private boolean naciljan;
	public ArrayList<Projektil> projektili;
	public TipTopa tipTopa;
	
	public Top(TipTopa tipTopa, Plocica pocetnaPlocica, CopyOnWriteArrayList<Neprijatelj> neprijatelji) {
		this.tipTopa = tipTopa;
		this.teksture = tipTopa.tekstura;
		this.domet = tipTopa.domet;
		this.cijena = tipTopa.cijena;
		this.x = pocetnaPlocica.dohvatiX();
		this.y = pocetnaPlocica.dohvatiY();
		this.sirina = pocetnaPlocica.dohvatiSirinu();
		this.visina = pocetnaPlocica.dohvatiVisinu();
		this.neprijatelji = neprijatelji;
		this.naciljan = false;
		this.vrijemeOdPosljednjegPucnja = 0f;
		this.projektili = new ArrayList<Projektil>();
		this.ucestalostPucanja = tipTopa.ucestalostPucanja;
		this.kut = 0f;
	}
	
	private Neprijatelj naciljajMetu() {
		Neprijatelj najblizi = null;
		float najblizaUdaljenost = 10000;
		// Prodji kroz svakog neprijatelja i dohvati najblizeg
		for (Neprijatelj e : neprijatelji) {
			if (jeUDometu(e) && pronadjiUdaljenost(e) < najblizaUdaljenost && e.dohvatiTrenutnuEnergiju() > 0) {
				najblizaUdaljenost = pronadjiUdaljenost(e);
				najblizi = e;
			}
		}
		
		// Ako neprijatelj postoji i dohvacen je, naciljan = true
		if (najblizi != null) {
			naciljan = true;
		}
		return najblizi;
	}
	
	private boolean jeUDometu(Neprijatelj neprijatelj) {
		float udaljenostX = Math.abs(neprijatelj.dohvatiX() - x);
		float udaljenostY = Math.abs(neprijatelj.dohvatiY() - y);
		if (udaljenostX < domet && udaljenostY < domet) {
			return true;
		}
		return false;
	}
	
	private float pronadjiUdaljenost(Neprijatelj neprijatelj) {
		float udaljenostX = Math.abs(neprijatelj.dohvatiX() - x);
		float udaljenostY = Math.abs(neprijatelj.dohvatiY() - y);
		return udaljenostX + udaljenostY;
	}
	
	private float izracunajKut() {
		double trenutniKut = Math.atan2(meta.dohvatiY() - y, meta.dohvatiX() - x);
		return (float) Math.toDegrees(trenutniKut) - 90;
	}
	
	public abstract void pucaj(Neprijatelj meta);
	
	public void osvjeziListuNeprijatelja(CopyOnWriteArrayList<Neprijatelj> novaLista) {
		neprijatelji = novaLista;
	}
	
	public void osvjezi() {
		if (!naciljan || meta.dohvatiTrenutnuEnergiju() < 0) {
			meta = naciljajMetu();
		} else {
			kut = izracunajKut();
			if (vrijemeOdPosljednjegPucnja > ucestalostPucanja) {
				pucaj(meta);
				vrijemeOdPosljednjegPucnja = 0;
			}
		}
		
		if (meta == null || meta.jeZiv() == false) {
			naciljan = false;
		}
		
		vrijemeOdPosljednjegPucnja += Delta();
		
		for (Projektil p : projektili) {
			p.osvjezi();
		}
		
		crtaj();
	}

	public void crtaj() {
		CrtajQTeksturu(teksture[0], x, y, sirina, visina);
		if (teksture.length > 1) {
			for (int i = 1; i < teksture.length; i++ ) {
				CrtajQTeksturuRot(teksture[i], x, y, sirina, visina, kut);
			}
		}
	}

	public float dohvatiX() {
		return x;
	}

	public float dohvatiY() {
		return y;
	}

	public void postaviX(float x) {
		this.x = x;
	}

	public void postaviY(float y) {
		this.y = y;
	}

	public int dohvatiSirinu() {
		return sirina;
	}

	public int dohvatiVisinu() {
		return visina;
	}

	public void postaviSirinu(int sirina) {
		this.sirina = sirina;
	}

	public void postaviVisinu(int visina) {
		this.visina = visina;
	}
	
	public Neprijatelj dohvatiMetu() {
		return meta;
	}
	
	public int dohvatiCijenu() {
		return cijena;
	}

	

}
