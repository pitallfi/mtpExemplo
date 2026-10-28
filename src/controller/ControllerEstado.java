/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import dao.DaoEstado;
import model.Estado;
/**
 *
 * @author afranio
 */
public class ControllerEstado {
    private DaoEstado dao;
    private List<Estado> estados;
    private int indice;

    public ControllerEstado() {
        this.dao = new DaoEstado();
        this.estados = new ArrayList<Estado>();
        this.indice = 0;

        carregarEstados();
    }

    // =========================================================
    // CRUD
    // =========================================================
    public boolean inserirEstado(Estado estado) {

        if (!estadoValido(estado)) {
            return false;
        }

        dao.salvarEstado(estado);

        carregarEstados();

        ultimo();

        return true;
    }

    public boolean alterarEstado(Estado estado) {

        if (!estadoValido(estado)) {
            return false;
        }

        if (estado.getId() <= 0) {
            return false;
        }

        dao.alterarEstado(estado);

        carregarEstados();

        return true;
    }

    public Estado deletarEstado(int id) {
        if (id <= 0) {
            return null;
        }
        Estado estado = getEstadoPorId(id);
        if (estado == null) {
            return null;
        }
        // Guarda a posição do estado que será excluído
        int indiceExcluido = indice;
        dao.excluirEstado(id);
        carregarEstados();
        // Não existem mais estados
        if (estados.isEmpty()) {
            indice = 0;
            return null;
        }
        /*
     * Se o estado excluído não era o último,
     * o mesmo índice agora aponta para o próximo estado.
         */
        if (indiceExcluido < estados.size()) {
            indice = indiceExcluido;
        } else {
            /*
         * O estado excluído era o último.
         * Então voltamos para o anterior.
             */
            indice = estados.size() - 1;
        }

        return estados.get(indice);
    }
    // =========================================================
    // CONSULTA
    // =========================================================

    public List<Estado> getEstados() {

        carregarEstados();

        return estados;
    }

    public Estado getEstadoPorId(int id) {

        for (Estado estado : estados) {

            if (estado.getId() == id) {
                return estado;
            }
        }

        return null;
    }
    
    
    
    
    public Estado getEstadoBySigla(String sigla)
    {
        Estado estado = new Estado();
        if(estados.isEmpty())
        {
            return null;
        }
        for(int i=0;i<estados.size();i++)
        {
            if(estados.get(i).getSigla().equals(sigla))
            {
                estado = estados.get(i);
            }
        }
        return estado;
    }

    // =========================================================
    // NAVEGAÇÃO
    // =========================================================
    public Estado primeiro() {

        if (estados.isEmpty()) {
            return null;
        }

        indice = 0;

        return estados.get(indice);
    }

    public Estado anterior() {

        if (estados.isEmpty()) {
            return null;
        }

        if (indice > 0) {
            indice--;
        }

        return estados.get(indice);
    }

    public Estado proximo() {

        if (estados.isEmpty()) {
            return null;
        }

        if (indice < estados.size() - 1) {
            indice++;
        }

        return estados.get(indice);
    }

    public Estado ultimo() {

        if (estados.isEmpty()) {
            return null;
        }

        indice = estados.size() - 1;

        return estados.get(indice);
    }

    // =========================================================
    // CONTROLE DOS BOTÕES DE NAVEGAÇÃO
    // =========================================================
    public boolean temAnterior() {

        return !estados.isEmpty() && indice > 0;
    }

    public boolean temProximo() {

        return !estados.isEmpty() && indice < estados.size() - 1;
    }

    public boolean temEstados() {

        return !estados.isEmpty();
    }

    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================
    private void carregarEstados() {

        estados = dao.getEstados();

        if (estados == null) {
            estados = new ArrayList<>();
        }

        if (estados.isEmpty()) {
            indice = 0;
        } else if (indice >= estados.size()) {
            indice = estados.size() - 1;
        }
    }

    private boolean estadoValido(Estado estado) {

        if (estado == null) {
            return false;
        }

        if (estado.getNome() == null || estado.getSigla().trim().isEmpty()) {
            return false;
        }

        if (estado.getNome() == null || estado.getSigla().trim().isEmpty()) {
            return false;
        }

        

        return true;
    }

    // =========================================================
    // INFORMAÇÕES DA NAVEGAÇÃO
    // =========================================================
    public int getIndice() {

        return indice;
    }

    public int getQuantidadeEstados() {

        return estados.size();
    }
}
