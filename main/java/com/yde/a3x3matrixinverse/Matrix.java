package com.yde.a3x3matrixinverse;

public class Matrix{
    private int a[][];
    public Matrix(int input[][]){
        a= input;
    }
    private int[][]  Transpose(int input[][]){
        int b[][] = new int[input.length][input.length];
        for(int i = 0 ; i <input.length; i++)
            for(int j = 0 ; j <input.length ; j++ ){
                b[i][j] = input[j][i];
            }
        return b;
    }

    public int Determinant(){
        return a[0][0]*(a[1][1]*a[2][2]-a[2][1]*a[1][2]) - a[0][1]*(a[1][0]*a[2][2]-a[2][0]*a[1][2]) + a[0][2]*(a[1][0]*a[2][1]-a[2][0]*a[1][1]);
    }

    private int[][] Adjoint(){
        int b[][] = new int[a.length][a.length];
        int sign = 1;
        for(int i = 0 ; i <a.length; i++){
            for(int j = 0 ; j<a.length ; j++){
                int lr = 0 , lc = 0 , mr = 0 , mc = 0;
                if(i == 0){
                    lr = 1;
                    mr = 2;
                }
                if(i == 1){
                    lr = 0;
                    mr = 2;
                }
                if(i == 2){
                    lr = 0;
                    mr = 1;
                }
                if(j == 0){
                    lc = 1;
                    mc = 2;
                }
                if(j == 1){
                    lc = 0;
                    mc = 2;
                }
                if(j == 2){
                    lc = 0;
                    mc = 1;
                }
                b[i][j] = sign*(a[lr][lc]*a[mr][mc] - a[mr][lc]*a[lr][mc]);
                sign = (-1)*sign;
            }
        }
        return Transpose(b);
    }

    public String[][] Inverse(){
        String b[][] = new String[a.length][a.length];
            int c[][] = Adjoint();
            for(int i = 0 ; i <a.length; i++){
                for(int j = 0 ; j <a.length ; j++){
                    int hcf = HCF(c[i][j],Determinant());
                    if(hcf==(int)(Math.abs(Determinant()))){
                        b[i][j] = String.valueOf((int)(c[i][j]/Determinant()));
                    }

                    else{
                        if((c[i][j]<0&&Determinant()<0)||c[i][j]>0&&Determinant()>0)
                            b[i][j] = (int)(Math.abs(c[i][j]/hcf)) + "/" + (int)(Math.abs(Determinant()/hcf));
                        else if(c[i][j]==0){
                            b[i][j] = String.valueOf(0);
                        }
                        else
                            b[i][j] = "-" + (int)(Math.abs(c[i][j]/hcf)) + "/" + (int)(Math.abs(Determinant()/hcf));

                    }
                }
            }
            return b;
    }

   private static int HCF(int a, int b){
        int lower = (int)(Math.min(a,b)), higher = (int)(Math.max(a, b)), hcf = 1;
        if(higher%lower==0) return lower;
        for(int i = 2; i<Math.sqrt(lower); i++){
            if(a%i==0 && b%i==0 && i>hcf) hcf = i;
            if(a%(lower/i)==0 && b%(lower/i)==0 && lower/i > hcf) return (lower/i);
        }//foo loop - i    
        return hcf;
    }//end of private static int HCF(int,int)

    private static int EuclideanAlgorithm(int a, int b) {
        if(b==0) return a;
        return EuclideanAlgorithm(b, a%b);
    }
}
