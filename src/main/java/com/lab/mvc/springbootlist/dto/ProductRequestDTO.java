// Organizo este arquivo no pacote com.lab.mvc.springbootlist.dto.
package com.lab.mvc.springbootlist.dto;

// Disponibilizo as regras de validação, como NotBlank, Size, NotNull e DecimalMin.
import jakarta.validation.constraints.*;

// Importo BigDecimal para representar o preço com precisão decimal.
import java.math.BigDecimal;

// Defino minha ficha de entrada para cadastro e edição.
// Com record, obtenho construtor, acessores como nome(), equals, hashCode e toString.
// Forneço os componentes na criação e não tenho setters para trocá-los depois.
// Aciono as regras com validação, como o @Valid do controlador.
public record ProductRequestDTO(

    // Recebo o ID, que pode ser null no cadastro de um produto novo.
    Long id,

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank(message = " O nome do Produto é obrigatório.")

    // Defino máximo de 60 caracteres para o componente logo abaixo.
    @Size(max = 60, message = "O nome do produto  deve ter no maximo 60 caracteres. ")

    // Recebo o nome e depois o consulto com dto.nome().
    String nome,

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank(message = "O nome do produto é obrigatório")

    // Defino máximo de 60 caracteres para o componente logo abaixo.
    @Size(max = 60, message = "O nome do produto deve ter no maximo 60 caracteres. ")

    // Recebo a marca e a consulto com dto.marca().
    // Preciso revisar as regras acima: a mensagem fala em nome e o limite está em 60.
    // No tutorial, uso min = 2 e max = 45 para marca; minha entidade prevê coluna de 45.
    String marca,

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank(message = "O nome do pais onde foi fabricado o produto é obrigatório")

    // Aceito país entre 3 e 60 caracteres na validação do DTO.
    // Preciso alinhar com Product, onde atualmente exijo pelo menos 5.
    @Size(min = 3, max = 60, message = "O pais deve ter entre 3 e 60 caracteres.")

    // Recebo o país e o consulto com dto.paisFabricacao().
    String paisFabricacao,

    // Exijo que o preço não seja null, isto é, que exista um valor.
    @NotNull(message = "O preço é obrigatório. ")

    // Defino 0.01 como menor preço aceito, inclusive.
    // Combino esta regra com NotNull porque DecimalMin, sozinha, aceita null.
    @DecimalMin(value = "0.01", message = "O preço deve ser superior a zero.")

    // Recebo o preço decimal e o consulto com dto.preco().
    BigDecimal preco

// Encerro a lista de componentes e abro o corpo do record, sem métodos adicionais.
){

// Encerro a declaração deste tipo.
}
