package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class Lane {
    private Game game;
    private int ord;
    private double speed;
    private int time;
    private ArrayList<Car> cars = new ArrayList<>();
    private boolean leftToRight;
    private double density;

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
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).avancer(false);
            }
        }
        else{
            for(int i=0; i<cars.size();i++){
                cars.get(i).avancer(true);
                if (cars.get(i).getLeftPosition().absc<=-1 || cars.get(i).getLeftPosition().absc >= game.width){
                    cars.remove(i);
            }
            }
            this.time = 0;
        }
        mayAddCar();
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
            if (game.randomGen.nextDouble() < density ) {
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
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).getLeftPosition().absc <= c.absc && c.absc < (cars.get(i).getLeftPosition().absc + cars.get(i).getLength())) {
                        return false;
                    }
                }
            return true;
    }
}