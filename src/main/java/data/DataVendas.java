package data;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Venda;


public class DataVendas{
    String nomeArquivo = "vendas.ser";

    public List<Venda> getAllVendas() {
        ArrayList<Venda> vendas = new ArrayList<>();
        FileInputStream fluxo;
        ObjectInputStream lerObj = null;
        try{
            fluxo = new FileInputStream(nomeArquivo);
            lerObj = new ObjectInputStream(fluxo);
            vendas = (ArrayList<Venda>)lerObj.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println ("Erro ao listar os pedidos");
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println ("Erro ao listar os pedidos");
        }
        return vendas;
    }

    public void createVenda(Venda venda) {
        ArrayList <Venda> vendas = (ArrayList<Venda>) getAllVendas();
        vendas.add(venda);
        try{
            FileOutputStream fluxo = new FileOutputStream(nomeArquivo);
            ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
            escreverObj.writeObject(vendas);

        } catch (FileNotFoundException ex) {
            System.out.println ("Erro ao cadastrar pedido");
        } catch (IOException ex) {
            System.out.println ("Erro ao cadastrar pedido");
        }
    }
    public Venda readVenda(int numero) {
        ArrayList <Venda> vendas = (ArrayList<Venda>) getAllVendas();
        Venda venda=null;
        for (int i=0; i<vendas.size(); i++){
            if (numero == vendas.get(i).getId()){
                venda = vendas.get(i);
                break;
            }
        }
        return venda;
    }
    public void updateVenda(int num, Venda v) {
        ArrayList <Venda> vendas = (ArrayList<Venda>) getAllVendas();
        boolean achou=false;
        for (int i=0; i<vendas.size(); i++){
            if (num == vendas.get(i).getId()){
                vendas.add(v);
                vendas.remove(i);
                achou=true;
                break;
            }
        }
        if (achou){
            atualizarArquivo(vendas);
        }
    }
    public ArrayList<Venda> vendasNoDia(int dia, int mes, int ano){
        ArrayList<Venda> vendas = (ArrayList<Venda>) getAllVendas();
        ArrayList<Venda> vendasNoDia = new ArrayList<Venda>();

        for(int i = 0; i<vendas.size(); i++){
            if(vendas.get(i).dateToCalendar().get(Calendar.DAY_OF_MONTH) == dia && vendas.get(i).dateToCalendar().get(Calendar.MONTH)==mes && vendas.get(i).dateToCalendar().get(Calendar.YEAR)==ano){
                vendasNoDia.add(vendas.get(i));
            }

        }
        return vendasNoDia;

    }
    public ArrayList<Venda> vendasNoMes(int mes, int ano){
        ArrayList<Venda> vendas = (ArrayList<Venda>) getAllVendas();
        ArrayList<Venda> vendasNoMes = new ArrayList<>();

        for(int i = 0; i<vendas.size(); i++){
            if(vendas.get(i).dateToCalendar().get(Calendar.MONTH)==mes && vendas.get(i).dateToCalendar().get(Calendar.YEAR)==ano){
                vendasNoMes.add(vendas.get(i));
            }

        }
        return vendasNoMes;

    }


    public void deleteVenda(Venda venda) {
        ArrayList <Venda> vendas = (ArrayList<Venda>) getAllVendas();
        boolean achou=false;
        for (int i=0; i<vendas.size(); i++){
            if (venda.getId() == vendas.get(i).getId()){
                vendas.remove(i);
                achou=true;
                break;
            }
        }
        if (achou){
            atualizarArquivo(vendas);
        }
    }
    private void atualizarArquivo (ArrayList <Venda> vendas){
        try{
            FileOutputStream fluxo = new FileOutputStream(nomeArquivo);
            ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
            escreverObj.writeObject(vendas);
            escreverObj.close();

        } catch (FileNotFoundException ex) {
            System.out.println ("Erro ao atualizar pedido");
        } catch (IOException ex) {
            System.out.println ("Erro ao atualizar pedido");
        }
    }





}