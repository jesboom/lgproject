package org.androidtown.schedule;
/**
 * Created by ohji1 on 2017-07-06.
 */

public class Groups
{
    private Boolean group_name;

    public Groups(){};
    public Groups(Boolean group_name) {
        this.group_name = group_name;
    }

    public Boolean getGroup_name() {
        return group_name;
    }

    public void setGroup_name(Boolean group_name) {
        this.group_name = group_name;
    }
}
