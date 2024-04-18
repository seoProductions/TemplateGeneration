# Template Generation made in JAVA!

Template generation maps a 2D vector field of integers onto the JavaFX application and its grid. Many settings are provided for tweaking and expiriementing, Along with some pre-made "Templates" that modify the 2D vector field 
directly. Please note that this project is aimed towards creativity :). Have some fun! 

## Features

Here is my project!

![first](https://github.com/seoProductions/TemplateGeneration/assets/111206081/0f59d4f0-892d-426a-83f8-29367aeedeaa)

## The templates I have created are the Following:

### Diagnal

![diagnal](https://github.com/seoProductions/TemplateGeneration/assets/111206081/463883c6-ab65-47a2-a85e-8318571ad231)


### Curves

Here I implimented some sinusoidal behavior

![curves](https://github.com/seoProductions/TemplateGeneration/assets/111206081/38b240de-e15f-4b0b-b109-ddacff036832)

### Spiral

again

![swirl](https://github.com/seoProductions/TemplateGeneration/assets/111206081/da8b9fd5-d4f0-4ca0-aab0-7e82bc48e013)

### ZigZags

![zigzag](https://github.com/seoProductions/TemplateGeneration/assets/111206081/77b5d53c-408b-4de6-8496-41f3a1adca91)


### C

Heres another interesting one:

![C](https://github.com/seoProductions/TemplateGeneration/assets/111206081/d0e4af37-b9a9-4105-8002-621620a7c3cd)

### Lightning!

My Favorite! This one's quite fun to play with

![lightning_magnitude](https://github.com/seoProductions/TemplateGeneration/assets/111206081/806cb90d-48df-4ba9-a730-a8a116d657eb)

Here it is again with: random magnitude [checked]

![lightning_random](https://github.com/seoProductions/TemplateGeneration/assets/111206081/8ed39e97-dc87-49a8-86ce-f1003ec3c0c0)


### Settings!

Here is a close up of my Setting's Implentation:

![settings](https://github.com/seoProductions/TemplateGeneration/assets/111206081/29610b02-53cc-40b5-80ad-7d407764ea84)

You can modify the sinusoidal behavior

![degrees](https://github.com/seoProductions/TemplateGeneration/assets/111206081/b45ab214-688c-4cdf-8966-bc700847f60b)

You can also change the grid size

![grid_size](https://github.com/seoProductions/TemplateGeneration/assets/111206081/ffa5a9a1-7c75-4d99-b443-49da4278fffe)

A visual play back speed could come in handy :)

![playbackspeed](https://github.com/seoProductions/TemplateGeneration/assets/111206081/972085b0-303f-4e0d-869b-bfed0d918147)

### More!

I created a Highlighting effect on the Tiles (or Rectangles). With this, you can acually see mouse in these demonstrations :)

![highlighting](https://github.com/seoProductions/TemplateGeneration/assets/111206081/71a96c32-7281-49e2-bc60-a957e39298fc)

I also created a Console that responds to any changes in the Application!

![console](https://github.com/seoProductions/TemplateGeneration/assets/111206081/0186f34d-292f-4b37-8d81-513d255f8d2f)

### Algorithm 2?
Yes I noted that too, dont worry its 🚦🚧🔶 under construction 🚦🚧🔶 :)


## Behind the Scenes - Visualy
My project heavily utilizes the concepts of Object Oriented Programing.
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

This application was my end of year project that I built my 11'th grade year of Highschool! I had just finished up Object Oriented Programing in Java and I felt that I was ready to build something! ... 

This project started off from simply printing a 2D array onto the terminal **->** and it turned into me creating a small scale application. Honestly I was a complete beginer with everything: java features, IDE's, git, debugging, Frameworks, Project Design (A big one!!!), and soo many more. All these I learned throught my deveopement of this App, I even had to redesign this project half way in - before I got my final product.

Shout out to my Pre-Cal teacher! She inspired me to make this!

If you have any advice or questions, please
feel free to reach out to me:
- [duque.eliseo.7@gmail.com](duque.eliseo.7@gmail.com)
- [LinkedIn](https://www.linkedin.com/in/eliseo-duque)
I dont bite :)

