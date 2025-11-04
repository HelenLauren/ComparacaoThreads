# Comparativo de Desempenho entre Modelos de Threading (1:1 vs N:M)

## Tabela com os resultados dos testes
<img width="703" height="208" alt="image" src="https://github.com/user-attachments/assets/c629020f-42c7-47f9-8093-617e21ad1722" />


## Gráfico comparativo entre as threads
<img width="500" height="300" alt="image" src="https://github.com/user-attachments/assets/1d77dc13-09aa-4161-b305-26344ea23545" />

## Análise dos resultados

Nos testes realizados, o **modelo 1:1** foi significativamente mais rápido, pois cada tarefa cria sua própria thread nativa.
Por isso, o tempo total praticamente não aumenta, mesmo com 1000 threads.  

O **modelo N:M**, por outro lado, utiliza um **pool fixo de threads** (8), o que faz com que as tarefas sejam executadas em fila, aumentando o tempo proporcionalmente ao número de tarefas.  

No código:
- O **modelo 1:1** usa `new Thread().start()`, criando uma thread por tarefa.  
- O **modelo N:M** usa `Executors.newFixedThreadPool(M)`, limitando o número de threads simultâneas.

---

Mesmo após aumentar o número de threads do modelo N:M para 15 continuou inferior ao modelo 1:1, comprovando que o revezamento interno das threads e o agendamento dentro do pool ainda causam lentidão no processamento.

<img width="703" height="378" alt="image" src="https://github.com/user-attachments/assets/1ea946da-8eca-4a24-ada4-03fdb67d216a" />


---

Resumindo, o modelo M:N é mais eficiente quando há poucas tarefas pois reutiliza threads e consome menos recursos.
Já o modelo 1:1 e mais eficiente em cenários com muitas tarefas, executando tudo em paralelo real. Porém, ele exige mais memória e processamento, podendo sobrecarregar o sistema.

Assim, o M:N prioriza eficiência e estabilidade, enquanto o 1:1 privilegia desempenho bruto.
