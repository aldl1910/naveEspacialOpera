package es.antoniodominguez.naveespacialopera;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


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
    
    // COORDENADAS NEGATIVAS DONDE SE COLOCA LA IMAGEN DEL FONDO CUANDO LLEGA 
    // AL LÍMITE INFERIOR
    final int POSICION_INICIO = -494;
    
    
////// SPACESHIP \\\\\\
    
    // POSICIÓN X GRUPO SPACESHIP
    int posXGroupSpaceship = 250;
    
    // POSICIÓN Y GRUPO SPACESHIP
    int posYGroupSpaceship = 340;
   
    // BOOLEANA TERMINAR PARTIDA
    boolean terminarPartida = false;
    
    
////// PROYECTIL \\\\\\
    
    // POSICIÓN X GRUPO PROYECTIL
    int posXGroupProyectil = posXGroupSpaceship + 45;
    
    // POSICIÓN Y GRUPO PROYECTIL
    int posYGroupProyectil = posYGroupSpaceship + 45;
    
    // VELOCIDAD "Y" PROYECTIL
    int velocidadProyectil = 0;
    
    // DETECCIÓN DE COLISIÓN CON ASTEROIDE
    boolean colisionAsteroide = false;
    
    // DETECCIÓN DE COLISIÓN CON SPACESHIP ENEMIGA
    boolean colisionSpaceshipEnemiga = false;
    
    // DISPARAR PROYECTIL AL PULSAR ESPACIO
    boolean dispararProyectil = false;
    
    
////// SPACESHIP ENEMIGA \\\\\\
    
    // POSICIÓN X GRUPO SPACESHIP ENEMIGA
    int posXGroupSpaceshipEnemiga = 47; // 47 147 247 347 447
    
    // POSICIÓN Y GRUPO SPACESHIP
    int posYGroupSpaceshipEnemiga = 30;
    
    // ESCALA X GRUPO SPACESHIP ENEMIGA
    double escalaXGroupSpaceshipEnemiga = 0.5;
    
    // ESCALA Y GRUPO SPACESHIP ENEMIGA
    double escalaYGroupSpaceshipEnemiga = 0.5;
    
    int contadorPuntos = 0;
    
    int maxPuntuacion = 0;
    // VELOCIDAD DE LA NAVE
    //int spaceshipEnemigaSpeed = 0;    
    
////// ASTEROIDES \\\\\\
    
    ////// ASTEROIDE 1 \\\\\\

        // POSICIÓN X GRUPO ASTEROIDE
        int posXGroupAsteroide1 = aparicionAsteroideX(); // 60 160 260 360 460

        // POSICIÓN Y GRUPO ASTEROIDE
        int posYGroupAsteroide1 = aparicionAsteroideY();


    ////// ASTEROIDE 2 \\\\\\

        // POSICIÓN X GRUPO ASTEROIDE
        int posXGroupAsteroide2 = aparicionAsteroideX(); // 60 160 260 360 460

        // POSICIÓN Y GRUPO ASTEROIDE
        int posYGroupAsteroide2 = aparicionAsteroideY();    


    ////// ASTEROIDE 3 \\\\\\

        // POSICIÓN X GRUPO ASTEROIDE
        int posXGroupAsteroide3 = aparicionAsteroideX(); // 60 160 260 360 460

        // POSICIÓN Y GRUPO ASTEROIDE
        int posYGroupAsteroide3 = aparicionAsteroideY();   


        // VELOCIDAD DEL ASTEROIDE
        double groupAsteroideSpeed = 4.0;

        // GRUPOS ASTEROIDES
        Group groupAsteroide1;

        Group groupAsteroide2;

        Group groupAsteroide3;
    
        // GRUPO SPACESHIPENEMIGA
        Group groupSpaceshipEnemiga;
        
        // GRUPO PROYECTIL
        Group groupProyectil;
        
        // SCORE
        Text textScore;
        Text textHighScore;
        Text textGameOver;

    @Override
    public void start(Stage stage) {
        
        Random r = new Random();
        
             
        Pane root = new Pane();
        var scene = new Scene(root, pantallaX, pantallaY);
        stage.setScene(scene);
        stage.show();
        
////////// FONDO \\\\\\\\\\       
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

////////// PROYECTIL \\\\\\\\\\ 
        Circle circleCenterProjectile = new Circle (7.5, 14.14, 7.7, Color.YELLOW);
        root.getChildren().add(circleCenterProjectile);
        
        Rectangle romboPrimeroProjectile = new Rectangle(2.5, 2.5, 10, 10);
        romboPrimeroProjectile.setFill(Color.RED);
        romboPrimeroProjectile.setRotate(45);
        root.getChildren().add(romboPrimeroProjectile);
        
        Rectangle romboSegundoProjectile = new Rectangle(2.5, 16.5, 10, 10);
        romboSegundoProjectile.setFill(Color.RED);
        romboSegundoProjectile.setRotate(45);
        root.getChildren().add(romboSegundoProjectile);
        
        groupProyectil = new Group();
        groupProyectil.getChildren().addAll(circleCenterProjectile,romboSegundoProjectile, romboPrimeroProjectile);
        groupProyectil.setLayoutX(posXGroupProyectil);
        groupProyectil.setLayoutY(posYGroupProyectil);
        root.getChildren().add(groupProyectil);          
        
        
////////// SPACESHIP \\\\\\\\\\ 
        // IMAGEN DE SPACESHIP
        Image imgSpaceship = new Image(getClass().getResourceAsStream("/images/nave1.png"));
        ImageView imagenSpaceship = new ImageView(imgSpaceship);
        root.getChildren().add(imagenSpaceship);
        
        // RECTÁNGULO ALTURA SPACESHIP
        Rectangle rectHeightSpaceship = new Rectangle(40, 40, 25, 57.5);
        rectHeightSpaceship.setFill(Color.WHITE);
        root.getChildren().add(rectHeightSpaceship);
        //rectHeightSpaceship.setVisible(false);
        
        // RECTÁNGULO ANCHURA SPACESHIP
        Rectangle rectWidthSpaceship = new Rectangle(20, 75, 62.5, 12.5);
        rectWidthSpaceship.setFill(Color.RED);
        root.getChildren().add(rectWidthSpaceship);
        //rectWidthSpaceship.setVisible(false);
        
        // GRUPO SPACESHIP + RECTÁNGULOS
        Group groupSpaceship = new Group();
        groupSpaceship.getChildren().addAll(rectHeightSpaceship, rectWidthSpaceship, imagenSpaceship);
        groupSpaceship.setLayoutX(posXGroupSpaceship);
        groupSpaceship.setLayoutY(posYGroupSpaceship);
        //groupSpaceship.setScaleX(escalaXGroupSpaceship);
        //groupSpaceship.setScaleY(escalaYGroupSpaceship);
        root.getChildren().add(groupSpaceship);


////////// SPACESHIP ENEMIGA \\\\\\\\\\  
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
        groupSpaceshipEnemiga = new Group();
        groupSpaceshipEnemiga.getChildren().addAll(rectHeightSpaceshipEnemiga, rectWidthSpaceshipEnemiga, imagenSpaceshipEnemiga);
        groupSpaceshipEnemiga.setLayoutX(posXGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setLayoutY(posYGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setScaleX(escalaXGroupSpaceshipEnemiga);
        groupSpaceshipEnemiga.setScaleY(escalaYGroupSpaceshipEnemiga);
        root.getChildren().add(groupSpaceshipEnemiga);

        
////////// ASTEROIDES \\\\\\\\\\
        // IMAGEN DE ASTEROIDE
        Image imgAsteroide = new Image(getClass().getResourceAsStream("/images/asteroide1.png"));
        
        // ASTEROIDE 1 :
            // IMAGEVIEW 
            ImageView imagenAsteroide1 = new ImageView(imgAsteroide);
            root.getChildren().add(imagenAsteroide1);

            // CÍRCULO 
            Circle circuloAsteroide1 = new Circle(40,40,40);
            circuloAsteroide1.setFill(Color.WHITE);
            circuloAsteroide1.setVisible(false);

            // GRUPO ASTEROIDE + CÍRCULO
            groupAsteroide1 = new Group();
            groupAsteroide1.getChildren().addAll(imagenAsteroide1, circuloAsteroide1);
            groupAsteroide1.setLayoutX(posXGroupAsteroide1);
            groupAsteroide1.setLayoutY(posYGroupAsteroide1);
            root.getChildren().add(groupAsteroide1);
        
            
        // ASTEROIDE 2 :
            // IMAGEVIEW 
            ImageView imagenAsteroide2 = new ImageView(imgAsteroide);
            root.getChildren().add(imagenAsteroide2);

            // CÍRCULO 
            Circle circuloAsteroide2 = new Circle(40,40,40);
            circuloAsteroide2.setFill(Color.WHITE);
            circuloAsteroide2.setVisible(false);

            // GRUPO ASTEROIDE + CÍRCULO
            groupAsteroide2 = new Group();
            groupAsteroide2.getChildren().addAll(imagenAsteroide2, circuloAsteroide2);
            groupAsteroide2.setLayoutX(posXGroupAsteroide2);
            groupAsteroide2.setLayoutY(posYGroupAsteroide2);
            root.getChildren().add(groupAsteroide2);
        
        
        // ASTEROIDE 3:
            // IMAGEVIEW 
            ImageView imagenAsteroide3 = new ImageView(imgAsteroide);
            root.getChildren().add(imagenAsteroide3);

            // CÍRCULO 
            Circle circuloAsteroide3 = new Circle(40,40,40);
            circuloAsteroide3.setFill(Color.WHITE);
            circuloAsteroide3.setVisible(false);

            // GRUPO ASTEROIDE + CÍRCULO
            groupAsteroide3 = new Group();
            groupAsteroide3.getChildren().addAll(imagenAsteroide3, circuloAsteroide3);
            groupAsteroide3.setLayoutX(posXGroupAsteroide3);
            groupAsteroide3.setLayoutY(posYGroupAsteroide3);
            root.getChildren().add(groupAsteroide3);    
       
            
////////// PUNTUACION \\\\\\\\\\          
            // LAYOUTS PARA MOSTRAR PUNTUACIONES
            // LAYOUT PRINCIPAL
            HBox paneScores = new HBox();
            paneScores.setTranslateY(20);
            paneScores.setMinWidth(pantallaX);
            paneScores.setAlignment(Pos.CENTER);
            paneScores.setSpacing(100);
            root.getChildren().add(paneScores);
            // LAYOUT PARA PUNTUACIÓN ACTUAL
            HBox paneCurrentScore = new HBox();
            paneCurrentScore.setSpacing(10);
            paneScores.getChildren().add(paneCurrentScore);
            // LAYOUT PARA PUNTUACIÓN MÁXIMA
            HBox paneHighScore = new HBox();
            paneHighScore.setSpacing(10);
            paneScores.getChildren().add(paneHighScore);
            // LAYOUT PARA MOSTRAR QUE HAS PERDIDO
            HBox paneGameOver = new HBox();
            paneGameOver.setTranslateY(200);
            paneGameOver.setTranslateX(120);
            paneGameOver.setSpacing(30);
            paneGameOver.setAlignment(Pos.CENTER);
            root.getChildren().add(paneGameOver);
            
            
            // TEXTO DE ETIQUETA PARA LA PUNTUACIÓN
            Text textTitleScore = new Text("Score:");
            textTitleScore.setFont(Font.font(18));
            textTitleScore.setFill(Color.WHITE);
            // TEXTO PARA LA PUNTUACIÓN
            textScore = new Text("0");
            textScore.setFont(Font.font(18));
            textScore.setFill(Color.WHITE);
            // TEXTO DE ETIQUETA PAARA LA PUNTUACIÓN MÁXIMA
            Text textTitleHighScore = new Text("Max.Score:");
            textTitleHighScore.setFont(Font.font(18));
            textTitleHighScore.setFill(Color.WHITE);
            // TEXTO PARA LA PUNTUACIÓN MÁXIMA
            textHighScore = new Text("0");
            textHighScore.setFont(Font.font(18));
            textHighScore.setFill(Color.WHITE);
            // TEXTO GAME OVER
            textGameOver = new Text("GAME OVER!");
            textGameOver.setFont(Font.font(60));
            textGameOver.setFill(Color.RED);
            textGameOver.setVisible(false);
            
            // AÑADIR LOOS TEXTOS A LOS LAYOUTS RESERVADOS PARA ELLOS
            paneCurrentScore.getChildren().add(textTitleScore);
            paneCurrentScore.getChildren().add(textScore);
            
           
            paneHighScore.getChildren().add(textTitleHighScore);
            paneHighScore.getChildren().add(textHighScore);
            
            paneGameOver.getChildren().add(textGameOver);
            
 

//////////  TIMELINE FONDO ANIMADO  //////////
        Timeline fondoanimado = new Timeline (
            new KeyFrame (Duration.seconds(0.017), (ActionEvent ae) -> {
                if (terminarPartida == false){
                   // System.out.println("Spaceship:" + terminarPartida);
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
                    if(posXGroupSpaceship < 50){
                        posXGroupSpaceship = 50;
                    } else {
                        if (posXGroupSpaceship > 450){
                            posXGroupSpaceship = 450;
                        }
                    }
                    
    ///////// COLISIONES \\\\\\\\\
    
                //// SPACESHIP \\\\
                
                    // COLISIÓN ASTEROIDE 1
                    Shape colisionHNaveAsteroide1 = Shape.intersect(rectHeightSpaceship, circuloAsteroide1);
                    boolean colisionDelanteraAsteroide1 = colisionHNaveAsteroide1.getBoundsInLocal().isEmpty();

                    Shape colisionWNaveAsteroide1 = Shape.intersect(rectWidthSpaceship, circuloAsteroide1);
                    boolean colisionLateralAsteroide1 = colisionWNaveAsteroide1.getBoundsInLocal().isEmpty();
                    
                    // COLISIÓN ASTEROIDE 2
                    Shape colisionHNaveAsteroide2 = Shape.intersect(rectHeightSpaceship, circuloAsteroide2);
                    boolean colisionDelanteraAsteroide2 = colisionHNaveAsteroide2.getBoundsInLocal().isEmpty();

                    Shape colisionWNaveAsteroide2 = Shape.intersect(rectWidthSpaceship, circuloAsteroide2);
                    boolean colisionLateralAsteroide2 = colisionWNaveAsteroide2.getBoundsInLocal().isEmpty();
                    
                    // COLISIÓN ASTEROIDE 3
                    Shape colisionHNaveAsteroide3 = Shape.intersect(rectHeightSpaceship, circuloAsteroide3);
                    boolean colisionDelanteraAsteroide3 = colisionHNaveAsteroide3.getBoundsInLocal().isEmpty();

                    Shape colisionWNaveAsteroide3 = Shape.intersect(rectWidthSpaceship, circuloAsteroide3);
                    boolean colisionLateralAsteroide3 = colisionWNaveAsteroide3.getBoundsInLocal().isEmpty();
                    
                    
                    // DETECCION DE COLISIÓN DE LOS ASTEROIDES CON SPACESHIP
                    if (colisionDelanteraAsteroide1 == false || colisionLateralAsteroide1 == false
                            || colisionDelanteraAsteroide2 == false || colisionLateralAsteroide2 == false
                            || colisionDelanteraAsteroide3 == false || colisionLateralAsteroide3 == false){
                        resetGame();
                    }
                    
            
                    
                    
                }
                
            })
        );
////////// MÚSICA \\\\\\\\\\      
        URL urlAudio = getClass().getResource("/audio/retro.mp3");            
        if(urlAudio != null) {
            try {
                AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
                audioClip1.play();
                audioClip1.setCycleCount(AudioClip.INDEFINITE);
                audioClip1.setVolume(0.0);
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
            System.out.println("No se ha encontrado el archivo de audio");
        }
        
//////////  TIMELINE PROYECTIL  \\\\\\\\\\\        
        Timeline proyectil = new Timeline (
            new KeyFrame (Duration.seconds(0.017), (ActionEvent ae) -> {
                if (terminarPartida == false){   
                //// PROYECTIL \\\\

                    // COLISIÓN ASTEROIDE 1
                    Shape colisionProjectileAsteroide1 = Shape.intersect(romboPrimeroProjectile, circuloAsteroide1);
                    boolean colisionProjectilePrimerAsteroide = !colisionProjectileAsteroide1.getBoundsInLocal().isEmpty();

                    // COLISIÓN ASTEROIDE 2
                    Shape colisionProjectileAsteroide2 = Shape.intersect(romboPrimeroProjectile, circuloAsteroide2);
                    boolean colisionProjectileSegundoAsteroide = !colisionProjectileAsteroide2.getBoundsInLocal().isEmpty();

                    // COLISIÓN ASTEROIDE 3
                    Shape colisionProjectileAsteroide3 = Shape.intersect(romboPrimeroProjectile, circuloAsteroide3);
                    boolean colisionProjectileTercerAsteroide = !colisionProjectileAsteroide3.getBoundsInLocal().isEmpty();

                    // COLISIÓN SPACESHIP ENEMIGA 
                    Shape colisionProjectileSpaceshipEnemiga = Shape.intersect(romboPrimeroProjectile, rectHeightSpaceshipEnemiga);
                    boolean colisionProjectileSpaceshipEnemiga1 = !colisionProjectileSpaceshipEnemiga.getBoundsInLocal().isEmpty();

                // DETECCIÓN DE COLISIÓN DEL PROYECTIL CON LOS ASTEROIDES
                    //System.out.println("colision de proyectil con asteroide 1 " + colisionProjectilePrimerAsteroide);
                    //System.out.println("colision de proyectil con asteroide 2 " + colisionProjectileSegundoAsteroide);
                    //System.out.println("colision de proyectil con asteroide 3 " + colisionProjectileTercerAsteroide);
                            
                    if (colisionProjectilePrimerAsteroide == true || colisionProjectileSegundoAsteroide == true 
                            || colisionProjectileTercerAsteroide == true){
                        colisionAsteroide = true;
                        //System.out.println("Asteroide impactado");
                       // groupProyectil.setVisible(false);
                    }

                    // DETECCIÓN DE COLISIÓN DEL PROYECTIL CON SPACESHIP ENEMIGA
                    // REAPARICIÓN DE SPACESHIP ENEMIGA E INCREMENTO DE PUNTUACIÓN
                    if (colisionProjectileSpaceshipEnemiga1 == true){
                        // sumar puntos
                        //System.out.println("Enemigo impactado");
                        colisionSpaceshipEnemiga = true;
                        groupSpaceshipEnemiga.setLayoutX(aparicionSpaceshipEnemigaX());
                        //contadorPuntos++;
                        contadorPuntos +=10;
                        textScore.setText(String.valueOf(contadorPuntos));
                        System.out.println("CONTADOR DE PUNTOS " + contadorPuntos);
                    }
                    
                    
                    //////////  VELOCIDAD MOVIMIENTO ASTEROIDE  \\\\\\\\\\\ 
                    switch (contadorPuntos) {
                        case 10:
                            groupAsteroideSpeed = 5;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 20:
                            groupAsteroideSpeed = 6;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 30:
                            groupAsteroideSpeed = 7;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 40:
                            groupAsteroideSpeed = 8;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 50:
                            groupAsteroideSpeed = 9;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 60:
                            groupAsteroideSpeed = 10;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 70:
                            groupAsteroideSpeed = 11;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 80:
                            groupAsteroideSpeed = 12;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 90:
                            groupAsteroideSpeed = 13;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        case 100:
                            groupAsteroideSpeed = 14;
                            System.out.println("VELOCIDAD " + groupAsteroideSpeed);
                            break;
                        default:
                            break;
                    }
                    
                    //System.out.println("Coordenada X proyectil " + groupProyectil.getLayoutX());
                    //System.out.println("Coordenada Y proyectil " + groupProyectil.getLayoutY());
                    //System.out.println("Coordenada X NAVE " + groupSpaceship.getLayoutX());
                    //System.out.println("Coordenada Y NAVE " + groupSpaceship.getLayoutY());
                    
                    //System.out.println("DISPARAR PROYECTIL  " + dispararProyectil);
                    
                    if (dispararProyectil == false ){
                        posicionarProjectile();   
                        groupProyectil.setVisible(false);
                    }  else if (posYGroupProyectil > 0 && colisionAsteroide == false 
                            || posYGroupProyectil > 0 && colisionSpaceshipEnemiga == false) {
                        
                            posYGroupProyectil -= velocidadProyectil;
                            groupProyectil.setLayoutY(posYGroupProyectil);
                            groupProyectil.setVisible(true);
                        
                            if ( posYGroupProyectil < 0){
                                posicionarProjectile();
                                dispararProyectil = false;
                                groupProyectil.setVisible(false);
                            }
                    } 
                    
                    if (colisionAsteroide == true || colisionSpaceshipEnemiga == true){
                        groupProyectil.setVisible(false);
                        //System.out.println("DETECTANDO COLISIONES");
                        dispararProyectil = false;
                        posicionarProjectile();
                        colisionAsteroide = false;
                        colisionSpaceshipEnemiga = false;
                    }
                    
                    
                //}
                
                }
            })
        );
        
        
//////////  TIMELINE MOVIMIENTO SPACESHIP ENEMIGA  \\\\\\\\\\\      
        Timeline movimientoSpaceshipEnemiga = new Timeline (
            new KeyFrame (Duration.seconds(2.000), (ActionEvent ae) -> {
                if (terminarPartida == false){
                    //System.out.println("Enemigo:" + terminarPartida);
                    if (posXGroupSpaceshipEnemiga < pantallaY){
                        groupSpaceshipEnemiga.setLayoutX(aparicionSpaceshipEnemigaX());
                    } 
                }
            })
        );    
        
        
//////////  TIMELINE MOVIMIETNO ASTEROIDE  \\\\\\\\\\\        
        Timeline movimientoAsteroide = new Timeline (
            new KeyFrame (Duration.seconds(0.017), (ActionEvent ae) -> {
                if (terminarPartida == false){
                    
                    //groupAsteroide.setLayoutX(posXGroupAsteroide);
                    groupAsteroide1.setLayoutY(posYGroupAsteroide1);
                    if (posYGroupAsteroide1 > pantallaY){
                        posicionarAsteroide1();
                    } else{
                        posYGroupAsteroide1 += groupAsteroideSpeed;
                        groupAsteroide1.setLayoutY(posYGroupAsteroide1);
                    }   
                    
                    groupAsteroide2.setLayoutY(posYGroupAsteroide2);
                    if (posYGroupAsteroide2 > pantallaY){
                        posicionarAsteroide2();
                    } else{
                        posYGroupAsteroide2 += groupAsteroideSpeed;
                        groupAsteroide2.setLayoutY(posYGroupAsteroide2);
                    }   
                    
                    groupAsteroide3.setLayoutY(posYGroupAsteroide3);
                    if (posYGroupAsteroide3 > pantallaY){
                        posicionarAsteroide3();
                    } else{
                        posYGroupAsteroide3 += groupAsteroideSpeed;
                        groupAsteroide3.setLayoutY(posYGroupAsteroide3);
                    }  

                }
            })
        );   
        
        

        
        // FONDO ANIIMADO
        fondoanimado.setCycleCount(Timeline.INDEFINITE);
        fondoanimado.play();
        
        
        // PROYECTIL
        proyectil.setCycleCount(Timeline.INDEFINITE);
        proyectil.play();
        
        
        // MOVIMIENTO SPACESHIP ENEMIGA
        movimientoSpaceshipEnemiga.setCycleCount(Timeline.INDEFINITE);
        movimientoSpaceshipEnemiga.play();
        
        
        // MOVIMIENTO ASTEROIDE
        movimientoAsteroide.setCycleCount(Timeline.INDEFINITE);
        movimientoAsteroide.play();
        
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (terminarPartida == false){
                switch(event.getCode()){
                    case A:
                        posXGroupSpaceship -= 100;
                        //System.out.println(posXGroupSpaceship);
                        break;

                    case D:
                        posXGroupSpaceship += 100;
                        //System.out.println(posXGroupSpaceship);
                        break;
                        
                    case SPACE:
                        //System.out.println("Has pulsado el espacio");
                        dispararProyectil = true;
                        groupProyectil.setVisible(true);
                        velocidadProyectil = 15;
                        
                }
            } else{
                switch(event.getCode()){
                    case R:
                        terminarPartida = false;
                        groupAsteroideSpeed = 4;
                        imagenFondoSpeed = 8;
                        
                        posXGroupSpaceship = 250;
                        groupSpaceship.setLayoutX(posXGroupSpaceship);
                        
                        posicionarProjectile();
                        dispararProyectil = false;
                        
                        posXGroupSpaceshipEnemiga = aparicionSpaceshipEnemigaX();
                        groupSpaceshipEnemiga.setLayoutX(posXGroupSpaceshipEnemiga);
                        
                        posicionarAsteroide1();
                        
                        posicionarAsteroide2();

                        posicionarAsteroide3();
                       
                        textGameOver.setVisible(false);
                }
            }
        }); 
    }
    

/////////// MÉTODOO QUE PARA EL JUEGO \\\\\\\\\\\
    private void resetGame(){
        //System.out.println("Has entrado en resetGAME");
        imagenFondoSpeed = 0;
        groupAsteroideSpeed = 0; 
        terminarPartida = true;
        if (contadorPuntos > maxPuntuacion){
            maxPuntuacion = contadorPuntos;
            textHighScore.setText(String.valueOf(maxPuntuacion));
        } 
        contadorPuntos = 0;
        textScore.setText(String.valueOf(contadorPuntos));
        textGameOver.setVisible(true);
    }
    
    
/////////// MÉTODOO QUE POSICIONA EL PROYECTIL EN LA NAVE \\\\\\\\\\\
    private void posicionarProjectile(){
        posYGroupProyectil = posYGroupSpaceship + 45; 
        posXGroupProyectil = posXGroupSpaceship + 45;
        groupProyectil.setLayoutX(posXGroupProyectil);
        groupProyectil.setLayoutY(posYGroupProyectil);
    }
    
    
/////////// MÉTODOO QUE POSICIONA EL ASTEROIDE 1 \\\\\\\\\\\
    private void posicionarAsteroide1(){
        posXGroupAsteroide1 = aparicionAsteroideX();
        groupAsteroide1.setLayoutX(posXGroupAsteroide1);
        posYGroupAsteroide1 = aparicionAsteroideY();
        groupAsteroide1.setLayoutY(posYGroupAsteroide1);
    }


/////////// MÉTODOO QUE POSICIONA EL ASTEROIDE 2 \\\\\\\\\\\
    private void posicionarAsteroide2(){
        posXGroupAsteroide2 = aparicionAsteroideX();
        groupAsteroide2.setLayoutX(posXGroupAsteroide2);
        posYGroupAsteroide2 = aparicionAsteroideY();
        groupAsteroide2.setLayoutY(posYGroupAsteroide2);
    }
    
 
/////////// MÉTODOO QUE POSICIONA EL ASTEROIDE 3 \\\\\\\\\\\
    private void posicionarAsteroide3(){
        posXGroupAsteroide3 = aparicionAsteroideX();
        groupAsteroide3.setLayoutX(posXGroupAsteroide3);
        posYGroupAsteroide3 = aparicionAsteroideY();
        groupAsteroide3.setLayoutY(posYGroupAsteroide3);
    }
    
    
/////////// MÉTODO QUE GENERA LA POSICIÓN ALEATORIA DE LOS ASTEROIDES EN EL EJE X \\\\\\\\\\\
    public int aparicionAsteroideX(){
        Random r = new Random();
        int num = r.nextInt(5);
        //System.out.println("Numero aleatorio aparicionAsteroideX " + num);
        int posicionX = 0;
        switch (num){
            case 0:
                posicionX = 60;
                //System.out.println("Caso 0 : X ASTEROIDE " + posicionX);
                break;

            case 1:
                posicionX = 160;
                //System.out.println("Caso 1 : X ASTEROIDE " + posicionX);
                break;

            case 2:
                posicionX = 260;
                //System.out.println("Caso 2 : X ASTEROIDE " + posicionX);
                break;

            case 3:
                posicionX = 360;
                //System.out.println("Caso 3 : X ASTEROIDE " + posicionX);
                break;

            case 4:
                posicionX = 460;
                //System.out.println("Caso 4 : X ASTEROIDE " + posicionX);
                break;
        }
        return posicionX;
    }
    
    
/////////// MÉTODO QUE GENERA LA POSICIÓN ALEATORIA DE LOS ASTEROIDES EN EL EJE Y \\\\\\\\\\\
    public int aparicionAsteroideY(){
        Random r = new Random();
        int num1 = r.nextInt(494)-494;
        int posicionY = num1;
        //System.out.println("Posicion Y asteroide" + posicionY);
        return posicionY;
    }
    
    
/////////// MÉTODO QUE GENERA LA POSICIÓN ALEATORIA DE SPACESHIP ENEMIGA EN EL EJE X \\\\\\\\\\\
    private int aparicionSpaceshipEnemigaX( ){
        Random r = new Random();
        int num1 = r.nextInt(5);
        System.out.println("Posicion aleatoria posXGroupSpaceshipEnemiga " + num1);
        switch (num1){
            case 0:
                System.out.println("POS" + posXGroupSpaceshipEnemiga);
                if(posXGroupSpaceshipEnemiga != 47){
                    posXGroupSpaceshipEnemiga = 47;
                }
                break;

            case 1:
                System.out.println("POS" + posXGroupSpaceshipEnemiga);
                if(posXGroupSpaceshipEnemiga != 147){
                    posXGroupSpaceshipEnemiga = 147;
                }
                break;

            case 2:
                System.out.println("POS" + posXGroupSpaceshipEnemiga);
                if(posXGroupSpaceshipEnemiga != 247){
                    posXGroupSpaceshipEnemiga = 247;
                }
                break;

            case 3:
                System.out.println("POS" + posXGroupSpaceshipEnemiga);
                if(posXGroupSpaceshipEnemiga != 347){
                    posXGroupSpaceshipEnemiga = 347;
                }
                break;

            case 4:
                System.out.println("POS" + posXGroupSpaceshipEnemiga);
                if(posXGroupSpaceshipEnemiga != 447){
                    posXGroupSpaceshipEnemiga = 447;
                }
                break;
        }
        return (posXGroupSpaceshipEnemiga);
    }
    
    
    public static void main(String[] args) {
        launch();
    } 
}