package Game;

import Game.Listeners.FigureListener;

import java.awt.*;

/**
 * Created by Nikolay on 12/22/2016.
 */
public class Player {

    public static final int PAWNS_SIZE = 8;

    private Figure king; // 04 or 74
    private Figure queen;// 03 or 73
    private Figure top1;// 00 or 70
    private Figure top2;// 07 or 77
    private Figure officer1; // 02 or 72
    private Figure officer2;// 05 or 75
    private Figure horse1;// 01 or 71
    private Figure horse2; // 06 or 76
    private Figure[] pawns; // row 1 or row 6

    private Borde borde;

    public Player(Borde borde,Color color,boolean isUp){
        this.borde = borde;
        init(color);
        place(isUp);
        initListeners();
    }

    private void initListeners() {
        king.addMouseListener(new FigureListener(borde));
        queen.addMouseListener(new FigureListener(borde));
        top1.addMouseListener(new FigureListener(borde));
        top2.addMouseListener(new FigureListener(borde));
        officer1.addMouseListener(new FigureListener(borde));
        officer2.addMouseListener(new FigureListener(borde));
        horse1.addMouseListener(new FigureListener(borde));
        horse2.addMouseListener(new FigureListener(borde));
        for(int i = 0;i < PAWNS_SIZE;i++){
            pawns[i].addMouseListener(new FigureListener(borde));
        }
    }

    private void place(boolean isUp) {
        if(isUp){
            borde.add(king,new Point(7,4));
            borde.add(queen,new Point(7,3));
            borde.add(top1,new Point(7,0));
            borde.add(top2,new Point(7,7));
            borde.add(officer1,new Point(7,2));
            borde.add(officer2,new Point(7,5));
            borde.add(horse1,new Point(7,1));
            borde.add(horse2,new Point(7,6));
            for(int i =0;i < PAWNS_SIZE;i++){
                borde.add(pawns[i],new Point(6,i));
                pawns[i].setUp(isUp);
            }
        } else {
            borde.add(king,new Point(0,4));
            borde.add(queen,new Point(0,3));
            borde.add(top1,new Point(0,0));
            borde.add(top2,new Point(0,7));
            borde.add(officer1,new Point(0,2));
            borde.add(officer2,new Point(0,5));
            borde.add(horse1,new Point(0,1));
            borde.add(horse2,new Point(0,6));
            for(int i =0;i < PAWNS_SIZE;i++){
                borde.add(pawns[i],new Point(1,i));
                pawns[i].setUp(isUp);
            }
        }
    }

    private void init(Color color) {
        king = new Figure(Element.King,color);
        queen = new Figure(Element.Queen,color);
        top1 = new Figure(Element.Top,color);
        top2 = new Figure(Element.Top,color);
        officer1 = new Figure(Element.Officer,color);
        officer2 = new Figure(Element.Officer,color);
        horse1 = new Figure(Element.Horse, color);
        horse2 = new Figure(Element.Horse,color);
        pawns = new Figure[PAWNS_SIZE];
        for(int i = 0;i < PAWNS_SIZE;i++){
            pawns[i] = new Figure(Element.Pawn,color);
        }
    }
}
