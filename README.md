## EQUALL Android Engineer Challenge

**A state-driven loan lifecycle experience built using Compose Multiplatform.**

## Screenshots

<img width="268" height="581" alt="Screenshot 2026-06-06 at 5 59 16 PM" src="https://github.com/user-attachments/assets/dfddfcaf-6fd7-4c6e-aba7-74af08311cc2" />

<img width="271" height="576" alt="Screenshot 2026-06-06 at 5 59 40 PM" src="https://github.com/user-attachments/assets/fb5a7ffb-784b-49c9-9ad4-cfb80bc5bd33" />

<img width="270" height="581" alt="Screenshot 2026-06-06 at 6 00 18 PM" src="https://github.com/user-attachments/assets/6a740ed1-cc22-4c87-a8fc-be15cc5c8f5b" />

<img width="269" height="583" alt="Screenshot 2026-06-06 at 6 00 31 PM" src="https://github.com/user-attachments/assets/a29f4729-e901-41f1-bc2d-698ffcf6a8da" />


## Tech Stack

* Compose Multiplatform (CMP)
* Jetpack Compose
* MVVM Architecture
* StateFlow

**Architecture**
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

## Testing

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

## Author

Janice Fernandes
