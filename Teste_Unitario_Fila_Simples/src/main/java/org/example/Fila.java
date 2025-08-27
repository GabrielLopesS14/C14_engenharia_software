package org.example;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ArrayList;

public class Fila<T>{
    private List<T> elementos = new ArrayList<T>();
    private int tamanho = 0;

    public int size() {
        return tamanho;
    }

    public boolean filaVazia() {
        return (tamanho == 0);
    }

    public void enfileirar(T elem) {
        elementos.add(elem);
        tamanho++;
    }

    public T removerFila() throws EmptyStackException {
        if (filaVazia())
            throw new EmptyStackException();
        T elem = elementos.remove(0);
        tamanho--;
        return elem;
    }
}

