# Library API - Biblioteca Digital

API REST para gerenciamento de uma biblioteca digital, desenvolvida com **Spring Boot 2.6.3** e **Java 11**. Parte do sistema full-stack que inclui o frontend Angular (Library_front).

---

## 🚀 Tecnologias

| Camada | Tecnologia |
|--------|------------|
| **Framework** | Spring Boot 2.6.3 |
| **Linguagem** | Java 11 |
| **Banco de Dados** | H2 (em memória, desenvolvimento) |
| **ORM** | Spring Data JPA / Hibernate |
| **Segurança** | Spring Security + JWT (jjwt 0.11.2) |
| **Validação** | Bean Validation (Hibernate Validator) |
| **Build** | Maven Wrapper |
| **Documentação API** | OpenAPI/Swagger (configurado) |

---

## 📋 Funcionalidades Principais

### 📚 Gestão de Livros
- **Listagem paginada** com ordenação customizável (`/api/books`)
- **Livros ativos** (`/api/books/ativos`)
- **Livros em destaque** para carrossel (`/api/books/destaques`)
- **Busca por ID** (`/api/books/{id}`)
- **Busca avançada com filtros** (`/api/books/search`)
- **CRUD completo** (criar, atualizar, excluir)
- **Ativação/Desativação** (`PATCH /api/books/{id}/status`)
- **Upload/Download de imagem** (`POST/GET /api/books/{id}/imagem`)

### 🏷️ Gestão de Categorias
- **Listagem completa** (`/api/categories`)
- **Busca por ID** (`/api/categories/{id}`)
- **Filtros paginados** (`/api/categories/filter`)
- **CRUD completo**
- **Ativação/Desativação** (`PATCH /api/categories/{id}/status`)
- **Soft delete** (campo `isDeleted`)

### 🔐 Autenticação e Autorização
- **Login JWT** (`POST /api/auth/login`)
- **Stateless** (session creation policy: STATELESS)
- **BCrypt** para hash de senha
- **Filtro JWT** personalizado para validação de token
- **Permissões baseadas em roles** (ADMIN)

### 🌱 Seed de Dados Iniciais
- **28 categorias** pré-cadastradas (Informática, Matemática, Física, Literatura, etc.)
- **200+ livros** de exemplo com capas reais (OpenLibrary) e placeholders
- **Usuário admin** padrão criado na inicialização

---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/api_test/book/
├── BookApplication.java           # Classe principal + seed admin
├── config/
│   ├── SecurityConfig.java        # Config Spring Security + JWT
│   ├── CorsConfig.java            # CORS para frontend
│   ├── OpenApiConfig.java         # Swagger/OpenAPI 3
│   ├── JwtTokenProvider.java      # Geração/validação JWT
│   ├── JwtTokenFilter.java        # Filtro de autenticação
│   ├── DataSeederConfig.java      # Seed categorias + livros
│   └── AuthController.java        # Endpoint /api/auth/login
├── controllers/
│   ├── BookController.java        # REST /api/books
│   └── CategoryController.java    # REST /api/categories
├── dto/                           # Data Transfer Objects
│   ├── BookDTO, BookFormDTO, BookFilterDTO, BookGridDTO
│   └── CategoryDTO, CategoryFormDTO, CategoryFilterDTO, CategoryGridDTO
├── exception/
│   ├── ResourceExceptionHandler.java  # Global exception handler
│   ├── ObjectNotFoundException.java   # 404 customizado
│   └── StandardError.java             # Padronização de erro
├── mapper/
│   ├── BookMapper.java            # Entity ↔ DTO
│   └── CategoryMapper.java
├── model/
│   ├── Book.java                  # Entidade JPA
│   ├── Category.java              # Entidade JPA
│   └── AdminUser.java             # Usuário admin (Spring Security)
├── repositories/
│   ├── BookRepository.java        # Spring Data JPA
│   ├── CategoryRepository.java
│   └── AdminUserRepository.java
├── service/
│   ├── BookService.java           # Regras de negócio
│   ├── CategoryService.java
│   └── AdminUserService.java
└── storage/
    └── ImageStorageService.java   # Armazenamento local de imagens
```

---

## 🔗 Endpoints da API

### Autenticação
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/login` | Login e geração de token JWT |

### Livros (`/api/books`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/` | Lista paginada (todos) |
| `GET` | `/ativos` | Lista paginada (apenas ativos) |
| `GET` | `/destaques` | Lista para carrossel (limit) |
| `GET` | `/{id}` | Busca por ID |
| `POST` | `/search` | Busca com filtros (title, author, categoryId, year, price range) |
| `POST` | `/` | Criar livro |
| `PUT` | `/{id}` | Atualizar livro |
| `DELETE` | `/{id}` | Excluir livro |
| `PATCH` | `/{id}/status` | Ativar/Desativar |
| `POST` | `/{id}/imagem` | Upload imagem (multipart) |
| `GET` | `/{id}/imagem` | Download imagem |

### Categorias (`/api/categories`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/` | Lista todas |
| `GET` | `/{id}` | Busca por ID |
| `POST` | `/filter` | Filtros paginados (name, isActive) |
| `POST` | `/` | Criar categoria |
| `PUT` | `/{id}` | Atualizar categoria |
| `DELETE` | `/{id}` | Excluir (soft delete) |
| `PATCH` | `/{id}/status` | Ativar/Desativar |

---

## ⚙️ Configuração e Execução

### Pré-requisitos
- **Java 11+**
- **Maven** (ou use o wrapper `./mvnw`)

### Variáveis de Ambiente (application.properties)
```properties
# Banco H2 (memória)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create

# JWT
app.jwt.secret=myVerySecretKeyForJWTTokenGenerationAndSigning2024BibliotecaDigitalSecurityKey512Bits!
app.jwt.expiration-ms=86400000  # 24h

# Imagens enviadas (caminho relativo ao diretório de execução)
app.upload.dir=uploads

# Servidor
server.port=8080
```

### Executar
```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API estará disponível em: **http://localhost:8080**

- **H2 Console**: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:testdb`)
- **Swagger UI**: http://localhost:8080/swagger-ui.html (se configurado)

---

## 🔐 Autenticação JWT

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

**Resposta:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "username": "admin",
  "roles": ["ADMIN"]
}
```

### Usar Token
```bash
curl -H "Authorization: Bearer <seu-token>" \
  http://localhost:8080/api/books
```

---

## 📊 Modelo de Dados

### Book (Livro)
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-------------|-----------|
| id | Long | Não (auto) | Identificador único |
| title | String | Sim | Título (max 200) |
| author | String | Sim | Autor (max 150) |
| text | String | Não | Descrição/sinopse |
| yearPublication | Integer | Não | Ano de publicação |
| price | BigDecimal | Sim | Preço (precision 10,2) |
| imagem | String | Não | Path/URL da capa |
| ativo | Boolean | Não (default true) | Status ativo |
| category | Category | Sim | Categoria (ManyToOne) |
| createdAt | LocalDateTime | Não (auto) | Data criação |
| updatedAt | LocalDateTime | Não (auto) | Data atualização |

### Category (Categoria)
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-------------|-----------|
| id | Long | Não (auto) | Identificador único |
| name | String | Sim | Nome (max 150) |
| description | String | Não | Descrição (max 500) |
| isActive | Boolean | Não (default true) | Status ativo |
| isDeleted | Boolean | Não (default false) | Soft delete |
| createdAt | LocalDateTime | Não (auto) | Data criação |
| updatedAt | LocalDateTime | Não (auto) | Data atualização |
| books | List<Book> | Não | Livros da categoria (OneToMany) |

---

## 🧪 Testes

```bash
# Executar testes unitários
./mvnw test
```

Testes existentes:
- `BookApplicationTests.java` - Context load
- `JwtTokenProviderTest.java` - Validação geração/parse JWT

---

## 📦 Build para Produção

```bash
# Gerar JAR executável
./mvnw clean package

# Executar JAR
java -jar target/book-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Docker (Opcional)

```dockerfile
FROM openjdk:11-jre-slim
COPY target/book-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 🔧 Configuração CORS

O `CorsConfig.java` permite requisições do frontend Angular (padrão `http://localhost:4200`):

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    };
}
```

---

## 📝 Observações Importantes

1. **Banco H2 em memória**: Dados são perdidos ao reiniciar (ideal para dev/teste). Para produção, configure PostgreSQL/MySQL no `application.properties`.

2. **JWT Secret**: Em produção, use variável de ambiente para `app.jwt.secret` (mínimo 512 bits para HS512).

3. **Upload de Imagens**: Armazenamento local no diretório definido por `app.upload.dir` (padrão `uploads/`); arquivos são servidos em `/uploads/**`. Para produção, considere S3/Cloud Storage.

4. **Permissões**: Atualmente `/api/books/**` e `/api/categories/**` são públicas (`permitAll()`). Ajuste conforme necessidade de autorização.

5. **Seed de Dados**: Executa automaticamente na primeira inicialização (verifica se tabelas estão vazias).

---

## 📄 Licença

Projeto educacional / acadêmico - Biblioteca Digital Full Stack.

---

## 🤝 Contribuição

1. Fork o projeto
2. Crie branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit (`git commit -m 'feat: adiciona nova funcionalidade'`)
4. Push (`git push origin feature/nova-funcionalidade`)
5. Abra Pull Request

---

**Desenvolvido com Spring Boot + Angular** 🚀