# 🌾 **AgroView – Sistema de Gerenciamento Agrícola**

Sistema completo para gestão de propriedades rurais utilizando banco de dados **MongoDB**.
Este projeto define as coleções essenciais e seus respectivos modelos de dados para o sistema **AgroView**.

## 🧠 **Sobre o Projeto**
O **AgroView** tem como objetivo oferecer um gerenciamento eficiente de clientes, propriedades, plantios e clima.
O sistema foi projetado utilizando **MongoDB**, garantindo escalabilidade, flexibilidade e performance no armazenamento das informações agrícolas.

## 📁 **Modelos e Coleções do Banco de Dados**

## 🧍‍♂️ **Cliente**
- **cliente** – Identificador único do cliente
- **nome** – Nome do cliente
- **email** – Login no sistema
- **senha** – Senha criptografada
- **cpf_cnpj** – Documento
- **telefone** – Contato telefônico
- **data_cadastro** – Data de cadastro no sistema

## 🌻 **Plantio**
- **data_plantio** – Data em que o plantio iniciou
- **data_prevista_colheita** – Estimativa baseada no ciclo
- **status** – Plantado, Crescimento, Colheita...
- **observacoes** – Observações gerais
- **producao_esperada** – Produção estimada (toneladas/hectare)
- **producao_real** – Produção real após colheita
- **quantidade** – Unidade de produção (kg, sacas, etc.)
- **gasto_estimado** – Custo estimado (R$)

## ☁️ **Clima**
- **data_registro** – Data da medição
- **temperatura** – Temperatura (°C)
- **umidade_ar** – Umidade relativa do ar (%)
- **chuva** – Chuva (mm)
- **vento** – Velocidade do vento (km/h)

## 💾 **Tecnologias Utilizadas**
###  Banco de Dados
- **MongoDB**
###  Back-end (API / Lógica de Negócio)
- **Node.js**
###  Servidor / Camada Corporativa
- **Java**
###  Ferramentas de Desenvolvimento
- **VSCode**
- **IntelliJ IDEA**
###  Controle de Versão
- **Git**
- **GitHub**
