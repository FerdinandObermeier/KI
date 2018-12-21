Solution1: 

Used Librarys:

java.io.File
java.io.FileNotFoundException
java.util.ArrayList
java.util.Scanner

Description of Solution1:

I created an additional "fuelarry" which holds the fuel value needed to reach a point i,j.
This Array is initialised with -1 for "*" and 0 otherwise. 
I'm trying to walk by lowest cost. 
So at first I'm trying to move right. 
If i can't go right due to "*" or because the value in my fuel array is lower then the new value would be,
if try to move left. If left doesn't work, I try to move top and so on.
If i can't go anywhere else or if I reached the "X", I move back. 
If every point was visited once or if I reach the start point again and can't move (due to fuelarray), I return the value 
in the fuelarray at the position of X.


Solution 2:

Used Librarys:

javafx.util.Pair
java.io.*
java.util.*

For every "." I'm searching for surrounding ".". I'm storing the indices of those "." in an ArrayList of type Pair, where the pair holds
the i and j value (the position) of the ".". If I reach a border of the matrix, I don't do anything and continue with the next "." in the matrix.
If I reach only "X" on all 4 sides for an ".", I turn every point in my ArrayList to an "X" and continue with the updated board to the next ".".