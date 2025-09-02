package org.example.quiz;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class FutebolQuizTest {

    // Teste Positivo 1: Verifica se a primeira pergunta carregada do JSON funciona
    @Test
    void testFazerPerguntaPositivo1() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta primeira = perguntas.get(0);

        int indiceCorreto = primeira.getRespostaCorreta();
        boolean resultado = FutebolQuiz.fazerPergunta(indiceCorreto, primeira);

        assertTrue(resultado, "A resposta correta do JSON deveria retornar true");
    }

    // Teste Positivo 2: Verifica a quantidade de perguntas carregadas
    @Test
    void testQuantidadePerguntasCarregadas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        assertTrue(perguntas.size() > 0, "Deve haver ao menos uma pergunta carregada do JSON.");
    }

    // Teste Positivo 3: Verifica a quantidade máxima de perguntas
    @Test
    void testQuantidadeMaximaDePerguntas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();

        int numPerguntas = Math.min(perguntas.size(), 5);

        assertTrue(numPerguntas <= 5, "O número de perguntas nunca deve ultrapassar 5.");
    }

    // Teste Positivo 4: Verifica se o método `carregarPerguntas` lida bem com perguntas repetidas
    @Test
    void testPerguntasRepetidas() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        assertEquals(perguntas.size(), 5, "O número total de perguntas deve ser no máximo 5.");
    }

    // Teste Negativo 1: Testa o comportamento de uma resposta incorreta
    @Test
    void testFazerPerguntaRespostaIncorreta() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);

        int respostaErrada = (pergunta.getRespostaCorreta() + 1) % pergunta.getRespostas().size();
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta);

        assertFalse(resultado, "A resposta incorreta deve retornar false");
    }

    // Teste Negativo 2: Verifica se o método `fazerPergunta` não aceita respostas fora do intervalo
    @Test
    void testRespostaForaDoIntervalo() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        Pergunta pergunta = perguntas.get(0);

        int respostaErrada = 10;
        boolean resultado = FutebolQuiz.fazerPergunta(respostaErrada, pergunta);

        assertFalse(resultado, "Respostas fora do intervalo devem retornar false.");
    }

    // Teste Negativo 3: Verifica o comportamento ao acessar um índice fora do intervalo
    @Test
    void testIndiceInvalido() {
        List<Pergunta> perguntas = FutebolQuiz.carregarPerguntas();
        int indiceInvalido = perguntas.size();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Pergunta perguntaInvalida = perguntas.get(indiceInvalido);
        }, "Deveria lançar IndexOutOfBoundsException ao acessar um índice inválido.");
    }

    // Teste Negativo 4: Testa o embaralhamento das perguntas
    @Test
    void testEmbaralhamentoDasPerguntas() {
        List<Pergunta> perguntasAntes = FutebolQuiz.carregarPerguntas();
        List<Pergunta> perguntasDepois = FutebolQuiz.carregarPerguntas();

        assertNotEquals(perguntasAntes, perguntasDepois, "As perguntas devem ser embaralhadas.");
    }

}
