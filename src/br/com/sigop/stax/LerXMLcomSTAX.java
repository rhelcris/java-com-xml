package br.com.sigop.stax;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import br.com.sigop.model.Produto;

public class LerXMLcomSTAX {

	public static void main(String[] args) throws Exception {

		InputStream is = new FileInputStream("src/venda.xml");
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		XMLEventReader eventos = factory.createXMLEventReader(is);

		List<Produto> produtos = carregarListaDeProdutos(eventos);
		System.out.println(produtos);
	}

	private static List<Produto> carregarListaDeProdutos(XMLEventReader eventos) throws Exception {

		Produto produto = new Produto();
		List<Produto> produtos = new ArrayList<>();

		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();
			
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {
				produto = new Produto();
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();

				String nome = evento.asCharacters().getData();
				produto.setNome(nome);
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {
				evento = eventos.nextEvent();

				produto.setValor(Double.parseDouble(evento.asCharacters().getData()));
			} else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {
				produtos.add(produto);
			}
		}
		
		return produtos;
	}

}
