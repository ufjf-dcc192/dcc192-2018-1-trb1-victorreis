package br.ufjf.dcc192;

import java.util.ArrayList;
import java.util.Date;

public class Persistencia {

    private static ArrayList<Comanda> comandas;
    private static ArrayList<Mesa> mesas;
    private static ArrayList<Item> itens;
    private static Integer autoIncrementoComandas;
    
    public static ArrayList<Item> getInstanceItens() {
        if (itens == null) {
            itens = new ArrayList<>();
            Integer i = 1;
            itens.add(new Item(i++, "Porção de Batata Frita", 19.99));
            itens.add(new Item(i++, "Porção de Mandioca", 19.99));
            itens.add(new Item(i++, "Porção de Torresmo", 19.99));
            
            itens.add(new Item(i++, "Brahma 350ml", 4.00));
            itens.add(new Item(i++, "Brahma 600ml", 8.00));
            itens.add(new Item(i++, "Brahma 1L", 12.00));
            
            itens.add(new Item(i++, "Skol 350ml", 3.50));
            itens.add(new Item(i++, "Skol 600ml", 7.50));
            itens.add(new Item(i++, "Skol 1L", 11.50));
            
            itens.add(new Item(i++, "Heineken 600ml", 9.50));
        }
        return itens;
    }
    
    public static ArrayList<Mesa> getInstanceMesas() {
        if (mesas == null) {
            mesas = new ArrayList<>();
            mesas.add(new Mesa(1, "Mesa 1"));
            mesas.add(new Mesa(2, "Mesa 2"));
            mesas.add(new Mesa(3, "Mesa 3"));
            mesas.add(new Mesa(4, "Mesa 4"));
            mesas.add(new Mesa(5, "Mesa 5"));
            mesas.add(new Mesa(6, "Mesa 6"));
            mesas.add(new Mesa(7, "Mesa 7"));
        }
        return mesas;
    }

    public static ArrayList<Comanda> getInstanceComandas() {
        getInstanceMesas();
        if (comandas == null) {
            autoIncrementoComandas = 1;
            comandas = new ArrayList<>();
            comandas.add(new Comanda(autoIncrementoComandas++, getMesaById(3), "Fulano", new Date().toString()));
            comandas.add(new Comanda(autoIncrementoComandas++, getMesaById(4), "Ciclano", new Date().toString()));
            comandas.add(new Comanda(autoIncrementoComandas++, getMesaById(1), "Beltrano", new Date().toString()));
        }
        return comandas;
    }

    public static Mesa getMesaById(Object id) {
        getInstanceMesas();
        Integer idInteger;
        
        if (id instanceof Integer) {
            idInteger = (Integer) id;
        } else if (id instanceof String) {
            idInteger = Integer.parseInt((String) id);
        } else {
            return null;
        }
        
        for (Mesa mesa : mesas) {
            if (idInteger.equals(mesa.getId())) {
                return mesa;
            }
        }
        return null;
    }
    
    public static Item getItemById(Object id) {
        getInstanceItens();
        Integer idInteger;
        
        if (id instanceof Integer) {
            idInteger = (Integer) id;
        } else if (id instanceof String) {
            idInteger = Integer.parseInt((String) id);
        } else {
            return null;
        }
        
        for (Item item : itens) {
            if (idInteger.equals(item.getId())) {
                return item;
            }
        }
        return null;
    }

    public static Comanda getComandaById(Object id) {
        getInstanceComandas();
        Integer idInteger;
        
        if (id instanceof Integer) {
            idInteger = (Integer) id;
        } else if (id instanceof String) {
            idInteger = Integer.parseInt((String) id);
        } else {
            return null;
        }
        
        for (Comanda comanda : comandas) {
            if (idInteger.equals(comanda.getId())) {
                return comanda;
            }
        }
        return null;
    }
    
    public static boolean criarNovaComanda(String idMesa, String nomeCliente, String horaAbertura) {
        getInstanceMesas();
        getInstanceComandas();
        Mesa mesa = getMesaById(idMesa);

        comandas.add(new Comanda(autoIncrementoComandas, mesa, nomeCliente, horaAbertura));
        autoIncrementoComandas++;
        return true;
    }

    public static boolean abrirOuFecharComanda(String id) {
        getInstanceComandas();
        Comanda comanda = getComandaById(id);
        
        if (comanda != null) {
            if (comanda.getHoraFechamento() == null) {
                comanda.setHoraFechamento(new Date().toString());
            } else {
                comanda.setHoraFechamento(null);
            }
            return true;
        }
        return false;
    }
    
    public static Integer adicionarItensNaComanda(String idComanda, String idItem, String quantidade) {
        getInstanceMesas();
        getInstanceComandas();
        getInstanceItens();
        
        Comanda comanda = getComandaById(idComanda);
        Item item = getItemById(idItem);
        Integer quantidadeItens = Integer.parseInt(quantidade);
        
        if (comanda.getItens().containsKey(item)) {
            Integer resultado = comanda.getItens().get(item) + quantidadeItens;
            if (resultado <= 0) {
                comanda.getItens().remove(item);
            } else {
                comanda.getItens().replace(item, resultado);
            }
        } else {
            comanda.getItens().put(item, quantidadeItens);
        }
        
        return autoIncrementoComandas++;
    }
    
    public static boolean removerItensNaComanda(String idComanda, String idItem) {
        getInstanceMesas();
        getInstanceComandas();
        getInstanceItens();
        
        Comanda comanda = getComandaById(idComanda);
        Item item = getItemById(idItem);
        
        if (comanda.getItens().containsKey(item)) {
            comanda.getItens().remove(item);
            return true;
        }
        return false;
    }
}
