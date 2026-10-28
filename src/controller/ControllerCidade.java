/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoCidade;
import dao.DaoCidade;
import java.util.List;
import model.Cidade;


/**
 *
 * @author afranio
 */
public class ControllerCidade {
    private DaoCidade dao;
    private List<Cidade> cidades;
    private int indice;

    public ControllerCidade() {
        dao =  new DaoCidade();
    }
    
    
    
    
    public boolean inserirCidade(Cidade cidade) {

       
        dao.salvarCidade(cidade);

      

        return true;
    }
}
