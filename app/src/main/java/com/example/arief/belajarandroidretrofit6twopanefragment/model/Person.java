package com.example.arief.belajarandroidretrofit6twopanefragment.model;

/**
 * Created by Arief on 8/20/2017.
 */

public class Person {


    private int id;
    private String nama;

    public Person() {
    }

    public Person(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                '}';
    }
}
