package test;

import Game.Borde.Borde;
import Game.Color;
import Game.Player;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    private Borde borde;

    public test(){
        Player p1 = new Player(Color.Black);
        Player p2 = new Player(Color.White);

        borde = new Borde(p2,p1,true);

        setSize(500,500);
        setVisible(true);
        setLayout(new GridLayout(8,8));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton[][] buttons = new JButton[8][8];
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                buttons[i][j] = new JButton("0");
                if(borde.borde[i][j] != null){
                    Class<?> same = borde.borde[i][j].getClass();
                    buttons[i][j].setText(same.getName());
                }
                add(buttons[i][j]);
            }
        }
    }

    public static void main(String[] args){
        new test();
    }
}
