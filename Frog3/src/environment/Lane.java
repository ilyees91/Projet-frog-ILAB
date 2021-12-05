package environment;

import java.util.ArrayList;
import java.util.Random;
import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private double speed;
	private ArrayList<Car> ensCars = new ArrayList<Car>();
	private boolean leftToRight;
	private int time = 0;
	private double density;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord,boolean leftToRight) {
		this.game = game;
		this.ord = ord;
		this.leftToRight = leftToRight;
		this.density = (double) Math.random() * 0.1;
		this.speed = (double) Math.random()*3;
		this.time = 0;
	}

	public void update() {
		this.time++;
		if(this.time <this.speed) {
			for (int i = 0; i < ensCars.size(); i++) {
				ensCars.get(i).avancer(false);
			}
		}
		else{
			for(int i=0; i<ensCars.size();i++){
				ensCars.get(i).avancer(true);
				if (ensCars.get(i).getLeftPosition().absc<=-1 || ensCars.get(i).getLeftPosition().absc >= game.width){
					ensCars.remove(i);
				}
			}
			this.time = 0;
		}
		mayAddCar();
	}


		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e



	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */
	public boolean isSafe(Case c) {
		for (int i = 0; i < ensCars.size(); i++) {
			if (ensCars.get(i).getLeftPosition().absc <= c.absc && c.absc < (ensCars.get(i).getLeftPosition().absc + ensCars.get(i).getLength())) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				ensCars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}
	public void moveOrd(int val){
		this.ord=val;
		for (Car l: ensCars){
			l.moveOrd(val);
		}
	}
	public int getOrd(){
		return this.ord;
	}

}
