🌾 AgroView – Sistema de Gerenciamento Agrícola

Sistema completo para gestão de propriedades rurais utilizando banco de dados NoSQL MongoDB.
Este projeto define as coleções essenciais e seus respectivos modelos de dados para o sistema AgroView.

🧠 Sobre o Projeto

O AgroView tem como objetivo oferecer um gerenciamento eficiente de clientes, propriedades, talhões, culturas, plantios e clima.
O sistema foi projetado utilizando MongoDB, garantindo escalabilidade, flexibilidade e performance no armazenamento das informações agrícolas.

📁 Modelos e Coleções do Banco de Dados
🧍‍♂️ Cliente
Campo	Descrição
cliente	Identificador único do cliente
nome	Nome do cliente
email	Login no sistema
senha	Senha criptografada
cpf_cnpj	Documento
telefone	Contato telefônico
data_cadastro	Data de cadastro no sistema
🌱 Terreno
Campo	Descrição
nome	Nome da propriedade
localizacao	Endereço ou coordenadas GPS
area_total	Área total em hectares
🧩 Talhão
Campo	Descrição
nome	Nome ou código do talhão
area	Área do talhão em m²
tipo_solo	Argiloso, arenoso, misto...
ph_solo	pH do solo
umidade_solo	Percentual atual de umidade
🌾 Cultura
Campo	Descrição
nome	Milho, Soja, Café, etc.
ciclo_dias	Duração média do plantio até a colheita
temperatura_ideal	Temperatura ideal (°C)
umidade_ideal	Umidade ideal (%)
chuva_ideal	Chuva ideal (mm/mês)
🌻 Plantio
Campo	Descrição
data_plantio	Data em que o plantio iniciou
data_prevista_colheita	Estimativa baseada no ciclo
status	Plantado, Crescimento, Colheita...
observacoes	Observações gerais
producao_esperada	Produção estimada (toneladas/hectare)
producao_real	Produção real após colheita
quantidade	Unidade de produção (kg, sacas, etc.)
gasto_estimado	Custo estimado (R$)
☁️ Clima
Campo	Descrição
data_registro	Data da medição
temperatura	Temperatura (°C)
umidade_ar	Umidade relativa do ar (%)
chuva	Chuva (mm)
vento	Velocidade do vento (km/h)
💾 Tecnologias Utilizadas

MongoDB

Node.js, Java ou Spring Boot

VSCode ou IntelliJ IDEA

Git / GitHub
