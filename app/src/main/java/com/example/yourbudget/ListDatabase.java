package com.example.yourbudget;

class BudgetList {

    private int _id;
    private String _itemname;
    private int cost;

    public BudgetList() {

    }

    public BudgetList(int id, String name,int cost) {
        this._id = id;
        this._itemname = name;
        this.cost = cost;
    }

    public BudgetList(String name, int cost) {
        this._itemname = name;
        this.cost = cost;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _itemname;
    }

    public void set_name(String _name) {
        this._itemname = _name;
    }

    public int get_cost() {
        return cost;
    }

    public void set_cost(int cost) { this.cost = cost; }

}
