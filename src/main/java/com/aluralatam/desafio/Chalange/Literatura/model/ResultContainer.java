package com.aluralatam.desafio.Chalange.Literatura.model;

import com.aluralatam.desafio.Chalange.Literatura.records.DatosBooks;
import com.aluralatam.desafio.Chalange.Literatura.records.DatosResults;

import java.util.List;

public class ResultContainer {
    private int count;
    private String next;
    private String previous;
    private List<DatosBooks> results;


    public ResultContainer(DatosResults datosResults){
        this.next = datosResults.next();
        this.count= datosResults.count();
        this.previous= datosResults.previous();
        this.results= datosResults.results();
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<DatosBooks> getResults() {
        return results;
    }

    public void setResults(List<DatosBooks> results) {
        this.results = results;
    }
}
