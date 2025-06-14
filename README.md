# TrelloChallenge 📅

Consuming the [Trello API](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/)  to show user cards list. Using Jetpack Compose to build the UI with Clean Architecture.

### Features 🖋️
- **List screen with user cards**

### Technical Requirements 🏆
| Android            |
|--------------------|
| Kotlin             |
| Compose            |
| Kotlin Flow        |
| Clean Architecture |
| SOLID              |
| MVI                |
| Koin               |
| Navigation         |
| Unit Tests         |
| UI Tests           |
| CI / CD            |
| Proguard           |

### How to run the project 🏃

- Clone the repository.
- Open the project in Android Studio.
- Make sure you have the Android SDK installed.
- Make sure you have a valid Trello API key and Token by [generated doc](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/) it to the `local.properties` file as `TRELLO_KEY` and `TRELLO_TOKEN`.
- Run the project on an emulator or Android device.

### Architectural decisions 💻

The use of MVI was purely to simplify the use of compose, it tends to be extremely simple and visually easy to understand the actions and code flow with it.
The Clean Architecture in 3 layers simply to have basically the source data, the remote data and the domain, the use case to apply the position to the BoardItem model. Initially, there was no need for more layers and increase complexity.

### Solution strengths 📶

Koin is already prepared to apply injection for new modules if the project grows. Even if there is a need to add new features or make changes, the project is easy to implement.
Unit and UI tests are ensuring main flows including CI/CD as well as ProGuard for the release version.