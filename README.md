# *Kotlin Multiplatform Project*

This is a *Kotlin Multiplatform* project targeting both *Android* and *iOS* platforms.

![image](https://github.com/md-alramlawi/Museum/assets/60019872/a632abf3-4293-4f16-9aac-59ec1346b343)

## *Project Structure*

### *Android Application*
The Android application is located in the `/androidApp` directory.

### *Shared Code*
The shared code for *Compose Multiplatform* applications resides in the `/shared` directory. It contains several subfolders:

- **`commonMain`**: Contains code that is common across all targets.
- **Platform-specific folders**: These folders contain Kotlin code that is compiled specifically for the indicated platform. For example:
  - **`iosMain`**: Use this folder for *iOS-specific code*, such as calls to *Apple’s CoreCrypto*.
  - **`androidMain`**: Use this folder for *Android-specific code*, such as calls to *Android libraries*.

### *iOS Application*
The iOS application is located in the `/iosApp` directory. Even if you are sharing your UI with *Compose Multiplatform*, you need this entry point for your *iOS app*. This is also where you should add any *SwiftUI code* for your project.

## *Learn More*
To learn more about *Kotlin Multiplatform*, visit the [official documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
