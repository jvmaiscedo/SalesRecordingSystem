package model;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class PdfCreator {
    private String titulo;
    private ArrayList<Venda> vendas;
    private Document document;


    public PdfCreator() {
    }

    public void gerarRelatorioPDFDiario(ArrayList<Venda> vendas) {
        this.vendas = vendas;
        this.document = new Document(PageSize.A4, 50, 50, 50, 50);
        String data = vendas.get(0).getDataRelatorio();
        this.titulo = "Relatório-Dia-"+data.substring(0,2);
        String descricao = "RELATÓRIO DO DIA ";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(titulo + ".pdf"));

            document.open();
            adicionarParagrafoTitulo(data, descricao);
            gerarCorpo();
            pularLinha();
            document.close();

            System.out.println("O relatório foi gerado com sucesso!");
        } catch (DocumentException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
    public void gerarRelatorioPDFMensal(ArrayList<Venda> vendas) {
        this.vendas = vendas;
        this.document = new Document(PageSize.A4, 50, 50, 50, 50);
        String data = vendas.get(0).getDataRelatorio().substring(3,10);
        this.titulo = "Relatório-Mês-"+data.substring(0,2);
        String descricao = "RELATÓRIO DO MÊS ";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(titulo + ".pdf"));

            document.open();
            adicionarParagrafoTitulo(data, descricao);
            gerarCorpo();
            pularLinha();
            document.close();

            System.out.println("O relatório foi gerado com sucesso!");
        } catch (DocumentException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
    public void gerarCorpo() {
        double valorTotal=0;
        pularLinha();
        for(int i=0; i<vendas.size(); i++) {
            document.add(identificaçãodaVenda(i));
            PdfPTable tableProdutos = this.criarTabelaComCabecalho();
            this.adicionarProdutosATabela(tableProdutos,i);
            this.document.add(tableProdutos);
            document.add(valorTotalDaVenda(i));
            pularLinha();
            adicionarQuebraDeSessao();
            pularLinha();
            valorTotal+=vendas.get(i).getValorTotal();
        }
        document.add(informativoTotalDasVendas(valorTotal));
    }

    private void pularLinha() {
        this.document.add(new Paragraph(" "));
    }
    private void adicionarQuebraDeSessao() {
        Paragraph paragrafoSessao = new Paragraph("______________________________________________________________");
        paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
        this.document.add(paragrafoSessao);
    }
    private void adicionarParagrafoTitulo(String data, String descricao) {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        Chunk cTitulo = new Chunk(descricao+data, new Font(Font.TIMES_ROMAN, 16));
        cTitulo.setFont(new Font(Font.COURIER, 24));
        cTitulo.setBackground(Color.lightGray, 2, 2, 2, 2);
        paragrafoTitulo.add(cTitulo);
        document.add(paragrafoTitulo);
    }
    private PdfPTable identificaçãodaVenda(int i){
        PdfPTable tableTitulo = new PdfPTable(2);
        tableTitulo.setWidthPercentage(98);
        tableTitulo.setWidths(new float[] { 2f, 2f });

        PdfPCell celulaNumero = new PdfPCell(new Phrase("VENDA Nº"+vendas.get(i).getId()));
        celulaNumero.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaNumero.setBackgroundColor(Color.LIGHT_GRAY);
        tableTitulo.addCell(celulaNumero);
        PdfPCell celulaData = new PdfPCell(new Phrase("DATA: "+vendas.get(i).getDataRelatorio()));
        celulaData.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaData.setBackgroundColor(Color.LIGHT_GRAY);
        tableTitulo.addCell(celulaData);
        return tableTitulo;
    }
    private PdfPTable valorTotalDaVenda(int i ){
        PdfPTable tableTotal = new PdfPTable(1);
        tableTotal.setWidthPercentage(98);
        PdfPCell celularTotal = new PdfPCell(new Phrase("VALOR TOTAL DA VENDA: R$"+vendas.get(i).getValorTotal()));
        celularTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celularTotal.setBackgroundColor(Color.LIGHT_GRAY);
        tableTotal.addCell(celularTotal);
        return tableTotal;

    }
    private PdfPTable criarTabelaComCabecalho() {
        // tabela com 4 colunas
        PdfPTable tableProdutos = new PdfPTable(4);
        tableProdutos.setWidthPercentage(98);
        tableProdutos.setWidths(new float[] { 2f, 1f, 1f, 1f });

        PdfPCell celulaTitulo = new PdfPCell(new Phrase("PRODUTO"));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableProdutos.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("QUANTIDADE"));
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableProdutos.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("VALOR UNI."));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableProdutos.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("SUBTOTAL"));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableProdutos.addCell(celulaTitulo);

        return tableProdutos;
    }


    private void adicionarProdutosATabela(PdfPTable tableProdutos, int i) {
            for (int j=0; j<vendas.get(i).getItens().size();j++){

                PdfPCell celulaNome = new PdfPCell(new Phrase(vendas.get(i).getItens().get(j).getProduto().getDescricao()));
                PdfPCell celulaQuantidade = new PdfPCell(new Phrase(String.valueOf(vendas.get(i).getItens().get(j).getQuantidade())));
                celulaQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celulaValor = new PdfPCell(new Phrase("R$ " + String.valueOf(vendas.get(i).getItens().get(j).getProduto().getPreco())));
                celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celulaTotalUnit = new PdfPCell(new Phrase("R$ " + String.valueOf(vendas.get(i).getItens().get(j).getSubTotal())));
                celulaTotalUnit.setHorizontalAlignment(Element.ALIGN_CENTER);

                tableProdutos.addCell(celulaNome);
                tableProdutos.addCell(celulaQuantidade);
                tableProdutos.addCell(celulaValor);
                tableProdutos.addCell(celulaTotalUnit);

        }

    }
    private PdfPTable informativoTotalDasVendas(double valorFinal){
        PdfPTable tableTotal = new PdfPTable(1);
        tableTotal.setWidthPercentage(100);
        tableTotal.setWidths(new float[] { 4f });

        PdfPCell celulaTotal = new PdfPCell(new Phrase("VALOR TOTAL FINAL: R$"+valorFinal));
        celulaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTotal.setBackgroundColor(Color.LIGHT_GRAY);
        tableTotal.addCell(celulaTotal);

        return tableTotal;
    }

}
