package PaqueteSerpiente;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainSnake extends Application{
	 private static final int ESCENA_TAM = 600;
	 private static final int MENU_TAM = 400;
	 private int POS_AÑADO_CUADRADO = 2;
	 protected Label ColorSerpiente = new Label();
	 protected Label Titulo = new Label();
	 protected Label NombreMenu = new Label();
	 protected Label Puntuacion = new Label();
	 protected TextField CampoNombre = new TextField();
	 protected Label NombreJ = new Label();
	 protected Label Pregunta = new Label();
	 protected Label Controles = new Label();
	 protected Button Inicio = new Button();
	 protected Image apple = new Image("PaqueteSerpiente/manzana1.png");
	 protected ImageView manzana = new ImageView(apple);
	 protected Image imageSnake = new Image("PaqueteSerpiente/s1.jpg");
	 protected ImageView imagenSnake = new ImageView(imageSnake);
	 protected String n1 = "";
	 protected static boolean seguir = false;
	 protected Rectangle comida = new Rectangle(Serpiente.LONG_CUADRADO,Serpiente.LONG_CUADRADO, Color.TRANSPARENT);
	 protected Image ico = new Image("logo.ico");
	
	 protected Stage crearEscenarioMenu(Stage stageMenu, Pane PaneMenu, Scene MenuScene, Serpiente nuevaSerpiente) {
			 ChoiceBox<String> coloresS = new ChoiceBox<String>();
			 
			 stageMenu.getIcons().add(ico);
			  coloresS.setLayoutX(105);
				 coloresS.setLayoutY(230);
				 coloresS.setPadding(new Insets(5,50,5,50));
				 coloresS.getItems().add("Verde");
				 coloresS.getItems().add("Naranja");
				 coloresS.getItems().add("Rojo");
				 coloresS.getItems().add("Azul");
				 coloresS.getItems().add("Amarillo");
				 coloresS.getItems().add("Gris");
				 coloresS.getItems().add("Lila");
				 coloresS.getItems().add("Colores");
				 coloresS.getSelectionModel().select(7);

			 stageMenu.setTitle("Menú");
			 stageMenu.setScene(MenuScene);
			 stageMenu.setResizable(false);
		 
		 	// Título del Menú
			Titulo.setText("Snake");
			Titulo.setLayoutX(90);
			Titulo.setLayoutY(6);
			Titulo.setFont(Font.font("Edwardian Script ITC", FontWeight.BOLD,90));
			Titulo.setTextFill(Color.RED);
				
			// Campo para introducir el Nombre
			CampoNombre.setLayoutX(80);
			CampoNombre.setLayoutY(180);
			CampoNombre.setPromptText("   Escribe tu Nombre");
			CampoNombre.setPadding(new Insets(10,50,10,50));
			
			// Seleccionar Colores con el ChoiceBox
			seleccionarColor(coloresS, nuevaSerpiente);
			
			// Boton para Comenzar
			Inicio.setText("Comenzar");
			Inicio.setLayoutX(110);
			Inicio.setLayoutY(320);
			Inicio.setPadding(new Insets(10,58,10,58));
			
			imagenSnake.setFitWidth(400);	
			imagenSnake.setFitHeight(500);
			
			// Añado todo al Pane del Menú
			PaneMenu.getChildren().addAll(imagenSnake, coloresS, NombreMenu, CampoNombre, ColorSerpiente, Titulo, Inicio);
	
			return stageMenu;
	 }
	 
	 protected Stage crearEscenaSerpiente(Stage stage, Serpiente nuevaSerpiente, Pane PaneSerpiente, Scene SnakeScene) throws InterruptedException {
		 
		 stage.setTitle("Snake");
		 stage.getIcons().add(ico);
		 stage.setScene(SnakeScene);
		 stage.setResizable(false);
		 añadirParedes(PaneSerpiente);
		 añadePuntuacion(PaneSerpiente, nuevaSerpiente);
		 añadirComida(PaneSerpiente);
		 añadirSerpiente(PaneSerpiente, nuevaSerpiente);	 
		 return stage;
	 }
	 
	 protected Stage crearEscenaPuntuacion(Stage stagePuntuacion, Scene PuntuacionScene, Pane PanePuntuacion, Serpiente nuevaSerpiente) {
		
		 stagePuntuacion.getIcons().add(ico);
		 stagePuntuacion.setTitle("Puntuación");
		 stagePuntuacion.setScene(PuntuacionScene);
		 stagePuntuacion.setResizable(false);
		 
		 Pregunta.setText("Tu puntuación es de: "+(nuevaSerpiente.getPuntos()-1));
		 Pregunta.setLayoutX(50);
		 Pregunta.setLayoutY(40);
		 Pregunta.setFont(new Font("",15));
		 
		 PanePuntuacion.getChildren().addAll(Pregunta);
		 return stagePuntuacion;
	 }
	 
	 protected Serpiente crearSerpiente() {
		Rectangle cabeza = new Rectangle();
		Rectangle cola = new Rectangle();
		ArrayList<Rectangle> serpiente = new ArrayList<>();
		
		Serpiente nuevaSerpiente = new Serpiente("", Color.GREEN, Color.GREENYELLOW, cabeza, cola, serpiente);
		nuevaSerpiente.serpiente.add(cabeza);
		nuevaSerpiente.serpiente.add(cola);
		
		cabeza.setWidth(Serpiente.LONG_CUADRADO);
		cabeza.setHeight(Serpiente.LONG_CUADRADO);
		
		cola.setWidth(Serpiente.LONG_CUADRADO);
		cola.setHeight(Serpiente.LONG_CUADRADO);
		
		cabeza.setFill(nuevaSerpiente.colorCabeza);
		cola.setFill(nuevaSerpiente.colorS);
		
		nuevaSerpiente.serpiente.get(0).setX(nuevaSerpiente.getPOS_X_SERPIENTE());
		nuevaSerpiente.serpiente.get(0).setY(nuevaSerpiente.getPOS_Y_SERPIENTE());
		
		nuevaSerpiente.serpiente.get(1).setX(nuevaSerpiente.getPOS_X_SERPIENTE()-21);
		nuevaSerpiente.serpiente.get(1).setY(nuevaSerpiente.getPOS_Y_SERPIENTE());
		return nuevaSerpiente;
	 }
	 
	 protected void seleccionarColor(ChoiceBox<String> coloresS, Serpiente nuevaSerpiente) { 	
			coloresS.setOnAction(e -> {
				if(coloresS.getValue().equals(coloresS.getItems().get(0))) {
					nuevaSerpiente.colorCabeza = Color.GREEN;
					nuevaSerpiente.colorS = Color.GREENYELLOW;
				}
				else if(coloresS.getValue().equals(coloresS.getItems().get(1))) {
					nuevaSerpiente.colorCabeza = Color.DARKORANGE;
					nuevaSerpiente.colorS = Color.ORANGE;
				}
				else if (coloresS.getValue().equals(coloresS.getItems().get(2))) {
					nuevaSerpiente.colorCabeza = Color.DARKRED;
					nuevaSerpiente.colorS = Color.RED;
				}
				else if (coloresS.getValue().equals(coloresS.getItems().get(3))) {
					nuevaSerpiente.colorCabeza = Color.BLUE;
					nuevaSerpiente.colorS = Color.AQUA;
				}
				else if (coloresS.getValue().equals(coloresS.getItems().get(4))) {
					nuevaSerpiente.colorCabeza = Color.GREENYELLOW;
					nuevaSerpiente.colorS = Color.YELLOW;
				}
				else if (coloresS.getValue().equals(coloresS.getItems().get(5))) {
					nuevaSerpiente.colorCabeza = Color.GREY;
					nuevaSerpiente.colorS = Color.DARKGREY;
				}
				else if (coloresS.getValue().equals(coloresS.getItems().get(6))) {
					nuevaSerpiente.colorCabeza = Color.PURPLE;
					nuevaSerpiente.colorS = Color.MEDIUMPURPLE;
				}
				nuevaSerpiente.cabeza.setFill(nuevaSerpiente.colorCabeza);
				nuevaSerpiente.cola.setFill(nuevaSerpiente.colorS);
			});
		 }
	 
	 protected void añadirParedes(Pane PaneSerpiente) { 
		 	ArrayList<Rectangle> listaParedes = new ArrayList<>();
			listaParedes.add(new Rectangle(600,2, Color.BLACK));
			listaParedes.add(new Rectangle(600,2, Color.BLACK));
			listaParedes.add(new Rectangle(2,600, Color.BLACK));
			listaParedes.add(new Rectangle(2,600, Color.BLACK));
			listaParedes.get(0).setX(0);
			listaParedes.get(0).setY(598);
			
			listaParedes.get(1).setX(0);
			listaParedes.get(1).setY(0);
			
			listaParedes.get(2).setX(0);
			listaParedes.get(2).setY(0);
			
			listaParedes.get(3).setX(598);
			listaParedes.get(3).setY(0);
			for (int i = 0; i < listaParedes.size(); i ++) {
				PaneSerpiente.getChildren().add(listaParedes.get(i));
			}
		 }
	 
	 protected void añadirComida(Pane PaneSerpiente) { 	
		 	
		 	int nAleatorioY = (int)(Math.random() * 30)*20;;
			int nAleatorioX = (int)(Math.random() * 30)*20;
			while(nAleatorioX == Serpiente.POS_X_SERPIENTE|| nAleatorioY == Serpiente.POS_Y_SERPIENTE && (nAleatorioY == 0 || nAleatorioY == 29) && (nAleatorioX == 0 || nAleatorioX == 29)) {
			nAleatorioY = (int)(Math.random() * 30)*20;
			nAleatorioX = (int)(Math.random() * 30)*20;
			}
			manzana.setX(nAleatorioX);
			manzana.setY(nAleatorioY);
			comida.setX(nAleatorioX);
			comida.setY(nAleatorioY);
			PaneSerpiente.getChildren().addAll(manzana, comida);
		 }
	 
	 protected void añadirComida() { 	
		 	int nAleatorioY = (int)(Math.random() * 30)*20;;
			int nAleatorioX = (int)(Math.random() * 30)*20;
			while(nAleatorioX == Serpiente.POS_X_SERPIENTE|| nAleatorioY == Serpiente.POS_Y_SERPIENTE && (nAleatorioY == 0 || nAleatorioY == 29) && (nAleatorioX == 0 || nAleatorioX == 29)) {
			nAleatorioY = (int)(Math.random() * 30)*20;
			nAleatorioX = (int)(Math.random() * 30)*20;
			}
			manzana.setX(nAleatorioX);
			manzana.setY(nAleatorioY);
			comida.setX(nAleatorioX);
			comida.setY(nAleatorioY);
		 }
	 
	 protected void añadirSerpiente(Pane PaneSerpiente, Serpiente nuevaSerpiente) { 	
		 for (int i = 0; i < nuevaSerpiente.serpiente.size(); i ++) {
				PaneSerpiente.getChildren().add(nuevaSerpiente.serpiente.get(i));
			}
		 }
	 
	 protected void añadePuntuacion(Pane PaneSerpiente, Serpiente nuevaSerpiente) { 	
			Puntuacion.setText("Puntuación: "+nuevaSerpiente.getPuntos());
			Puntuacion.setLayoutX(420);
			Puntuacion.setLayoutY(10);
			Puntuacion.setFont(new Font("Comic Sans MS",20));
			nuevaSerpiente.aumentaPuntos();
			PaneSerpiente.getChildren().add(Puntuacion);
		 }
	 
	 protected void pulsarBoton(Stage stage, Stage stageMenu, Serpiente nuevaSerpiente, Pane PaneSerpiente) { 	 
	 Inicio.setOnAction(e -> {
		 nuevaSerpiente.n1 = CampoNombre.getText();
		 if (!nuevaSerpiente.n1.equals("")) {
				PaneSerpiente.getChildren().add(NombreJ);
				NombreJ.setText("Jugador: "+nuevaSerpiente.n1);
				NombreJ.setLayoutX(30);
				NombreJ.setLayoutY(10);
				NombreJ.setFont(new Font("Comic Sans MS",20));	
				}
				else {
					nuevaSerpiente.n1 = "Jugador1";
					PaneSerpiente.getChildren().add(NombreJ);
					NombreJ.setText("Jugador: "+nuevaSerpiente.n1);
					NombreJ.setLayoutX(10);
					NombreJ.setLayoutY(10);
					NombreJ.setFont(new Font("Comic Sans MS",20));	
				}
			stage.show();
			stageMenu.hide();
		});
	 }
	 
	 protected void pulsarFlechas(Scene SnakeScene, Serpiente nuevaSerpiente, Pane PaneSerpiente, AnimationTimer animationSnake) { 
		 SnakeScene.setOnKeyPressed(e->{
		    	switch(e.getCode()) {
		    		case UP:
		    			if (!nuevaSerpiente.direccion.equals("ab")) {
		    			nuevaSerpiente.direccionSnakeY = -20;
		    			nuevaSerpiente.direccionSnakeX = 0;
		    			nuevaSerpiente.direccion = "ar";
		    			}
		    			break;
		    		case DOWN:
		    			if (!nuevaSerpiente.direccion.equals("ar")) {
		    			nuevaSerpiente.direccionSnakeY = 20;
		    			nuevaSerpiente.direccionSnakeX = 0;
		    			nuevaSerpiente.direccion = "ab";
		    			}
		    			break;
		    		case RIGHT:
		    			if (!nuevaSerpiente.direccion.equals("iz")) {
		    			nuevaSerpiente.direccionSnakeX = 20;
		    			nuevaSerpiente.direccionSnakeY = 0;
		    			nuevaSerpiente.direccion = "de";
		    			}
		    			break;
		    		case LEFT:
		    			if (!nuevaSerpiente.direccion.equals("de")) {
		    			nuevaSerpiente.direccionSnakeX = -20;
		    			nuevaSerpiente.direccionSnakeY = 0;
		    			nuevaSerpiente.direccion = "iz";
		    			}
		    			break;
		    		case A:
		    			animationSnake.stop();
		    			break;
		    		case D:
		    			animationSnake.start();
		    			break;
		    		default:
		    			break;
		    	}
		    	
		    });
		 }
	 
	 protected void FormatearDatos(Serpiente nuevaSerpiente, Pane PaneSerpiente) { 
		 for (int i = 0; i < nuevaSerpiente.serpiente.size(); i ++) {
		    	PaneSerpiente.getChildren().remove(nuevaSerpiente.serpiente.get(i));
		    }
		 
		 for (int i = 0; i < nuevaSerpiente.serpiente.size(); i ++) {
		    	nuevaSerpiente.serpiente.remove(i);
		    }
		 
		 
		
		POS_AÑADO_CUADRADO = 2;
		Serpiente.POS_X_SERPIENTE = 100;
		Serpiente.POS_Y_SERPIENTE = 300;
	    nuevaSerpiente.direccionSnakeY = 0;
	    nuevaSerpiente.direccionSnakeX = 0;
	    nuevaSerpiente.direccion = "";
	    nuevaSerpiente.puntos = 0;
	   
	    nuevaSerpiente.serpiente.add(0,nuevaSerpiente.cabeza);
	    nuevaSerpiente.serpiente.add(1,nuevaSerpiente.cola);
	    nuevaSerpiente.serpiente.get(0).setX(nuevaSerpiente.getPOS_X_SERPIENTE());
		nuevaSerpiente.serpiente.get(0).setY(nuevaSerpiente.getPOS_Y_SERPIENTE());
		
		nuevaSerpiente.serpiente.get(1).setX(nuevaSerpiente.getPOS_X_SERPIENTE()-21);
		nuevaSerpiente.serpiente.get(1).setY(nuevaSerpiente.getPOS_Y_SERPIENTE());
		Puntuacion.setText("Puntuación: "+nuevaSerpiente.getPuntos());
		Puntuacion.setLayoutX(420);
		Puntuacion.setLayoutY(10);
		Puntuacion.setFont(new Font("Comic Sans MS",20));
		nuevaSerpiente.aumentaPuntos();
		PaneSerpiente.getChildren().add(nuevaSerpiente.serpiente.get(0));
		PaneSerpiente.getChildren().add(nuevaSerpiente.serpiente.get(1));
		 }
	 
	@Override
	public void start(Stage stage) throws Exception {
		
		// Crear Escena Menú y Serpiente
		Stage stageMenu = new Stage();
		Pane PaneMenu = new Pane();
		Scene MenuScene = new Scene(PaneMenu, MENU_TAM, MENU_TAM );
		Serpiente nuevaSerpiente = crearSerpiente();
		stageMenu = crearEscenarioMenu(stageMenu, PaneMenu, MenuScene, nuevaSerpiente);
		stageMenu.show();
		
		Pane PaneSerpiente = new Pane();		
		Scene SnakeScene = new Scene(PaneSerpiente, ESCENA_TAM, ESCENA_TAM);
		stage = crearEscenaSerpiente(stage, nuevaSerpiente, PaneSerpiente, SnakeScene);
		
		pulsarBoton(stage, stageMenu, nuevaSerpiente, PaneSerpiente);
		
		 AnimationTimer animationSnake = new AnimationTimer() {
				@Override
				public void handle(long now) {
					
					Serpiente.POS_Y_SERPIENTE += nuevaSerpiente.direccionSnakeY;
					Serpiente.POS_X_SERPIENTE += nuevaSerpiente.direccionSnakeX;
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

						// Actualizar el resto de la serpiente
						for (int i = nuevaSerpiente.serpiente.size()-1; i >= 0; i--) {
							if (i!=0) {
								nuevaSerpiente.serpiente.get(i).setY(nuevaSerpiente.serpiente.get(i-1).getY());
								nuevaSerpiente.serpiente.get(i).setX(nuevaSerpiente.serpiente.get(i-1).getX());
							}
						}
						
						//Actualizar la cabeza
						nuevaSerpiente.serpiente.get(0).setY(Serpiente.POS_Y_SERPIENTE);
						nuevaSerpiente.serpiente.get(0).setX(Serpiente.POS_X_SERPIENTE);		

						// Colisión de la cabeza con la comida
					 	Shape shapeColision = Shape.intersect(comida, nuevaSerpiente.serpiente.get(0));
					    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
					    if(colisionVacia == false) {
					    	
					    	añadirComida();
							
							Rectangle nuevoCuadrado = new Rectangle(20, 20, nuevaSerpiente.colorS);
							nuevaSerpiente.serpiente.add(nuevoCuadrado);	
							nuevaSerpiente.serpiente.get(nuevaSerpiente.serpiente.size()-1).setY(nuevaSerpiente.serpiente.get(nuevaSerpiente.serpiente.size()-2).getY());
							nuevaSerpiente.serpiente.get(nuevaSerpiente.serpiente.size()-1).setX(nuevaSerpiente.serpiente.get(nuevaSerpiente.serpiente.size()-2).getX());
			    			nuevaSerpiente.serpiente.set(POS_AÑADO_CUADRADO, nuevoCuadrado);
			    			PaneSerpiente.getChildren().add(nuevaSerpiente.serpiente.get(nuevaSerpiente.serpiente.size()-1));
			    			POS_AÑADO_CUADRADO++;
			    			
			    			Puntuacion.setText("Puntuación: "+nuevaSerpiente.getPuntos());
			    			Puntuacion.setLayoutX(420);
			    			Puntuacion.setLayoutY(10);
			    			Puntuacion.setFont(new Font("Comic Sans MS",20));
			    			nuevaSerpiente.aumentaPuntos();
			    			
					    }   
					    	
					    
					    // Colisión de la cabeza con el cuerpo
					    for(int i = 3; i < nuevaSerpiente.serpiente.size(); i++) {
					 	Shape shapeColision2 = Shape.intersect(nuevaSerpiente.serpiente.get(0), nuevaSerpiente.serpiente.get(i));
					    boolean colisionVacia2 = shapeColision2.getBoundsInLocal().isEmpty();
					    	if(colisionVacia2 == false) {
					    		stop();
					    		FormatearDatos(nuevaSerpiente, PaneSerpiente);
						    	start();
					    	}
					    }
					    
					 // Colisión de la cabeza con Paredes
					    
					    if (nuevaSerpiente.serpiente.get(0).getX() >= ESCENA_TAM || nuevaSerpiente.serpiente.get(0).getX()<0) {
					    	stop();
					    	FormatearDatos(nuevaSerpiente, PaneSerpiente);
					    	start();
					    }
					    
					    if (nuevaSerpiente.serpiente.get(0).getY() >= ESCENA_TAM || nuevaSerpiente.serpiente.get(0).getY()<0) {
					    	stop();
					    	FormatearDatos(nuevaSerpiente, PaneSerpiente);
					    	start();
						}
					    
				};
			 };
		 animationSnake.start();
		 pulsarFlechas(SnakeScene, nuevaSerpiente, PaneSerpiente, animationSnake);
		 
	}	

	public static void main(String[] args) {
		
		launch(args);
	}
}
