package DAO;

import Model.Usuario;
import Util.PostgreSQLJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author silas
 */
public class UsuarioDAO {

    private static final Connection con = new PostgreSQLJDBC().getConnection();

    public void insert(Usuario usuario) {

        try {
            PreparedStatement p = con.prepareStatement("insert into usuario (nome, email, senha, telefone, dt_nascimento) values (?,?,?,?,?)");

            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getEmail());
            p.setString(3, usuario.getSenha());
            p.setString(4, usuario.getTelefone());
            p.setString(5, usuario.getNascimento());
            p.execute();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Usuario usuario) throws Exception {
        PreparedStatement p = con.prepareStatement("delete from usuario where id_usuario = ?");
        p.setLong(1, usuario.getIdUsuario());
        p.execute();
        p.close();
    }

    public void update(Usuario usuario) throws Exception {
        PreparedStatement p = con.prepareStatement("update usuario set nome = ?, email = ?,"
                + " senha = ?, telefone = ?, dt_nascimento = ? where id_usuario = ?");
        p.setString(1, usuario.getNome());
        p.setString(2, usuario.getEmail());
        p.setString(3, usuario.getSenha());
        p.setString(4, usuario.getTelefone());
        p.setString(5, usuario.getNascimento());

        p.setLong(6, usuario.getIdUsuario());
        p.executeUpdate();
        p.close();
    }

    public List<Usuario> list() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from usuario ");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getLong("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getString("dt_nascimento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuarios.add(usuario);
        }

        rs.close();
        p.close();
        return usuarios;
    }

    public List<Usuario> search(String collumnName, String search) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from usuario where " + collumnName + " like '%" + search + "%'");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getLong("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getString("dt_nascimento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuarios.add(usuario);
        }

        rs.close();
        p.close();
        return usuarios;
    }

    public List<Usuario> findById(Long id) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from usuario where id_usuario = " + id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getLong("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getString("dt_nascimento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));
            usuarios.add(usuario);
        }

        rs.close();
        p.close();
        return usuarios;
    }

    public Usuario login(Usuario pUsuario) throws Exception {
        String sql = "select * from usuario where email = '" + pUsuario.getEmail() + "' and senha = '" + pUsuario.getSenha() + "';";
        System.out.println("SQL: "+sql);
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        Usuario usuario = new Usuario();

        while (rs.next()) {
            usuario.setIdUsuario(rs.getLong("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getString("dt_nascimento"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTelefone(rs.getString("telefone"));

        }

        rs.close();
        p.close();
        return usuario;
    }
}
