# Pokedex_Compose

This Pokedex - Pokemon app is a project that contain and apply the latest Android technologies
recommended by Google such as Jetpack Compose, the new way to build the design.
Also, it contains some other important technologies such as Room Database, KSP, Hilt Dependencies
Injection, the new Shared Element Transaction and new Navigation Type Safety. <br />

> [!IMPORTANT]  
> Similar project with Flutter (Dart Language) :point_right: [Pokedex_Flutter](https://github.com/NicosNicolaou16/Pokedex_Flutter) :point_left: <br />

# Examples

<p align="left">
  <a title="simulator_image"><img src="examples/Screenshot_20240511_012335.png" height="500" width="200"></a>
  <a title="simulator_image"><img src="examples/Screenshot_20240816_221338.png" height="500" width="200"></a>
  <a title="simulator_image"><img src="examples/Screenshot_20240816_221449.png" height="500" width="200"></a>
  <a title="simulator_image"><img src="examples/example_gif1.gif" height="500" width="200"></a>
</p>

# The Project Contain the following technologies

The programming language is the [Kotlin](https://kotlinlang.org/docs/getting-started.html), it is a
modern, JVM-based programming language that is concise, safe, and interoperable with Java. <br />
[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) is used for asynchronous
tasks. <br />
[Kotlin KTX](https://developer.android.com/kotlin/ktx) is a collection of Kotlin extensions that
offer more concise and expressive code for working with Android APIs and libraries.
The UI is build using [Jetpack Compose](https://developer.android.com/develop/ui/compose). <br />
For Navigation between screens is use
the [New Navigation Type Safety](https://medium.com/androiddevelopers/navigation-compose-meet-type-safety-e081fb3cf2f8). <br />
For Animation and Navigation from the main screen to details screen is use the
new [Shared Element Transition](https://developer.android.com/develop/ui/compose/animation/shared-elements). ([Shared Element Transition - Article](https://fvilarino.medium.com/shared-element-transitions-in-jetpack-compose-8f553078101e), [Shared Element Transition - Article](https://getstream.io/blog/shared-element-compose/))  <br />
[Retrofit](https://square.github.io/retrofit/) is responsible for making requests and retrieving
data from the remote server. ([Repository](https://github.com/square/retrofit)) <br />
[Room Database](https://developer.android.com/training/data-storage/room) is responsible for saving
the retrieved data from the remote server, querying data from the local database, and supporting
offline functionality.  <br />
[Palette](https://developer.android.com/develop/ui/views/graphics/palette-colors) is used to
retrieve the color from the image; in our case, we are using the Pokémon color to paint the linear
indicator with the same color. <br />
[KSP](https://developer.android.com/build/migrate-to-ksp) ("Kotlin Symbol Processing") is a tool for
efficient annotation processing in Kotlin, providing faster code generation and symbol manipulation
compared to KAPT. [Repository](https://github.com/google/ksp) <br />
[Coil](https://coil-kt.github.io/coil/compose/) for Jetpack Compose is a library that it is
responsible for loading the images
asynchronous. ([Coil Documentation](https://coil-kt.github.io/coil/), [Repository](https://github.com/coil-kt/coil)) <br />
[Hilt Dependencies Injection](https://developer.android.com/training/dependency-injection/hilt-android)
is an Android library that simplifies dependency injection by using annotations to automatically
manage and provide dependencies across components, built on top of
Dagger. ([Documentation](https://dagger.dev/hilt/)) <br />
[MVVM](https://developer.android.com/topic/architecture#recommended-app-arch) with repository is an
architecture where the Repository manages data sources (e.g., network, database), the ViewModel
processes the data for the UI, and the View displays the UI, ensuring a clear separation of
concerns. <br />
[UI State](https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events)
to initial, loading, loaded and error. <br />
[R8](https://developer.android.com/build/shrink-code) enabled, is a code shrinker and obfuscator for
Android that optimizes and reduces the size of APKs by removing unused code and resources, while
also obfuscating the remaining code to improve security. <br />
The percentage for showing the skills of each Pokémon is calculated using
the [PercentageWithAnimation](https://github.com/NicosNicolaou16/PercentagesWithAnimationCompose)
built by @NicosNicolaou16. <br />

# Versioning

Target SDK version: 35 <br />
Minimum SDK version: 28 <br />
Kotlin version: 2.1.21 <br />
Gradle version: 8.10.1 <br />

# Feeds/Urls/End Point (parsing some data from the response)

## (Links References for Ends Points)

https://pokeapi.co/ <br />
https://github.com/PokeAPI/sprites (GitHub) <br />

# References

https://fvilarino.medium.com/shared-element-transitions-in-jetpack-compose-8f553078101e <br />
https://getstream.io/blog/shared-element-compose/  <br />
https://medium.com/androiddevelopers/navigation-compose-meet-type-safety-e081fb3cf2f8  <br />
https://dribbble.com/shots/6540871-Pokedex-App# - Get some UI - not completely use it (CHECK THE
LINK FOR THE DESIGN)  <br />
