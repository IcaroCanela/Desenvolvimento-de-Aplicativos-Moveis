# Desenvolvimento-de-Aplicativos-Moveis

---

# Receitas Deliciosas 🍽️

O **Receitas Deliciosas** é um aplicativo móvel que permite aos usuários explorar e descobrir receitas incríveis. Com a integração da **Spoonacular API**, você terá acesso a milhares de receitas, informações nutricionais e muito mais!

## Funcionalidades

1. **Feed Interativo**
   - Role pelo feed para ver uma variedade de receitas deliciosas.
   - Toque em uma receita para obter detalhes completos, incluindo ingredientes, instruções e informações nutricionais.

2. **Pesquisa Inteligente**
   - Use a barra de pesquisa para encontrar receitas específicas.
   - Pesquise por palavras-chave, ingredientes ou tipos de prato.

3. **Filtro por Tipo de Comida**
   - Filtrar receitas com base no tipo de comida desejado (vegetariano, vegano, sem glúten, etc.).

4. **Perfil de Favoritos**
   - Salve suas receitas favoritas para acessá-las facilmente mais tarde.
   - Marque receitas como favoritas para criar sua própria coleção personalizada.

## Como Começar

1. **Obtenha uma Chave de API**
   - Acesse o site da [Spoonacular](https://spoonacular.com/contact) para obter sua chave de API.
   - Substitua `"YOUR API KEY"` no código pelo valor real da sua chave.

2. **Configuração do Projeto**
   - Crie um novo projeto no Android Studio.
   - Defina o nome do pacote como `com.example.receitasdeliciosas`.

3. **Dependências**
   - Adicione a dependência da Spoonacular API ao seu arquivo `build.gradle`:
     ```gradle
     implementation "com.spoonacular:java-client:1.1"
     ```

4. **Exemplo de Uso**
   - Configure o cliente da API:
     ```java
     ApiClient defaultClient = Configuration.getDefaultApiClient();
     defaultClient.setBasePath("https://api.spoonacular.com");
     ApiKeyAuth apiKeyScheme = (ApiKeyAuth) defaultClient.getAuthentication("apiKeyScheme");
     apiKeyScheme.setApiKey("YOUR API KEY");

     DefaultApi apiInstance = new DefaultApi(defaultClient);
     // Exemplo: Obter informações de uma receita
     AnalyzeRecipeRequest analyzeRecipeRequest = new AnalyzeRecipeRequest();
     // Preencha os detalhes da requisição conforme necessário.
     ```

5. **Divirta-se!**
   - Explore as receitas, salve suas favoritas e aproveite a culinária!

---
