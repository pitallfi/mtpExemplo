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
import model.Usuario;

/**
 *
 * @author RODOMIX
 */
public class DaoUsuario {
     Connection con = null;
    PreparedStatement pstm = null;

    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<Usuario>();
        ResultSet rs = null;
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("SELECT * FROM tb_usuario", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = this.pstm.executeQuery();
            if (rs.first()) {
                do {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setSenha(rs.getString("senha"));
                    u.setPerfil(rs.getInt("perfil"));
                    

                    lista.add(u);

                } while (rs.next());
            }

            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

        return lista;
    }

    public void salvarUsuario(Usuario usuario) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("INSERT INTO tb_usuario (nome,email, senha, perfil) VALUES (?,?,?,?)");
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
             pstm.setInt(4, usuario.getPerfil());
           
            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuario no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão." + e);
            }
        }

    }

    public void alterarUsuario(Usuario usuario) {
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("UPDATE tb_usuario SET nome=?, email=?, senha=?, perfil=? WHERE id=?");
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3,usuario.getSenha());
            pstm.setInt(4, usuario.getPerfil());
            pstm.setInt(5, usuario.getId());
            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar usuario no banco de dados.",e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

    }

    public void excluirUsuario(int id) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("DELETE FROM tb_usuario  WHERE id=?");
            pstm.setInt(1, id);

            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuario no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

    }
    
    
    public boolean verificarLogin(String email, String senha) {
        boolean resposta = false;
        
        ResultSet rs = null;
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("SELECT * FROM tb_usuario WHERE email = ? and senha = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             pstm.setString(1, email);
             pstm.setString(2,senha);
            rs = this.pstm.executeQuery();
            if (rs.first()) {
               resposta = true;
            }

            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

        return resposta;
    }
}
