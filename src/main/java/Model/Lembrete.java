/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author silas
 */
public class Lembrete {
    private Long lembreteId;
    private Long origemId;
    private String titulo;
    private String descricao;
    private Date dataLembrete;
    private Date cadastro;
    private Date alteracao;
    private String tipo_origem;
    private boolean fixo;

    public Long getLembreteId() {
        return lembreteId;
    }

    public void setLembreteId(Long lembreteId) {
        this.lembreteId = lembreteId;
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLembrete() {
        return dataLembrete;
    }

    public void setDataLembrete(Date dataLembrete) {
        this.dataLembrete = dataLembrete;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public String getTipo_origem() {
        return tipo_origem;
    }

    public void setTipo_origem(String tipo_origem) {
        this.tipo_origem = tipo_origem;
    }

    public boolean isFixo() {
        return fixo;
    }

    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }
    
    
}
