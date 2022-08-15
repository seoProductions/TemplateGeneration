/*Manages generation of a intField passed in
  Templates! V2!*/
/**********************  Pre made templates **********************/ 
public class GenerationTemplates {
    int r;
    int c;
    
    //row then column
    public GenerationTemplates(int r, int c) { updateRowColumn(r, c); }

    /*makes L's*/
    public void Maccaroni(IntField intField)
    {
        if (randomBool())
             intField.crossTheGrid(true, true,/*Row*/ r,/*Column*/ c);//right
        else intField.crossTheGrid(true, false,/*Row*/ r,/*Column*/ c);//left
        if (randomBool())
             intField.crossTheGrid(false, false,/*Row*/ r,/*Column*/ c);//up
        else intField.crossTheGrid(false, true,/*Row*/ r,/*Column*/ c);//down

    }
    
    /*Generates a X */
    public void diagnal(IntField field)
    {
        field.diagGrid(r,c, 1);
        field.diagGrid(r, c, 2);
        field.diagGrid(r, c, 3);
        field.diagGrid(r, c, 4);
        field.testSetBox(r, c);
    }

    /*Generates cool waves */
    public void curves(IntField field)
    {
        int randDirection = (int)(Math.random()*2 + 1);// 1 - 2
        field.curveGrid(r-1, c, randDirection,     (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c+1, randDirection + 2, (int)(Math.random() * (field.getColumns() -2 ) + 1));
        field.curveGrid(r+1, c, randDirection + 4, (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c-1, randDirection + 6, (int)(Math.random() * (field.getColumns() -2 ) + 1));
    }

    /*Generates a spiral */
    public void spiral(IntField field)
    {
        int randDirection = (int)(Math.random()*2 + 1);// 1 - 2
        //Choosing specific directions
        if (randDirection == 1){

        field.curveGrid(r-1, c, 1, (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c+1, 4, (int)(Math.random() * (field.getColumns() -2 ) + 1));
        field.curveGrid(r+1, c, 6, (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c-1, 7, (int)(Math.random() * (field.getColumns() -2 ) + 1)); }
        else {
        
        field.curveGrid(r-1, c, 2,     (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c+1, 3, (int)(Math.random() * (field.getColumns() -2 ) + 1));
        field.curveGrid(r+1, c, 5, (int)(Math.random() * (field.getRows() -2 ) + 1));
        field.curveGrid(r, c-1, 8, (int)(Math.random() * (field.getColumns() -2 ) + 1)); 
        }
    }

    /*Generate a V , and a extra line along it */
    public void zigZag(IntField field, int magnitude){
        if (randomBool())
        { //  creates \/

            field.diagGrid(r,c, 1);
            field.diagGrid(r, c, 3);
        
            //generate diagnal line at end of other line
            if (randomBool())
                diagOffset(field, magnitude, 2, "northeast");
            else 
                diagOffset(field, magnitude, 4, "northwest");
            }
        else{//creates /\

            field.diagGrid(r,c, 2);
            field.diagGrid(r, c, 4);
            //generate diagnal line at end of other line
            if (randomBool())
                diagOffset(field, magnitude, 1, "southeast");
            else 
                diagOffset(field, magnitude, 3, "southwest");
            }

        
        field.testSetBox(r, c);//set starting box

    }
    
    /*Creates a C cup shape , acually dont know why but it will help me with the recursive stuff */
    public void C(IntField field, int magnitude)
    {
        if (randomBool()){
            // make a \/
            field.diagGrid(r, c, 1);
            field.diagGrid(r, c, 3);
            if (randomBool())
                diagOffset(field, magnitude, 3, "northeast");
            else 
                diagOffset(field, magnitude, 1, "northwest");
            }
            // make a /\
        else{
            field.diagGrid(r, c, 2);
            field.diagGrid(r, c, 4);
            if (randomBool())
                diagOffset(field, magnitude, 4, "southeast");
            else 
                diagOffset(field, magnitude, 2, "southwest");
            }
        field.testSetBox(r, c);//set starting box

    }

    /*Generates diagnal line , with the startingpos Offset by the direction given (moved by +-Magnitude)
     * NOT casesensitive: Noththeast southeast northwest southwest
     */
    public void diagOffset(IntField field, int magnitude, int direction, String s)
    {
        s = s.toLowerCase(); 
        switch (s) { //handles the offset
            case "northeast":
                field.diagGrid(r - magnitude, c + magnitude, direction);
                break;
            case "southeast":
                field.diagGrid(r + magnitude, c + magnitude, direction);
                break;
            case "northwest":
                field.diagGrid(r - magnitude, c - magnitude, direction);
                break;
            case "southwest":
                field.diagGrid(r + magnitude, c - magnitude, direction);
                break;
            default:
                System.out.println("DiagOffset String unrecognized:");
                break;
        }
    }

    public void recursiveLightning(){

    }

    public static boolean randomBool()
    {
        return ((int)(Math.random()*10)) % 2 == 0;
    }
    public void updateRowColumn(int r, int c) { this.r = r; this.c = c; }
    //lightning

}
