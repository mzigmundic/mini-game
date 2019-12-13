package com.glavni;

import static com.sporedni.Grafika.*;

import org.newdawn.slick.opengl.Texture;

public enum TipTopa {

	TopNarancasti(new Texture[] {UcitajTeksturuBrzo("topBazaNarancasta"), UcitajTeksturuBrzo("TopGunNarancasti")}, TipProjektila.MetakVatraMali, 10, 1000, 1, 30),
	TopLed(new Texture[] {UcitajTeksturuBrzo("topBazaLed"), UcitajTeksturuBrzo("topGunLed")}, TipProjektila.MetakLed, 20, 1000, 2, 40),
	TopUltra(new Texture[] {UcitajTeksturuBrzo("topBazaUltra"), UcitajTeksturuBrzo("topGunUltra")}, TipProjektila.MetakCrveni, 20, 1000, 2, 80);
	
	Texture[] tekstura;
	TipProjektila tipProjektila;
	int snaga, domet, cijena;
	float ucestalostPucanja;
	
	TipTopa(Texture[] tekstura, TipProjektila tipProjektila, int snaga, int domet, float ucestalost, int cijena) {
		this.tekstura = tekstura;
		this.tipProjektila = tipProjektila;
		this.snaga = snaga;
		this.domet = domet;
		this.ucestalostPucanja = ucestalost;
		this.cijena = cijena;
	}
	
}
