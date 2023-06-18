[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10848496)
# JoaoCaramflix
O objetivo do projeto é desenvolver um sistema para ser vendido para companhias de streaming. 
Nosso uma plataforma possui várias funcionalidades que deixa um ambiente 

# Comentários - Projeto 4

## Nota base: 14,5

### Comentários

- Usar 'add' e false para verificação de existência é gambiarra. Temos métodos contains, indexOf, get...
- O uso de booleano para especialista é pobre. O especialista tem um método/habilidade a mais: comentarista. Como está, fere no mínimo o OCP.
- Fazer um for na lista para localizar a mídia para avaliar é pobre. Temos métodos contains, indexOf, get... agora temos stream também.
- Vejam também que a regra de não repetir a avaliação deveria ser da mídia, não do cliente
- BigDecimal é adequado/necessário para média? Pode dar exceção se não arredondar


----
	
- Aderência às classes do diagrama: 2/2 pontos


- Requisitos de corretamente implementados: 14 pontos
    - só pode avaliar o que tiver visto		1,2/2 pontos
    - avaliar, calcular e exibir media 		2/2 pontos
    - cliente não pode avaliar 2x			2/3 pontos
    - especialistas podem comentar			2,5/4 pontos
    - verificação de especialistas			1,8/3 pontos
	
- Documentação de código: 1/2 pontos
	- Parcial: faltam parâmetros e retornos, e o texto principal é apenas uma declaração do nome dos métodos. A documentação precisa explicar como usar, o que pode acontecer etc.

- Implementação na aula inicial: 2/2 pontos (02/05)
    - arquivos JavaDoc  ✔️
    - diagrama atualizado ✔️
    - backlog de pendências ✔️

----

## Alunos integrantes da equipe

* Arthur Jansen Oliveira
* Bárbara Mattioly Andrade
* Gabriel Pimentel Tabatinga
* Henrique Grissi C Soeiro de Carvalho
* Laura Enísia Rodrigues Melo

## Professores responsáveis

* João Caram Santos de Oliveira

