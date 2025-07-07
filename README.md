# Crud Item App

Um aplicativo Android de CRUD (Create, Read, Update, Delete) de itens usando Jetpack Compose e Firebase Realtime Database.

## Funcionalidades

- ✅ Listar itens
- ✅ Adicionar novos itens
- ✅ Editar itens existentes
- ✅ Excluir itens
- ✅ Interface moderna com Material Design 3
- ✅ Navegação entre telas
- ✅ Integração com Firebase Realtime Database
- ✅ Sincronização em tempo real
- ✅ Arquitetura MVVM com ViewModel

## Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Jetpack Compose** - UI declarativa
- **Material Design 3** - Design system
- **Firebase Realtime Database** - Banco de dados em tempo real
- **ViewModel** - Gerenciamento de estado
- **Navigation Compose** - Navegação entre telas
- **Coroutines** - Programação assíncrona

## Estrutura do Projeto

```
app/
├── src/main/
│   ├── java/com/example/cruditemapp/
│   │   ├── data/
│   │   │   └── Item.kt
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── ItemListScreen.kt
│   │   │   │   └── ItemFormScreen.kt
│   │   │   ├── navigation/
│   │   │   │   └── AppNavigation.kt
│   │   │   ├── theme/
│   │   │   │   ├── Color.kt
│   │   │   │   ├── Theme.kt
│   │   │   │   └── Type.kt
│   │   │   └── ItemViewModel.kt
│   │   └── MainActivity.kt
│   ├── res/
│   │   ├── values/
│   │   │   ├── strings.xml
│   │   │   ├── colors.xml
│   │   │   └── themes.xml
│   │   └── xml/
│   │       ├── backup_rules.xml
│   │       ├── data_extraction_rules.xml
│   │       └── realtime_database_rules.xml
│   ├── AndroidManifest.xml
│   └── google-services.json
├── build.gradle.kts
└── ...
```

## Configuração do Firebase

1. Crie um projeto no [Firebase Console](https://console.firebase.google.com/)
2. Adicione um aplicativo Android com o package name `com.example.cruditemapp`
3. Baixe o arquivo `google-services.json` e substitua o arquivo existente em `app/google-services.json`
4. Ative o **Realtime Database** no console do Firebase
5. Configure as regras de segurança do Realtime Database:
   - Para desenvolvimento, use regras de teste
   - Para produção, configure regras de segurança adequadas

### Regras do Realtime Database

Para desenvolvimento, você pode usar estas regras no console do Firebase:

```json
{
  "rules": {
    "items": {
      ".read": true,
      ".write": true
    }
  }
}
```

Para produção, use regras mais seguras:

```json
{
  "rules": {
    "items": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
```

## Como Executar

1. Abra o projeto no Android Studio
2. Sincronize o projeto com os arquivos Gradle
3. Configure o arquivo `google-services.json` com suas credenciais do Firebase
4. Execute o aplicativo em um emulador ou dispositivo físico

## Funcionalidades do App

### Tela de Lista
- Exibe todos os itens cadastrados em tempo real
- Botão flutuante para adicionar novo item
- Botões de editar e excluir em cada item
- Estados de carregamento e erro
- Sincronização automática quando dados mudam
- Contador de itens no cabeçalho

### Tela de Formulário
- Campos para nome e descrição do item
- Validação de campos obrigatórios
- Suporte para adicionar e editar itens
- Navegação de volta para a lista
- Design moderno com Material Design 3

## Arquitetura

O projeto segue o padrão MVVM (Model-View-ViewModel):

- **Model**: Classe `Item` que representa os dados
- **View**: Telas Compose (`ItemListScreen`, `ItemFormScreen`)
- **ViewModel**: `ItemViewModel` que gerencia o estado e a lógica de negócio

## Vantagens do Realtime Database

- **Sincronização em tempo real**: Mudanças são refletidas instantaneamente
- **Offline-first**: Funciona mesmo sem conexão
- **Performance**: Otimizado para leitura/escrita frequente
- **Simplicidade**: API mais simples que o Firestore

## Dependências Principais

- `androidx.compose.ui` - Componentes UI do Compose
- `androidx.compose.material3` - Material Design 3
- `androidx.navigation.compose` - Navegação
- `androidx.lifecycle.viewmodel.compose` - ViewModel para Compose
- `com.google.firebase.database` - Firebase Realtime Database
- `kotlinx.coroutines.android` - Coroutines para Android

## Próximos Passos

- [ ] Adicionar autenticação de usuários
- [ ] Implementar busca e filtros
- [ ] Adicionar categorias aos itens
- [ ] Implementar sincronização offline avançada
- [ ] Adicionar testes unitários e de UI
- [ ] Implementar notificações push 