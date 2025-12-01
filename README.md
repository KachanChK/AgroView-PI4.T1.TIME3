## 🌾 MongoDB AgroView

Sistema de gerenciamento agrícola utilizando banco de dados NoSQL **MongoDB**.
Este projeto define as **coleções mínimas necessárias** e seus respectivos **atributos (modelos)** para o sistema **AgroView**.

## 🧍‍♂️ Cliente
- **cliente**: Identificador único do cliente
- **nome**: Nome do cliente
- **email**: Login no sistema
- **senha**: Senha (criptografada)
- **cpf_cnpj**: Documento
- **telefone**: Contato
- **data_cadastro**: Data de cadastro no sistema

## 🌱 Terreno
- **nome**: Nome da propriedade
- **localizacao**: Endereço ou coordenadas GPS
- **area_total**: Área total em hectares

## 🧩 Talhão
- **nome**: Nome ou código do talhão
- **area**: Área do talhão em m²
- **tipo_solo**: Argiloso, arenoso, misto...
- **ph_solo**: pH do solo
- **umidade_solo**: Percentual atual de umidade

## 🌾 Cultura
- **nome**: Milho, Soja, Café, etc.
- **ciclo_dias**: Duração média do plantio até a colheita
- **temperatura_ideal**: Temperatura ideal (°C)
- **umidade_ideal**: Umidade ideal (%)
- **chuva_ideal**: Chuva ideal (mm/mês)

## 🌻 Plantio
- **data_plantio**: Quando foi plantado
- **data_prevista_colheita**: Baseada no ciclo
- **status**: Plantado, Crescimento, Colheita...
- **observacoes**: Notas gerais
- **producao_esperada**: Produção esperada (toneladas/hectare)
- **producao_real**: Produção real após colheita
- **quantidade**: kg, sacas, etc.
- **gasto_estimado**: R$

## ☁️ Clima
- **data_registro**: Data da medição
- **temperatura**: Temperatura (°C)
- **umidade_ar**: Umidade relativa do ar (%)
- **chuva**: Chuva em mm
- **vento**: Velocidade do vento (km/h)

## 💾 Tecnologias Utilizadas
- **MongoDB**
- **Node.js / Java / Spring Boot** (opcional)
- **VSCode** ou **IntelliJ IDEA**
- **Git / GitHub**

## 🧠 Sobre
Este documento serve como **README** do projeto, descrevendo as coleções essenciais do banco de dados MongoDB do sistema **AgroView**.
