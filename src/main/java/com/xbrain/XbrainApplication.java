package com.xbrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xbrain.entities.Produto;

@SpringBootApplication
public class XbrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbrainApplication.class, args);
		System.out.println("Servidor iniciado");

		Produto produto = new Produto(1, "tenis", 20.0, 1);
		System.out.println(produto);
		produto.setId(2);
		System.out.println(produto.getId());
		System.out.println(produto);




	}

}
