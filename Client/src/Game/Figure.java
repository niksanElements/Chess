package Game;

import Game.Element;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Figure extends JComponent {

    public static final String BLACK_KING = "images/Chess_kdt60.png";
    public static final String BLACK_QEEN = "images/Chess_qdt60.png";
    public static final String BLACK_HORSE = "images/Chess_ndt60.png";
    public static final String BLACK_OFFICER = "images/Chess_bdt60.png";
    public static final String BLACK_PAWN = "images/Chess_pdt60.png";
    public static final String BLACK_TOP = "images/Chess_rdt60.png";

    public static final String WHITE_KING = "images/Chess_klt60.png";
    public static final String WHITE_QEEN = "images/Chess_qlt60.png";
    public static final String WHITE_HORSE = "images/Chess_nlt60.png";
    public static final String WHITE_OFFICER = "images/Chess_blt60.png";
    public static final String WHITE_PAWN = "images/Chess_plt60.png";
    public static final String WHITE_TOP = "images/Chess_rlt60.png";

    private int figureSize;
    private Color color;
    private boolean isUp;
    private Element type;
    private ImageIcon image;

    private Color clickedColor;

    public boolean isFirstTurn = true;

    public Figure(){
        this.figureSize = 88;
    }

    public void setFigureSize(int figureSize) {
        this.figureSize = figureSize;
    }

    public Color getColor() {
        return color;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public Element getType() {
        return type;
    }

    public Figure(Element type, Color color){
        this.type = type;
        this.color = color;
        checkType(type);
        this.figureSize = 88;
        setOpaque(false);
        this.clickedColor = Color.RED;
    }

    private void checkType(Element type) {
        switch(type){
            case King:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_KING);
                else
                    this.image = new ImageIcon(WHITE_KING);
                break;
            case Queen:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_QEEN);
                else
                    this.image = new ImageIcon(WHITE_QEEN);
                break;
            case Pawn:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_PAWN);
                else
                    this.image = new ImageIcon(WHITE_PAWN);
                break;
            case Officer:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_OFFICER);
                else
                    this.image = new ImageIcon(WHITE_OFFICER);
                break;
            case Top:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_TOP);
                else
                    this.image = new ImageIcon(WHITE_TOP);
                break;
            case Horse:
                if(color == Color.BLACK)
                    this.image = new ImageIcon(BLACK_HORSE);
                else
                    this.image = new ImageIcon(WHITE_HORSE);
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(type != null){
            g.drawImage(this.image.getImage(),0,0,this.figureSize,this.figureSize,null);
        }
        Dimension d = new Dimension(this.figureSize,this.figureSize);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,this.figureSize,this.figureSize);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
    }



    @Override
    public Dimension getPreferredSize(){
        return new Dimension(this.figureSize,this.figureSize);
    }

    public void isClicked(boolean isClicked, int x, int y, Graphics g) {
        if(isClicked) {
            g.setColor(clickedColor);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawRect(x,y,this.figureSize,this.figureSize);
    }
}
