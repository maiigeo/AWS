/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.service;


import com.tendencias.app.Usuarios.model.Persona;
import com.tendencias.app.Usuarios.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements GenericService<Persona, Integer> {

    @Autowired
   public PersonaRepository personaRepository;

    @Override
    public CrudRepository<Persona, Integer> getDao() {
        return personaRepository;
    }
}
