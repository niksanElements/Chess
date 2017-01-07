package test;

import Game.Borde;
import Game.Element;
import Game.Figure;
import Game.Listeners.FigureListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikolay on 12/22/2016.
 */
public class test2 extends JFrame {
    public test2(){
        Figure king = new Figure(Element.Horse,Color.BLACK);
        Figure queen = new Figure(Element.King,Color.WHITE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(king);
        FigureListener lis = new FigureListener(new Borde());
        king.addMouseListener(lis);
        panel.add(queen);
        add(king);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String [] args){
        new test2();
    }
}
