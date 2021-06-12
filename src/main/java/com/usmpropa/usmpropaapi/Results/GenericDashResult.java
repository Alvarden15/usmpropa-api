package com.usmpropa.usmpropaapi.Results;

public class GenericDashResult
{
    private String grupo;
    
    private Object data;

    private Double dash_total;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Double getDashTotal() {
        return dash_total;
    }

    public void setDashTotal(Double dash_total) {
        this.dash_total = dash_total;
    }

    public GenericDashResult(String grupo, Double dash_total) {
        this.grupo = grupo;
        this.dash_total = dash_total;
    }
    
}
