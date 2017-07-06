package org.androidtown.schedule;
/**
 * Created by ohji1 on 2017-07-06.
 */

public class M1
{
    private String D1;
    private String D2;
    private String D3;

    public M1(){};
    public M1(String D1, String D2, String D3) {
        this.D1 = D1;
        this.D2 = D2;
        this.D3 = D3;
    }

    public String getD1() {
        return D1;
    }
    public void setD1(String d1) {
        D1 = d1;
    }
    public String getD2() {
        return D2;
    }
    public void setD2(String d2) {
        D2 = d2;
    }
    public String getD3() {
        return D3;
    }
    public void setD3(String d3) {
        D3 = d3;
    }
}
