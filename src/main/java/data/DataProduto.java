package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Produto;


public class DataProduto {
    String nomeArquivo = "produtos.ser";

    public ArrayList<Produto> getAllProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        FileInputStream fluxo;
        ObjectInputStream lerObj = null;
        try {
            fluxo = new FileInputStream(nomeArquivo);
            lerObj = new ObjectInputStream(fluxo);
            produtos = (ArrayList<Produto>) lerObj.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao listar os produtos");
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println("Erro ao listar os produtos");
        }
        return produtos;
    }

    public void createProduto(Produto produto) {
        ArrayList<Produto> produtos = (ArrayList<Produto>) getAllProdutos();
        produtos.add(produto);
        try {
            FileOutputStream fluxo = new FileOutputStream(nomeArquivo);
            ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
            escreverObj.writeObject(produtos);
            escreverObj.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar produto");
        } catch (IOException ex) {
            System.out.println("Erro ao cadastrar produto");
        }
    }

    public Produto readProduto(String descricao) {
        ArrayList<Produto> produtos = (ArrayList<Produto>) getAllProdutos();
        Produto produto = null;
        for (int i = 0; i < produtos.size(); i++) {
            if (descricao.equals(produtos.get(i).getDescricao())) {
                produto = produtos.get(i);
                break;
            }
        }
        return produto;
    }

    public void updateProduto(Produto produto) {
        ArrayList<Produto> produtos = (ArrayList<Produto>) getAllProdutos();
        boolean achou = false;
        for (int i = 0; i < produtos.size(); i++) {
            if (produto.getDescricao().equals(produtos.get(i).getDescricao())) {
                produtos.remove(i);
                produtos.add(produto);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(produtos);
        }
    }

    public void deleteProduto(Produto produto) {
        ArrayList<Produto> produtos = (ArrayList<Produto>) getAllProdutos();
        boolean achou = false;
        for (int i = 0; i < produtos.size(); i++) {
            if (produto.getDescricao().equals(produtos.get(i).getDescricao())) {
                produtos.remove(i);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(produtos);
        }
    }

    private void atualizarArquivo(ArrayList<Produto> produtos) {
        try {
            FileOutputStream fluxo = new FileOutputStream(nomeArquivo);
            ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
            escreverObj.writeObject(produtos);
            escreverObj.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao atualizar produto");
        } catch (IOException ex) {
            System.out.println("Erro ao atualizar produto");
        }
    }

}
