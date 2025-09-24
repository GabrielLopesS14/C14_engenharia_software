package org.example.quiz;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;

public class FutebolQuiz {

    private static final String PERGUNTAS_JSON = "perguntas.json";

    public static void main(String[] args) {
        List<Pergunta> perguntas = carregarPerguntas();
        int pontos = jogar(perguntas);
        exibirResultado(pontos, perguntas.size());
    }

    public static int jogar(List<Pergunta> perguntas) {
        int pontos = 0;

        //Embaralhar perguntas
        Collections.shuffle(perguntas);

        //Limitar o número de perguntas a 5 ou o número de perguntas disponíveis
        int numPerguntas = Math.min(perguntas.size(), 5);  //Garante que nunca será mais de 5

        for (int i = 0; i < numPerguntas; i++) {
            Pergunta pergunta = perguntas.get(i);
            int respostaUsuario = fazerPerguntaInterativo(pergunta);
            if (respostaUsuario == pergunta.getRespostaCorreta()) {
                pontos++;
            }
        }
        return pontos;
    }

    //Versão com Scanner para interação com o usuário (execução normal)
    public static int fazerPerguntaInterativo(Pergunta pergunta) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + pergunta.getPergunta());
        exibirRespostas(pergunta.getRespostas());

        //Solicita a resposta do usuário
        int respostaUsuario;
        while (true) {
            System.out.print("Digite o número da sua resposta: ");
            if (scanner.hasNextInt()) {
                respostaUsuario = scanner.nextInt();
                if (respostaUsuario >= 1 && respostaUsuario <= pergunta.getRespostas().size()) {
                    return respostaUsuario - 1; //Ajusta para o índice correto
                } else {
                    System.out.println("Resposta inválida. Tente novamente.");
                }
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); //Limpar a entrada inválida
            }
        }
    }

    //Versão de teste do método fazerPergunta, sem interação com o usuário
    public static boolean fazerPergunta(int respostaUsuario, Pergunta pergunta) {
        System.out.println("\n" + pergunta.getPergunta());
        exibirRespostas(pergunta.getRespostas());

        return respostaUsuario == pergunta.getRespostaCorreta();
    }

    public static void exibirRespostas(List<String> respostas) {
        for (int i = 0; i < respostas.size(); i++) {
            System.out.println((i + 1) + ". " + respostas.get(i));
        }
    }

    public static void exibirResultado(int pontos, int totalPerguntas) {
        System.out.println("\nFim do jogo! Você acertou " + pontos + " de " + totalPerguntas + " perguntas.");
    }

    public static List<Pergunta> carregarPerguntas() {
        List<Pergunta> perguntas = new ArrayList<>();
        try {
            FileReader reader = new FileReader(PERGUNTAS_JSON);
            JsonArray perguntasJson = JsonParser.parseReader(reader).getAsJsonArray();

            //Verificando se o JSON está vazio ou nulo
            if (perguntasJson == null || perguntasJson.size() == 0) {
                return perguntas;  // Retorna lista vazia
            }

            for (int i = 0; i < perguntasJson.size(); i++) {
                JsonObject perguntaJson = perguntasJson.get(i).getAsJsonObject();
                String perguntaTexto = perguntaJson.get("pergunta").getAsString();
                JsonArray respostasJson = perguntaJson.getAsJsonArray("respostas");

                List<String> respostas = new ArrayList<>();
                for (int j = 0; j < respostasJson.size(); j++) {
                    respostas.add(respostasJson.get(j).getAsString());
                }

                int respostaCorreta = perguntaJson.get("correta").getAsInt();
                perguntas.add(new Pergunta(perguntaTexto, respostas, respostaCorreta));
            }
        } catch (IOException | JsonParseException e) {
            //Caso haja erro ao carregar o JSON, retornamos lista vazia ou null
            System.out.println("Erro ao carregar perguntas: " + e.getMessage());
            return null;  //Retorna null para indicar falha no carregamento
        }
        return perguntas;  //Retorna as perguntas carregadas
    }

}
