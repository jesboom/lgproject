package org.androidtown.schedule;
/**
 * Created by ohji1 on 2017-07-06.
 */

public class User
{
    private String id;
    private String name;
    private Groups groups;
    private Calander calander;

    public User(){};
    public User(String id, String name, Groups groups, Calander calander) {
        this.id = id;
        this.name = name;
        this.groups = groups;
        this.calander = calander;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Calander getCalander() {
        return calander;
    }

    public void setCalander(Calander calander) {
        this.calander = calander;
    }
}
