package colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ColaboradorDAO {

    private Connection conexao;

    public void cadastrar(Colaborador colaborador) {

        PreparedStatement stmt= null;

        try {
            conexao = EmpresaDBManager.obterConexao();
            String sql = "INSERT INTO TAB_COLABORADOR (NOME, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES ( ?, ?, ?, ?)";

            stmt = conexao.prepareCall(sql);
            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getEmail());
            stmt.setDouble(3, colaborador.getSalario());
            Date data= new Date(colaborador.getDataContratacao().getTimeInMillis());
            stmt.setDate(4, data);

            stmt.executeUpdate();

        } catch (SQLDataException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Colaborador> listar() {

        List<Colaborador> lista = new ArrayList<Colaborador>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = EmpresaDBManager.obterConexao();
            stmt = conexao.prepareStatement("SELECT * FROM TAB_COLABORADOR");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("CODIGO_COLABORADOR");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                double salario = rs.getDouble("SALARIO");
                Date data= rs.getDate("DATA_CONTRATACAO");
                Calendar dataContratacao= Calendar.getInstance();
                dataContratacao.setTimeInMillis(data.getTime());

                Colaborador colaborador= new Colaborador(codigo, nome, email, salario, dataContratacao);
                lista.add(colaborador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return lista;
    }

    public void remover(int codigo) {
        PreparedStatement stmt = null;

        try {
            conexao = EmpresaDBManager.obterConexao();
            String sql = "DELETE FROM TAB_COLABORADOR WHERE CODIGO_COLABORADOR = ?";

            stmt= conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Colaborador buscarPorId(int codigoBusca) {

        Colaborador colaborador = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = EmpresaDBManager.obterConexao();
            stmt = conexao.prepareStatement("SELECT * FROM TAB_COLABORADOR WHERE CODIGO_COLABORADOR = ?");
            stmt.setInt(1, codigoBusca);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigo = rs.getInt("CODIGO_COLABORADOR");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                double salario = rs.getDouble("SALARIO");
                Date data = rs.getDate("DATA_CONTRATACAO");
                Calendar dataContratacao = Calendar.getInstance();
                dataContratacao.setTimeInMillis(data.getTime());

                colaborador = new Colaborador(codigo, nome, email, salario, dataContratacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return colaborador;
    }

    public void atualizar(Colaborador colaborador) {
        PreparedStatement stmt = null;

        try {
            conexao = EmpresaDBManager.obterConexao();
            String sql = "UPDATE TAB_COLABORADOR SET NOME= ?, EMAIL= ?, SALARIO= ?, DATA_CONTRATACAO= ? WHERE CODIGO_COLABORADOR= ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getEmail());
            stmt.setDouble(3, colaborador.getSalario());
            Date data = new Date(colaborador.getDataContratacao().getTimeInMillis());
            stmt.setDate(4, data);
            stmt.setInt(5, colaborador.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
