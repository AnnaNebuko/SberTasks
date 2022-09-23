package org.anniken;

public class City {
    private final int id;
    private final String name;
    private final String region;
    private final String district;
    private final int population;
    private final String foundation;

    public City(int id, String name, String region, String district, int population, String foundation) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", region='" + region + '\'' +
            ", district='" + district + '\'' +
            ", population=" + population +
            ", foundation='" + foundation + '\'' +
            '}';
    }
}