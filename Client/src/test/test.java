package test;

import Game.*;
import Game.Listeners.BordeListener;
import Game.Listeners.FigureListener;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    private Borde borde;

    public test(){
        Borde borde = new Borde();
        borde.addMouseListener(new BordeListener(borde.getFigures()));
        JPanel panel = new JPanel();
        Player black = new Player(borde,Color.BLACK,true);
        Player white = new Player(borde,Color.WHITE,false);
        panel.add(borde);
        add(panel);
        setVisible(true);
       // add(new JFigure(new King(0,0),new ImageIcon("images/Chess_kdt60.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new test();
    }
}
