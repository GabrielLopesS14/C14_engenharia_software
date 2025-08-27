package org.example;

import java.util.ArrayList;
import java.util.List;

public class Fila<T> {
    private List<T> elementos = new ArrayList<>();
    private int tamanho = 0;

    // Retorna o tamanho da fila
    public int size() {
        return tamanho;
    }

    // Verifica se a fila está vazia
    public boolean filaVazia() {
        return tamanho == 0;
    }

    // Adiciona um elemento no final da fila (enfileirar)
    public void enfileirar(T elem) {
        elementos.add(elem);
        tamanho++;
    }

    // Remove o elemento do início da fila (removerFila)
    public T removerFila() {
        if (filaVazia()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        T elem = elementos.remove(0);
        tamanho--;
        return elem;
    }
}

