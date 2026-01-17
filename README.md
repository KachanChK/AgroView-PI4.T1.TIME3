# 🌾 **AgroView – Sistema de Gerenciamento Agrícola**

Sistema completo para gestão de propriedades rurais utilizando banco de dados **MongoDB**.
Este projeto define as coleções essenciais e seus respectivos modelos de dados para o sistema **AgroView**.

## 🧠 **Sobre o Projeto**
O **AgroView** tem como objetivo oferecer um gerenciamento eficiente de clientes, propriedades, plantios e clima.
O sistema foi projetado utilizando **MongoDB**, garantindo escalabilidade, flexibilidade e performance no armazenamento das informações agrícolas.

## **Como utilizar**
1. Clone o repositório:

```bash
git clone https://github.com/KachanChK/AgroView-PI4.T1.TIME3.git

```

2. Instale as dependências:

```bash
npm install
```

3. Criar o arquivo ".env" e preencher conforme o ".env.exemple" mostra;

```bash
MONGO_URI="INSIRA A STRING DE CONEXAO DO DRIVER DO MONGODB"
```

4. Rode em modo de desenvolvimento:

```bash
npm run dev
```

A aplicação irá rodar em http://localhost:3000

5. Clone o repositorio no Intellij IDEA e entre na branch Servidor_Reformulado:

```bash
git checkout Servidor_Reformulado
```

Execute a classe Servidor para ativar a validação de senha


## **Tecnologias Utilizadas**
###  Banco de Dados
- **MongoDB**
###  Back-end
- **Node.js**
- **Typescript**
###  Servidor
- **Java**
