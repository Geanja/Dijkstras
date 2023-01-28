import java.util.*;

class Dijkstras {
  private PriorityQueue<Integer> pq;
  public Integer dist[];
  public Set<Integer> settled;
  public Map map;

  public Dijkstras() {
    map = new Map("trains.csv");
    dist = new Integer[52];
    settled = new HashSet<Integer>();
    pq = new PriorityQueue<Integer>();
  }

  public void dijkstras (int from, int to ){

    //for (int i = 0; i < dist.length; i++)
      //dist[i] = Integer.MAX_VALUE;
    //dist[from] = 0;

    //pq.add(dist[from]);
    getCityWithNumber(from).dist = 0;
    pq.add(getCityWithNumber(from).dist);

    while (!pq.isEmpty()){
      int temp = pq.poll();
      //System.out.println(temp);
      City current = getCityArray(temp);
      //System.out.println("current namn " + current.name);
      settled.add(current.number);

      if (current == getCityWithNumber(to))
        break;

      for (Connection conn: current.neighbors){
        if(conn != null){
          if (!pq.contains(conn.city.dist/*dist[conn.city.number]*/) && !settled.contains(conn.city.number)){
            /*dist[conn.city.number]*/ conn.city.dist = /*dist[current.number]*/ current.dist + conn.distance;
            pq.add(conn.city.dist/*dist[conn.city.number]*/);
            //System.out.println("vi lägger in " + conn.city.dist + " med namnet " + conn.city.name);
            //System.out.println(pq.toString());
            //System.out.println("conn namn " + conn.city.name + " som har " + conn.city.dist + " uppdaterades med " + current.name + " med dist " + current.dist + " och " + conn.distance);
          }
          else{
            if (/*dist[current.number]*/ current.dist + conn.distance < conn.city.dist/*dist[conn.city.number]*/) {
              if (pq.contains(conn.city.dist)){
                pq.remove(conn.city.dist);
                conn.city.dist = current.dist + conn.distance;
                pq.add(conn.city.dist);
              }
              else
                conn.city.dist = current.dist + conn.distance;

              //dist[conn.city.number] = dist[current.number] + conn.distance;
              //System.out.println("hej " + (current.dist + conn.distance) + " conn namn " + conn.city.name);
            }
          }
        }
      }
    }
  }

  public City getCityWithNumber (int cityNumber){
    for (int i = 0; i < map.cities.length; i++)
      if(map.cities[i] != null)
        if (map.cities[i].number == cityNumber)
          return map.cities[i];
    return null;
  }

  public int getNumberWithCity(String cityName){
    for (int i = 0; i < map.cities.length; i++)
      if(map.cities[i] != null)
        if (map.cities[i].name.equals(cityName))
          return map.cities[i].number;
    return -1;
  }

  public City getCityArray (int distNumber){
    //for (int i = 0; i < dist.length; i++){
      //if (dist[i] == distNumber)
        //return getCityWithNumber(i);
    //}
    for (int i = 0; i < map.cities.length; i++){
      if (map.cities[i] != null)
        if (map.cities[i].dist == distNumber)
          return map.cities[i];
    }
    return null;
  }
}