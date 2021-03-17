package csci2040u.lab07;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.io.*;
import java.util.Map;
import java.lang.String;
import java.util.ArrayList;
import java.util.TreeMap;

public class Controller {
    //Tree map to store the word and count of a word in column 6 of the csv file
    static Map<String, Integer> wordCounts =  new TreeMap<>();

    //Array of colours for the pie chart
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Drawing the pi chart using the drawPieChart function
        drawPieChart(gc);
    }

    //Function to draw pie chart
    private void drawPieChart(GraphicsContext gc) {
        //To store the number of sections the pie chart will be divided into
        double numOfCount = 0.0;

        //Creating an array list of integers to store the integer values in the wordCounts map
        ArrayList<Integer> intKeys = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : getWordCounts(wordCounts).entrySet()) {
            //System.out.println("Hello");
            intKeys.add(entry.getValue());
        }

        //Getting number of arcs needed for pie chart
        for (double numOfOccurrences : intKeys) {
            numOfCount += numOfOccurrences;
        }

        //Setting the initial angle location
        double startAngle = 0.0;

        /*
         * For loop to go through data and determine the percentage of how much
         * the arc will cover in pie chart depending on data
         */
        for (int i = 0; i < intKeys.size(); i++) {
            double slicePercentage = (double) intKeys.get(i) / numOfCount;

            //Multiplying the slice percentage by 360 because a circle is 360 degrees
            double sweepAngle = slicePercentage * 360.0;

            //Setting pie chart colour and outline colour as well as outline width
            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(0.5);

            //Setting pir chart with location and size
            gc.fillArc(470, 150, 300, 300, startAngle, sweepAngle, ArcType.ROUND);
            gc.strokeArc(470, 150, 300, 300, startAngle, sweepAngle, ArcType.ROUND);

            //Incrementing the startAngle so location of new arc is determined.
            startAngle += sweepAngle;
        }

        //Setting the start position for the rectangles
        int startPosition = 200;
        for (int i = 0; i < intKeys.size(); i++) {
            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);
            gc.fillRect(240, startPosition, 40, 20);
            gc.strokeRect(240, startPosition, 40, 20);
            startPosition+=30;
        }
        //Resetting start position for the text
        startPosition = 214;
        /*
        * Converting the words in a map to an array of string
        * Used website: https://www.codota.com/code/java/methods/java.util.Set/toArray
        */
        String[] label = getWordCounts(wordCounts).keySet().toArray(new String[0]);
        for (int i = 0; i < intKeys.size(); i++) {
            gc.setFill(Color.BLACK);
            gc.fillText(label[i],290, startPosition);
            startPosition+=30;
        }

    }

    private static void countWord(String word){
        if(wordCounts.containsKey(word)){
            int previous = wordCounts.get(word);
            wordCounts.put(word, previous+1);
        }else{
            wordCounts.put(word, 1);
        }
    }

    //Function to get the data for pie chart by parsing the csv file
    public static Map<String, Integer> getWordCounts(Map<String,Integer> wordCounts) {
        //Setting the path to the csv file to read in
        File inputFile = new File("weatherwarnings-2015.csv");

        //file manipulation
        try{
            FileReader fileInput = new FileReader(inputFile);
            BufferedReader input = new BufferedReader(fileInput);

            String line;
            while((line = input.readLine()) != null){
                String[] data = line.split(",");
                //Storing the string in column 6 of the csv file in wordCounts map using countWord function
                countWord(data[5]);
            }
            input.close();
            return wordCounts;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}