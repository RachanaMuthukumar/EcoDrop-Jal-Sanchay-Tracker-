Jal Sanchay Tracker

##  Overview
This Android application helps households **track rainwater collected**, convert it into **“water days”** (how many days of usage the water covers), and display both **total savings** and **today’s savings**.  
The app is designed to be **practical, visual, and easy to use**, encouraging sustainable water practices.

---

##  Project Structure

###  Data Layer
- `data/Data.kt`  

###  UI Layer
- `home/HomeActivity.kt` – Main navigation hub.  
- `info/InfoActivity.kt` – Displays app information and usage tips.  
- `input/InputActivity.kt` – Handles user input (roof area, tank size, rainfall).  
- `navigation/SidebarAdapter.kt` – Sidebar navigation adapter.  
- `progress/ProgressActivity.kt` – Shows tank fill level with progress bar.  
- `reports/ReportsActivity.kt` – Displays daily and total savings reports.  
- `theme/Color.kt` – Color palette definitions.  
- `theme/Theme.kt` – App theme setup.  
- `theme/Type.kt` – Typography styles.

###  ViewModel Layer
- `ViewModel/SharedViewModel.kt`  

###  Root Files
- `MainActivity.kt` – Entry point of the application.  
- **Drawables** – Contains two images used in the UI.

---

##  Features
- **Progress Bar** – Visual representation of water collected.  
- **Input Screen** – User setup and rainfall entry with validation.  
- **Reports Screen** – Displays today’s savings and total savings.
## Screenshots
### Progress Screen
![Alt text](Screenshot%202026-05-15%20232114.png)
### Home Screen
![Alt text](Screenshot%202026-05-03%20210951.png)
### Side Bar
![Alt text](Screenshot%202026-05-03%20211002.png)
### Inputs Screen
![Alt text](Screenshot%202026-05-15%20231936.png)
### Info Screen
![Alt text](/Screenshot%202026-05-03%20211207.png)
### Reports Screen
![Alt text](Screenshot%202026-05-15%20232125.png)
