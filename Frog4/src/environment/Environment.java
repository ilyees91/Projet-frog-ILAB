package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//TODO
    private Game game;
    private ArrayList<Lane> ensLane;


    public Environment(Game game) {
        this.game = game;
        this.ensLane = new ArrayList<Lane>();
        for(int i=0; i<game.height;i++){
            this.ensLane.add(new Lane (game, i,i%2!=0));
        }
    }

    public boolean isSafe(Case c) {
        for (Lane l : ensLane){
            if (l.getOrd()==c.ord)
                return l.isSafe(c);
        }
        return true;
    }

    public boolean isWinningPosition(Case c){
        if (c.ord>=game.height-1){
            return true;
        }
        return false;
    }

    public void update() {
        for (int i=2; i< ensLane.size()-1; i++){
            ensLane.get(i).update();
        }
    }

    public void addLane(){
        this.ensLane.add(new Lane(this.game,this.ensLane.size(), ensLane.size()%2 == 0));
    }
    public void moveLane(int val){
        for (int i=0; i< ensLane.size();i++){
            Lane l=ensLane.get(i);
            l.moveOrd(i-val);
        }
    }

}
