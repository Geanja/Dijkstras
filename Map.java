import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    public City[] cities;
    private final int MOD = 541;
    int j;

    public Map(String file) {
        cities = new City[MOD];
        j = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String city1 = row[0];
                String city2 = row[1];
                int dist = Integer.parseInt(row[2]);

                lookup(city1).connectBidirectionally(lookup(city2), dist);
            }
        }
        catch (Exception e) {
            System.out.println("The file " + file + " was not found or is corrupted.");
        }
    }

    private int hash(String name) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++)
            hash = (hash*31 % MOD) + name.charAt(i);
        return hash % MOD;
    }

    public City lookup(String name) {
        int index = hash(name);
        for (int i = index; i < cities.length; i++) {
            if (cities[i] == null) {
                cities[i] = new City(name, j++);
                return cities[i];
            }
            else if (cities[i].name.equals(name))
                return cities[i];
        }
        for (int i = 0; i < index; i++) {
            if (cities[i] == null) {
                cities[i] = new City(name, j++);
                return cities[i];
            }
            else if (cities[i].name.equals(name))
                return cities[i];
        }
        return null;
    }
}
