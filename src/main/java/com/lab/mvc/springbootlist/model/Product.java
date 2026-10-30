// Organizo este arquivo no pacote com.lab.mvc.springbootlist.model.
package com.lab.mvc.springbootlist.model;

// Disponibilizo os tipos deste pacote, como Entity, Id, GeneratedValue e Column.
import jakarta.persistence.*;

// Importo a regra com que rejeito texto nulo, vazio ou formado apenas por espaços.
import jakarta.validation.constraints.NotBlank;

// Importo a regra com que limito o tamanho do texto durante a validação.
import jakarta.validation.constraints.Size;

// Importo a anotação com que peço um construtor contendo todos os campos.
import lombok.AllArgsConstructor;

// Importo a anotação com que peço getters, setters e outros métodos repetitivos.
import lombok.Data;

// Importo a anotação com que peço um construtor sem parâmetros.
import lombok.NoArgsConstructor;

// Importo a anotação com que oriento a conversão entre números e texto no Spring.
import org.springframework.format.annotation.NumberFormat;

// Importo BigDecimal para representar o preço com precisão decimal.
import java.math.BigDecimal;

// Marco esta classe como entidade: permito que o Hibernate a mapeie para registros do banco.
@Entity

// Peço ao Lombok que gere Product(). Com new Product(), crio uma ficha sem valores preenchidos.
@NoArgsConstructor

// Peço um construtor com id, nome, marca, paisFabricacao e preco, nessa ordem.
@AllArgsConstructor

// Peço ao Lombok que gere os getters (leitura) e setters (alteração) na compilação.
// No Service, chamo product.setNome(...) para guardar o nome.
// No ResponseDTO, chamo product.getNome() para ler o nome e montar a resposta.
// Não preciso escrever esses métodos aqui, mas continuo precisando chamá-los onde uso os dados.
// Também obtenho toString, equals e hashCode. Se eu declarar um setter equivalente, o Lombok não o substitui.
@Data

// Represento um produto com esta ficha. Com public, permito seu uso por outros pacotes.
public class Product {

    // Marco o campo seguinte como identificador único, a chave primária do produto.
    @Id

    // Escolho IDENTITY para que o banco gere o ID usando sua coluna de identidade/autoincremento.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Guardo o ID como Long, permitindo null antes de o identificador ser gerado.
    private Long id;

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank(message ="O nome do produto é obrigatório")

    // Limito o nome a 60 caracteres quando a validação for executada.
    @Size(max = 60, message ="O nome do produto deve ter no maximo 60 caracteres")

    // Mapeio o campo para nome, uma coluna não nula de comprimento 60.
    // Descrevo o esquema do banco; não impeço uma atribuição Java apenas com Column.
    @Column(name = "nome", nullable = false, length = 60)

    // Guardo o nome; com private, restrinjo o acesso direto e uso os getters/setters do Lombok.
    private String nome;

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank (message = "O nome da marca do produto é obrigatório")

    // Exijo pelo menos 2 caracteres para a marca; aqui não defini um máximo.
    @Size(min = 2, message = "O nome da marca deve ter no minimo 2 caracteres")

    // Mapeio a marca para uma coluna não nula de comprimento 45.
    @Column(name = "marca", nullable = false, length = 45)

    // Guardo a marca do produto.
    private String marca;

    // Exijo texto preenchido quando a validação for executada.
    // Uso message para definir o aviso de erro; não bloqueio uma atribuição Java só com a anotação.
    @NotBlank (message = "O nome do pais onde foi fabricado o produto é obrigatório")

    // Exijo pelo menos 5 caracteres para o país.
    // Preciso alinhar essa regra com o RequestDTO, em que atualmente aceito a partir de 3.
    @Size(min = 5, message = "O nome do pais deve ter no minimo 5 caracteres")

    // Ligo paisFabricacao, no Java, à coluna pais_fabricacao, não nula e de comprimento 60.
    @Column (name = "pais_fabricacao", nullable = false, length = 60)

    // Guardo o país de fabricação.
    private String paisFabricacao;

    // Oriento a formatação e a leitura do preço pelo Spring.
    // Com o padrão explícito #,##0.00, peço duas casas decimais; os símbolos dependem da localidade.
    // Não valido preço positivo com esta anotação; o padrão explícito tem precedência sobre style.
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")

    // Defino preco como DECIMAL(10,2): 10 dígitos no total, com 2 casas decimais.
    // Declaro DEFAULT 0.00 para o banco quando aplicável; isso não inicializa meu campo Java.
    @Column(name = "preco", nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")

    // Guardo o preço decimal. Sem atribuir um valor, começo com null.
    private BigDecimal preco;

// Encerro a declaração deste tipo.
}
