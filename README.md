# AgroView

## Descrição Geral

O **AgroView** é um sistema de gerenciamento de agronegócio desenvolvido para apoiar produtores rurais na administração de suas atividades agrícolas.
A plataforma fornece ferramentas de **monitoramento climático, gestão de insumos e máquinas, relatórios inteligentes, previsão de safras e análise de perdas**, com o objetivo de otimizar a produção, reduzir desperdícios e apoiar a tomada de decisão por meio de dados e relatórios automatizados.

---

## Objetivos do Sistema

* Centralizar a gestão das operações agrícolas em uma única plataforma.
* Melhorar a eficiência e o controle sobre o uso de recursos (insumos, máquinas, área plantada).
* Oferecer informações climáticas em tempo real para auxiliar nas decisões de plantio e colheita.
* Automatizar a geração de relatórios e indicadores de desempenho.
* Apoiar o agricultor com previsões de produtividade e alertas inteligentes.

---

## Funcionalidades Principais

* Cadastro e autenticação de usuários com segurança e criptografia de dados.
* Monitoramento do clima e previsões meteorológicas da região do produtor.
* Gestão de plantios, insumos e máquinas agrícolas.
* Geração de relatórios personalizados sobre safras, custos e lucros.
* Visualização de dashboards com indicadores de desempenho.
* Exportação de relatórios em PDF e envio de notificações automáticas.

---

## Decisões de Projeto

Durante o desenvolvimento do AgroView, foram tomadas as seguintes decisões de arquitetura e implementação:

1. **Linguagem:**
   Optou-se pelo uso de **Java** no backend para garantir escalabilidade, segurança e integração com APIs externas (ex: APIs meteorológicas).

2. **Banco de Dados:**
   Utilizou-se o **MongoDB** como banco de dados principal por sua flexibilidade na modelagem de dados não estruturados e integração simples.

3. **Integração com APIs Climáticas: (Em breve)**
   Decidiu-se pela integração com uma **API meteorológica externa** (como OpenWeather ou WeatherAPI) para obter dados climáticos em tempo real e previsões semanais.

4. **Geração de Relatórios: (Em breve)**
   Os relatórios são gerados dinamicamente a partir dos dados do banco, com possibilidade de exportação em **PDF**.
   A biblioteca **iTextPDF** foi escolhida pela sua confiabilidade e facilidade de uso com Java.

5. **Rotas/Endpoints:** 

* Cadastrar novo Plantio: "/novoPlantio"
   -Tabela Plantio: plantio, area, quantidadePlantio, gastoEstimado, producaoEsperada, observacoes, status, dataPlantio

---

## Arquitetura do Sistema

A arquitetura do AgroView segue o padrão **cliente-servidor**, composta por:

* **Frontend (Web App)**: Interface gráfica acessível via navegador.
* **Backend**: Responsável pela lógica de negócio, autenticação e comunicação com o banco.
* **Banco de Dados (MongoDB)**: Armazena informações de usuários, insumos, máquinas, plantios e relatórios.
* **APIs Externas**: Fornecem dados climáticos e previsões meteorológicas.

---

## Tecnologias Utilizadas

* **Java**
* **MongoDB**

---


