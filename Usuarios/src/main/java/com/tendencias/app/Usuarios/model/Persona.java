/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "Persona")
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private int idPersona;

    private String nombre, apellido, correo, cedula, celular, direccion, instruccion;
    private Date fechanacimiento;

    @JsonIgnore
    @OneToMany(mappedBy = "persona")
    private List<Usuario> listaUsuarios;

}
