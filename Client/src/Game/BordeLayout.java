package Game;

import java.awt.*;

/**
 * BorderLayout is a layout with matrix n x n. It is use for displaying
 * the figures on the JBorde.
 */




public class BordeLayout implements LayoutManager2 {

    // Number of cells on one of the four sides.
    private int numberCells;
    private int sizePreCell;

    //private int side;
    private Figure items[][];


    public BordeLayout(int numberCells,int sizePreCell){
        this.numberCells = numberCells;
        this.sizePreCell = sizePreCell;
        this.items = new Figure[this.numberCells][this.numberCells];
    }

    public Figure getFigure(int x,int y) {
        return this.items[x][y];
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if(comp instanceof Figure){
            Point p = (Point) constraints;
            if(constraints == null){
                throw new IllegalArgumentException("cannot add to layout: constraint must be Figure");
            }
            if(this.items[p.x][p.y] == null)
                this.items[p.x][p.y] = (Figure)comp;
        }
    }

    public Figure[][] getFigures() {
        return  items;
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        Point p = comp.getLocation();
        items[p.y/this.sizePreCell][p.x/this.sizePreCell] = null;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            if (parent instanceof Borde) {

                int numberComp = parent.getComponentCount();
                if (numberComp == 0) {
                    return;
                }

                // Total parent dimensions
                Dimension size = parent.getSize();
                int totalW = size.width - (insets.left + insets.right);
                int totalH = size.height - (insets.top + insets.bottom);


                for (int i = 0; i < this.numberCells; i++) {
                    for (int j = 0; j < this.numberCells; j++) {
                        Component c = this.items[i][j];
                        if (c != null) {
                            int y = insets.left + (this.sizePreCell * i);
                            int x = insets.top + (this.sizePreCell * j);
                            c.setBounds(x, y, this.sizePreCell, this.sizePreCell);
                        }
                    }
                }
            }
        }
    }
}