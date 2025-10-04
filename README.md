Projeto: Mensageria
===================

- Vinicius Baldochi Cardoso
- Igor Owen
- Pedro Gonçalves Moreira
- Samuel Santos Souza

Resumo
------
Este repositório contém o projeto "Mensageria" (consumidor + API) desenvolvido para a atividade. O objetivo é consumir mensagens de reservas vindas de um sistema de hotelaria, persistir os dados em um banco relacional e expor uma API REST para consulta das reservas.


Evidências de implementação
---------------------------
- Consumidor Pub/Sub
  - `src/main/java/com/faculdade/pubsub/PubSubConsumerSpring.java` (consumer Spring Boot usando Google Pub/Sub e chamando `ReservaService.processarReserva`)

- Persistência e entidades (Banco relacional)
  - `src/main/java/com/faculdade/pubsub/entity/Reserva.java` (contém `indexedAt`, lista de `QuartoReservado` e `valorTotal`)
  - `src/main/java/com/faculdade/pubsub/entity/QuartoReservado.java` (calcula `valorTotal` por quarto em `calculateValorTotal`)
  - `src/main/java/com/faculdade/pubsub/entity/Cliente.java`
  - Repositórios: `ReservaRepository`, `ClienteRepository`, `QuartoReservadoRepository` em `src/main/java/com/faculdade/pubsub/repository/`

- Lógica de processamento
  - `src/main/java/com/faculdade/pubsub/service/ReservaService.java` (converte o payload JSON, cria/atualiza cliente, cria reserva e quartos, calcula `valorTotal` e seta `indexedAt`/`createdAt`)

- API de consulta
  - `src/main/java/com/faculdade/pubsub/controller/ReservaController.java`
    - Endpoint: `GET /reserves` com filtros `uuid`, `cliente_id`, `hotel_id`, `room_id`
    - Endpoint: `GET /reserves/{uuid}` para busca por UUID
  - DTOs de resposta: `src/main/java/com/faculdade/pubsub/dto/response/ReservaResponseDto.java` e `RoomResponseDto`


Requisitos mínimos implementados
---------------------------------
1) Consumidor
   - Lê mensagens de Reservas (formato JSON) publicadas por um sistema de hotelaria.
   - Persiste as informações em banco relacional com, no mínimo, as tabelas: `reserva`, `quarto_reservado` e `cliente`.
   - Registra a hora em que a mensagem foi indexada na base (campo `indexed_at` na tabela `reserva`).

2) API
   - Endpoint GET /reserves
   - Filtros suportados: uuid (da reserva), cliente_id, quarto_id, hotel_id
   - Retorno no formato JSON com o contrato de payload (exemplo abaixo)
   - A resposta inclui cálculo do valor total da reserva e do valor de cada quarto reservado


DER (Modelo conceitual simplificado)
-----------------------------------

Relacionamentos:
- Um `cliente` tem muitas `reserva` (1:N)
- Uma `reserva` tem muitos `quarto_reservado` (1:N)

cliente
  - cliente_id (PK)
  - nome
  - email
  - telefone

reserva
  - reserva_id (PK)
  - uuid (UNIQUE)
  - cliente_id (FK)
  - hotel_id
  - data_checkin
  - data_checkout
  - total_calculado
  - indexed_at

quarto_reservado
  - quarto_reservado_id (PK)
  - reserva_id (FK)
  - quarto_id
  - diaria_valor
  - quantidade_noites
  - subtotal


API: /reserves
---------------
Method: GET
Path: /reserves

Query parameters (todas opcionais):
- uuid: string (UUID da reserva)
- cliente_id: integer
- quarto_id: integer
- hotel_id: integer

Resposta: 200 OK
Content-Type: application/json
