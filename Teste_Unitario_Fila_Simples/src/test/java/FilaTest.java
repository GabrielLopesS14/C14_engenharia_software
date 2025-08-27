package org.example.test;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.example.Fila;
import java.util.EmptyStackException;
public class FilaTest {
    @Test
    public void testFilaVazia()
    {
        Fila<Integer> filaInteiros = new Fila<Integer>();
        boolean vazia = filaInteiros.filaVazia();
        assertTrue(vazia);
    }
    @Test
    public void testEnfileirar() {
        Fila<Integer> filaInteiros = new Fila<Integer>();
        filaInteiros.enfileirar(1);
        assertFalse(filaInteiros.filaVazia());
        assertEquals(1, filaInteiros.size());
    }
    @Test
    public void testRemoverFila() {
        Fila<Integer> filaInteiros = new Fila<Integer>();
        filaInteiros.enfileirar(1);
        filaInteiros.enfileirar(2);
        assertEquals(Integer.valueOf(1), filaInteiros.removerFila());
        assertEquals(1, filaInteiros.size());
    }
    @Test(expected = EmptyStackException.class)
    public void testRemoverFilaVazia() {
        Fila<Integer> filaInteiros = new Fila<Integer>();
        filaInteiros.removerFila(); // Deveria lançar a exceção
    }
}
