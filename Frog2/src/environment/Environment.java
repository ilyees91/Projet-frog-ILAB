package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> ensLane;

    public Environment(Game game) {
        this.game = game;
        this.ensLane = new ArrayList<Lane>();

    }
    public boolean isSafe(Case c){
        Lane atm = ensLane.get(c.ord);
        return atm.isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        if (c.ord==this.game.height-1){
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        for (int i=0; i< ensLane.size(); i++){
            Lane atm= ensLane.get(i);
            atm.update();
        }


    }

}
