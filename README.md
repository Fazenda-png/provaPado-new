#  Teste para desenvolvedor back-end junior - Concluido

---     

# Startar o projeto

Após fazer o clone para a maquina local rodar.

```
  mvn package 
```
Depois do passo acima ter sido concluido rodar os comandos.

```
  1 - docker network created my_newtwork
  2 - docker-compose up
```

Desafios:

1 - **Cadastro de um único dispositivo IoT**: Aqui deixo um JSON para exemplo(cadastrar um dispositivos de cada vez)
a url para acesso "http://localhost:8080/registrar".

```
Verbo: POST
{
  "name": "nome do dispositivo",
  "mac": "mac do dispositivo",
  "email": "email do dono do dispositivo",
  "latitude": "latitude",
  "longitude": "longitude"
}

outro JSON

{
  "name": "nome do dispositivo 1",
  "mac": "mac do dispositivo 1",
  "email": "email do dono do dispositivo 1",
  "latitude": "latitude 1",
  "longitude": "longitude 1"
}
```
2 - **Listar todos os dispositivos cadastrados**: A url para acesso "http://localhost:8080/listar".

```
Verbo: GET
```
OBS: A resposta vai depender dos itens cadastrados no desafio acima.


3 - **Listar dispositivo pelo "deviceId"**: A url para acesso "http://localhost:8080/listar/deviceId/{id}"
utilize as informações retornadas do desafio acima para pegar o deviceID. Trocar o "{id}" pelo valor retirado da resposta.

```
Verbo: GET
```
OBS: A resposta irá depender do valor colocado no id.


4 - **Desafio Bônus**: Aqui deixo um JSON para exemplo a url para acesso "http://localhost:8080/registrar/async"

```
Verbo: POST
[
  {
    "name": "nome do dispositivo 1",
    "mac": "mac do dispositivo 1",
    "email": "email do dono do dispositivo 1",
    "latitude": "latitude 1",
    "longitude": "longitude 1"
  },
  {
    "name": "nome do dispositivo 2",
    "mac": "mac do dispositivo 2",
    "email": "email do dono do dispositivo 2",
    "latitude": "latitude 2",
    "longitude": "longitude 2"
  },
    {
    "name": "nome do dispositivo 3",
    "mac": "mac do dispositivo 3",
    "email": "email do dono do dispositivo 3",
    "latitude": "latitude 3",
    "longitude": "longitude 3"
  }
]
```
Nesta etapa o payload será enviado para uma fila no RabbitMQ(link de acesso "http://localhost:15672/" com o user: guest e a senha: guest).
