package com.usmpropa.usmpropaapi.Controllers;

import com.usmpropa.usmpropaapi.Repository.BoletaRepository;
import com.usmpropa.usmpropaapi.Repository.RopaRepository;
import com.usmpropa.usmpropaapi.Results.RopaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import com.usmpropa.usmpropaapi.Models.*;

@RestController
@RequestMapping("api/ropa")
public class RopaController
{
    @Autowired
    private RopaRepository ropaRepository;

    @Autowired
    private BoletaRepository boletaRepository;
    
    @GetMapping("simple")
    public ResponseEntity<List<RopaResult>> ListadoRopa()
    {
        List<Ropa> ropas = ropaRepository.findAll();
        List<RopaResult> result = ropas.stream()
            .map(r -> new RopaResult(r.getId(), r.getNombre(), r.getPrecio(), r.getStock()))
            .collect(Collectors.toList());
        
        return new ResponseEntity<List<RopaResult>>(result,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ropa>> ListadoRopaCompleta()
    {
        List<Ropa> result = ropaRepository.findAll();
        
        return new ResponseEntity<List<Ropa>>(result,HttpStatus.OK);
    }

    @GetMapping("boleta")
    public ResponseEntity<List<Boleta>> ListadoBoleta()
    {
        List<Boleta> result = boletaRepository.findAll();

        return new ResponseEntity<List<Boleta>>(result,HttpStatus.OK);
        
    }

    @GetMapping("{id}")
    public ResponseEntity<Ropa> RopaEspecifica(@PathVariable(name = "id") int id)
    {
        if(id < 0)
        {
            return new ResponseEntity<Ropa>(HttpStatus.BAD_REQUEST);
        }
        
        Optional<Ropa> ropaEspecifica = ropaRepository.findById(id);

        if(ropaEspecifica.isPresent())
        {
            return new ResponseEntity<Ropa>(ropaEspecifica.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<Ropa>(HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("dashboard")
    @ApiOperation(value = "Dashboard de prueba", notes = "API para demostrar como funciona el dashboard (ve stock por cada tipo de ropa)")
    public ResponseEntity<Map<TipoRopa,Integer>> Dashboard()
    {
        List<Ropa> ropas = ropaRepository.findAll();
        
        Map<TipoRopa,Integer> dashResult = ropas.stream()
                    .collect(
                        Collectors.groupingBy(Ropa:: getTipoRopa,Collectors.summingInt(Ropa::getStock))
                        );
        
        return new ResponseEntity<Map<TipoRopa,Integer>>(dashResult,HttpStatus.OK);
    }

    @GetMapping("dashboard/tipos")
    @ApiOperation(value = "Dashboard de boletas por tipo de ropa", notes = "API para obtener las ganancias obtenidas por cada tipo de ropa")
    public ResponseEntity<Map<String,Double>> DashboardBoletaPorTipo()
    {
        List<Boleta> boletas = boletaRepository.findAll();
        Map<String,Double> dashResult = boletas.stream()
                    .collect(
                        Collectors.groupingBy(Boleta:: getNombreTipoRopa,Collectors.summingDouble(Boleta::getTotal))
                        );

        return new ResponseEntity<Map<String,Double>>(dashResult,HttpStatus.OK);
    }


    @GetMapping("dashboard/fechas")
    @ApiOperation(value = "Dashboard de boletas por fecha", 
        notes = "API para obtener las ganancias obtenidas en un rango de fecha (deben estar en formato: YYYY-MM-DD). Si alguna no esta presente, se listaran de todas las fechas registradas."
        )
    public ResponseEntity<List<Map<String, Object>>> DashboardBoletaPorFecha
    (
        @ApiParam(value = "La fecha inicial del rango (YYYY-MM-DD)", example = "2021-03-16")
        @RequestParam(required = false, name = "fechaInicial") String fechaInicial,

        @ApiParam(value = "La fecha final del rango (YYYY-MM-DD)", example = "2021-06-20")
        @RequestParam(required = false, name = "fechaFinal") String fechaFinal)
    {
        
        List<Map<String, Object>> dashResult;

        if(fechaFinal == null || fechaInicial == null)
        {
            dashResult = boletaRepository.queryByDate();

        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            try {
                Date inicio = formatter.parse(fechaInicial);
                Date fin = formatter.parse(fechaFinal);
                
                if(inicio.after(fin))
                {
                    return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
                }
                dashResult = boletaRepository.queryByDate(inicio, fin);

            } catch (Exception e) {
                return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
            }
            
        } 

        return new ResponseEntity<List<Map<String, Object>>>(dashResult,HttpStatus.OK);
    }

    @GetMapping("dashboard/direccion")
    @ApiOperation(value = "Dashboard de boletas por local", notes = "API para obtener las ganancias de ropa obtenidas por cada local")
    public ResponseEntity<List<Map<String, Object>>> direccion(){
        return  new ResponseEntity<List<Map<String, Object>>>(
            boletaRepository.queryByDireccion(), HttpStatus.OK);
    }

}
