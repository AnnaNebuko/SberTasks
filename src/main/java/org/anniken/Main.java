package org.anniken;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<City> cities =
            readCityFromCSV("./src/main/resources/city_ru.csv");

        //Sort by name
        cities.stream()
            .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
            .forEach(System.out::println);


        //sort by 2 parameters
        cities.stream()
            .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
            .forEach(System.out::println);

        //index of maximum element and amount of people there
        cities.stream()
            .max(Comparator.comparing(City::getPopulation)).ifPresent(
                c -> System.out.println("[" + (c.getId() - 1) + "] = " + c.getPopulation())
            );

        findBySimpleBruteForce(cities);
    }

    private static void findBySimpleBruteForce(List<City> cities) {
        //Array of Cities
        City[] array = new City[cities.size()];
        cities.toArray(array);

        //first == current
        City current = array[0];

        //index to print
        int index = 0;

        //Starting from second one
        for (int i = 1; i < array.length; i++) {
            if (array[i].getPopulation() > current.getPopulation()) {
                current = array[i];
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", index, array[index].getPopulation()));
    }

    private static List<City> readCityFromCSV(String fileName) throws IOException {
        List<City> cities = new ArrayList<>();
        Path path = Path.of(fileName);

        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("windows-1251"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(";");
                City city = createCity(attributes);
                cities.add(city);
                line = br.readLine();
            }
        }
        return cities;
    }

    private static City createCity(String[] attributes) {
        int id = Integer.parseInt(attributes[0]);
        String name = attributes[1];
        String region = attributes[2];
        String district = attributes[3];
        int population = Integer.parseInt(attributes[4]);
        String foundation = attributes.length == 5 ? null : attributes[5];

        return new City(id, name, region, district, population, foundation);
    }
}