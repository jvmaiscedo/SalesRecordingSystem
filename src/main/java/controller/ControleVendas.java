package controller;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import java.util.ArrayList;

import data.DataVendas;

public class ControleVendas {

    private DataVendas dataVendas = new DataVendas();

    public void cadastrarVenda(int num){
        dataVendas.createVenda(new Venda(num));
    }
    public void addItemVenda(int num, Produto p, int qtd){
        Venda v = pesquisarVenda(num);
        v.addItem(new ItemVenda(p,qtd));
        dataVendas.updateVenda(num, v);
    }
    public void removeItemVenda(int num, Produto p){
        Venda v = pesquisarVenda(num);
        v.removeItem(p);
        dataVendas.updateVenda(num, v);
    }

    public Venda pesquisarVenda(int numero){
        return dataVendas.readVenda(numero);
    }
    public void deletarVenda(int numero){
        dataVendas.deleteVenda(pesquisarVenda(numero));
    }
    public ArrayList<Venda> listarVendas(){
        return (ArrayList<Venda>) dataVendas.getAllVendas();
    }

    public DataVendas getDataVendas() {
        return dataVendas;
    }

    public String imprimir (){
        ArrayList<Venda> vendas = listarVendas();
        String resultado = "";
        for (int i=0; i< vendas.size(); i++){
            resultado += vendas.get(i).toString()+"\n";
        }
        return resultado;
    }
    public String vendasNoDia (int dia, int mes, int ano){
        ArrayList<Venda> vendas = dataVendas.vendasNoDia(dia, mes, ano);
        String resultado = "";
        for (int i=0; i< vendas.size(); i++){
            resultado += vendas.get(i).toString()+"\n";
        }
        return resultado;
    }
    public String vendasNoMes (int mes, int ano){
        ArrayList<Venda> vendas = dataVendas.vendasNoMes(mes, ano);
        String resultado = "";
        for (int i=0; i< vendas.size(); i++){
            resultado += vendas.get(i).toString()+"\n";
        }
        return resultado;
    }
    public String imprimirVenda(int num){
      return pesquisarVenda(num).toString();
    }
}
