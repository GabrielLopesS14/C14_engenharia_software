import json
import random
from rich.console import Console
from rich.prompt import Prompt

#Acessando o JSON com as perguntas
def carregar_perguntas():
    with open("perguntas.json", "r") as file:
        perguntas = json.load(file)
    return perguntas

#Função do jogo
def jogar():
    console = Console()
    perguntas = carregar_perguntas()
    pontos = 0
    total_perguntas = len(perguntas)

    console.print("Bem-vindo ao Jogo de Perguntas e Respostas sobre Futebol!", style="bold yellow")

    #Deixando as perguntas aleatórias
    random.shuffle(perguntas)

    for i, pergunta in enumerate(perguntas[:5]): #Definindo um número máximo de perguntas
        console.print(f"\nPergunta {i + 1}: {pergunta['pergunta']}", style="bold green")
        respostas = pergunta['respostas']
        random.shuffle(respostas)

        for idx, opcao in enumerate(respostas, 1):
            console.print(f"{idx}. {opcao}", style="bold cyan")

        resposta_usuario = Prompt.ask("Digite o número da sua resposta", choices=["1", "2", "3", "4"])
        resposta_usuario = int(resposta_usuario) - 1

        #Verificando as respostas
        if respostas[resposta_usuario] == pergunta['correta']:
            pontos += 1
            console.print(f"Correto! Você tem {pontos} pontos.", style="bold green")
        else:
            console.print(f"Errado! A resposta correta era {pergunta['correta']}.", style="bold red")

    #Resultado final
    console.print(f"\nFim do jogo! Você acertou {pontos} de {total_perguntas} perguntas.", style="bold yellow")

if __name__ == "__main__":
    jogar()
