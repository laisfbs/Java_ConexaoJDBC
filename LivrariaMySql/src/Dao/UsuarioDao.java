package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.sound.sampled.SourceDataLine;

import com.mysql.cj.xdevapi.Statement;

import ConexaoApi.ConexaoJDBC;
import Model.Usuario;

// Classes DAO, sao classes que tem as instruções SQL pra os metodos necessarios, INSERT, DELET, etc.
public class UsuarioDao {

    private ConexaoJDBC conexao;
    private Connection connection;

    public UsuarioDao(Connection connection) {
		this.connection = connection;
	}


    public void inserirUsuario(Usuario usuario) {
        String sqlInserir = "INSERT INTO USUARIO (CPF, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, ENDERECO) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = this.connection.prepareStatement(sqlInserir)) {
            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getDataNascimento());
            pstm.setString(4, usuario.getTelefone());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getEndereco());

            pstm.execute();
            System.out.println("Usuario inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir " + e.getMessage());
        }
    }

	
	public void salvarComCategoria(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO PRODUTO (CPF, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, ENDERECO) VALUES ( ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getDataNascimento());
            pstm.setString(4, usuario.getTelefone());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getEndereco());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					usuario.setCpf(cpf);(rst.getString(1));
				}
			}
		}
	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT CPF, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, ENDERECO FROM Usuario";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			trasformarResultSetEmProduto(usuarios, pstm);
		}
		return usuarios;
	}

	
	public void deletar(Integer id) throws SQLException {
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
			pstm.setInt(1, id);
			pstm.execute();
		}
	}

	public void alterar(String cpf, String nome, String descricao, Integer id) throws SQLException {
		try (PreparedStatement pstm = connection
				.prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
                    pstm.setString(1, cpf);
                    pstm.setString(2, nome);
                    pstm.setString(3, descricao);
                

                    pstm.setString(1, cpf);
           
                    pstm.execute();
		}
	}

	private void trasformarResultSetEmProduto(List<Usuario> usuarios, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Usuario usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));

				usuarios.add(usuario);
			}
		}
	}