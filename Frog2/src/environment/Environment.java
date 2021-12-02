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
        this.ensLane.add(new Lane (game, 0,true));
        for(int i=1; i<game.height-1;i++){
            this.ensLane.add(new Lane (game, i,i%2!=0));
        }

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
           ensLane.get(i).update();
        }
    }

}