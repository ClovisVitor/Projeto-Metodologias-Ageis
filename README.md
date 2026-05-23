# Sistema de Estacionamento 🚗

Projeto desenvolvido em Java para controle de entrada e saída de veículos em um estacionamento.

## 📚 Objetivo

O sistema foi criado como atividade acadêmica para aplicar conceitos de:

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Herança
- Polimorfismo
- Integração com PostgreSQL usando JDBC

---

# ⚙️ Funcionalidades

- Cadastro de veículos
- Registro de entrada
- Registro de saída
- Controle de vagas
- Cálculo automático de cobrança
- Histórico de movimentações
- Persistência de dados no PostgreSQL
- Menu interativo no terminal

---

# 🚘 Tipos de veículos

O sistema trabalha com:

- Carro
- Moto
- Caminhonete

### Regras de cobrança

- Até 1 hora: R$ 5,00
- Hora adicional: R$ 3,00
- Moto: 50% do valor
- Caminhonete: 150% do valor

---

# 🛠️ Tecnologias utilizadas

- Java 17
- IntelliJ IDEA
- PostgreSQL
- JDBC

---

# 🗂️ Estrutura do Projeto

```bash
src/
 ├── estacionamento/
 │    ├── model/
 │    ├── dao/
 │    ├── config/
 │
 ├── Main.java
