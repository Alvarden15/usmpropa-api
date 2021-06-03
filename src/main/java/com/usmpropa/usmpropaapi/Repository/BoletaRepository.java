package com.usmpropa.usmpropaapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usmpropa.usmpropaapi.Models.*;


@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Integer>
{
    
}
