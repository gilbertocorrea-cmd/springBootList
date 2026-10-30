// Organizo este arquivo no pacote com.lab.mvc.springbootlist.dto.
package com.lab.mvc.springbootlist.dto;

// Importo a entidade que representa o produto na memória e no mapeamento do banco.
import com.lab.mvc.springbootlist.model.Product;

// Importo BigDecimal para representar o preço com precisão decimal.
import java.math.BigDecimal;

// Defino minha ficha de saída com os dados que entrego para exibição.
// Com record, obtenho o construtor completo e acessores como nome() e preco().
// Aqui transporto valores; não consulto nem salvo no banco.
public record ProductResponseDTO(

    // Incluo o identificador do produto na resposta.
    Long id,

    // Incluo o nome do produto na resposta.
    String nome,

    // Incluo a marca do produto na resposta.
    String marca,

    // Incluo o país de fabricação na resposta.
    String paisFabricacao,

    // Incluo o preço decimal na resposta.
    BigDecimal preco

// Encerro os componentes e abro o corpo para declarar meu construtor adicional.
) {

    // Recebo um Product já obtido e monto a resposta a partir dele.
    // Uso new ProductResponseDTO(produto), passando um produto não nulo.
    public ProductResponseDTO(Product product) {

        // Chamo o construtor principal deste mesmo record com os cinco valores na ordem declarada.
        this(

            // Chamo o getter gerado pelo Lombok para ler o ID e copiá-lo para a resposta.
            product.getId(),

            // Chamo o getter para ler o nome do Product; não estou criando o método aqui.
            product.getNome(),

            // Leio a marca da entidade e a entrego ao componente marca do DTO.
            product.getMarca(),

            // Leio o país da entidade e o entrego ao componente paisFabricacao do DTO.
            product.getPaisFabricacao(),

            // Leio o preço da entidade e o entrego ao componente preco do DTO.
            product.getPreco()

        // Termino a chamada ao construtor principal.
        );

    // Encerro este bloco.
    }

// Encerro a declaração deste tipo.
}
