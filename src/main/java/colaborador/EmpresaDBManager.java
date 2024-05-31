package colaborador;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmpresaDBManager {

    public static Connection obterConexao() {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/COLABORADOR", "root", "admin");
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return conexao;
    }
}
