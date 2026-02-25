# ⚙️ Gerenciador de Tarefas - Desafio Técnico ESIG Group

## 📌 Sobre o Projeto
Este projeto foi desenvolvido como parte do desafio técnico para a vaga de estágio na ESIG Group. Trata-se de uma aplicação web robusta para o gerenciamento de tarefas corporativas, construída com base na arquitetura MVC e utilizando a stack oficial do ecossistema Java EE. 

O foco do desenvolvimento foi entregar um código limpo, escalável e com uma excelente Experiência do Usuário (UX), adotando o padrão Enterprise Dark Mode.

### 🛠️ Tecnologias Utilizadas
* **Backend:** Java EE, JPA (Java Persistence API), Hibernate Core.
* **Frontend:** JSF (JavaServer Faces), Facelets, HTML5 Passthrough, Bootstrap 4 (Tema Bootswatch Darkly).
* **Injeção de Dependência:** CDI (Contexts and Dependency Injection) com Weld.
* **Banco de Dados:** PostgreSQL.
* **Servidor de Aplicação:** Apache Tomcat 9.
* **Gerenciador de Dependências:** Maven.

---

## ✅ Itens e Funcionalidades Implementadas

O projeto atende aos requisitos funcionais do desafio, com os seguintes destaques técnicos:

* **[A] Cadastro de Tarefas:** Inserção de novas tarefas com título, descrição, responsável, prioridade e deadline.
* **[B] Listagem e Filtros Dinâmicos:** Consulta de tarefas com filtros combinados (Número, Título/Descrição, Responsável e Situação). As buscas utilizam JPQL dinâmico para otimização de performance.
* **[C] Edição de Tarefas:** Fluxo de atualização de dados utilizando **Flash Scope** nativo do JSF, permitindo o tráfego seguro do objeto entre as views sem sobrecarregar a sessão do servidor.
* **[D] Exclusão e Conclusão:** Remoção de registros e alteração de status com gerenciamento de estado via `@ViewScoped`, garantindo a integridade dos índices da tabela.
* **[E] Database Seeding (Diferencial):** Implementação de um `@WebListener` (`AppStartupListener`) que detecta a inicialização do Tomcat e cadastra automaticamente 3 perfis fictícios no banco de dados, facilitando os testes da equipe de avaliação.
* **[F] Design Responsivo e Dark Mode (Diferencial):** Interface estilizada com Bootstrap, adotando um tema escuro profissional e tipografia moderna (Google Fonts - Inter).

---

## 🚀 Instruções para Execução Local

Siga os passos abaixo para rodar o projeto no seu ambiente local:

### 1. Pré-requisitos
* **JDK** (Java Development Kit) 8 ou superior instalado.
* **Apache Tomcat** (versão 9 recomendada) configurado na sua IDE (Eclipse/IntelliJ).
* **PostgreSQL** instalado e rodando na porta padrão (`5432`).
* **Maven** para o gerenciamento das dependências.

### 2. Configuração do Banco de Dados
1. Abra o seu SGBD (ex: pgAdmin) e crie um banco de dados vazio. *(Exemplo: `tarefas_esig`)*.
2. No projeto, navegue até o arquivo `src/main/resources/META-INF/persistence.xml`.
3. Atualize as propriedades de conexão com as suas credenciais locais:
   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/SEU_BANCO_AQUI"/>
   <property name="javax.persistence.jdbc.user" value="SEU_USUARIO"/>
   <property name="javax.persistence.jdbc.password" value="SUA_SENHA"/>