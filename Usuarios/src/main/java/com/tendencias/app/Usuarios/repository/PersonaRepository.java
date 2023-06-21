/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tendencias.app.Usuarios.repository;

import com.tendencias.app.Usuarios.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HP
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query(value = "Select * from persona p where p.cedula = :cedula", nativeQuery = true)
    public Persona buscarPersona(String cedula);
}
