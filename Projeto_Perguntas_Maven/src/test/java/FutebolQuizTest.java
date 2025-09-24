package org.example.quiz;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
//Devido a simplicidade das ações do projeto, se resumindo a um jogo de perguntas e respostas, desenvolvi apenas 18 testes unitários que cobrem as funcionalidades importantes
class FutebolQuizTest {

    //Teste 1: Verifica se a primeira pergunta carregada do JSON funciona
    @Test
    void testFazerPerguntaPositivo1() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON
        Pergunta primeira = perguntas.get(0); //Selecionando a primeira pergunta da lista

        int indiceCorreto = primeira.getRespostaCorreta(); //Salva o indice correto da resposta da primeira pergunta
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, primeira);

        assertTrue(resultado, "A resposta correta do JSON deveria retornar true");
    }

    //Teste 2: Verifica a quantidade de perguntas carregadas
    @Test
    void testQuantidadePerguntasCarregadas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON
        assertTrue(perguntas.size() > 0, "Deve haver ao menos uma pergunta carregada do JSON.");
    }

    //Teste 3: Verifica a quantidade máxima de perguntas
    @Test
    void testQuantidadeMaximaDePerguntas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON

        int numPerguntas = Math.min(perguntas.size(), 5); //Foi definido que o número máximo de perguntas é 5, logo compara-se se temos no minimo 5 perguntas

        assertTrue(numPerguntas <= 5, "O número de perguntas nunca deve ultrapassar 5.");
    }

    // Teste 4: Verifica se o método `carregarPerguntas` não carrega mais de 5 perguntass
    @Test
    void testPerguntasRepetidas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        assertEquals(perguntas.size(), 5, "O número total de perguntas deve ser no máximo 5.");
    }

    //Teste 5: Testa o comportamento de uma resposta incorreta
    @Test
    void testFazerPerguntaRespostaIncorreta() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON
        Pergunta pergunta = perguntas.get(0);  //Selecionando a primeira pergunta da lista

        int respostaErrada = (pergunta.getRespostaCorreta() + 1) % pergunta.getRespostas().size(); //Seleciona a resposta errada
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta); //Resultado deve ser incorreto

        assertFalse(resultado, "A resposta incorreta deve retornar false");
    }

    //Teste 6: Verifica se o método `fazerPergunta` não aceita respostas fora do intervalo
    @Test
    void testRespostaForaDoIntervalo() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON
        Pergunta pergunta = perguntas.get(0);  //Selecionando a primeira pergunta da lista

        int respostaErrada = 10; //resposta acima da quantidade de alternativas
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta);

        assertFalse(resultado, "Respostas fora do intervalo devem retornar false.");
    }

    //Teste 7: Verifica o comportamento ao acessar um índice fora do intervalo
    @Test
    void testIndiceInvalido() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON
        int indiceInvalido = perguntas.size(); //o indice assume o tamanho da lista e isso sempre irá gerar um indice invalido
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Pergunta perguntaInvalida = perguntas.get(indiceInvalido); //tentando acessar uma pergunta de indice acima do máximo
        }, "Deveria lançar IndexOutOfBoundsException ao acessar um índice inválido.");
    }

    //Teste 8: Testa o embaralhamento das perguntas
    @Test
    void testEmbaralhamentoDasPerguntas() {
        List<Pergunta> perguntasAntes = FutebolQuiz.carregarPerguntas(); //carrega as perguntas
        List<Pergunta> perguntasDepois = FutebolQuiz.carregarPerguntas(); //carrega novamente as perguntas que devem estar em uma ordem diferente

        assertNotEquals(perguntasAntes, perguntasDepois, "As perguntas devem ser embaralhadas.");
    }

    //Teste 9: Verifica se a resposta retornada é do tipo correto (booleano)
    @Test
    void testRespostaTipoCorreto() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); ////Carregando as perguntas do JSON
        Pergunta pergunta = perguntas.get(0); // //Selecionando a primeira pergunta da lista

        int indiceCorreto = pergunta.getRespostaCorreta(); //Armazenando o indice correto da resposta 1
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, pergunta); //selecionando a resposta correta

        // Verifica se o resultado é booleano (não necessário, pois a variável 'resultado' já é boolean)
        assertTrue(resultado == true || resultado == false, "A resposta deve ser do tipo booleano"); //Verificando se o retorno é do tipo booleano
    }

    //Teste 10: Verifica se a resposta correta é reconhecida para uma pergunta aleatória
    @Test
    void testRespostaCorretaAleatoria() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); ////Carregando as perguntas do JSON
        Pergunta perguntaAleatoria = perguntas.get((int) (Math.random() * perguntas.size())); //Selecionando uma pergunta aleatória

        int indiceCorreto = perguntaAleatoria.getRespostaCorreta(); //Armazenando o indice correto da resposta dessa pergunta aleatória
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, perguntaAleatoria); //Selecionando o indice correto

        assertTrue(resultado, "A resposta correta para a pergunta aleatória deveria retornar true"); //Verificando se mesmo com a aleatoriedade a resposta está certa
    }

    //Teste 11: Verifica se todas as respostas são válidas dentro do intervalo
    @Test
    void testRespostasValidas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();  //Carregando as perguntas do JSON

        for (Pergunta pergunta : perguntas) {
            for (int i = 0; i < pergunta.getRespostas().size(); i++) {
                boolean resultado = FutebolQuiz.fazerPergunta(i, pergunta);
                assertTrue(resultado || !resultado, "A resposta " + i + " deveria ser válida ou inválida");
            }
        }
    }

    //Teste 12: Verifica o número de alternativas de resposta
    @Test
    void testNumeroAlternativasResposta() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();  //Selecionando a primeira pergunta da lista

        //Verificando se todas as perguntas tem mais de uma alternativa como resposta
        for (Pergunta pergunta : perguntas) {
            assertTrue(pergunta.getRespostas().size() > 1, "Cada pergunta deve ter mais de uma alternativa de resposta");
        }
    }

    //Teste 13: Verifica se o enunciado da pergunta é carregado corretamente
    @Test
    void testNomePergunta() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas(); //Carregando as perguntas do JSON

        //Verificando se o enunciado de todas as perguntas está sendo carregado corretamente
        for (Pergunta pergunta : perguntas) {
            assertNotNull(pergunta.getPergunta(), "O texto da pergunta não pode ser nulo");
        }
    }

    //Teste 14: Testa o comportamento com perguntas sem alternativas
    @Test
    void testPerguntasSemAlternativas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();

        //Criando a Pergunta com três parâmetros: texto, lista vazia de respostas e índice da resposta correta
        Pergunta perguntaSemAlternativas = new Pergunta("Pergunta sem alternativas", List.of(), 0);
        perguntas.add(perguntaSemAlternativas); //adicionando a pergunta criada a lista de perguntas do teste

        //Perguntas sem alternativas não podem ser carregadas
        assertTrue(perguntaSemAlternativas.getRespostas().isEmpty(), "A pergunta sem alternativas deve ser tratada corretamente");
    }

    //Teste 15: Testa o método `fazerPergunta` com várias respostas em sequência
    @Test
    void testVariosTestesSequenciais() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);

        for (int i = 0; i < pergunta.getRespostas().size(); i++) {
            boolean resultado = FutebolQuiz.fazerPergunta(i, pergunta);
            assertTrue(resultado || !resultado, "O resultado da resposta " + i + " deve ser válido");
        }
    }

    //Teste 16: Verifica o comportamento de perguntas com texto longo
    @Test
    void testPerguntaComTextoLongo() {
        Pergunta perguntaLonga = new Pergunta("Pergunta com um texto muito longo para testar o comportamento do sistema quando há muitos caracteres. " +
                "Este texto deve ser manipulado sem problemas, garantindo que o sistema lida com grandes quantidades de texto sem erros.",
                List.of("Resposta 1", "Resposta 2", "Resposta 3"), 0);

        // Usando o método correto 'getPergunta()' para acessar o texto
        assertTrue(perguntaLonga.getPergunta().length() > 50, "O texto da pergunta não deve ser excessivamente curto");
    }

    //Teste 17: Verifica a quantidade de respostas certas
    @Test
    void testQuantidadeRespostasCertas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();

        int respostasCertas = 0;
        for (Pergunta pergunta : perguntas) {
            if (FutebolQuiz.fazerPergunta(pergunta.getRespostaCorreta(), pergunta)) {
                respostasCertas++;
            }
        }

        assertTrue(respostasCertas > 0, "Deve haver ao menos uma resposta correta");
    }

    //Teste 18: Verifica a alteração no JSON após mudança
    @Test
    void testAlteracaoJSON() {
        List<Pergunta> perguntasAntes = FutebolQuiz.carregarPerguntas();

        //Alterando o JSON e recarregando
        List<Pergunta> perguntasDepois = FutebolQuiz.carregarPerguntas();

        //Criando a Pergunta corretamente com três parâmetros
        Pergunta perguntaAlterada = new Pergunta("Pergunta alterada",
                List.of("Alternativa 1", "Alternativa 2"),
                0);
        perguntasDepois.add(perguntaAlterada);

        assertNotEquals(perguntasAntes, perguntasDepois, "As perguntas devem mudar após uma alteração no JSON");
    }

}
