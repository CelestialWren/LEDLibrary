# LED Library
A simple LED driver to abstract the base code of LEDs in WPIlib. It uses a subsystem that you can extend called LED to set the colors. To manage moveing patterns and dynamic elements, extend the Animation class to create a file that generates the hash map to control the LEDs or use a pre-defined Automation. Documentaion will be added later.

## Building
1. Clone Repository
2. Run ./gradlew build in a terminal
3. JAR should be placed into the build folder

## Adding to Build System
Credit for this system of atting libraries without using WPIlib jsons goes to Dannie on team 4272, Maverick Boiler Robotics.
Their code is great! You can find it here: https://github.com/maverick-boiler-robotics-team-4272

1. Create a folder called libs in the root directory of the project
2. Add built library to the libs folder
3. Go into build.gradle and add 
```groovy
repositories {
  flatDir {
    dirs "./libs" 
  }
}
```
4. Find dependencies block and add ```implementation name:'[insert name of JAR here, omit the .jar]'``` after the ```implementation wpi.java.vendor.java()``` line

5. Add ```!libs/*.jar``` to your .gitignore to allow the file to be added to your repo. (or analagous action for other version tracking software)