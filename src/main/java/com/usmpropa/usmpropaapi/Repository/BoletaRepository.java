package com.usmpropa.usmpropaapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.usmpropa.usmpropaapi.Models.*;


@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Double>
{
    
    @Query("select direccion as dash_direccion,sum(total) as dash_total from Boleta group by direccion")
    List<Map<String, Object>> queryByDireccion();
    
    @Query("SELECT fechatransaccion as fecha,sum(total) as dash_total FROM Boleta where fechatransaccion between :startDate and :endDate group by fechatransaccion")
    List<Map<String, Object>> queryByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    
    @Query("SELECT fechatransaccion as fecha,sum(total) as dash_total FROM Boleta group by fechatransaccion order by fechatransaccion")
    List<Map<String, Object>> queryByDate();
    
}
