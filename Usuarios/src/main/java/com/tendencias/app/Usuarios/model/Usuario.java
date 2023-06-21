/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author HP
 */

@Data
@Entity
@Table(name = "Usuario")
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id_usuario;

    @Size(min = 3, max = 10, message = "El usuario debe tener entre 3 y 10 caracteres")
    @NotBlank(message = "El usuario no puede estar en blanco")
    @Column(name = "nombre")
    private String nombre;
    
    @NotBlank(message = "La clave no puede estar en blanco")
    @Column(name = "clave")
    private String clave;

    @Email(message = "Debe ingresar una dirección de correo válida")
    @Column(name = "email")
    private String email;
    
    @Column(name = "estado")
    private int estado;

    @Transient
    private String fotoURL;

    private String imagenPath;

    @ManyToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    private Rol rol;


}
