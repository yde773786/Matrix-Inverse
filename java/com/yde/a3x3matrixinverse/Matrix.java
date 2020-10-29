package com.yde.a3x3matrixinverse;

public class Matrix{

    private static int[][] transpose(int[][] input){
        
        int[][] b = new int[input.length][input.length];
        for(int i = 0 ; i <input.length; i++)
            for(int j = 0 ; j <input.length ; j++ ){
                b[i][j] = input[j][i];
            }
        return b;
            
    }
    
    public static int determinant(int n , int[][] matrix){

        if(n==1){
            return matrix[0][0];
        }
        if(n == 2){
            return matrix[0][0] * matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else
        {
            int sign = 1;
            int sum = 0;
            for(int i = 0 ; i < n ; i++){
                sum += sign*matrix[0][i]*determinant(n-1, subMatrix(n , 0 , i , matrix));
                sign*=-1;
            }
            return sum;
        }

    }

    private static int[][] subMatrix(int n, int omit_row , int omit_col , int[][] matrix) {
        
        int[][] output = new int[n-1][n-1];
        for(int i = 0 , l = 0 ; i <n ; i++){
            if(i!=omit_row) {
                for (int j = 0, k = 0; j < n; j++) {
                    if (j != omit_col) {
                        output[l][k++] = matrix[i][j];
                    }
                }
                l++;
            }
        }
        return output;
        
    }

    private static int[][] adjoint(int n , int[][] matrix){

        int sign;
        int[][] adj = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                sign = (int)Math.pow(-1 , (i+1 + j+1));
                adj[i][j] = sign*determinant(n-1 , subMatrix(n, i , j , matrix));
            }
        }

        return transpose(adj);
        
    }
    
    public static String[][] Inverse(int[][] a){
        
        String[][] b = new String[a.length][a.length];
            int[][] c = adjoint(a.length , a);
            for(int i = 0 ; i <a.length; i++){
                for(int j = 0 ; j <a.length ; j++){
                    int hcf = HCF(c[i][j],determinant(a.length , a));
                    if(hcf==(int)(Math.abs(determinant(a.length , a)))){
                        b[i][j] = String.valueOf((int)(c[i][j]/determinant(a.length , a)));
                    }

                    else{
                        if((c[i][j]<0&&determinant(a.length , a)<0)||c[i][j]>0&&determinant(a.length , a)>0)
                            b[i][j] = (int)(Math.abs(c[i][j]/hcf)) + "/" + (int)(Math.abs(determinant(a.length , a)/hcf));
                        else if(c[i][j]==0){
                            b[i][j] = String.valueOf(0);
                        }
                        else
                            b[i][j] = "-" + (int)(Math.abs(c[i][j]/hcf)) + "/" + (int)(Math.abs(determinant(a.length , a)/hcf));

                    }
                }
            }
            return b;
            
    }

    private static int HCF(int a, int b){

        int x = Math.abs(a);
        int y = Math.abs(b);

        while (y != 0){
           int r = x % y;
            x = y;
            y = r;
        }

        return x;

    }
}
