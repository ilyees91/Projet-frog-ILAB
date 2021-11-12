package frog;

import gameCommons.Game;
import gameCommons.*;
import util.Case;
import util.Direction;


public class Frog implements IFrog {
	private Case position;
	private Direction myDirection;
	private Game game;

	public Frog(Game game) {
		this.game = game;
		this.position = new Case(this.game.width/2, 0);
		this.myDirection = Direction.up;
	}


	public Case getPosition() {
		return this.position;
	}


	public Direction getDirection() {
		return this.myDirection;
	}


	public void move(Direction key) {

		if (key == Direction.up && this.position.ord+1<this.game.height){
			this.position = new Case(position.absc, position.ord+1);
		}
		if (key == Direction.down && this.position.ord-1>0){
			this.position = new Case(position.absc, position.ord-1);
		}
		if (key == Direction.left && this.position.absc-1>0){
			this.position = new Case(position.absc-1, position.ord);
		}
		if (key == Direction.right && this.position.absc+1<this.game.width){
			this.position = new Case(position.absc+1, position.ord);
		}
	}
}
