package Teste;

import java.util.ArrayList;
import java.util.List;

import ConexaoApi.ConexaoJDBC;
import Dao.UsuarioDao;
import Model.Usuario;

public class TesteConexao {

  public static void main(String[] args) {

    ConexaoJDBC cnx = new ConexaoJDBC();
    cnx.getConexao();
  }

    UsuarioDao usrDAO = new UsuarioDao();
    Usuario addUsr = new Usuario(null, null, null, null, null, null);

    addUsr.setCpf("87495889456");
    addUsr.setNome("Maria");
    addUsr.setDataNascimento("25/10/1964");
    addUsr.setTelefone("11965584635");
    addUsr.setEmail("maria@email.com.br");
    addUsr.setEndereco("Avenida Araucaria, 71");
   
    usrDAO.inserirUsuario(addUsr);

   List<Usuario> listaDeUsuarios = usrDAO.listarUsuarios();
    listaDeUsuarios.stream().forEach

}