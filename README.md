**EQUALL Android Engineer Challenge**

**A state-driven loan lifecycle experience built using Compose Multiplatform.**

**Tech Stack**

* Compose Multiplatform (CMP)
* Jetpack Compose
* MVVM Architecture
* Kotlin Coroutines
* StateFlow
* Architecture

The application follows a state-driven architecture where the Home screen dynamically adapts based on the user's loan lifecycle state.

**Key principles:**

* Unidirectional data flow
* Sealed UI states
* Reusable composables
* Separation of UI, domain layers

States Implemented 

**Discovery Phase**
**Pending Review Phase
Active Loan Management Phase**

**Testing**

A developer testing menu is available via the floating action button on the Home screen.

Use this menu to switch between:

Discovery Phase
Pending Review Phase
Active Loan Management Phase

This allows all lifecycle states to be reviewed without additional setup.

Build
Android
./gradlew :androidApp:assembleDebug
iOS

Open the iosApp project in Xcode and run the application.

**Author**

Janice Fernandes
