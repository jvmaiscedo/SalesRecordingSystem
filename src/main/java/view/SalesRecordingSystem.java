package view;

import controller.ControleProduto;
import controller.ControleVendas;
import model.Produto;
import model.PdfCreator;

import javax.swing.JOptionPane;
import java.util.Locale;

public class SalesRecordingSystem {
    public static String menu(){
        return "Digite:\n" +
                "1 - Gerenciar produtos\n"+
                "2 - Gerenciar vendas \n"+
                "3 - Criar relatorio diario\n"+
                "4 - Criar relatorio mensal\n"+
                "0 - para sair";
    }
    public static String gerenciarProdutos(){
        return "Digite:\n"+
                "1 - Cadastrar produto\n"+
                "2 - Pesquisar produto \n"+
                "3 - Remover produto \n"+
                "4 - Atualizar produto \n"+
                "5 - Listar produtos \n";
    }
    public static String gerenciarVendas(){
        return "Digite:\n"+
                "1 - Cadastrar venda \n"+
                "2 - Adicionar item a venda\n"+
                "3 - Remover item da venda\n"+
                "4 - Remover venda\n"+
                "5 - Mostrar venda\n"+
                "6 - Mostrar vendas\n"+
                "7 - Mostrar vendas do dia\n"+
                "8 - Mostrar vendas do mês\n";

    }

    public static void main(String[] args) {
        ControleProduto cProduto = new ControleProduto();
        ControleVendas cVendas = new ControleVendas();
        PdfCreator pdfCreatorTeste = new PdfCreator();
        int op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        while (op!=0){
            switch (op){
                case 1:{
                    int op2 = Integer.parseInt(JOptionPane.showInputDialog(gerenciarProdutos()));
                    switch (op2){
                        case 1:{
                            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto");
                            double custo = Double.parseDouble(JOptionPane.showInputDialog("Digite o custo do produto"));
                            double percentual = Double.parseDouble(JOptionPane.showInputDialog("Digite o percentual  de lucro desejado"));
                            cProduto.cadastrarProduto(descricao, custo, percentual);
                            break;
                        }
                        case 2: {
                            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto");
                            JOptionPane.showMessageDialog(null,  cProduto.pesquisarProduto(descricao).toString());
                            break;
                        }
                        case 3:{
                            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto").toUpperCase(Locale.ROOT);
                            cProduto.removerProduto(cProduto.pesquisarProduto(descricao));
                            break;
                        }
                        case 4:{
                            String descricao = JOptionPane.showInputDialog("Digite a descrição do produto").toUpperCase(Locale.ROOT);
                            int opcao = Integer.parseInt(JOptionPane.showInputDialog( "O que gostaria de atualizar: \n 1 - preço\n 2-percentual"));
                            switch (opcao){
                                case 1:{
                                    double preco = Double.parseDouble(JOptionPane.showInputDialog("informe o novo preço"));
                                    cProduto.atualizarProduto(new Produto( cProduto.pesquisarProduto(descricao).getDescricao(),preco, cProduto.pesquisarProduto(descricao).getPercentual()));
                                    break;
                                }
                                case 2:{
                                    double percentual = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo percentual"));
                                    cProduto.atualizarProduto(new Produto(cProduto.pesquisarProduto(descricao).getDescricao(), cProduto.pesquisarProduto(descricao).getCusto(),percentual));
                                    break;
                                }

                            }
                            break;
                        }
                        case 5:{
                            JOptionPane.showMessageDialog(null, cProduto.imprimir());
                            break;
                        }
                    }
                    break;
                }
                case 2:{
                    int op3 = Integer.parseInt(JOptionPane.showInputDialog(gerenciarVendas()));
                    switch (op3){
                        case 1:{
                            int numero = cVendas.listarVendas().size();
                            cVendas.cadastrarVenda(numero);
                            break;
                        }
                        case 2:{
                            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da venda"));
                            if(cVendas.pesquisarVenda(numero)!=null){
                                String descricao = JOptionPane.showInputDialog("Digite a descrição do produto").toUpperCase(Locale.ROOT);
                                if(cProduto.pesquisarProduto(descricao)!=null){
                                    int qtd = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade do item."));
                                    cVendas.addItemVenda(numero,cProduto.pesquisarProduto(descricao),qtd);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "produto nao encontrado.");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "ordem de venda nao encontrada.");
                            }
                            break;
                        }
                        case 3:{
                            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da venda"));
                            if(cVendas.pesquisarVenda(numero)!=null){
                                String descricao = JOptionPane.showInputDialog("Digite a descrição do produto").toUpperCase(Locale.ROOT);
                                if(cProduto.pesquisarProduto(descricao)!=null){
                                    cVendas.removeItemVenda(numero,cProduto.pesquisarProduto(descricao));
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "produto nao encontrado.");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "ordem de venda nao encontrada.");
                            }
                            break;
                        }
                        case 4:{
                            int numero = Integer.parseInt(JOptionPane.showInputDialog( "Digite o número da venda que deseja excluir: "));
                            if(cVendas.pesquisarVenda(numero)!= null) {
                                cVendas.deletarVenda(numero);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "ordem de venda nao encontrada.");
                            }
                            break;
                        }
                        case 5:{
                            int numero = Integer.parseInt(JOptionPane.showInputDialog( "Digite o número da venda que deseja ver: "));
                            JOptionPane.showMessageDialog(null, cVendas.imprimirVenda(numero));
                            break;
                        }
                        case 6:{
                            JOptionPane.showMessageDialog(null, cVendas.imprimir());
                            break;
                        }
                        case 7:{
                            int dia = Integer.parseInt(JOptionPane.showInputDialog( "Digite o dia."));
                            int mes = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mes."));
                            int ano = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano."));
                            JOptionPane.showMessageDialog(null, cVendas.vendasNoDia(dia, mes-1, ano));
                            break;
                        }
                        case 8:{
                            int mes = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mes."));
                            int ano = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano."));
                            JOptionPane.showMessageDialog(null, cVendas.vendasNoMes( mes-1, ano));
                            break;
                        }

                    }
                    break;
                }
                case 3:{
                    int dia = Integer.parseInt(JOptionPane.showInputDialog( "Digite o dia."));
                    int mes = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mes."));
                    int ano = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano."));
                    pdfCreatorTeste.gerarRelatorioPDFDiario(cVendas.getDataVendas().vendasNoDia(dia, mes-1, ano));
                    break;
                }
                case 4:{
                    int mes = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mes."));
                    int ano = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano."));
                    pdfCreatorTeste.gerarRelatorioPDFMensal(cVendas.getDataVendas().vendasNoMes(mes-1, ano));
                    break;
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(menu()));

        }

    }

}