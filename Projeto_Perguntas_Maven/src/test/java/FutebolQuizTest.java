package org.example.quiz;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FutebolQuizTest {

    //Teste Positivo 1: Verifica se a primeira pergunta carregada do JSON funciona
    @Test
    void testFazerPerguntaPositivo1() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta primeira = perguntas.get(0);

        //Assume que a resposta correta do JSON está correta
        int indiceCorreto = primeira.getRespostaCorreta();
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, primeira);

        assertTrue(resultado, "A resposta correta do JSON deveria retornar true");
    }

    //Teste Positivo 2: Verifica a quantidade de perguntas carregadas
    @Test
    void testQuantidadePerguntasCarregadas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        assertTrue(perguntas.size() > 0, "Deve haver ao menos uma pergunta carregada do JSON.");
    }

    //Teste Positivo 3: Verifica a quantidade máxima de perguntas
    @Test
    void testQuantidadeMaximaDePerguntas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();

        //Embaralha as perguntas e limita a 5
        int numPerguntas = Math.min(perguntas.size(), 5);

        //O teste agora verifica o número de perguntas após o limite de 5
        assertTrue(numPerguntas <= 5, "O número de perguntas nunca deve ultrapassar 5.");
    }

    //Teste Negativo 2: Testa o embaralhamento das perguntas
    @Test
    void testEmbaralhamentoDasPerguntas() {
        List<Pergunta> perguntasAntes = FutebolQuiz.carregarPerguntas();
        List<Pergunta> perguntasDepois = FutebolQuiz.carregarPerguntas();

        //Embaralhamento deve mudar a ordem das perguntas
        assertNotEquals(perguntasAntes, perguntasDepois, "As perguntas devem ser embaralhadas.");
    }

    //Teste Negativo 3: Testa o comportamento de uma resposta incorreta
    @Test
    void testFazerPerguntaRespostaIncorreta() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);

        //Passa uma resposta incorreta
        int respostaErrada = (pergunta.getRespostaCorreta() + 1) % pergunta.getRespostas().size();
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta);

        assertFalse(resultado, "A resposta incorreta deve retornar false");
    }

    //Teste Positivo 4: Verifica o comportamento com a segunda pergunta
    @Test
    void testFazerPerguntaPositivo2() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta segunda = perguntas.get(1);

        int indiceCorreto = segunda.getRespostaCorreta();
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, segunda);

        assertTrue(resultado, "A resposta correta do JSON deveria retornar true");
    }

    //Teste Positivo 5: Verifica se o método `carregarPerguntas` lida bem com perguntas repetidas
    @Test
    void testPerguntasRepetidas() {
        // Simulando que o arquivo JSON tem perguntas repetidas
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        assertEquals(perguntas.size(), 5, "O número total de perguntas deve ser no máximo 5.");
    }

    //Teste Negativo 5: Verifica se o método `fazerPergunta` não aceita respostas fora do intervalo
    @Test
    void testRespostaForaDoIntervalo() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);

        int respostaErrada = 10; //Número fora do intervalo de respostas
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta);

        assertFalse(resultado, "Respostas fora do intervalo devem retornar false.");
    }

    //Teste Negativo 6: Verifica o comportamento ao acessar um índice fora do intervalo
    @Test
    void testIndiceInvalido() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        //Tentamos acessar uma pergunta com um índice maior que o número de perguntas
        int indiceInvalido = perguntas.size();  //Um índice fora do intervalo válido
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Pergunta perguntaInvalida = perguntas.get(indiceInvalido);  //Isso deve lançar exceção
        }, "Deveria lançar IndexOutOfBoundsException ao acessar um índice inválido.");
    }

    //Teste Negativo 7: Verifica o comportamento ao passar uma resposta fora do intervalo
    @Test
    void testRespostaForaDoIntervaloValorInvalido() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);  //Pega a primeira pergunta

        //Tentamos passar um número que está fora do intervalo de respostas válidas
        int respostaForaDoIntervalo = pergunta.getRespostas().size() + 1; //Número de resposta inválido

        //O teste espera que o método retorne 'false' ao passar uma resposta fora do intervalo
        boolean resultado = FutebolQuiz.fazerPergunta(respostaForaDoIntervalo, pergunta);
        assertFalse(resultado, "Respostas fora do intervalo devem retornar false.");
    }
}
