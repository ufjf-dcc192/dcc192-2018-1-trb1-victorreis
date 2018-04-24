package br.ufjf.dcc192;

import java.util.HashMap;
import java.util.Map;

public class Comanda {
    private Integer id;
    private Mesa mesa;
    private Map<Item, Integer> itens;
    private String nomeClienteResponsavel;
    private String horaAbertura;
    private String horaFechamento;
    
    public Comanda(Integer id, Mesa mesa, String nomeClienteResponsavel, String horaAbertura) {
        this.id = id;
        this.mesa = mesa;
        this.nomeClienteResponsavel = nomeClienteResponsavel;
        this.horaAbertura = horaAbertura;
        this.itens = new HashMap<>();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Map<Item, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<Item, Integer> itens) {
        this.itens = itens;
    }

    public String getNomeClienteResponsavel() {
        return nomeClienteResponsavel;
    }

    public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
        this.nomeClienteResponsavel = nomeClienteResponsavel;
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    
}
