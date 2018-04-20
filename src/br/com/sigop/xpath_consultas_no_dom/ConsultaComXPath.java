package br.com.sigop.xpath_consultas_no_dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.sigop.model.Produto;

public class ConsultaComXPath {

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse("src/venda.xml");
		
//		String moeda = document.getDocumentElement().getAttribute("moeda");
//		System.out.println(moeda);
		
//		Element tagFormaDePagamento  = (Element) document.getElementsByTagName("formaDePagamento").item(0);
//		String formaDePagamento = tagFormaDePagamento.getTextContent();
//		System.out.println(formaDePagamento);
		
//		String expressao = "venda/produtos/produto[1]"; // Carrega apenas o primeiro produto
		String expressao = "venda/produtos/produto[contains(nome, 'Caderno')]"; // Carrega apenas os produtos que contem nome carderno
		
		XPath path = XPathFactory.newInstance().newXPath();
		XPathExpression xpathExmpression = path.compile(expressao);
		
		NodeList produtos = (NodeList) xpathExmpression.evaluate(document, XPathConstants.NODESET); 
				
		for(int i = 0; i < produtos.getLength(); i++ ) {
			Element produto = (Element) produtos.item(i);
			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			double valor = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
			
			Produto p = new Produto(nome, valor);
			System.out.println(p);
		}
	}
	
}
