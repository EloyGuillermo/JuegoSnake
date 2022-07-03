package PaqueteSerpiente;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Serpiente {
	 protected static final int LONG_CUADRADO = 20;
	 protected static int POS_X_SERPIENTE = 100;
	 protected static int POS_Y_SERPIENTE = 300;
	 protected int direccionSnakeY = 0;
	 protected int direccionSnakeX = 0;
	 protected String direccion = "";
	 protected int puntos = 0;
	 protected String n1 = "";
	 protected Color colorCabeza = Color.GREEN;
	 protected Color colorS = Color.GREENYELLOW;
	 protected Rectangle cabeza = new Rectangle();
	 protected Rectangle cola = new Rectangle();
	 ArrayList<Rectangle> serpiente = new ArrayList<>();
	 
	 protected Serpiente(){
		 
	 }
	 
	 public Serpiente(String n1, Color colorCabeza, Color colorS, Rectangle cabeza, Rectangle cola, ArrayList<Rectangle> serpiente){
		 this.n1 = n1;
		 this.colorCabeza = colorCabeza;
		 this.colorS = colorS;
		 this.cabeza = cabeza;
		 this.cola = cola;
		 this.serpiente = serpiente;
	 }

	public int getPOS_X_SERPIENTE() {
		return POS_X_SERPIENTE;
	}

	public void setPOS_X_SERPIENTE(int pOS_X_SERPIENTE) {
		POS_X_SERPIENTE = pOS_X_SERPIENTE;
	}

	public int getPOS_Y_SERPIENTE() {
		return POS_Y_SERPIENTE;
	}

	public void setPOS_Y_SERPIENTE(int pOS_Y_SERPIENTE) {
		POS_Y_SERPIENTE = pOS_Y_SERPIENTE;
	}

	public int getDireccionSnakeY() {
		return direccionSnakeY;
	}

	public void setDireccionSnakeY(int direccionSnakeY) {
		this.direccionSnakeY = direccionSnakeY;
	}

	public int getDireccionSnakeX() {
		return direccionSnakeX;
	}

	public void setDireccionSnakeX(int direccionSnakeX) {
		this.direccionSnakeX = direccionSnakeX;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getN1() {
		return n1;
	}

	public void setN1(String n1) {
		this.n1 = n1;
	}

	public Color getColorCabeza() {
		return colorCabeza;
	}

	public void setColorCabeza(Color colorCabeza) {
		this.colorCabeza = colorCabeza;
	}

	public Color getColorS() {
		return colorS;
	}

	public void setColorS(Color colorS) {
		this.colorS = colorS;
	}

	public Rectangle getCabeza() {
		return cabeza;
	}

	public void setCabeza(Rectangle cabeza) {
		this.cabeza = cabeza;
	}

	public Rectangle getCola() {
		return cola;
	}

	public void setCola(Rectangle cola) {
		this.cola = cola;
	}

	public ArrayList<Rectangle> getSerpiente() {
		return serpiente;
	}

	public void setSerpiente(ArrayList<Rectangle> serpiente) {
		this.serpiente = serpiente;
	}

	public static int getLongCuadrado() {
		return LONG_CUADRADO;
	}
	
	public void aumentaPuntos() {
		this.puntos++;
	}
		
}
