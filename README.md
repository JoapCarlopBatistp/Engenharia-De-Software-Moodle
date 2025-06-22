<a id="readme-top"></a>

# ${\color{purple}Portal \space Do \space Aluno \space - \space Engenharia \space De \space Software}$
Trabalho da disciplina de Engenharia de Software que tem por objetivo a implementação de um Portal do Aluno com base nos seguintes casos de uso:

![image](https://github.com/user-attachments/assets/27de6a71-e53b-4c77-8a8f-15879ad02e5c)

O padrão arquitetural escolhido foi o MVC (Model-View-Controller)

### ${\color{purple}Contribuidores}$

<a href="https://github.com/JoapCarlopBatistp/Engenharia-De-Software-Moodle/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=JoapCarlopBatistp/Engenharia-De-Software-Moodle" />
</a>

### ${\color{purple}Feito \space Usando}$

Lista de frameworks, banco de dados e ferramentas utilizadas no projeto.

* [![Java][Java.java]][Java-url]
* [![Apache Maven][Maven.mvn]][Maven-url]
* [![Postgres][Postgres.sql]][Postgres-url]

<!--<p align="right">(<a href="#readme-top">Voltar ao Topo</a>)</p>-->

### ${\color{purple}Setup \space E \space Utilização}$
- <b>[1]</b> Instale o Java 24 em https://www.java.com/pt-BR/download/
- <b>[2]</b> Instale o Maven em https://maven.apache.org/download.cgi
- <b>[3]</b> Instale o PostgreSQL em https://www.postgresql.org/download/
- <b>[4]</b> Instale uma ferramenta de gerenciamento de banco de dados, no nosso caso, instalamos o DBeaver a partir de https://dbeaver.io/download/
- <b>[5]</b> Clone o repositório utilizando o comando:
   ```sh
   git clone https://github.com/JoapCarlopBatistp/Engenharia-De-Software-Moodle
   ```
- <b>[6]</b> Com o DBeaver aberto, clique em "Banco de Dados" e depois em "Nova Conexão"
  
![image](https://github.com/user-attachments/assets/fa06340f-dbad-4b88-af35-93597ac1fafe)

- <b>[7]</b> Na aba "SQL" ecolha o postgreSQL
  
![image](https://github.com/user-attachments/assets/e744c402-9ccf-4a8a-b9b7-8a6eaedc8b55)

- <b>[8]</b> Clique com o botão direito no folder "Banco de Dados" e depois em "Criar novo(a) Banco de Dados" (Nomeie o banco de dados como "Trab_EngSoftware")
  
![image](https://github.com/user-attachments/assets/df98e523-7b03-4833-a75a-2335999c2197)

- <b>[9]</b> O SQL para rodar no banco está no repositório (arquivo banco.sql). Para rodá-lo basta criar um novo sql script no DBeaver como a imagem abaixo sugere:

![image](https://github.com/user-attachments/assets/0b487374-5ea2-4e7f-8cb0-8fb3bdd70bc5)

- <b>[10]</b> No arquivo Database/databaseconn troque o campo SENHA pela senha do seu banco de dados
  
![image](https://github.com/user-attachments/assets/5fb6322c-3ab1-4f4f-b04b-27f93f0a3102)

- <b>[11]</b> No terminal rode o comando:
  ```sh
   mvn package
   ```
- <b>[11]</b> Execute o código!


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Java.java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/pt-BR/
[Maven.mvn]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[Maven-url]: https://maven.apache.org
[Postgres.sql]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[Postgres-url]: https://www.postgresql.org
