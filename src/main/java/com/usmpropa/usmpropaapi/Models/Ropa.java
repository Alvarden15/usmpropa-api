package com.usmpropa.usmpropaapi.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ropa")
public class Ropa
{
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String nombre;

    private double precio;

    private String marca;

    private int stock;

    private String descripcion;

    private double puntuacion;

    @ManyToOne
    @JoinColumn(name = "tiporopa_id")
    private TipoRopa tipoRopa;

    @Column(name = "tiporopa_id", insertable = false, updatable = false)
    private int tipoRopaId;
    
    
    public int getTipoRopaId() {
        return tipoRopaId;
    }

    public void setTipoRopaId(int tipoRopaId) {
        this.tipoRopaId = tipoRopaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public TipoRopa getTipoRopa() {
        return tipoRopa;
    }

    public void setTipoRopa(TipoRopa tipoRopa) {
        this.tipoRopa = tipoRopa;
    }

    public Ropa(int id, String nombre, double precio, String marca, int stock, String descripcion, double puntuacion,
            TipoRopa tipoRopa) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.stock = stock;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.tipoRopa = tipoRopa;
    }

    public Ropa(int id, String nombre, double precio, int stock, TipoRopa tipoRopa) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tipoRopa = tipoRopa;
    }

    public Ropa(){

    }
    
    
}
