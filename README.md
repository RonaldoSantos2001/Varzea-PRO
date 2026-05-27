# ⚽ Varzea Pro - Gestão de Futebol de Várzea

Sistema web para gerenciamento de campeonatos de futebol amador, desenvolvido como requisito para a disciplina de Linguagem de Programação 2 (Centro Educacional Fundação Salvador Arena). O sistema permite o controle completo de torneios, equipes, escalação de jogadores e agendamento de partidas.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework Backend:** Spring Boot (v4.0.6)
* **Segurança:** Spring Security (Criptografia BCrypt)
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (In-Memory)
* **Frontend:** Thymeleaf, HTML5, CSS3, Bootstrap 5 (UI Customizada Neon)
* **Gerenciador de Dependências:** Maven

## ⚙️ Funcionalidades Implementadas

* **Controle de Acesso:** Autenticação e autorização de administradores com Spring Security.
* **Gestão de Equipes:** Cadastro e listagem de times.
* **Gestão de Atletas:** Cadastro de jogadores amarrados às suas respectivas equipes.
* **Gestão de Campeonatos:** Criação de torneios ativos.
* **Gestão de Partidas:** Agendamento de confrontos entre equipes cadastradas.

## 🚀 Como Executar o Projeto

Como a aplicação utiliza o banco de dados em memória **H2**, os dados são zerados a cada reinicialização do servidor. Siga os passos abaixo para testar corretamente:

1. Clone este repositório:
```bash
git clone [https://github.com/SeuUsuario/Varzea-PRO.git](https://github.com/SeuUsuario/Varzea-PRO.git)
```

2. Abra o projeto na sua IDE de preferência (NetBeans, Eclipse, IntelliJ).

3. Execute o comando Maven para baixar as dependências e compilar:
```bash
mvn clean install
```

4. Inicie a aplicação executando a classe principal `CampeonatoApplication.java` ou via terminal:
```bash
mvn spring-boot:run
```

5. Acesse o sistema no navegador:
* **URL:** `http://localhost:8081`

### 🔑 Primeiro Acesso (Importante para Avaliação)

O banco de dados inicia vazio. Para testar o sistema, é obrigatório registrar o primeiro administrador:

1. Na tela de login inicial, clique em **"Registre-se aqui"**.
2. Preencha seus dados (Nome, Login, Senha e Confirmação).
3. Após o redirecionamento, faça o login com as credenciais recém-criadas.
4. **Fluxo recomendado de teste:** Para evitar erros de chave estrangeira, cadastre os itens na seguinte ordem: Equipes -> Jogadores -> Campeonatos -> Partidas.

## 📊 Diagrama UML

> **Nota:** O diagrama UML estrutural do sistema (Classes e Relacionamentos) está disponível na pasta `/docs` deste repositório.
