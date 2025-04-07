package com.vexe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.vexe")
@EntityScan(basePackages = "com.vexe.model")
@EnableJpaRepositories(basePackages = "com.vexe.repository")
public class VexeApplication extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(VexeApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/MainView.fxml"));
        rootNode = fxmlLoader.load();

        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // Set application icon
        Image icon = new Image(getClass().getResourceAsStream("/images/vexe.png"));
        stage.getIcons().add(icon);
        
        // Set application title
        stage.setTitle("VeXe - Bus Booking System");
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }
} 