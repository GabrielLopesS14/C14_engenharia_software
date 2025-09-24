package org.example.quiz;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FutebolQuizMockTest {

    @Test
    void testFazerPerguntaComRespostaCorreta() {
        //Instanciando a classe real de teste
        FutebolQuiz quiz = new FutebolQuiz();

        //Mockando a dependência (classe Pergunta)
        Pergunta perguntaMock = mock(Pergunta.class);

        //Programando o mock para ter um comportamento definido
        //Quando 'getRespostaCorreta' for chamado no mock, ele deve retornar 0
        when(perguntaMock.getRespostaCorreta()).thenReturn(0);

        //Chamando o método real da classe FutebolQuiz
        //Passando a resposta do usuário (0) e objeto mockado
        //Agora, o método 'fazerPergunta' usa o mock para obter a resposta correta
        boolean resultado = quiz.fazerPergunta(0, perguntaMock);

        //Verificando o resultado esperado que seria true para uma resposta correta
        assertTrue(resultado, "A resposta correta deve retornar true");
    }

    @Test
    void testFazerPerguntaComRespostaIncorreta() {
        //Instanciando a classe real de teste
        FutebolQuiz quiz = new FutebolQuiz();

        //Mockando a dependência (classe Pergunta)
        Pergunta perguntaMock = mock(Pergunta.class);

        //Programando o mock para ter um comportamento definido
        //A resposta correta segue sendo 0
        when(perguntaMock.getRespostaCorreta()).thenReturn(0);

        //Chamando o método real da classe FutebolQuiz com a resposta errada
        boolean resultado = quiz.fazerPergunta(1, perguntaMock);

        //Verificando o resultado esperado que seria false para uma resposta incorreta
        assertFalse(resultado, "A resposta incorreta deve retornar false");
    }
}