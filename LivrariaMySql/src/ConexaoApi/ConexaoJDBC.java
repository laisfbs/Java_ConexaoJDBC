package ConexaoApi;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoJDBC {

    public Connection getConexao() {
        try {
            Connection conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/livraria?user=Timezone=true&serverTimezone=UTC", "root",
                    "Lav@553249");
        
            System.out.println("Conectado!");
            return conexao;

        } catch (Exception e) {
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }

    }
  
}