/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Receita;
import Util.PostgreSQLJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author guima
 */
public class ReceitaDAO {

    private static final Connection con = new PostgreSQLJDBC().getConnection();

    public boolean insert(Receita receita) {

        try {
            PreparedStatement p = con.prepareStatement("insert into receita (id_contato, id_usuario, id_categoria,"
                    + "titulo, descricao, dt_cadastro, dt_alteracao, dt_vencimento, valor, fixo, pago, parcelado) values (?,?,?,?,?,?,?,?,?,?,?,?)");

            p.setLong(1, receita.getContatoId());
            p.setLong(2, receita.getUsuarioId());
            p.setLong(3, receita.getCategoriaId());
            p.setString(4, receita.getTitulo());
            p.setString(5, receita.getDescricao());
            p.setString(6, receita.getCadastro());
            p.setString(7, receita.getAlteracao());
            p.setString(8, receita.getVencimento());
            p.setBigDecimal(9, receita.getValor());
            p.setBoolean(10, receita.isFixo());
            p.setBoolean(11, receita.isPago());
            p.setBoolean(12, receita.isParcelado());
            
            p.execute();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean delete(long pIdReceita) throws Exception {
        try {
            PreparedStatement p = con.prepareStatement("delete from receita where id_receita = ?");
            p.setLong(1, pIdReceita);
            p.execute();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(Receita receita) throws Exception {
        try {
            PreparedStatement p = con.prepareStatement("update receita set id_contato = ?, id_usuario = ?, id_categoria = ?, "
                    + "titulo = ?, descricao = ?, dt_cadastro = ?, dt_alteracao = ?, dt_vencimento = ?, valor = ?, fixo = ?, "
                    + "pago = ?, parcelado  = ? where id_receita = ?");
            p.setLong(1, receita.getContatoId());
            p.setLong(2, receita.getUsuarioId());
            p.setLong(3, receita.getCategoriaId());
            p.setString(4, receita.getTitulo());
            p.setString(5, receita.getDescricao());
            p.setString(6, receita.getCadastro());
            p.setString(7, receita.getAlteracao());
            p.setString(8, receita.getVencimento());
            p.setBigDecimal(9, receita.getValor());
            p.setBoolean(10, receita.isFixo());
            p.setBoolean(11, receita.isPago());
            p.setBoolean(12, receita.isParcelado());

            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    public List<Receita> list(Long idPerson) throws Exception {
        List<Receita> receitas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from receita where id_person = " + idPerson);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Receita receita = new Receita();
            receita.setReceitaId(rs.getLong("id_receita"));
            receita.setUsuarioId(rs.getLong("id_usuario"));
            receita.setContatoId(rs.getLong("id_contato"));
            receita.setCategoriaId(rs.getLong("id_categoria"));
            receita.setTitulo(rs.getString("titulo"));
            receita.setDescricao(rs.getString("descricao"));
            receita.setVencimento(rs.getString("vencimento"));
            receita.setValor(rs.getBigDecimal("valor"));
            receita.setCadastro(rs.getString("dt_cadastro"));
            receita.setAlteracao(rs.getString("alteracao"));
            receita.setFixo(rs.getBoolean("fixed"));
            receita.setPago(rs.getBoolean("pago"));
            receita.setParcelado(rs.getBoolean("parcelado"));
            receitas.add(receita);
        }

        rs.close();
        p.close();
        return receitas;
    }

    public static List<Receita> search(String collumnName, String search, Long usuarioId) throws Exception {
        List<Receita> receitas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from receita where " + collumnName + " like '%" + search + "%' and person_id = " + usuarioId);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Receita receita = new Receita();
            receita.setReceitaId(rs.getLong("id_receita"));
            receita.setUsuarioId(rs.getLong("id_usuario"));
            receita.setContatoId(rs.getLong("id_contato"));
            receita.setCategoriaId(rs.getLong("id_categoria"));
            receita.setTitulo(rs.getString("titulo"));
            receita.setDescricao(rs.getString("descricao"));
            receita.setVencimento(rs.getString("vencimento"));
            receita.setValor(rs.getBigDecimal("valor"));
            receita.setCadastro(rs.getString("dt_cadastro"));
            receita.setAlteracao(rs.getString("alteracao"));
            receita.setFixo(rs.getBoolean("fixed"));
            receita.setPago(rs.getBoolean("pago"));
            receita.setParcelado(rs.getBoolean("parcelado"));
            receitas.add(receita);
        }

        rs.close();
        p.close();
        return receitas;
    }

    public List<Receita> findById(Long id) throws Exception {
        List<Receita> receitas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from receita where id_receita = " + id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Receita receita = new Receita();
            receita.setReceitaId(rs.getLong("id_receita"));
            receita.setUsuarioId(rs.getLong("id_usuario"));
            receita.setContatoId(rs.getLong("id_contato"));
            receita.setCategoriaId(rs.getLong("id_categoria"));
            receita.setTitulo(rs.getString("titulo"));
            receita.setDescricao(rs.getString("descricao"));
            receita.setVencimento(rs.getString("vencimento"));
            receita.setValor(rs.getBigDecimal("valor"));
            receita.setCadastro(rs.getString("dt_cadastro"));
            receita.setAlteracao(rs.getString("alteracao"));
            receita.setFixo(rs.getBoolean("fixed"));
            receita.setPago(rs.getBoolean("pago"));
            receita.setParcelado(rs.getBoolean("parcelado"));
            receitas.add(receita);
        }

        rs.close();
        p.close();
        return receitas;
    }
}
