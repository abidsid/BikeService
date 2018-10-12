package com.abid.admin.bikeservice;

import java.util.ArrayList;

public class Network
{
    private ArrayList<String> company;

    public ArrayList<String> getCompany() { return this.company; }

    public void setCompany(ArrayList<String> company) { this.company = company; }

    private String href;

    public String getHref() { return this.href; }

    public void setHref(String href) { this.href = href; }

    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private Location location;

    public Location getLocation() { return this.location; }

    public void setLocation(Location location) { this.location = location; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}
