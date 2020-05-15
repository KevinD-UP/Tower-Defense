package Field;

import Datamodel.Point;

public class FactoryLevel {

	public static Level factory(int i) throws Exception{
		Level tmp = null;
		switch(i) {

		case 0 :	//level survival
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x==3 && y<=17){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(((x>=3 && x<=11) || x>=17  && x<29)&& y==17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==11 && y>=6 && y<=17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=8 && x<=11 && y==6) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==8 && y>=3 && y<=6) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=8 && x<=17 && y==3) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==17 && y>=3 && y<17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			Tile h = new Tile(new Point((Tile.side/2)+(29*(Tile.side)), (Tile.side/2)+(17*(Tile.side))),true);
			tmp.setLayout(h, 29, 17);
			tmp.initLevel(500);
			break;

		case 1 :	//level 1
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x<=15 && y==5){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==15 && y>=5 && y<=18) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=15 && y==18) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(500);
			break;

		case 2 :	//level 2
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x==4 && y<=4){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>4 && x<=22 && y==4) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==22 && y>4 && y<14) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>4 && x<=22 && y==14) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==4 && y>=14 && y<19){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			Tile a = new Tile(new Point((Tile.side/2)+(4*(Tile.side)), (Tile.side/2)+(19*(Tile.side))),true);
			tmp.setLayout(a, 4, 19);
			tmp.initLevel(550);
			break;

		case 3 :	//level 3
			tmp = new Level(30,20,i);
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x>=0 && x<=15 && y==0) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==15 && y>0 && y<=5){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=5 && x<15 && y==5) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==5 && y>5 && y<15) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=5 && y==15) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(600);
			break;

		case 4 :	//level 4
			tmp = new Level(30,20,i);
			for(int x=0;x< tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x>=0 && x<27 && y==0) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==26 && y>0 && y<=5) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>18 && x<26 && y==5){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==18 && y>=5 && y<12) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>4 && x<=18 && y==12) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==4 && y>=12 && y<19) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			Tile d = new Tile(new Point((Tile.side/2)+(4*(Tile.side)), (Tile.side/2)+(19*(Tile.side))),true);
			tmp.setLayout(d, 4, 19);
			tmp.initLevel(650);
			break;

		case 5 :	//level 5
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x==6 && y<16){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=6 && x<24 && y==16) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==24 && y<=16 && y>5) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=24 && y==5) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(700);
			break;

		case 6 :	//level 6
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x==5 && y<17){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x<25 && x>=5 && y==17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==25 && y<=17 && y>0) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			Tile e = new Tile(new Point((Tile.side/2)+(25*(Tile.side)), (Tile.side/2)+(0*(Tile.side))),true);
			tmp.setLayout(e, 25, 0);
			tmp.initLevel(750);
			break;

		case 7 :	//level 7
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x<=17 && y==7){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==17 && y>7 && y<=14) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>17 && x<=22 && y==14) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==22 && y>14 && y<=17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>22 && y==17) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(800);
			break;

		case 8 :	//level 8
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x<=12 && y==8){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==12 && y>=2 && y<8) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>12 && x<=26 && y==2) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==26 && y>2 && y<=16) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=20 && x<26 && y==16) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==20 && y>16 && y<19) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			Tile f = new Tile(new Point((Tile.side/2)+(20*(Tile.side)), (Tile.side/2)+(19*(Tile.side))),true);
			tmp.setLayout(f, 20, 19);
			tmp.initLevel(850);
			break;

		case 9 :	//level 9
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x>=0 && x<=22 && y==0) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==22 && y>0 && y<=8){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=4 && x<22 && y==8) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==4 && y>8 && y<=16) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>4 && y==16) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(500);
			break;

		case 10 :	//level 10
			tmp = new Level(30,20,i);	
			for(int x=0;x<tmp.getWidth();x++){
				for(int y=0;y<tmp.getHeight();y++){
					if(x<=18 && y==5){
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==18 && y>5 && y<=10) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>18 && x<=25 && y==10) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==25 && y>10 && y<=15) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>=13 && x<25 && y==15) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x==13 && y>15 && y<=18) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else if(x>13 && y==18) {
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),true);
						tmp.setLayout(t, x, y);
					}
					else{
						Tile t = new Tile(new Point((Tile.side/2)+(x*(Tile.side)), (Tile.side/2)+(y*(Tile.side))),false);
						tmp.setLayout(t, x, y);
					}
				}
			}
			tmp.initLevel(500);
			break;

		default : new Exception("Erreur d'indice factoryLevel");
		}
		return tmp;
	}
}
