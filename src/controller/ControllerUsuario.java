/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoUsuario;

/**
 *
 * @author RODOMIX
 */
public class ControllerUsuario {
      private DaoUsuario dao;

    public ControllerUsuario() {
        this.dao = new DaoUsuario();
    }
      
      
     public boolean verificarLogin(String email, String senha) {
         return dao.verificarLogin(email, senha);
     }
    
}
