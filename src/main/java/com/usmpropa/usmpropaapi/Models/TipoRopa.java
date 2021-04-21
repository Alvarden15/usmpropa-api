package com.usmpropa.usmpropaapi.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tiporopa")
public class TipoRopa
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;
    
    @OneToMany(mappedBy = "tipoRopa", cascade = CascadeType.ALL)
    private List<Ropa> ropas = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoRopa(){
        
    }
    
}
