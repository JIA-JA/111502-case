package com.system;

public class API2 {
    private String must_know_name;
    private String must_know_describe;
    public API2(String must_know_name,String must_know_describe){
        this.must_know_name = must_know_name;
        this.must_know_describe = must_know_describe;
    }
    public String getMust_know_name(){
        return must_know_name;
    }
    public String getMust_know_describe(){
        return must_know_describe;
    }

    public String toString(){
        return "must_know_name: "+must_know_name+
                "must_know_describe: "+must_know_describe;
    }
}
