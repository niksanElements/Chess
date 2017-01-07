package Game.Listeners;

import Game.Borde;
import Game.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nikolay on 12/24/2016.
 */
public class FigureListener implements MouseListener {
    protected Borde borde;

    protected Point buffer;
    protected Point location;

    private boolean isClicked = false;

    public boolean pawnIsFirstTurn = true;

    public FigureListener(Borde borde){
        this.borde = borde;
        //this.location = new Point(0,0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Figure fig = (Figure) e.getSource();
        if(!isClicked) {
            buffer = new Point((int) fig.getY() / borde.getSizePreCell(), (int) fig.getX() / borde.getSizePreCell());
            if(borde.getBuffer() != null){
                Point p = borde.getBuffer();
                Figure old = borde.getFigure(p.x,p.y);
                BordeListener bl = (BordeListener) borde.getMouseListeners()[0];
                if( bl.checkNewPosition(old.getType(),p,buffer) && fig.getColor() != old.getColor()) {
                    borde.remove(fig);
                    borde.repaint();
                    borde.remove(old);
                    borde.add(old,buffer);
                    borde.getLayout().layoutContainer(borde);
                }
                borde.setBuffer(null);
                FigureListener oldListener = (FigureListener)old.getMouseListeners()[0];
                oldListener.isClicked = false;
            } else {
                borde.setBuffer(buffer);
                isClicked = true;
            }
        } else{
            borde.setBuffer(null);
            isClicked = false;
        }
        fig.isClicked(isClicked,fig.getX(),fig.getY(),borde.getGraphics());

    }

    public void setIsClicked(Figure fig,boolean flag){
        isClicked = flag;
        fig.isClicked(isClicked,fig.getX(),fig.getY(),borde.getGraphics());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
