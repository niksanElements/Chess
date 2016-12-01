package Game;

import Game.Borde.Borde;
import Game.Figure.*;

public class Player {
    private Color color;

    private Pawn[] pawns  = new Pawn[Borde.SIZE_BORDE];
    private Horse leftHorse;
    private Horse rightHorse;
    private Top leftTop;
    private Top rightTop;
    private Officer leftOfficer;
    private Officer rightOfficer;
    private Queen queen;
    private King king;

    public Player(Color color){
        this.color = color;
        if(color == Color.White) {
            for (int i = 0; i < Borde.SIZE_BORDE; i++) {
                this.pawns[i] = new Pawn(1, i);
            }
            this.leftOfficer = new Officer(0, 0);
            this.rightOfficer = new Officer(0, 7);
            this.leftHorse = new Horse(0, 1);
            this.rightHorse = new Horse(0, 6);
            this.leftTop = new Top(0, 2);
            this.rightTop = new Top(0, 5);
            this.queen = new Queen(0, 3);
            this.king = new King(0, 4);
        }
        else{
            for (int i = 0; i < Borde.SIZE_BORDE; i++) {
                this.pawns[i] = new Pawn(6, i);
            }
            this.leftOfficer = new Officer(7, 0);
            this.rightOfficer = new Officer(7, 7);
            this.leftHorse = new Horse(7, 1);
            this.rightHorse = new Horse(7, 6);
            this.leftTop = new Top(7, 2);
            this.rightTop = new Top(7, 5);
            this.queen = new Queen(7, 3);
            this.king = new King(7, 4);
        }
    }

    public Pawn getPawn(int index){
        if(index < Borde.SIZE_BORDE){
            throw new IndexOutOfBoundsException();
        }
        return this.pawns[index];
    }
    public Horse getLeftHorse(){return this.leftHorse;}
    public Horse getRightHorse(){return this.rightHorse;}
    public Top getLeftTop(){return this.leftTop;}
    public  Top getRightTop(){return this.rightTop;}
    public Officer getLeftOfficer(){return this.leftOfficer;}
    public Officer getRightOfficer(){return this.rightOfficer;}
    public Queen getQueen(){return this.queen;}
    public King getKing(){return this.king;}

    public Figure[] getAll(){
        Figure[] figures  = new Figure[]{
            pawns[0],pawns[1],pawns[2],pawns[3],pawns[4],pawns[5],pawns[6],pawns[7],
            leftOfficer,leftHorse,leftTop,queen,king,rightTop,rightHorse,rightOfficer};

        return figures;
    }

}