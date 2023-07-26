package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Venda implements Serializable {

    private Date data;
    private int id;
    private double valorTotal;
    private ArrayList<ItemVenda> itens = new ArrayList<>();

    public Venda (int numero){
        Calendar c = Calendar.getInstance();
        this.data=c.getTime();
        this.id = numero+1;

    }
    public void addItem(ItemVenda i){
        itens.add(i);
    }
    public void removeItem(Produto p){
        for (int i=0; i<itens.size();i++){
            if(p.getDescricao().equals(itens.get(i).getProduto().getDescricao())){
                itens.remove(i);
            }
        }
    }
    public double calcularValorTotal(){
        double valorTotal = 0;
        for(int i=0; i<itens.size();i++){
            valorTotal += itens.get(i).getSubTotal();
        }
        this.valorTotal = valorTotal;
        return valorTotal;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }
    public String getDataRelatorio() {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/y");
        return sfd.format(data);
    }

    public double getValorTotal() {
        return calcularValorTotal();
    }

    public Calendar dateToCalendar() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getData());
        return calendar;

    }
    public String imprimirItens(){
        String lista = "";
        for (int i=0; i<itens.size();i++){
            lista += itens.get(i).getProduto().getDescricao()+"   " +itens.get(i).getQuantidade() + "    "+itens.get(i).getSubTotal()+"\n";
        }
        return lista;
    }

    public ArrayList<ItemVenda> getItens() {
        return itens;
    }
    public String toString(){
        String impressao = "Nº da venda: "+id+"     "+"\nData de venda: "+this.data+"\n Item  | Qtd | SubTotal   "+
                "\n" +imprimirItens()+
                "Total: "+getValorTotal()+
                "\n-----------------------------------------";
        return impressao;

    }
}
