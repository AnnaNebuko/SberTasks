package org.anniken;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<City> cities =
            readCityFromCSV("C:\\Users\\user\\Downloads\\_данные_\\city_ru.csv");
        cities.forEach(System.out::println);
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