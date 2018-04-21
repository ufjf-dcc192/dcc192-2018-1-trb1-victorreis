package br.ufjf.dcc192;

import java.util.Map;

public class Comanda {
    private Mesa mesa;
    private Map<Integer, Item> itens;
    private String nomeClienteResponsavel;
    private String horaAbertura;
    private String horaFechamento;
    
    public Comanda(Mesa mesa, String nomeClienteResponsavel, String horaAbertura) {
        this.mesa = mesa;
        this.nomeClienteResponsavel = nomeClienteResponsavel;
        this.horaAbertura = horaAbertura;
    }
    
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Map<Integer, Item> getItens() {
        return itens;
    }

    public void setItens(Map<Integer, Item> itens) {
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
