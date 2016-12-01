package Game.Borde;

import Game.Color;
import Game.Figure.Figure;
import Game.Player;

public class Borde{
    public static final int SIZE_BORDE = 8;

    public Figure[][] borde = new Figure[SIZE_BORDE][SIZE_BORDE];
    private Player black;
    private Player white;
    private boolean isWhiteInFronte;

    public Borde(Player black, Player white,boolean isWhiteInFronte){
        this.black = new Player(Color.Black);
        this.white = new Player(Color.White);
        this.setFigures(white);
        this.setFigures(black);
        this.isWhiteInFronte = isWhiteInFronte;
    }

    private void setFigures(Player player) {
        Figure[] figures = player.getAll();
        for(int i = 0;i < figures.length;i++){
            this.borde[figures[i].getX()][figures[i].gety()] = figures[i];
        }
    }
}