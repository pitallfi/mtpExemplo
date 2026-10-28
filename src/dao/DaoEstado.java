package dao;

import conexao.Conexao;
import model.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEstado {
    Connection con = null;
    PreparedStatement pstm = null;

    public List<Estado> getEstados() {
        List<Estado> lista = new ArrayList<Estado>();
        ResultSet rs = null;
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("SELECT * FROM tb_estado ORDER BY sigla ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = this.pstm.executeQuery();
            if (rs.first()) {
                do {
                    Estado e = new Estado();
                    e.setId(rs.getInt("id"));
                    e.setNome(rs.getString("nome"));
                    e.setSigla(rs.getString("sigla"));
                    

                    lista.add(e);

                } while (rs.next());
            }

            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar estado no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

        return lista;
    }

    public void salvarEstado(Estado estado) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("INSERT INTO tb_estado (nome,sigla) VALUES (?,?)");
            pstm.setString(1, estado.getNome());
            pstm.setString(2, estado.getSigla());
           
            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar estado no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão." + e);
            }
        }

    }

    public void alterarEstado(Estado estado) {
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("UPDATE tb_estado SET nome=?, sigla=? WHERE id=?");
            pstm.setString(1, estado.getNome());
            pstm.setString(2, estado.getSigla());
            pstm.setInt(3, estado.getId());
            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar estado no banco de dados.",e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

    }

    public void excluirEstado(int id) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("DELETE FROM tb_estado  WHERE id=?");
            pstm.setInt(1, id);

            this.pstm.execute();

            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar estado no banco de dados.", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão.", e);
            }
        }

    }
}
