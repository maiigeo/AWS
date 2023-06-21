/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;

import com.tendencias.app.Usuarios.model.Rol;
import com.tendencias.app.Usuarios.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    public RolServiceImpl rolServiceImpl;

    @GetMapping("/listar")
    public ResponseEntity<List<Rol>> listaRoles() {
        return new ResponseEntity<>(rolServiceImpl.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol r) {
        return new ResponseEntity<>(rolServiceImpl.save(r), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol r) {
        Rol rol = rolServiceImpl.findById(id);
        if (rol != null) {
            try {
                rol.setNombre(r.getNombre());
                rol.setEstado(r.getEstado());
                rol.setDescripcion(r.getDescripcion());
                return new ResponseEntity<>(rolServiceImpl.save(rol), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Rol> elimiarRol(@PathVariable Integer id) {
        rolServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
