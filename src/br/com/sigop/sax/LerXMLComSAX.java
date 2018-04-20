package br.com.sigop.sax;

import java.io.FileInputStream;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import br.com.sigop.sax.handler.ProdutoHandler;

public class LerXMLComSAX {
	
	public static void main(String[] args) throws Exception {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		ProdutoHandler handler = new ProdutoHandler();
		reader.setContentHandler(handler);
		
		InputStream is = new FileInputStream("src/venda.xml");
		InputSource arquivo = new InputSource(is);
		
		reader.parse(arquivo);
		
		System.out.println(handler.getProdutos());
	}

}
