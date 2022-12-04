import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SnakePane extends Pane{

    int height = 600;
    int width = 800;
    

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final int RADIUS = 5;
    
    private Pane root;
    private Text score;
    private Circle food;
    private Random random;
    private Snake snake;

    public SnakePane(){
        System.out.println("New SnakePane created!");

        setHeight(height);
        setWidth(width);
        BackButton backButton = new BackButton();
        backButton.setLayoutY(getHeight()-new BackButton().getHeight());
        getChildren().add(backButton);

        setOnMouseClicked(e->{
            App.cont.stage.getScene();
        });

    }

        public void newFood()
        { 
            food = new Circle(random.nextInt(WIDTH), random.nextInt(HEIGHT), RADIUS);
            food.setFill(Color.RED);
            root.getChildren().add(food);
        }

          private void newSnake()
        {
            snake = new Snake(WIDTH, HEIGHT, RADIUS);
            root.getChildren().add(snake);
            for (int i = 0; i < 25; i++)
            {
                newFood();
                snake.eat(food);
            }
        }
    

        private boolean hit()
        {
            return food.intersects(snake.getBoundsInLocal());
        }
    
        private boolean gameOver()
        {
            return snake.hitSnake();
        }

        private void move()
        {
            Platform.runLater(() -> {
                snake.step();
                switchDirection();
                if(hit())
                {
                    snake.eat(food);
                    score.setText("" + snake.getLength());
                    newFood();
                }
                else if (gameOver())
                {
                    root.getChildren().clear();
                    root.getChildren().add(score);
                    score.setText("GAME OVER" + snake.getLength());
                    newSnake();
                    newFood();
                }
            });
            
        }

        private void switchDirection()
        {
            if (snake.getCenterX() < 0) {
                snake.setCenterX(WIDTH);
            }
            else if (snake.getCenterX() > WIDTH) {
                snake.setCenterX(0);
            }
            if (snake.getCenterY() < 0) {
                snake.setCenterY(HEIGHT);
            }
            else if (snake.getCenterY() > HEIGHT) {
                snake.setCenterY(0);
            }        
        }

        @Override
        public void start(Stage primaryStage)
        {
            root = new Pane();
            root.setPrefSize(WIDTH, HEIGHT);
            random = new Random();
            score = new Text(0,32,"0");
    
            newFood();
            newSnake();
    
            Runnable r = () ->{
                try { 
                    for(;;) 
                    {
                        move();
                        Thread.sleep(100/(1+(snake.getLength()/10)));
                    }
                } catch (InterruptedException e) {
    
                }
            
            };
    
            Scene scene = new Scene(root);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                KeyCode code = event.getCode();
                if(code == KeyCode.UP) {
                    snake.setCurrent(Moving.UP);
                }
                else if(code == KeyCode.DOWN) {
                    snake.setCurrent(Moving.DOWN);
                }
                else if(code == KeyCode.LEFT) {
                    snake.setCurrent(Moving.LEFT);
                }
                else if(code == KeyCode.RIGHT) {
                    snake.setCurrent(Moving.RIGHT);
                }
    
            });
            primaryStage.setTitle("SNAKE");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.start();
        }


}
