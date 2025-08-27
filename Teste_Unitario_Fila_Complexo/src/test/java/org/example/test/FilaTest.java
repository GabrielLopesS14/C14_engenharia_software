package org.example.test;

import org.example.Fila;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FilaTest {
    @Test
    public void testEnfileirar() {
        Fila<Integer> fila = new Fila<>();
        fila.enfileirar(10);
        assertEquals(1, fila.size());
    }

    @Test
    public void testRemoverFila() {
        Fila<Integer> fila = new Fila<>();
        fila.enfileirar(10);
        fila.enfileirar(20);

        int removed = fila.removerFila();
        assertEquals(10, removed);
        assertEquals(1, fila.size());
    }

    @Test
    public void testFilaVazia() {
        Fila<Integer> fila = new Fila<>();
        assertTrue(fila.filaVazia());

        fila.enfileirar(10);
        assertFalse(fila.filaVazia());
    }

    @Test
    public void testRemoverFilaQuandoVazia() {
        Fila<Integer> fila = new Fila<>();
        assertThrows(IllegalStateException.class, () -> fila.removerFila());
    }

    @Test
    public void testSize() {
        Fila<Integer> fila = new Fila<>();
        assertEquals(0, fila.size());

        fila.enfileirar(10);
        assertEquals(1, fila.size());

        fila.enfileirar(20);
        assertEquals(2, fila.size());
    }
}

