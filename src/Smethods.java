/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Payman
 */

public class Smethods
{
    public static byte select(byte[][] sudoku, byte number, byte position, byte step)
    {
        if((sudoku[position*9 + number][step] == 0) || (sudoku[position*9 + number][step] > 9))
            return step;//end of number not possible or is selected

        step += 1; // we can select so write this step to the sudoku array
        int count = 0;
        for(count = 0; count < 729; count++)
            sudoku[count][step] = sudoku[count][step - 1]; //copy existing to next step
        for(count = 0; count < 9; count++)
            sudoku[position*9 + count][step] = 0; //Can't select any in box

        byte row =   (byte) (position/9);
        for(count = 0; count < 9; count++)
            sudoku[row * 81 + count * 9 + number][step] = 0; //horizontal row

        byte column =   (byte) (position%9);
        for(count = 0; count < 9; count++)
            sudoku[column * 9 + count * 81 + number][step] = 0;  //vertical row

        int brow =  (position/27)*243; //row block 0f 3
        column = (byte) (((position%9)/3)*27);  //Column block of 3
        byte incount;
        for(incount = 0; incount < 3; incount++)
        {
            for(count = 0; count < 3; count++)
                sudoku[brow + column + count * 9 + incount * 81 + number ][step] = 0;  //box of 3 x 3
        }//end of 3 x 3 box
        //we have selected one number
        sudoku[position*9 + number][step] = (byte) (number + 11); //selected now 11 to 19
        return step;
    }//end of select a number
}//end of class
