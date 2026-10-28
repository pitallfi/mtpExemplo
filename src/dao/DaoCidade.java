/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Cidade;
import model.Estado;

/**
 *
 * @author afranio
 */
public class DaoCidade {
    Connection con = null;
    PreparedStatement pstm = null;
    
     public List<Cidade> getCidades() {
        List<Cidade> lista = new ArrayList<Cidade>();
        ResultSet rs = null;
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("SELECT c.id AS cidade_id, c.nome AS cidade_nome, e.id AS estado_id, e.nome AS estado_nome, e.sigla AS estado_sigla FROM tb_cidade AS c INNER JOIN tb_estado AS e ON c.uf_id = e.id", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = this.pstm.executeQuery();
            if (rs.first()) {
                do {
                    Cidade c = new Cidade();
                    c.setId(rs.getInt("cidade_id"));
                    c.setNome(rs.getString("cidade_nome"));
                    
                    Estado e = new Estado();
                    e.setId(rs.getInt("estado_id"));
                    e.setNome(rs.getString("estado_nome"));
                    e.setSigla(rs.getString("estado_sigla"));
                    
                    c.setEstado(e);
                    

                    lista.add(c);

                } while (rs.next());
            }

            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cidade no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

        return lista;
    }

   public void salvarCidade(Cidade cidade) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("INSERT INTO tb_cidade (nome, uf_id) VALUES (?,?)");
            pstm.setString(1, cidade.getNome());
            pstm.setInt(2, cidade.getEstado().getId());
           
            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cidade no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão." + e);
            }
        }

    }
        
        
        
}
