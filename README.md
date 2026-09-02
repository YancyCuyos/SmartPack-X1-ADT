# SmartPack X1 – Futuristic Object Simulation

## Project Description

SmartPack X1 is a futuristic redesign of an ordinary backpack. It is designed to make carrying personal belongings safer, smarter, and more convenient.

The SmartPack uses an Abstract Data Type (ADT) to manage its data and operations. A simple Java Swing graphical interface is used to demonstrate how the backpack works.

## Futuristic Features

* ☀️ **Solar Charging** – Uses simulated solar energy to increase the backpack's battery level.
* 📍 **GPS Tracking** – Allows the user to activate GPS tracking.
* 🚨 **Anti-Theft Alarm** – Activates an alarm when unauthorized access is detected.
* ⚖️ **Smart Weight Monitoring** – Checks whether the backpack is approaching its maximum capacity.
* 🔐 **Smart Locking** – The backpack can be locked and unlocked through the application.

## ADT Components

### Attributes / Data

The `SmartPack` ADT contains:

* `owner`
* `battery`
* `capacity`
* `currentLoad`
* `locked`
* `gpsOn`
* `alarmOn`
* `items`

### Operations / Methods

The ADT provides operations such as:

* `unlock()`
* `lock()`
* `addItem()`
* `removeItem()`
* `solarCharge()`
* `toggleGPS()`
* `triggerAlarm()`
* `turnOffAlarm()`
* `isOverloaded()`

## Files

```text
src/
├── SmartPack.java
└── SmartPackApp.java
```

### SmartPack.java

Contains the Abstract Data Type, including its private attributes, constructor, and methods.

### SmartPackApp.java

Contains the graphical user interface and acts as the Tester/Driver program. It creates a `SmartPack` object and calls its methods through buttons and user interactions.

## Sample Simulation

The application demonstrates the following sequence:

1. Start the SmartPack application.
2. Unlock the backpack.
3. Add items such as a laptop, books, water bottle, and lunch.
4. Check the backpack's current load.
5. Activate GPS tracking.
6. Activate solar charging.
7. Lock the backpack.
8. Test the anti-theft alarm.
9. View the final SmartPack status.

Screenshots of the simulation are available in the `screenshots` folder.

## How to Run

1. Install Java JDK.
2. Open the project in Visual Studio Code.
3. Open `SmartPackApp.java`.
4. Run the `main()` method.
5. The SmartPack X1 graphical application will open.

## AI-Use Disclosure

AI was used as an assistance tool during the development of this project. It helped generate ideas for the futuristic object, organize the Abstract Data Type (ADT), provide Java coding suggestions, and assist with debugging and improving the program. The code was reviewed, tested, and modified to ensure that I understood how the ADT, methods, and simulation work. I am responsible for understanding and explaining the final program.

## Technologies Used

* Java
* Java Swing
* Object-Oriented Programming
* Abstract Data Type (ADT)
* Visual Studio Code
* GitHub
