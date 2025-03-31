package com.vexe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

public class VexeJavaFXLauncher extends Application {

    private static ConfigurableApplicationContext springContext;
    private static String[] savedArgs;
    private Parent rootNode;
    private final static String MAIN_FXML = "/fxml/MainView.fxml";

    public static void launch(ConfigurableApplicationContext context, String[] args) {
        VexeJavaFXLauncher.springContext = context;
        VexeJavaFXLauncher.savedArgs = args;
        launch(VexeJavaFXLauncher.class, args);
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_FXML));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("VeXe - Bus Booking System");
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
    }
} 