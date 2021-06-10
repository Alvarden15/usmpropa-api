package com.usmpropa.usmpropaapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.usmpropa.usmpropaapi.Models.*;


@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Double>
{
    
    @Query("select direccion as dash_direccion,sum(total) as dash_total from Boleta group by direccion")
    List<Map<String, Object>> queryByMarca();
    
}
