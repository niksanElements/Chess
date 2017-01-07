package Game;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ComponentEvent;

/**
 * Created by Nikolay on 12/20/2016.
 */
public class Borde extends JPanel {

    public static final Color BLACK = new Color(128,64,0);
    public static final Color WHITE = new Color(255,230,153);

    private Point buffer;
    
    private int numberCells;
    private int sizePreCell;

    //private Figure[][] figures;

    public Borde(){

        this.numberCells = 8;
        this.sizePreCell = 88;
        setLayout(new BordeLayout(this.numberCells,this.sizePreCell));
        this.setBodeSize(this.numberCells*this.sizePreCell,this.numberCells*this.sizePreCell);
        //figures = new Figure[this.numberCells][this.numberCells];
    }

    public Point getBuffer() {
        return buffer;
    }

    public void setBuffer(Point buffer) {
        this.buffer = buffer;
    }


    public void setBodeSize(int width,int height){
        Dimension d = new Dimension(width,height);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0;i < this.numberCells;i++){
            for(int j = 0;j < this.numberCells;j++) {
                Color color;
                if (i % 2 == 0) {
                    if(j%2 == 1) {
                        color = BLACK;
                    }else {
                        color = WHITE;
                    }
                } else {
                    if(j%2 == 0) {
                        color = BLACK;
                    }else {
                        color = WHITE;
                    }
                }
                g.setColor(color);
                g.fill3DRect(i*this.sizePreCell,j*this.sizePreCell, this.sizePreCell,this.sizePreCell,true);
            }
        }
    }

    public int getSizePreCell(){
        return sizePreCell;
    }

    public Figure getFigure(int x,int y){
        BordeLayout bordeLayout = (BordeLayout) getLayout();
        return bordeLayout.getFigure(x,y);
    }

    public Figure[][] getFigures(){
        BordeLayout bordeLayout = (BordeLayout) getLayout();
        return bordeLayout.getFigures();
    }
}
