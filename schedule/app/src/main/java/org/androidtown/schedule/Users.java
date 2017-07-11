package org.androidtown.schedule;

import java.util.ArrayList;

/**
 * Created by ohji1 on 2017-07-06.
 */

public class Users
{

    private ArrayList<User> users= new ArrayList<User>();

    public Users(){};
    public Users(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
