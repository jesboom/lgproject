package org.androidtown.schedule;
/**
 * Created by ohji1 on 2017-07-06.
 */

public class User
{
    private String name;
    private Groups groups;
    private Schedule schedule;

    public User(){};

    public User(String name, Groups groups, Schedule schedule) {
        this.name = name;
        this.groups = groups;
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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


}
