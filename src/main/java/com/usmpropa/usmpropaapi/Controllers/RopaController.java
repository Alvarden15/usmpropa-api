package com.usmpropa.usmpropaapi.Controllers;

import com.usmpropa.usmpropaapi.Repository.BoletaRepository;
import com.usmpropa.usmpropaapi.Repository.RopaRepository;
import com.usmpropa.usmpropaapi.Results.RopaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

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
    public ResponseEntity<Map<String,Double>> DashboardBoletaPorTipo()
    {
        List<Boleta> boletas = boletaRepository.findAll();
        Map<String,Double> dashResult = boletas.stream()
                    .collect(
                        Collectors.groupingBy(Boleta:: getNombreTipoRopa,Collectors.summingDouble(Boleta::getTotal))
                        );

        return new ResponseEntity<Map<String,Double>>(dashResult,HttpStatus.OK);
    }

    //necesita aun algunos cambios y que se ordene por fechas
    @GetMapping("dashboard/fechas")
    public ResponseEntity<Map<String,Double>> DashboardBoletaPorFecha
            (@RequestParam("fechaInicial") @DateTimeFormat(pattern =  "YYYY-dd-MM") Date fechaInicial,
             @RequestParam("fechaFinal") @DateTimeFormat(pattern =  "YYYY-dd-MM") Date fechaFinal)
    {
        if(fechaFinal == null || fechaInicial == null)
        {
            return new ResponseEntity<Map<String,Double>>(HttpStatus.BAD_REQUEST);
        }
        
        if(fechaInicial.after(fechaFinal))
        {
            return new ResponseEntity<Map<String,Double>>(HttpStatus.BAD_REQUEST);
        }

        List<Boleta> boletas = boletaRepository.findAll();
        Map<String,Double> dashResult = boletas.stream()
                    .collect(
                        Collectors.groupingBy(Boleta:: getNombreTipoRopa,Collectors.summingDouble(Boleta::getTotal))
                        );

        return new ResponseEntity<Map<String,Double>>(dashResult,HttpStatus.OK);
    }
}
