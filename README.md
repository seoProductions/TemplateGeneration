# Template Generation made in JAVA!

Template generation maps a 2D vector field of integers onto the JavaFX application and its grid. Many settings are provided for tweaking and expiriementing, Along with some pre-made "Templates" that modify the 2D vector field 
directly. Have some fun! 

## Features

Here is a class diagram of my project:

![image](https://github.com/seoProductions/TemplateGeneration/assets/111206081/a96dc700-dc43-4775-8033-1bd0a39fc0c2)

as usual, Each class has its own functionality and behavior. Take a look:

![image](https://github.com/seoProductions/TemplateGeneration/assets/111206081/4f5b8118-8051-46ea-a9a5-a6691e710e6b)

This is the advantage of Object Oriented programming! I am able to  create different classes and organize them for specific needs.

## Project Design

At the center of it all is the ***APP***. Its responsible for
 - Creating window (using JavaFX)
 - Loading FXML file onto the application (Similar to HTML)
 - Setting application Controller & Initializing some Event listeners for FXML components
 - Setting window as draggable
 - Instantiating all other classes (TileManager, AppController, ect...)

The ***AppController*** class an extention (subclass) of JavaFX's Initializable class. Essencially, it's what "makes the application alive". Its responsible for
 - Initializing Events listeners for FXML components. (sliders, boxes, ect...)
 - Applying inline CSS style to FXML components.
 - Handling behavior and selection options from the GUI components.

The ***TileManager*** handles the "Tiles/Rectangles" on the screen. Its responsiblity is to
  -  Retrieve values from FXML components
  -  Initialize & manage 2D Vector field of integers (IntField)
  -  Initialize & manage grid of "FancyRectangles" (the one you see on screen)
  -  Animate "FancyRectangles" according to the rendering algorithm
  -  Handle Animations using JavaFX's TimeLine class

The ***FancyRectange*** class uses composition of JavaFX's Rectangle class. It is responsible for
  - Highlighting Tiles/Rectangles when the user's mouse is hovering over
  - Modifying CSS properties
  - Storing current color and refrence to its Rectangle object

The ***DraggableMaker*** class simply makes the window draggable by your mouse.
 - The App is responsible for initializing ***DraggableMaker***, and with this **->** it waits for a mouse' click and runs some calculations.

The ***IntField*** class holds the 2D vector/array of integers I have been talking about. It 
- Initializes 2D vector
- Holds the backbone for modifying these integer values
- Houses a few additional helper "Templates" 
- handles many calculations and inbound checks
It also Supports the toString() funtion for debugging (This was my original prototype)

Finally, the ***GenerationTemplates*** class is a large collection of user-made "Templates". It has many expiriemental templates that I made during developement, aswell as some other interesting additions.
The lightning Template was by far the most challenging to create, but is by far my favorite.

## Member Variables

For more insight, take a look at the member variables for each class. It really highlight the "Behind the Scenes"

![image](https://github.com/seoProductions/TemplateGeneration/assets/111206081/7fc250d9-09c1-452c-90f5-303a9fa91e14)

## Context

This application was my end of year project that I built my 11'th grade year of Highschool! I had just finished up Object Oriented Programing in Java and I felt that I was ready to build something! And tada! 


I went from printing a 2D vector field onto the terminal **->** to creating a small scale application. 

I was a complete beginer with 

🚦🚧🔶 !!! this README is under construction !!!! 🚦🚧🔶
