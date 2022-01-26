package es.antoniodominguez.naveespacialopera;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    
    
    
    // MEDIDAS PANTALLA
    int pantallaX = 590;
    int pantallaY = 494;
    
    // POSICIÓN DE LA IMAGEN FONDO 1
    int imagenFondo1Y = 0;
    
    // POSICIÓN DE LA IMAGEN FONDO 2
    int imagenFondo2Y = -494;
    
    // VELOCIDAD MOVIMIENTO DE IMÁGENES DE FONDO
    int imagenFondoSpeed = 8;
    
    
    final int POSICION_INICIO = -494;

    
    
//// SPACESHIP //////
    
    // POSICIÓN X GRUPO SPACESHIP
    int posXGroupSpaceship = 200;
    
    // POSICIÓN Y GRUPO SPACESHIP
    int posYGroupSpaceship = 270;
    
    // ESCALA X GRUPO SPACESHIP
    double escalaXGroupSpaceship = 0.5;
    
    // ESCALA Y GRUPO SPACESHIP
    double escalaYGroupSpaceship = 0.5;
   
    
    
////// SPACESHIP ENEMIGA //////
    
    // POSICIÓN X GRUPO SPACESHIP ENEMIGA
    int posXGroupSpaceshipEnemiga = 47;
    
    // POSICIÓN Y GRUPO SPACESHIP
    int posYGroupSpaceshipEnemiga = 30;
    
    // ESCALA X GRUPO SPACESHIP ENEMIGA
    double escalaXGroupSpaceshipEnemiga = 0.5;
    
    // ESCALA Y GRUPO SPACESHIP ENEMIGA
    double escalaYGroupSpaceshipEnemiga = 0.5;
    
    // VELOCIDAD DE LA NAVE
    //int spaceshipEnemigaSpeed = 0;    
    
////// ASTEROIDE //////
    
    // POSICIÓN X GRUPO ASTEROIDE
    int posXGroupAsteroide = 60; // 60 160 260 360 460
    
    // POSICIÓN Y GRUPO ASTEROIDE
    int posYGroupAsteroide = 40;
    
    // VELOCIDAD DEL ASTEROIDE
    int groupAsteroideSpeed = 4;
    
    
    
    @Override
    public void start(Stage stage) {
        
        Random r = new Random();
        
             
        Pane root = new Pane();
        var scene = new Scene(root, pantallaX, pantallaY);
        stage.setScene(scene);
        stage.show();
        
/////// FONDO //////////       
        // IMAGEN DE FONDO
        Image fondo = new Image(getClass().getResourceAsStream("/images/fondo-fluido.png"));
        
        // POSICIÓN DE IMAGEN FONDO 1
        ImageView imagenFondo1 = new ImageView(fondo);
        imagenFondo1.setLayoutY(0);
        root.getChildren().add(imagenFondo1);
        
        // POSICIÓN DE IMAGEN FONDO 2
        ImageView imagenFondo2 = new ImageView(fondo);
        imagenFondo2.setLayoutY(imagenFondo2Y);
        root.getChildren().add(imagenFondo2);
        
        
/////// SPACESHIP ////////// 
        // IMAGEN DE SPACESHIP
        Image imgSpaceship = new Image(getClass().getResourceAsStream("/images/nave1.png"));
        ImageView imagenSpaceship = new ImageView(imgSpaceship);
        root.getChildren().add(imagenSpaceship);
        
        // RECTÁNGULO ALTURA SPACESHIP
        Rectangle rectHeightSpaceship = new Rectangle(80, 80, 50, 115);
        rectHeightSpaceship.setFill(Color.WHITE);
        root.getChildren().add(rectHeightSpaceship);
        //rectHeightSpaceship.setVisible(false);
        
        // RECTÁNGULO ANCHURA SPACESHIP
        Rectangle rectWidthSpaceship = new Rectangle(40, 150, 125, 25);
        rectWidthSpaceship.setFill(Color.RED);
        root.getChildren().add(rectWidthSpaceship);
        //rectWidthSpaceship.setVisible(false);
        
        // GRUPO SPACESHIP + RECTÁNGULOS
        Group groupSpaceship = new Group();
        groupSpaceship.getChildren().addAll(rectHeightSpaceship, rectWidthSpaceship, imagenSpaceship);
        groupSpaceship.setLayoutX(posXGroupSpaceship);
        groupSpaceship.setLayoutY(posYGroupSpaceship);
        groupSpaceship.setScaleX(escalaXGroupSpaceship);
        groupSpaceship.setScaleY(escalaYGroupSpaceship);
        root.getChildren().add(groupSpaceship);
        
/////// SPACESHIP ENEMIGA //////////  
        // IMAGEN DE SPACESHIP ENEMIGA
        Image imgSpaceshipEnemiga = new Image(getClass().getResourceAsStream("/images/naveEnemiga.png"));
        ImageView imagenSpaceshipEnemiga = new ImageView(imgSpaceshipEnemiga);
        root.getChildren().add(imagenSpaceshipEnemiga);
        
        // RECTÁNGULO ALTURA SPACESHIP ENEMIGA
        Rectangle rectHeightSpaceshipEnemiga = new Rectangle(30, 30, 50, 70);
        rectHeightSpaceshipEnemiga.setFill(Color.WHITE);
        root.getChildren().add(rectHeightSpaceshipEnemiga);
        rectHeightSpaceshipEnemiga.setVisible(false);
        
        // RECTÁNGULO ANCHURA SPACESHIP ENEMIGA
        Rectangle rectWidthSpaceshipEnemiga = new Rectangle(10, 10, 90, 25);
        rectWidthSpaceshipEnemiga.setFill(Color.RED);
        root.getChildren().add(rectWidthSpaceshipEnemiga);
        rectWidthSpaceshipEnemiga.setVisible(false);
        
        // GRUPO SPACESHIP ENEMIGA + RECTÁNGULOS
        Group groupSpaceshipEnemiga = new Group();
        groupSpaceshipEnemiga.getChildren().addAll(rectHeightSpaceshipEnemiga, rectWidthSpaceshipEnemiga, imagenSpaceshipEnemiga);
        groupSpaceshipEnemiga.setLayoutX(posXGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setLayoutY(posYGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setScaleX(escalaXGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setScaleY(escalaYGroupSpaceshipEnemiga);
        root.getChildren().add(groupSpaceshipEnemiga);

/////// ASTEROIDE //////////
        // IMAGEN DE ASTEROIDE
        Image imgAsteroide = new Image(getClass().getResourceAsStream("/images/asteroide1.png"));
        ImageView imagenAsteroide = new ImageView(imgAsteroide);
        root.getChildren().add(imagenAsteroide);
        
        // CÍRCULO 
        Circle circuloAsteroide = new Circle(40,40,40);
        circuloAsteroide.setFill(Color.WHITE);
        circuloAsteroide.setVisible(false);
        
        // GRUPO ASTEROIDE + CÍRCULO
        Group groupAsteroide = new Group();
        groupAsteroide.getChildren().addAll(imagenAsteroide, circuloAsteroide);
        groupAsteroide.setLayoutX(posXGroupAsteroide);
        groupAsteroide.setLayoutY(posYGroupAsteroide);
        root.getChildren().add(groupAsteroide);

        
//////////  TIMELINE FONDO ANIMADO  //////////
        Timeline fondoanimado = new Timeline (
            new KeyFrame (Duration.seconds(0.017), (ActionEvent ae) -> {
                groupSpaceship.setLayoutX(posXGroupSpaceship);
                //posXGroupSpaceship += spaceshipSpeed;
                
                if (imagenFondo1Y > pantallaY){
                    imagenFondo1Y = POSICION_INICIO;
                    imagenFondo1.setLayoutY(POSICION_INICIO);
                    
                } else if (imagenFondo2Y > pantallaY){
                    imagenFondo2Y = POSICION_INICIO;
                    imagenFondo2.setLayoutY(POSICION_INICIO);
                    
                } else{
                    //System.out.println("Fondo1Y: " + fondo1Y);
                    imagenFondo1Y += imagenFondoSpeed;
                    imagenFondo1.setLayoutY(imagenFondo1Y);
                    //System.out.println("Fondo2Y: " + fondo2Y);
                    imagenFondo2Y += imagenFondoSpeed;
                    imagenFondo2.setLayoutY(imagenFondo2Y);
                }
                if(posXGroupSpaceship < 0){
                    posXGroupSpaceship = 0;
                } else {
                    if (posXGroupSpaceship > 400){
                        posXGroupSpaceship = 400;
                    }
                }
                Shape colisionHNaveAsteroide1 = Shape.intersect(rectHeightSpaceship, circuloAsteroide);
                boolean colisionDelanteraAsteroide1 = colisionHNaveAsteroide1.getBoundsInLocal().isEmpty();
                
                Shape colisionWNaveAsteroide1 = Shape.intersect(rectWidthSpaceship, circuloAsteroide);
                boolean colisionLateralAsteroide1 = colisionWNaveAsteroide1.getBoundsInLocal().isEmpty();
                
                if (colisionDelanteraAsteroide1 == true || colisionLateralAsteroide1 == true){
                    resetGame();
                }
            })
        );
//////////  TIMELINE MOVIMIENTO SPACESHIP ENEMIGA  //////////      
        Timeline movimientoSpaceshipEnemiga = new Timeline (
            new KeyFrame (Duration.seconds(3.000), (ActionEvent ae) -> {
                if (posYGroupAsteroide > pantallaY){
                    int num1 = r.nextInt(5);
                    System.out.println(num1);
                    switch (num1){
                        case 0:
                            posXGroupAsteroide = 60;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 1:
                            posXGroupAsteroide = 160;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 2:
                            posXGroupAsteroide = 260;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 3:
                            posXGroupAsteroide = 360;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 4:
                            posXGroupAsteroide = 460;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;
                    }
                }
            })
        );    
//////////  TIMELINE MOVIMIETNO ASTEROIDE  //////////        
        Timeline movimientoAsteroide = new Timeline (
            new KeyFrame (Duration.seconds(0.017), (ActionEvent ae) -> {
                //groupAsteroide.setLayoutX(posXGroupAsteroide);
                groupAsteroide.setLayoutY(posYGroupAsteroide);
                if (posYGroupAsteroide > pantallaY){
                    posYGroupAsteroide = POSICION_INICIO;
                    groupAsteroide.setLayoutY(POSICION_INICIO);
                    int num = r.nextInt(5);
                    System.out.println(num);
                    switch (num){
                        case 0:
                            posXGroupAsteroide = 60;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 1:
                            posXGroupAsteroide = 160;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 2:
                            posXGroupAsteroide = 260;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 3:
                            posXGroupAsteroide = 360;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;

                        case 4:
                            posXGroupAsteroide = 460;
                            groupAsteroide.setLayoutX(posXGroupAsteroide);
                            break;
                    }
                } else{
                    posYGroupAsteroide += groupAsteroideSpeed;
                    groupAsteroide.setLayoutY(posYGroupAsteroide);
                }   
                
            })
        );   
        
        // FONDOANIIMADO
        fondoanimado.setCycleCount(Timeline.INDEFINITE);
        fondoanimado.play();
        
        
        // MOVIMIENTOSPACESHIPENEMIGA
        movimientoSpaceshipEnemiga.setCycleCount(Timeline.INDEFINITE);
        movimientoSpaceshipEnemiga.play();
        
        
        // MOVIMIENTOASTEROIDE
        movimientoAsteroide.setCycleCount(Timeline.INDEFINITE);
        movimientoAsteroide.play();
        
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case A:
                    posXGroupSpaceship -= 100;
                    System.out.println(posXGroupSpaceship);
                    break;
                    
                case D:
                    posXGroupSpaceship += 100;
                    System.out.println(posXGroupSpaceship);
                    break;
            }
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            //spaceshipSpeed = posXGroupSpaceship;
        });
    }
    
    private void resetGame(){
        
    }
    public static void main(String[] args) {
        launch();
    }
    
}