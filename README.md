# Pokedex Compose  Pokédex App

[![Linktree](https://img.shields.io/badge/linktree-1de9b6?style=for-the-badge&logo=linktree&logoColor=white)](https://linktr.ee/nicos_nicolaou)
[![Site](https://img.shields.io/badge/Site-blue?style=for-the-badge&label=Web)](https://nicosnicolaou16.github.io/)
[![X](https://img.shields.io/badge/X-%23000000.svg?style=for-the-badge&logo=X&logoColor=white)](https://twitter.com/nicolaou_nicos)
[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/nicos-nicolaou-a16720aa)
[![Medium](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@nicosnicolaou)
[![Mastodon](https://img.shields.io/badge/-MASTODON-%232B90D9?style=for-the-badge&logo=mastodon&logoColor=white)](https://androiddev.social/@nicolaou_nicos)
[![Bluesky](https://img.shields.io/badge/Bluesky-0285FF?style=for-the-badge&logo=Bluesky&logoColor=white)](https://bsky.app/profile/nicolaounicos.bsky.social)
[![Dev.to blog](https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=dev.to&logoColor=white)](https://dev.to/nicosnicolaou16)
[![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=YouTube&logoColor=white)](https://www.youtube.com/@nicosnicolaou16)
[![Google Developer Profile](https://img.shields.io/badge/Developer_Profile-blue?style=for-the-badge&label=Google)](https://g.dev/nicolaou_nicos)

A modern Pokédex app for Android built with the latest technologies recommended by Google. This
project showcases a clean, scalable architecture and a fluid user interface using Jetpack Compose.

> [!IMPORTANT]  
> A similar project is also available in Flutter (Dart)!  
> 👉 **[Pokedex_Flutter](https://github.com/NicosNicolaou16/Pokedex_Flutter)** 👈

## ✨ Features

* **Modern UI:** Built entirely with **Jetpack Compose** for a declarative and intuitive UI.
* **Dynamic Theming:** Extracts prominent colors from Pokémon images using the **Palette API** to
  create dynamic and immersive detail screens.
* **Seamless Transitions:** Implements the new **Shared Element Transition** for smooth navigation
  between the list and detail views.
* **Offline Support:** Caches Pokémon data using **Room Database**, allowing the app to work
  offline.
* **Efficient Networking:** Fetches data from the [PokéAPI](https://pokeapi.co/) using **Retrofit**.
* **Optimized Performance:** Leverages **Coroutines** for asynchronous operations, **KSP** for
  faster annotation processing, and **R8** for code shrinking.
* **Scalable Architecture:** Follows the **MVVM** pattern with a repository, ensuring a clean
  separation of concerns and maintainable code.
* **Navigation:** Leverages **Navigation 3** and its **Type Safety** features for robust,
  compile-time checked, and error-free navigation.

## 📸 Screenshots & Demos

<p align="left">
  <img src="examples/Screenshot_20240511_012335.png" alt="Pokemon List Screen" height="500" width="200">
  <img src="examples/Screenshot_20240816_221338.png" alt="Pokemon Detail Screen" height="500" width="200">
  <img src="examples/Screenshot_20240816_221449.png" alt="Pokemon Stats" height="500" width="200">
  <img src="examples/example_gif1.gif" alt="List to Detail Transition" height="500" width="200">
  <img src="examples/example_gif2.gif" alt="Scrolling and Searching" height="500" width="200">
</p>
<p align="left">
  <img src="examples/Screenshot_20251214_201927.png" alt="Landscape Detail View" height="500" width="400">
  &nbsp;
  <img src="examples/example_gif3.gif" alt="Landscape to Portrait Transition" height="500" width="400">
</p>

## 🛠️ Tech Stack & Libraries

This project is built with **[Kotlin](https://kotlinlang.org/docs/getting-started.html)** and
utilizes a variety of modern Android libraries and tools:

- **UI:** [Jetpack Compose](https://developer.android.com/develop/ui/compose), [Coil](https://coil-kt.github.io/coil/compose/) (
  Image
  Loading), [Palette API](https://developer.android.com/develop/ui/views/graphics/palette-colors)
- **Architecture:** [MVVM](https://developer.android.com/topic/architecture#recommended-app-arch), [UI State Management](https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events)
- **Asynchronicity:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html), [Kotlin KTX](https://developer.android.com/kotlin/ktx)
- **Navigation:** [Navigation Compose 3](https://developer.android.com/guide/navigation/navigation-3), [Navigation Type Safety](https://medium.com/androiddevelopers/navigation-compose-meet-type-safety-e081fb3cf2f8), [Shared Element Transition](https://developer.android.com/develop/ui/compose/animation/shared-elements)
- **Data:** [Retrofit](https://square.github.io/retrofit/) (
  Networking), [Room](https://developer.android.com/training/data-storage/room) (Database)
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Build & Optimization:** [KSP](https://developer.android.com/build/migrate-to-ksp), [R8](https://developer.android.com/build/shrink-code)
- **Custom Components:** [PercentageWithAnimation](https://github.com/NicosNicolaou16/PercentagesWithAnimationCompose)
  for displaying Pokémon stats built by [@NicosNicolaou16](https://github.com/NicosNicolaou16).

## 🔧 Versioning

- **Target SDK:** **36**
- **Minimum SDK:** **29**
- **Kotlin Version:** **2.3.10**
- **Gradle Version:** **9.1.0**

## 📚 APIs & References

### Data Sources

- **Primary API:** [PokéAPI (pokeapi.co)](https://pokeapi.co/)
- **Image Sprites:** [PokeAPI/sprites on GitHub](https://github.com/PokeAPI/sprites)

### Articles & Inspiration

- **Shared Element Transitions:**
    - [Shared Element Transitions in Jetpack Compose](https://fvilarino.medium.com/shared-element-transitions-in-jetpack-compose-8f553078101e)
    - [Building a Shared Element Transition in Jetpack Compose](https://getstream.io/blog/shared-element-compose/)
- **Type-Safe Navigation:**
    - [Navigation-Compose meets Type-Safety](https://medium.com/androiddevelopers/navigation-compose-meet-type-safety-e081fb3cf2f8)
- **UI/UX Design Inspiration:**
    - [Dribbble - Pokedex App by Alexandr](https://dribbble.com/shots/6540871-Pokedex-App) (Note:
      Used as inspiration, not a direct copy)
- **App Icon:**
    - Derived
      from [Fandom Ideas Wiki](https://ideas.fandom.com/wiki/Pok%C3%A9_Ball_Pok%C3%A9mon_Ideas_(Super_Smash_Bros._series)?file=Pok%C3%A9_Ball_Redraw_by_oykawoo.png)

## ⭐ Stargazers

If you enjoy this project, please give it a star!
Check out all the stargazers
here: [Stargazers on GitHub](https://github.com/NicosNicolaou16/Pokedex_Compose/stargazers)

## 🙏 Support & Contributions

This project is actively maintained. Feedback, bug reports, and feature requests are welcome! Please feel free to **open an issue** or submit a **pull request**.