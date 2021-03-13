package csci2040u.lab06;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    //Data set 1 for bar graph (red bars)
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    //Data set 2 for bar graph (blue bars)
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    //Data sets for pie chart
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawPieChart(gc);
        drawBarGraph(gc,100,450,avgHousingPricesByYear,avgCommercialPricesByYear,
                Color.BLUE,1550/avgCommercialPricesByYear.length);
    }

    //Function to draw pie chart
    private void drawPieChart(GraphicsContext gc) {
        double numOfPeople = 0.0;

        //Getting number of arcs needed for pie chart
        for (double numOfPeopleForPurchase: purchasesByAgeGroup) {
            numOfPeople += numOfPeopleForPurchase;
        }

        //Setting the initial angle location
        double startAngle = 0.0;
        /*
        For loop to go through data and determine the percentage of how much
        the arc will cover in pie chart depending on data
         */
        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            double slicePercentage = (double) purchasesByAgeGroup[i] / numOfPeople;
            //Multiplying the slice percentage by 360 because a circle is 360 degrees
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            //Setting pir chart with location and size
            /*
            v: horizontal start position, v1: vertical start position,
            v2: width of pie chart, v3: height of pie chart
            */
            gc.fillArc(480, 150, 300, 300, startAngle, sweepAngle, ArcType.ROUND);

            //Incrementing the startAngle so location of new arc is determined.
            startAngle += sweepAngle;
        }
    }

    //Function to draw bar graph
    public void drawBarGraph(GraphicsContext gc, int width, int height, double[] data, double[] data2, Color colour,int startPos){
        //Finding the horizontal increment of the graph (the size of the bars)
        double xInc = (width/data.length);

        //Setting the minimum and maximum values for the bar graph for data set 1
        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = Double.MAX_VALUE;
        //Setting the minimum and maximum values for the bar graph for data set 2
        double maxVal2 = Double.NEGATIVE_INFINITY;
        double minVal2 = Double.MAX_VALUE;

        //Finding the max and min for scaled height calculation for data set 1
        for(double val: data){
            if(val > maxVal){
                maxVal = val;
            }
            if(val < minVal){
                minVal = val;
            }
        }
        //Finding the max and min for scaled height calculation for data set 2
        for(double val: data2){
            if(val > maxVal2){
                maxVal2 = val;
            }
            if(val < minVal2){
                minVal2 = val;
            }
        }

        //Setting the initial x-axis position at which data set 1's bars start
        double x = startPos;
        for(double val: data){
            //x += 0.5 * xInc;
            //scaled height calculation for data set 1
            double barHeight = ((val-minVal) / (maxVal - minVal)) * (height-350);
            gc.setFill(Color.RED);
            //Making the rectangle (the bars) for the bar graph using Graphics Content
            gc.fillRect(x,(height-barHeight),xInc,barHeight);
            //adding horizontal space for each of the bars in data set 1
            x += 2.5 * xInc;
        }
        //Setting the initial x-axis position at which data set 2's bars start
        double x2 = startPos+10;
        for(double val: data2){
            //scaled height calculation for data set 2
            double barHeight2 = ((val-minVal2) / (maxVal2 - minVal2)) * (height-150);
            gc.setFill(Color.BLUE);
            //Making the rectangle (the bars) for the bar graph using Graphics Content
            gc.fillRect(x2,(height-barHeight2),xInc,barHeight2);
            //adding horizontal space for each of the bars in data set 2
            x2 += 2.5 * xInc;
        }

    }
}

