package com.glavni;

public class ValSetup {
	
	private float vrijemeOdPosljednjegVala, vrijemeIzmedjuNeprijatelja;
	private int redniBrojVala, brojNeprijateljaPoValu;
	private Neprijatelj[] tipoviNeprijatelja;
	private Val trenutniVal;
	
	public ValSetup(Neprijatelj[] tipoviNeprijatelja, float vrijemeIzmedjuNeprijatelja, int neprijateljaPoValu) {
		this.tipoviNeprijatelja = tipoviNeprijatelja;
		this.vrijemeIzmedjuNeprijatelja = vrijemeIzmedjuNeprijatelja;
		this.brojNeprijateljaPoValu = neprijateljaPoValu;
		this.vrijemeOdPosljednjegVala = 0;
		this.redniBrojVala = 0;
		this.trenutniVal = null;
		noviVal();
	}
	
	public void osvjezi( ) {
		if (!trenutniVal.jeValZavrsen()) {
			trenutniVal.osvjezi();
		} else {
			noviVal();
		}
	}
	
	public void noviVal() {
		trenutniVal = new Val(tipoviNeprijatelja, vrijemeIzmedjuNeprijatelja, brojNeprijateljaPoValu);
		redniBrojVala++;
		System.out.println("Zapocni Val " + redniBrojVala);
	}
	
	public Val dohvatiTrenutniVal() {
		return trenutniVal;
	}
	
	public int dohvatiRedniBrojVala() {
		return redniBrojVala;
	}

}
