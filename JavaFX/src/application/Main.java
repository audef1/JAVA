package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Main extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("TestApp");

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Zeit");
        yAxis.setLabel("Temperatur");
        
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();     
        lineChart.setTitle("Temperatur Wohnzimmer");
        
        //defining a series
        XYChart.Series series = new XYChart.Series(data);
        series.setName("Sensor 1");
        
        //populating the series with data
        data.add(new XYChart.Data(1, 23));
        data.add(new XYChart.Data(2, 14));
        data.add(new XYChart.Data(3, 15));
        data.add(new XYChart.Data(4, 24));
        data.add(new XYChart.Data(5, 34));
        data.add(new XYChart.Data(6, 36));
        data.add(new XYChart.Data(7, 22));
        data.add(new XYChart.Data(8, 45));
        data.add(new XYChart.Data(9, 43));
        data.add(new XYChart.Data(10, 17));
        data.add(new XYChart.Data(11, 29));
        data.add(new XYChart.Data(12, 25));
        
        Scene scene  = new Scene(lineChart);
        lineChart.getData().add(series);        
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
