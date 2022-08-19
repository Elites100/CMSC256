package cmsc256;

import bridges.base.*;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class GraphEarthquakeData {

    public static double calcDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        final int radius = 6371; // Radius of the earth in km

        // Haversine formula to calculate a value between 0 and 1 between 2 points on a sphere,
        //  1 being the opposite side of the sphere
        double laDistance = Math.toRadians(latitude2 - latitude1);
        double loDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(laDistance / 2) * Math.sin(laDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(loDistance / 2) * Math.sin(loDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = radius * c;    //convert to km
        return distance;
    }



    public static void main(String[] args) throws Exception {
        Bridges bridges = new Bridges(3, "kevinphung01", "777680704448" );
        DataSource ds = bridges.getDataSource();

        List<EarthquakeUSGS> eqList = ds.getEarthquakeUSGSData(5000);

        bridges.setTitle("Earthquake Data Graph Lab");
        bridges.setDescription("CMSC 256, Spring 2021");

        for(EarthquakeUSGS a : eqList) {
            a.getMagnitude();
        }
        Collections.sort(eqList, new eqCompare());
        Collections.reverse(eqList);

        GraphAdjListSimple<String> graph = new GraphAdjListSimple<>();

        /* TODO:
         * Add the Earthquakes to the graph
         * Set each earthquake's location based on its latitude and longitude
         * ex: graph.getVisualizer(key).setLocation(earthquake.getLongit(), earthquake.getLatit());
         * Tweak the colors or other visual elements if you wish; For example, if the magnitude is higher than 6, set the color to red
         */
        eqList = eqList.subList(0, 9);

        for (EarthquakeUSGS e : eqList) {
            graph.addVertex(e.getTitle(), e.getLocation());
            graph.getVisualizer(e.getTitle()).setLocation(e.getLongit(), e.getLatit());
            graph.getVisualizer(e.getTitle()).setColor("green");

            if (e.getMagnitude() >= 7.0) {
                graph.getVisualizer(e.getTitle()).setColor("firebrick");
            }
            else if (e.getMagnitude() >= 6.7) {
                graph.getVisualizer(e.getTitle()).setColor("gold");
            }
        }

        bridges.setCoordSystemType("equirectangular");
        bridges.setDataStructure(graph);
        bridges.setMapOverlay(true);
        bridges.setTitle("Earthquake Map");
        bridges.visualize();


        /* TODO:
         * Compare the distances between all vertexes in the graph, drawing an edge
         * if they are within 500km. A method is provided to give a rough
         * estimate between 2 lat,long points.
         *
         * example usage: calcDistance(eq1.getLatit(), eq1.getLongit(),
         *                eq2.getLatit(), eq2.getLongit());
         * which returns a double representing the distance of two points in km
         */
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                double distance = calcDistance(eqList.get(i).getLatit(), eqList.get(i).getLongit(),
                        eqList.get(j).getLatit(), eqList.get(j).getLongit());

                if (distance <= 500 && i != j) {
                    graph.addEdge(eqList.get(i).getTitle(), eqList.get(j).getTitle());
                }
            }
        }

        bridges.visualize();

        /* TODO:
         * Reset the locations of the vertices by setting their location to
         * Double.POSITIVE_INFINITY
         *
         * ex: graph.getVisualizer(key).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
         */
        for (EarthquakeUSGS e : eqList) {
            graph.getVisualizer(e.getTitle()).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        bridges.setMapOverlay(false);
        bridges.visualize();
    }
    public static class eqCompare implements Comparator<EarthquakeUSGS> {
        public int compare(EarthquakeUSGS a, EarthquakeUSGS b) {
            return Double.compare(a.getMagnitude(), b.getMagnitude());
        }
    }
}