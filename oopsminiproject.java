package com.example.oops;




import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Toolkit;

public class CountdownTimerFX extends Application {

    private int timeLeft;
    private int startTime;
    private Label timerLabel;
    private Timeline timeline;
    private StackPane root;
    private boolean isRunning = false;
    private ScaleTransition pulse;

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("🔥 JavaFX Countdown Timer 🔥");
        title.setFont(Font.font("Verdana", 30));
        title.setTextFill(Color.ORANGE);

        TextField inputField = new TextField();
        inputField.setPromptText("Enter seconds (e.g. 10)");
        inputField.setMaxWidth(150);
        inputField.setStyle("-fx-font-size: 18px; -fx-alignment: center;");

        Button startButton = new Button("Start Timer");
        Button stopButton = new Button("Stop Timer");

        // Button styles
        startButton.setFont(Font.font(18));
        startButton.setStyle("-fx-background-color: #00C853; -fx-text-fill: white;");
        stopButton.setFont(Font.font(18));
        stopButton.setStyle("-fx-background-color: #D50000; -fx-text-fill: white;");

        // Event handlers
        startButton.setOnAction(e -> startCountdown(inputField));
        stopButton.setOnAction(e -> stopCountdown());

        timerLabel = new Label("--");
        timerLabel.setFont(Font.font("Verdana", 100));
        timerLabel.setTextFill(Color.LIME);
        timerLabel.setEffect(new DropShadow(20, Color.DARKSLATEBLUE));

        HBox buttonBox = new HBox(20, startButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, title, inputField, buttonBox, timerLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #1a2a6c, #b21f1f, #fdbb2d);");

        root = new StackPane(layout);

        Scene scene = new Scene(root, 700, 500);

        // ✅ FIX: no external CSS, so no StyleManager error
        // We directly apply inline styling instead of scene.getStylesheets().add()

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Countdown Timer");
        primaryStage.show();
    }

    private void startCountdown(TextField inputField) {
        if (isRunning && timeline != null) {
            timeline.stop();
        }

        try {
            startTime = Integer.parseInt(inputField.getText().trim());
            if (startTime <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            timerLabel.setText("Invalid!");
            timerLabel.setTextFill(Color.RED);
            return;
        }

        timeLeft = startTime;
        timerLabel.setText(String.valueOf(timeLeft));
        isRunning = true;

        // Stop any previous pulse animation
        if (pulse != null) pulse.stop();

        // Pulse animation on timer
        pulse = new ScaleTransition(Duration.millis(800), timerLabel);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.2);
        pulse.setToY(1.2);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.play();

        // Countdown logic
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();
    }

    private void updateTimer() {
        if (timeLeft > 0) {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            Toolkit.getDefaultToolkit().beep();

            // Animate color change dynamically
            Color color = Color.hsb((startTime - timeLeft) * 36 % 360, 1.0, 1.0);
            timerLabel.setTextFill(color);
            root.setStyle("-fx-background-color: linear-gradient(to bottom, " + toRgb(color) + ", #000000);");

        } else {
            Toolkit.getDefaultToolkit().beep();
            timerLabel.setText("Time Up!");
            timerLabel.setTextFill(Color.RED);

            // Blink “Time Up!” text
            Timeline blink = new Timeline(
                    new KeyFrame(Duration.seconds(0.3), e -> timerLabel.setVisible(false)),
                    new KeyFrame(Duration.seconds(0.6), e -> timerLabel.setVisible(true))
            );
            blink.setCycleCount(5);
            blink.setOnFinished(e -> stopCountdown());
            blink.play();

            timeline.stop();
        }
    }

    private void stopCountdown() {
        if (timeline != null) {
            timeline.stop();
        }
        if (pulse != null) {
            pulse.stop();
        }
        isRunning = false;
        timerLabel.setText("Stopped");
        timerLabel.setTextFill(Color.YELLOW);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #3a3a3a, #000000);");
    }

    private String toRgb(Color color) {
        return String.format("rgb(%d,%d,%d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

