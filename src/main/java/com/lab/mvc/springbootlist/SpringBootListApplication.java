// Organizo a classe principal no pacote raiz do projeto.
// Mantenho controller, service e repository abaixo deste pacote para a busca padrão de componentes.
package com.lab.mvc.springbootlist;

// Importo a classe que uso para iniciar a aplicação Spring Boot.
import org.springframework.boot.SpringApplication;
// Importo a anotação com que marco o ponto principal de configuração da aplicação.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Reúno aqui três recursos do Spring:
// 1. Com SpringBootConfiguration, marco esta classe como fonte de configuração.
// 2. Com EnableAutoConfiguration, peço configurações automáticas conforme as bibliotecas
//    disponíveis, minhas propriedades e os componentes que já defini.
// 3. Com ComponentScan, procuro componentes neste pacote e nos seus subpacotes.
// Assim, encontro classes como ProductController (@Controller) e ProductService (@Service).
// Para os repositórios JPA, conto também com a configuração automática específica do Spring Data.
@SpringBootApplication
// Declaro a classe principal: penso nela como o botão de ligar o sistema.
// Com public, permito o acesso à classe por outros pacotes.
public class SpringBootListApplication {
    // Defino o ponto de entrada usado quando inicio esta classe pelo Java.
    // Com public, deixo o método acessível; com static, não preciso criar um objeto desta classe.
    // Com void, indico que o método não devolve um valor.
    // Recebo em args uma lista de textos passados na execução, como --server.port=8081.
    public static void main(String[] args) {
        // Inicio o Spring Boot usando esta classe como referência de configuração.
        // Com .class, passo a descrição da classe; não estou criando um objeto com new.
        // Encaminho args para que o Spring também considere as opções da execução.
        // Nesta inicialização, carrego configurações como application.properties e crio os objetos
        // que o Spring gerencia, conectando dependências como Controller -> Service -> Repository.
        // Neste projeto web, também inicio o servidor embutido conforme a configuração disponível.
        // Não executo o cadastro aqui: preparo o sistema para receber as requisições do navegador.
        // A inicialização pode falhar se alguma dependência ou configuração necessária estiver incorreta.
        SpringApplication.run(SpringBootListApplication.class, args);
    // Encerro o método main.
    }
// Encerro a classe principal.
}
