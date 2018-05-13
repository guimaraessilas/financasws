/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Despesa;
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
public class DespesaDAO {

    private static final Connection con = new PostgreSQLJDBC().getConnection();

    public void insert(Despesa despesa) {

        try {
            PreparedStatement p = con.prepareStatement("insert into despesa (id_contato, id_usuario, id_categoria,"
                    + "titulo, descricao, dt_cadastro, dt_alteracao, dt_vencimento, valor, fixo, pago, parcelado) values (?,?,?,?,?,?,?,?,?,?,?,?)");

            p.setLong(1, despesa.getContatoId());
            p.setLong(2, despesa.getUsuarioId());
            p.setLong(3, despesa.getCategoriaId());
            p.setString(4, despesa.getTitulo());
            p.setString(5, despesa.getDescricao());
            try {
                p.setDate(6, new java.sql.Date(despesa.getCadastro().getTime()));
            } catch (NullPointerException e) {
                p.setDate(6, null);
            }
            try {
                p.setDate(7, new java.sql.Date(despesa.getAlteracao().getTime()));
            } catch (NullPointerException e) {
                p.setDate(7, null);
            }
            try {
                p.setDate(8, new java.sql.Date(despesa.getVencimento().getTime()));
            } catch (NullPointerException e) {
                p.setDate(8, null);
            }
            p.setBigDecimal(9, despesa.getValor());
            p.setBoolean(10, despesa.isFixo());
            p.setBoolean(11, despesa.isPago());
            p.setBoolean(12, despesa.isParcelado());
            p.execute();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Despesa despesa) throws Exception {
        PreparedStatement p = con.prepareStatement("delete from despesa where id_despesa = ?");
        p.setLong(1, despesa.getDespesaId());
        p.execute();
        p.close();
    }

    public void update(Despesa despesa) throws Exception {
        PreparedStatement p = con.prepareStatement("update despesa set id_contato = ?, id_usuario = ?, id_categoria = ?, "
                + "titulo = ?, descricao = ?, dt_cadastro = ?, dt_alteracao = ?, dt_vencimento = ?, valor = ?, fixo = ?, "
                + "pago = ?, parcelado  = ? where id_despesa = ?");
        p.setLong(1, despesa.getContatoId());
        p.setLong(2, despesa.getUsuarioId());
        p.setLong(3, despesa.getCategoriaId());
        p.setString(4, despesa.getTitulo());
        p.setString(5, despesa.getDescricao());
        try {
            p.setDate(6, new java.sql.Date(despesa.getCadastro().getTime()));
        } catch (NullPointerException e) {
            p.setDate(6, null);
        }
        try {
            p.setDate(7, new java.sql.Date(despesa.getAlteracao().getTime()));
        } catch (NullPointerException e) {
            p.setDate(7, null);
        }
        try {
            p.setDate(8, new java.sql.Date(despesa.getVencimento().getTime()));
        } catch (NullPointerException e) {
            p.setDate(8, null);
        }
        p.setBigDecimal(9, despesa.getValor());
        
        p.setBoolean(10, despesa.isFixo());
        p.setBoolean(11, despesa.isPago());
        p.setBoolean(12, despesa.isParcelado());
        
        p.executeUpdate();
        p.close();
    }

    public List<Despesa> list(Long idPerson) throws Exception {
        List<Despesa> despesas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from despesa where id_person = "+idPerson);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Despesa despesa = new Despesa();
            despesa.setDespesaId(rs.getLong("id_despesa"));
            despesa.setUsuarioId(rs.getLong("id_usuario"));
            despesa.setContatoId(rs.getLong("id_contato"));
            despesa.setCategoriaId(rs.getLong("id_categoria"));
            despesa.setTitulo(rs.getString("titulo"));
            despesa.setDescricao(rs.getString("descricao"));
            despesa.setVencimento(rs.getDate("vencimento"));
            despesa.setValor(rs.getBigDecimal("valor"));
            despesa.setCadastro(rs.getDate("dt_cadastro"));
            despesa.setAlteracao(rs.getDate("alteracao"));
            despesa.setFixo(rs.getBoolean("fixed"));
            despesa.setPago(rs.getBoolean("pago"));
            despesa.setParcelado(rs.getBoolean("parcelado"));
            despesas.add(despesa);
        }

        rs.close();
        p.close();
        return despesas;
    }
    
    public static List<Despesa> search(String collumnName, String search, Long usuarioId) throws Exception {
        List<Despesa> despesas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from despesa where "+collumnName+" like '%"+search+"%' and person_id = "+usuarioId);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Despesa despesa = new Despesa();
            despesa.setDespesaId(rs.getLong("id_despesa"));
            despesa.setUsuarioId(rs.getLong("id_usuario"));
            despesa.setContatoId(rs.getLong("id_contato"));
            despesa.setCategoriaId(rs.getLong("id_categoria"));
            despesa.setTitulo(rs.getString("titulo"));
            despesa.setDescricao(rs.getString("descricao"));
            despesa.setVencimento(rs.getDate("vencimento"));
            despesa.setValor(rs.getBigDecimal("valor"));
            despesa.setCadastro(rs.getDate("dt_cadastro"));
            despesa.setAlteracao(rs.getDate("alteracao"));
            despesa.setFixo(rs.getBoolean("fixed"));
            despesa.setPago(rs.getBoolean("pago"));
            despesa.setParcelado(rs.getBoolean("parcelado"));
            despesas.add(despesa);
        }

        rs.close();
        p.close();
        return despesas;
    }
    public List<Despesa> findById(Long id) throws Exception {
        List<Despesa> despesas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select * from despesa where id_despesa = "+id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            Despesa despesa = new Despesa();
            despesa.setDespesaId(rs.getLong("id_despesa"));
            despesa.setUsuarioId(rs.getLong("id_usuario"));
            despesa.setContatoId(rs.getLong("id_contato"));
            despesa.setCategoriaId(rs.getLong("id_categoria"));
            despesa.setTitulo(rs.getString("titulo"));
            despesa.setDescricao(rs.getString("descricao"));
            despesa.setVencimento(rs.getDate("vencimento"));
            despesa.setValor(rs.getBigDecimal("valor"));
            despesa.setCadastro(rs.getDate("dt_cadastro"));
            despesa.setAlteracao(rs.getDate("alteracao"));
            despesa.setFixo(rs.getBoolean("fixed"));
            despesa.setPago(rs.getBoolean("pago"));
            despesa.setParcelado(rs.getBoolean("parcelado"));
            despesas.add(despesa);
        }

        rs.close();
        p.close();
        return despesas;
    }
}