import java.lang.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Solution {
    /*static boolean reachingPoint(int sx, int sy, int tx, int ty){//stackOverflow!!!!
        if(sx == tx && sy == ty)
            return true;
        if((sx+sy>max(tx,ty))||(min(sx,sy)>min(tx,ty))){
            return false;
        }
        return reachingPoint(sx+sy,sy,tx,ty)||reachingPoint(sx,sx+sy,tx,ty);
    }*/
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }

    public static void main(String[] args){
        System.out.println(reachingPoints(2,4,22,16));
    }
}
