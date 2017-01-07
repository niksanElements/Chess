package Game.Listeners;

import Game.Borde;
import Game.Element;
import Game.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nikolay on 12/24/2016.
 */
public class BordeListener implements MouseListener {



    private Figure figures[][];

    public BordeListener(Figure[][] figures){
        this.figures = figures;
    }

    public boolean checkNewPosition(Element type, Point oldPosition, Point newPosition){
        switch(type){
            case King:
                if(king(oldPosition,newPosition)) {
                    figures[oldPosition.x][oldPosition.y].isFirstTurn = false;
                    return true;
                }
                break;
            case Queen:
                if(queen(oldPosition,newPosition)) return true;
                break;
            case Pawn:
                if(pawn(oldPosition,newPosition,
                        figures[oldPosition.x][oldPosition.y].isUp(),
                        figures[oldPosition.x][oldPosition.y].isFirstTurn)) {
                    figures[oldPosition.x][oldPosition.y].isFirstTurn = false;
                    return true;
                }
                break;
            case Officer:
                if(offecer(oldPosition,newPosition))return true;
                break;
            case Top:
                if(top(oldPosition,newPosition)){
                    figures[oldPosition.x][oldPosition.y].isFirstTurn = false;
                    return true;
                }
                break;
            case Horse:
                if(horse(oldPosition,newPosition))return true;
                break;
        }

        return false;
    }

    private boolean checkForEnemy(Point oldPosition, Point newPosition) {
        if(figures[newPosition.x][newPosition.y] != null) {
            if (figures[newPosition.x][newPosition.y].getColor() !=
            figures[oldPosition.x][oldPosition.y].getColor()){
                return true;
            }
        }
        return false;
    }

    private boolean horse(Point oldPosition, Point newPosition) {
        int oX = oldPosition.x;
        int oY = oldPosition.y;
        int nX = newPosition.x;
        int nY = newPosition.y;
        if(oX + 1 == nX && oY + 2 == nY) return true;
        if(oX - 1 == nX  && oY + 2 == nY) return true;
        if(oX + 1 == nX && oY - 2 == nY) return true;
        if(oX - 1 == nX && oY - 2 == nY) return true;
        if(oX + 2 == nX && oY + 1 == nY) return true;
        if(oX - 2 == nX && oY + 1 == nY) return true;
        if(oX + 2 == nX && oY - 1 == nY) return true;
        if(oX - 2 == nX && oY - 1 == nY) return true;
        return false;
    }

    private boolean top(Point oldPosition, Point newPosition) {
        int oX = oldPosition.x;
        int oY = oldPosition.y;
        int nX = newPosition.x;
        int nY = newPosition.y;
        if(figures[oX][oY].isFirstTurn && figures[oX][oY].getType() == Element.Top ){
            if(nY  >= figures.length && figures[nX][nY + 1] != null && figures[nX][nY + 1].getType() == Element.King &&
                    figures[nX][nY + 1].isFirstTurn){
                if(figures[oX][oY].getColor() == figures[nX][nY + 1].getColor()){
                    figures[nX][nY-1] = figures[nX][nY +1];
                    figures[nX][nY +1] = null;
                    figures[nX][nY-1].isFirstTurn = false;
                    return true;
                }
            }
            if(nY == -1 && figures[nX][nY - 1] != null && figures[nX][nY - 1].getType() == Element.King &&
                    figures[nX][nY - 1].isFirstTurn){
                if(figures[oX][oY].getColor() == figures[nX][nY - 1].getColor()){
                    figures[nX][nY+1] = figures[nX][nY - 1];
                    figures[nX][nY -1] = null;
                    figures[nX][nY+1].isFirstTurn = false;
                    return true;
                }
            }
        }
        for(int i = oldPosition.x - 1;i >= newPosition.x + 1;i--){
            if(figures[i][oldPosition.y] != null) return false;
        }
        for(int i = oldPosition.x + 1;i <= newPosition.x - 1;i++){
            if(figures[i][oldPosition.y] != null) return false;
        }
        for(int i = oldPosition.y - 1;i >= newPosition.y + 1;i--){
            if(figures[oldPosition.x][i] != null) return false;
        }
        for(int i = oldPosition.y + 1;i <= newPosition.y - 1;i++){
            if(figures[oldPosition.x][i] != null) return false;
        }
        if(oldPosition.x == newPosition.x && oldPosition.y != newPosition.y)
            return true;
        if(oldPosition.x != newPosition.x && oldPosition.y == newPosition.y)
            return true;
        return false;
    }

    private boolean offecer(Point oldPosition, Point newPosition) {
        int oX = oldPosition.x;
        int oY = oldPosition.y;
        int nX = newPosition.x;
        int nY = newPosition.y;
        for(int i = oX - 1, j = oY -1;i >= nX+1 && j >= nY + 1;i--,j--){
            if(figures[i][j] != null) return false;
        }
        for(int i = oX + 1, j = oY + 1;i <= nX - 1 && j <= nY - 1;i++,j++){
            if(figures[i][j] != null) return false;
        }
        for(int i = oX+1,j = oY -1;i <= nX-1&&j >= nY +1;i++,j--){
            if(figures[i][j] != null) return false;
        }
        for(int i = oX -1,j = oY +1;i >= nX+1 && j <= nY - 1;i--,j++){
            if(figures[i][j] != null) return false;
        }
        if(oX + nY == oY + nX) return true;
        if(oX + oY == nX + nY) return true;
        return false;
    }

    private boolean pawn(Point oldPosition, Point newPosition,boolean isUp,boolean isFirstTurn) {
        int oX = oldPosition.x;
        int oY = oldPosition.y;
        int nX = newPosition.x;
        int nY = newPosition.y;
        if(!isUp) {
            if(isFirstTurn){
                if((oX == nX-1 || oX == nX - 2) && oY == nY && figures[nX][nY] == null ) return true;
            } else {
                if(oX == nX-1 && oY == nY && figures[nX][nY] == null ) return true;
            }
            if(oX == nX-1 && oY == nY+1 && figures[nX][nY] != null) return true;
            if(oX == nX-1 && oY == nY-1 && figures[nX][nY] != null) return true;
        } else {
            if(isFirstTurn){
                if((oX == nX + 1 || oX == nX + 2) && oY == nY && figures[nX][nY] == null ) return true;
            } else {
                if(oX == nX + 1 && oY == nY && figures[nX][nY] == null ) return true;
            }
            if(oX == nX+1 && oY == nY+1 && figures[nX][nY] != null) return true;
            if(oX == nX+1 && oY == nY-1 && figures[nX][nY] != null) return true;
        }
        return false;
    }

    private boolean queen(Point oldPosition, Point newPosition) {
        Boolean flag = this.top(oldPosition,newPosition);
        if(flag == true) return flag;
        flag = this.offecer(oldPosition,newPosition);
        return flag;
    }

    private boolean king(Point oldPosition, Point newPosition) {
        int differenceX = Math.abs(newPosition.x - oldPosition.x);
        int differenceY = Math.abs(newPosition.y - oldPosition.y);
        if(differenceX <= 1 && differenceY <= 1){
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Borde borde = (Borde)e.getSource();
        Point oldPosition = borde.getBuffer();
        if(oldPosition != null) {
            Figure fig = borde.getFigure(oldPosition.x,oldPosition.y);
            if(fig != null) {
                Point newPosition = new Point(e.getY() / borde.getSizePreCell(), e.getX() / borde.getSizePreCell());
                if(checkNewPosition(fig.getType(), oldPosition, newPosition)) {
                    if(borde.getBuffer() != null) {
                        borde.repaint();
                        borde.add(fig, new Point((int) e.getY() / borde.getSizePreCell(),
                                (int) e.getX() / borde.getSizePreCell()));
                        borde.getLayout().layoutContainer(borde);
                        borde.setBuffer(null);
                        FigureListener fl = (FigureListener) fig.getMouseListeners()[0];
                        fl.setIsClicked(fig, false);
                    }
                }
            }
        }
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
