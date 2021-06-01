package com.usmpropa.usmpropaapi.Controllers;

import com.usmpropa.usmpropaapi.Repository.RopaRepository;
import com.usmpropa.usmpropaapi.Results.RopaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Map<Integer,Integer>> Dashboard()
    {
        List<Ropa> ropas = ropaRepository.findAll();
        
        Map<Integer,Integer> dashResult = ropas.stream()
                    .collect(
                        Collectors.groupingBy(Ropa:: getTipoRopaId,Collectors.summingInt(Ropa::getStock))
                        );
        
        return new ResponseEntity<Map<Integer,Integer>>(dashResult,HttpStatus.OK);
    }

}
