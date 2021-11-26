package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;

	public Lane(Game game, int ord,boolean leftToRight) {
		this.game = game;
		this.ord = ord;
		this.leftToRight = leftToRight;
		this.density = (double) Math.random() * 2;
		this.speed = (int) Math.random() * 2;
	}

	public void update(){
		if (this.leftToRight){
			for (int i=0; i<cars.size();i++){

			}
		}
		else{
			for (int i=0; i<cars.size();i++){

			}
		}

		}
		// TODO

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

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
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

		public boolean isSafe(Case c) {
			if (c.ord == this.ord) {
				if (cars.isEmpty())
					return true;
				else {
					if (this.leftToRight) {
						for (int i = 0; i < cars.size(); i++) {
							Car atm = cars.get(i);
							int abs = atm.getLeftPosition().absc;
							int taille = atm.getLength();
							if (abs >= c.absc && (abs + taille) <= c.absc) {
								return false;
							}
						}
					} else {
						for (int i = 0; i < cars.size(); i++) {
							Car atm = cars.get(i);
							int abs = atm.getLeftPosition().absc;
							int taille = atm.getLength();
							if (abs <= c.absc && (abs - taille) >= c.absc) {
								return false;
							}
						}
					}
					return true;
				}

			}
			return true;
		}
}
