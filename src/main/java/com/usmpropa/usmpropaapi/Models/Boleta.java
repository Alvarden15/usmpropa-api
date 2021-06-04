package com.usmpropa.usmpropaapi.Models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boleta")
public class Boleta {

    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private int dni;

    private String nombre;

    private String descripcion;

    private double importe;

    private double cantidad;

    private double total;

    private Date fechatransaccion;

    private String direccion;

    private String celular;
    
    @ManyToOne
    @JoinColumn(name = "ropa_id")
    private Ropa ropA;

    @Column(name = "ropa_id", insertable = false, updatable = false)
    private int ropaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechatransaccion() {
        return fechatransaccion;
    }

    public void setFechatransaccion(Date fechatransaccion) {
        this.fechatransaccion = fechatransaccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Ropa getRopA() {
        return ropA;
    }

    public void setRopA(Ropa ropA) {
        this.ropA = ropA;
    }

    public int getRopaId() {
        return ropaId;
    }

    public void setRopaId(int ropaId) {
        this.ropaId = ropaId;
    }

    public Boleta(int id, int dni, String nombre, String descripcion, double importe, double cantidad, double total, Date fechatransaccion, String direccion, String celular,Ropa ropA) {
        this.id = id;//
        this.dni = dni;//
        this.nombre = nombre;//
        this.descripcion = descripcion;//
        this.importe = importe;//
        this.cantidad = cantidad;//
        this.total = total;//
        this.fechatransaccion = fechatransaccion;//
        this.direccion = direccion;//
        this.celular = celular;//
        this.ropA=ropA;/**/
    }
   
    public Boleta(){

    }


}
