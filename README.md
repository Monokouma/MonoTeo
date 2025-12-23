# MonoTeo API 🌤️

<p align="center">
  <strong>Backend météo pour l'application MonoTeo</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9-7F52FF?style=flat&logo=kotlin" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Ktor-2.x-087CFA?style=flat" alt="Ktor"/>
  <img src="https://img.shields.io/badge/Architecture-Clean-brightgreen?style=flat" alt="Clean"/>
</p>

---

## 🎯 Description

API backend servant de proxy entre l'application MonoTeo et l'API OpenWeatherMap. Gère la récupération des données météo, le caching et la transformation des réponses.

---

## 🏗️ Architecture
```
MonoTeo/
├── data/
│   ├── api/              # Clients HTTP
│   ├── dto/              # Data Transfer Objects
│   ├── mapper/           # DTO → Entity
│   └── repository/       # Implémentations
├── domain/
│   ├── entity/           # Modèles métier
│   ├── repository/       # Interfaces
│   └── usecase/          # Logique métier
└── di/                   # Injection de dépendances
```

---

## 🛠️ Stack

| Technologie | Usage |
|-------------|-------|
| **Kotlin** | Langage principal |
| **Ktor Client** | Requêtes HTTP |
| **Kotlinx Serialization** | JSON parsing |
| **Koin** | Dependency Injection |
| **Coroutines** | Async |

---

## 📡 Endpoints utilisés

### OpenWeatherMap
```
GET /data/2.5/weather
  ?lat={latitude}
  &lon={longitude}
  &appid={API_KEY}
  &units=metric
  &lang={language}
```

**Response transformée** :
```kotlin
data class WeatherEntity(
    val condition: String,      // "Clear", "Rain", "Snow"...
    val temperature: Double,    // En Celsius
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val visibility: Int,
    val sunrise: Long,
    val sunset: Long,
    val city: String,
    val country: String,
    val icon: String
)
```

---

## 🚀 Utilisation

Ce module est intégré directement dans l'app KMP via le layer `data/`.
```kotlin
// Use case
class GetWeatherForCurrentLocationUseCase(
    private val requestUserPositionUseCase: RequestUserPositionUseCase,
    private val requestWeatherApiUseCase: RequestWeatherApiUseCase
) {
    operator fun invoke(): Flow<Result<WeatherEntity>> = 
        requestUserPositionUseCase()
            .flatMapLatest { position ->
                requestWeatherApiUseCase(position)
            }
}
```

---

## 📱 Application cliente

Voir [MonoTeoApp](https://github.com/Monokouma/MonoTeoApp) - Application Kotlin Multiplatform (Android/iOS)

---

## 📄 Licence

MIT License - Copyright (c) 2025 Flac Inc

---

<p align="center">
  Made with ❤️ by <a href="https://github.com/Monokouma">Monokouma</a>
</p>