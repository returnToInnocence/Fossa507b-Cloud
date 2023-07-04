package com.zenq.cloud507b.po;

public class Ac {
    private Integer temper;
    private Integer population;

    public Ac() {
    }

    public Ac(Integer temper, Integer population) {
        this.temper = temper;
        this.population = population;
    }

    public Integer getTemper() {
        return temper;
    }

    public void setTemper(Integer temper) {
        this.temper = temper;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Ac{" +
                "temper=" + temper +
                ", population=" + population +
                '}';
    }
}
