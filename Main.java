

class Main{

    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        //map.printNeighbours();

        /*int i = 0;
        while (map.cities[map.hash("Stockholm")].neighbors[i] != null) {
            System.out.println(map.cities[map.hash("Stockholm")].neighbors[i].city.name);
            i++;
        }*/
        Paths path = new Paths();
        PathsImproved imp = new PathsImproved();
        String[] args1 = {"Malmö", "Stockholm", "100000"};
       // Naive.main(args1);
        path.main(args1);
        //imp.main(args1);
        Dijkstras dijkstras = new Dijkstras();
        int to = dijkstras.getNumberWithCity("Kiruna");
        int from = dijkstras.getNumberWithCity("Lund");

        System.out.println("From Malmö, To Kiruna");
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++){
            dijkstras = new Dijkstras();
            long t0 = System.nanoTime();
            dijkstras.dijkstras(from, to);
            long t1 = System.nanoTime() - t0;

            if (t1 < min)
                min = t1;
        }


        //for (int i: dijkstras.settled){
          //  System.out.println("Snabbaste vägen till staden " + dijkstras.getCityWithNumber(i).name + " från staden " + dijkstras.getCityWithNumber(from).name + " är " + dijkstras.dist[i] + " lång");
        //}
        System.out.println(min/1000);
    }

}

