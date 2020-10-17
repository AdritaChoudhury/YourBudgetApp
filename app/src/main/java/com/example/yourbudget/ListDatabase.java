package com.example.yourbudget;

class BudgetList {

    private int id;
    private int listId;
    private String name;
    private int cost;

    public BudgetList() {

    }

    public BudgetList(int id, String name, int cost, int listId) {
        this.id = id;
        this.listId = listId;
        this.name = name;
        this.cost = cost;
    }

    public BudgetList(String name, int cost, int listId) {
        this.name = name;
        this.cost = cost;
        this.listId = listId;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public int get_cost() {
        return cost;
    }

    public void set_cost(int cost) { this.cost = cost; }

}

class ListDetails {

    private int id;
    private String name;
    private int duration;
    private int budget;

    public ListDetails () {

    }

    public ListDetails (int id, String name, int duration, int budget) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.budget = budget;
    }

    public ListDetails (String name, int duration, int budget) {
        this.name = name;
        this.duration = duration;
        this.budget = budget;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int _id) {
        this.id = id;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) { this.name = name; }

    public int get_duration() { return duration; }

    public void set_duration(int duration) { this.duration = duration; }

    public int get_budget() { return budget; }

    public void set_budget(int budget) { this.budget = budget; }

}