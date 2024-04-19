package com.seoproductions.templategenerationfx;/* This file is a trip back in time x_x */
/* My first prototype of this project came from this file named IntField */
/* I had used a "Field", or simply said, a 2D array inorder to generate  */
/* an interesting design of paterns. It started with a line, then a swirl, then a diagnal, so on, and so on,  */
/* This began my junior year towards my spring semester, my program originaly   */
/* spat a grid of 0's and 1's onto the console. infact I still have the toString() function */
/* Unfortunatley for me, my rubber duck was one of the few who took interest in this hobby... */


public class IntField {

    private int[][] grid;
    private int rows;
    private int columns;
    private int degrees;

    private boolean randomIterations;
    private int iterations = 1;

    //@param > 0
    //make shure to set iteration after constructor;
    public IntField(int row, int col)
    {
        grid = new int[row][col];
        rows = row;
        columns = col;
        createNew(0);
    }
    /*Fils all grid with number of your choosing
     * Default is 0's
     */
    public void createNew(int number)
    {
      for (int r = 0; r < grid.length; r++)  //accesses rows
      {
        for (int c = 0; c < grid[0].length; c++) //accesses columns 
        {
          grid[r][c] = number;
        }
      }
    }
    
    
    /* h - horizontal or not
     f - foward or backwards
     x - x pos passed in
     y - y pos passed in */   //above 5 x 5
    public void crossTheGrid(boolean h, boolean f, int r, int c)
    {
        
        if (h) //horizontal
        {
            //either random iterations , or to a pre-set iteration
            int rand;
            if (randomIterations)
                rand = (int)((Math.random() * columns) / 2) + 2; // 0 < x < half of # of colums
            else rand = iterations;

            for (int i = 0; i < rand; i++)
            {
                
                testSetBox(r, c);
                if (f) c++;  // increment right --->   
                else c--; // increment left <---
                
            }
        }
        else //verticle
        {
            //either random iterations , or to a pre-set iteration
            int rand;
            if (randomIterations)
                rand = (int)((Math.random() * rows) / 2) + 2; // 0 < x < half of # of colums
            else rand = iterations;

            for (int i = 0; i < rand; i++)
            {

                testSetBox(r, c);
                if (f) r++; // increment down |
                else r--; // increment up
                
            }
        }

    }

    /*
    direction -  
    1 - NorthEast
    2 - SouthEast
    3 - NorthWest
    4 - SouthWest

    r - startin row
    c - starting column

    Returns Array[2] Y,X - ending position of line generated
    */
    public int[] diagGrid(int r, int c, int direction)
    {
        int[] endPos = new int[2];//holds the end position
        //either random iterations , or to a pre-set iteration
        int rand;
        if (randomIterations)
            rand = (int)((Math.random() * Math.min(rows, columns)) / 2) + 2; // 0 < x < smallest side
        else rand = iterations;

        
        //NorthEast   Rows-- Colum++
        if (direction == 1){
            for (int i = 0; i < rand; i ++) 
            {
                testSetBox(r-1, c+1); //North East
                //side
                randomTestSetBox(r-1, c);//up
                randomTestSetBox(r, c+1);//right

                r--; //up the grid
                c++; //right the grid
            }
        }
        //SouthEast  All +
        if (direction == 2){
            for (int i = 0; i < rand; i ++) 
            {
                testSetBox(r+1, c+1); //South East
                //side
                randomTestSetBox(r+1, c);//down
                randomTestSetBox(r, c+1);//right

                r++; //down the grid
                c++; //right the grid
            }
        }
            //NorthWest   All -
        if (direction == 3){
            for (int i = 0; i < rand; i ++) 
            {  
                testSetBox(r-1, c-1); //NorthWest
          
                //side
                randomTestSetBox(r-1, c);//up
                randomTestSetBox(r, c-1);//left
                    
                r--; //up the grid
                c--; //left the grid
            }
        }
        //SouthWest   rows++ Columns --
        if (direction == 4){
            for (int i = 0; i < rand; i ++) 
            {  
                testSetBox(r+1, c-1); //SouthWest
          
                //side
                randomTestSetBox(r+1, c);//down
                randomTestSetBox(r, c-1);//left
                    
                r++; //down the grid/
                c--; //left the grid
            }
        }    
        endPos[0] = r; //row / Y
        endPos[1] = c; //column / X
        return endPos;
    }

    /***Directions***
    Sin -  SouthEast H (horizontal)
    cosF - SouthEast V (verticle)

    SinF - NorthEast H
    cos  - NorthEast V
    */
    public void curveGrid(int r, int c, int dir , int magnitude)
    {
        if (!randomIterations) magnitude = iterations; //if not random , set to iterations
        switch (dir){ //all combinations
            case 1: generateCurve(r, c, magnitude, true, false , false); break;//South  East H /
            case 2: generateCurve(r, c, magnitude, false, true, false); break;//South  East V
            case 3: generateCurve(r, c, magnitude, true, true, false); break; //North  East H
            case 4: generateCurve(r, c, magnitude, false, false, false); break;//North East V  /
            
            case 5: generateCurve(r, c, magnitude, true, false , true); break;//South  West H
            case 6: generateCurve(r, c, magnitude, false, true, true); break;//South  West V /
            case 7: generateCurve(r, c, magnitude, true, true, true); break; //North  West H /
            case 8: generateCurve(r, c, magnitude, false, false, true); break;//North West V

        }
    }

    /*Sets a cool curve onto the grid
        @x - starting x
        @y - starting y
        @mag   How long the curve is
        @_sin  True for Sin: else Cos
        @_flip True for scurve to go up: Else it will go down
        @_ref  True for curve to go Left <-- : Else --->
    */
    public void generateCurve(int x, int y, int _magnitude, boolean _sin, boolean _flipped, boolean reflect)
    {

        //return a list of values from math Function
        int[] fluxValues = triglistof90(_magnitude, _sin, _flipped);

        //if cosine - set to starting coords ( x, y)
        if (!_sin) //this is needed because cosine starts away from origin
        {
            for (int i = 0; i < fluxValues.length; i++)
            {
                if (!_flipped) fluxValues[i] -= _magnitude; //Normal sin: subtract (up)
                if (_flipped) fluxValues[i] += _magnitude; //Flipped sin: add (down)
            }
        }
        for (int i: fluxValues) 
        {

            testSetBox(x + i, y); //fluctuate

            if (reflect ) y--;//  <---
            if (!reflect) y++;//  --->
        }
    }
    
    /* Returns int[] of values based on ASinx
        - Magnitude: how far you want the values to flow
        - Boolean _sin: true if sin, else cosine
        - Boolean flipped: true for neg values ( change in neg direction ) North & West
    */
    private int[] triglistof90(int magnitude, boolean _sin, boolean flipped)
    {
        int degree = 0;
        int[] list = new int[magnitude];
        for (int i = 0; i < list.length; i++)
        {
            if (_sin) //sin
                list[i] = ((int)(Math.sin(Math.toRadians(degree))*magnitude + 0.5)); //rounded up: sin
            else //cosine
                list[i] = ((int)(Math.cos(Math.toRadians(degree))*magnitude - 0.5)); //rounded down: cos

            if (flipped) //going down: turn negative
                list[i] *= -1; 
            
            //okay we got degree and degrees here what are you doing seo:
            // degree is the intervals
            // degree's' is the total that the intervals will be taken from - also able to tweak globaly
            degree += (degrees/magnitude); //able to tweak ****************
        }
        return list;
    }


    /* sets box to coords passed in - at random chance
    */
    public void randomTestSetBox(int r, int c)
    {
        if ((int)(Math.random() * 10) % 4 == 0)
            testSetBox(r, c); //sets to param passed in
    }


    public void testSetBox(int r, int c)
    {
        if ( (r >= 0 && r < rows) && (c >= 0 && c < columns) ) //Inbounds
        {
            grid[r][c] = 1;
        }

    }


    public void setRandomBox()
    {
        grid[(int)(Math.random() * rows)][(int)(Math.random() * columns)] = 1;
    }

    /*Accesors                                          */
    public String toString()
    {
        String s = "";

        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                //prints 1 in color
                if (grid[r][c] == 1) s += "\033[91m1\033[0m" + "  ";
                else 
                    s += "" + grid[r][c] + "  ";
            }
            s += "\n";
        }
        return s + "\n";

    }

    public int getRows() { return rows; }

    public int getColumns() { return columns; }

    public int[][] getGrid() { return grid; }

    public int getNumOfIterations() { return iterations; }

    public void setDegrees(int d) { degrees = d; }
 
    public void setIterations(int s) { iterations = s; }

    public void setRandomIterations(boolean b) { randomIterations = b; }


}
