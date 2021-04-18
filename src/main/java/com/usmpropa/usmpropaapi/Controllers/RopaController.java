package com.usmpropa.usmpropaapi.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ropa")
public class RopaController
{
    @GetMapping("prueba")
    public String GetRopa()
    {
        return "Listo";
    }

    @GetMapping
    public String ListadoRopa()
    {
        return "La lista estara aqui";
    }

    @GetMapping("{id}")
    public String RopaEspecifica(@PathVariable(name = "id") int id)
    {
        return "El "+id+ " esta aqui";
    }


}
