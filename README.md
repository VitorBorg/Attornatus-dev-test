## Teste técnico backend Attornatus

## Questão 1 - Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

  Inicialmente é importante manter a mesma arquitetura e design do projeto para facilitar a manutenção e aumentar a legibilidade, mas também existem outros critérios como performance, segurança, documentação e testes.

## Questão 2 - Em qual etapa da implementação você considera a qualidade de software?
  
 A qualidade do software precisa estar presente em toda a etapa de implementação, em conformidade com as especificações de requisitos. Isso garante que o projeto alcance características muito importantes como fácil manutenção, e maior eficiência na implementação de novas funcionalidades.

## Rotas
(Um arquivo de exportação do postman foi colocado na pasta raíz, nele estão prontas todas as requisições).

# Post
* /person - Cria uma pessoa

 body:
{
    "namePerson": "Elio",
    "birthDatePerson": "1998-09-27"
}

* /address - Cria um endereço
body:
{
    "idPerson": "1",
    "logradouroAddress": "Cidade",
    "cepAddress": "44477477",
    "numberAddress": 214,
    "cityAddress": "Rio Grande"
}

# Get
* /person - Lista todas as pessoas cadastradas
* /person/`id` - Exibe as informações de um usuário específico
* /address/`id` - Lista todos os endereços de um usuário específico 

# Delete
* /person/`id` - Exclui um usuário específico
* /address/`id` - Exclui um endereço específico

# Put
* /person/`id`- Edita as informações de um usuário específico
body:
{
    "namePerson": "Elio",
    "idPrincipalAddress": "2",
    "birthDatePerson": "1998-09-27"
}