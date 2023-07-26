package controller;

import data.DataProduto;
import java.util.ArrayList;
import model.Produto;

public class ControleProduto {
    private DataProduto dataProduto= new DataProduto();

    public void cadastrarProduto (String descricao, double preco, double percentual){
        Produto p = new Produto (descricao, preco, percentual);
        dataProduto.createProduto(p);
    }

    public Produto pesquisarProduto (String descricao) {
        return dataProduto.readProduto(descricao);
    }

    public ArrayList<Produto> listarProdutos() {
        return (ArrayList <Produto>)dataProduto.getAllProdutos();
    }

    public void removerProduto (Produto produto) {
        dataProduto.deleteProduto(produto);
    }

    public void atualizarProduto (Produto produto){
        dataProduto.updateProduto(produto);
    }

    public String imprimir (){
        ArrayList<Produto> produtos = listarProdutos();
        String resultado = "";
        for (int i=0; i< produtos.size(); i++){
            resultado += produtos.get(i).toString()+"\n";
        }
        return resultado;
    }
}