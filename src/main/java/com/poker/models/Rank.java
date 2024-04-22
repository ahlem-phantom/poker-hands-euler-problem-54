package com.poker.models;

public class Rank {
    private int rank_value;
    private String rank_name;

    public Rank(int rank_value, String rank_name) {
        this.rank_value = rank_value;
        this.rank_name = rank_name;
    }

    public int getRank_value() {
        return rank_value;
    }

    public void setRank_value(int rank_value) {
        this.rank_value = rank_value;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    @Override
    public String toString() {
        return "Rank [rank_value=" + rank_value + ", rank_name=" + rank_name + "]";
    }

}