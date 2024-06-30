# BOOLFLIX-BACKEND

## Stack
- Java 17
- Spring Framework
- MongoDB
- Docker
- JUnit
- Maven

## Configurações do Projeto

O projeto contém duas variáveis de sistema:
1. `JWT_KEY`: Chave usada para a criptografia do Token JWT.
2. `DB_URI`: Define a URL do banco de dados.
3. É necessário utilizar um arquivo `.env` no diretório raiz do projeto.

## Comandos

Para gerar a imagem Docker, use o comando a seguir:
```bash
docker build aluraflix-api .
```

Para iniciar o container da aplicação, execute:
```bash
docker compose up -d --build
```

## Documentação das Rotas:

A documentação das rotas está disponível em Swagger UI.

Para efetuar solicitações aos endpoints, siga as instruções abaixo:

Crie um usuário através do endpoint "/authentication/signup".

Para criar um usuário com privilégios de administrador, cadastre um endereço de e-mail que contenha a palavra "adm".
Se você já possui um usuário registrado, é possível utilizar o endpoint "/authentication" diretamente.

Se a requisição estiver corretamente formatada, ambos os endpoints retornarão um token de acesso.

Para aplicar esse token, localize o campo "Authorize" no canto superior direito da página de documentação Swagger e insira o token gerado.

Dessa forma, você estará pronto para utilizar os endpoints com as devidas credenciais de acesso.
