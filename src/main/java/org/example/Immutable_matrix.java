package org.example;
import java.util.Arrays;

public class Immutable_matrix implements Matrix_interface{
    public final int[][] matrix;
    private int flag = 0 ;
    public  Immutable_matrix (int a, int b){
        this.matrix = new int[a][b];
    }
    public Immutable_matrix (){
        this.matrix =new int[][]{};
    }
    public  Immutable_matrix (int[][] mat){
        this.matrix = Arrays.copyOf(mat,mat.length);
    }
    public void fill (){
        if (flag==1){return;}
        for (int i = 0 ; i<this.matrix.length; i++){
            for (int j =0 ; j<this.matrix[i].length;j++){
                this.matrix[i][j] = (int) (Math.random() * 10)  ;
            }
        }
        this.flag=1;
    }
    public int[] get_column( int column){
        int[] res= new int[this.matrix.length];
        for (int i= 0; i<this.matrix.length;i++) {
            res[i]=this.matrix[i][column];
        }
        return res;
    }
    public int[] get_row( int row ){
        return this.matrix[row];
    }
    public int[] get_element( int row , int column){
        return new int[]{this.matrix[row][column]};
    }
    public int[] get_dimension(){
        return new int[]{this.matrix.length,this.matrix[0].length};
    }
    public int[] get_dimension(int[][] mat){
        return new int[]{mat.length,mat[0].length};
    }
    public int[] fget_dimension(float[][] mat){
        return new int[]{mat.length,mat[0].length};
    }
    public void print_matrix(){
        for (int[] ints : this.matrix) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.print("\n");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Immutable_matrix mat = (Immutable_matrix) obj ;

        if (!Arrays.equals(this.get_dimension(), mat.get_dimension())){return false;}
        for (int i=0 ;i<this.get_dimension()[0];i++){
            for (int j=0 ;j<this.get_dimension()[1];j++){
                if (this.matrix[i][j]!=mat.matrix[i][j]){return false;}
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public int[][] unit_mat(int size){
        int[][] un_mat = new int[size][size];
        for (int k=0 ; k<size; k++){
            un_mat[k][k]=1;
        }
        return un_mat;
    }
    public int[][] transposition(){
        int[] size = get_dimension();
        int[][] transposed= new int[size[1]][size[0]];
        for (int i = 0 ; i<this.matrix.length;i++){
            for (int j = 0 ; j<this.matrix[0].length;j++) {
                transposed[j][i]=this.matrix[i][j];
            }
        }
        return transposed;
    }
    public int[][] transposition(int[][] mat){
        int[] size = get_dimension(mat);
        int[][] transposed= new int[size[1]][size[0]];
        for (int i = 0 ; i<mat.length;i++){
            for (int j = 0 ; j<mat[0].length;j++) {
                transposed[j][i]=mat[i][j];
            }
        }
        return transposed;
    }
    public int[][] unit_mat(){
        float[][] reverse= reverse_matrix();
        if (reverse.length==0){
            return new int[0][0];
        }
        int[][] unit= new int[get_dimension()[0]][get_dimension()[0]];
        float buf =0;
        for (int i = 0; i < get_dimension()[0]; i++)
            for (int j = 0; j < get_dimension()[0]; j++) {
                for (int k = 0; k < get_dimension()[0]; k++)
                    buf += (this.matrix[i][k] * reverse[k][j]);
                unit[i][j]=Math.round(buf);
                buf=0;
            }
        return unit;
    }
    public int determinant(int[][] mat){
        if (get_dimension(mat)[0]!=get_dimension(mat)[1]){System.out.println("Error ! Don't square matrix");
            return 1000;}
        if (get_dimension(mat)[0]==1){return mat[0][0];}
        int det=0;
        for (int j=0;j<get_dimension(mat)[0];j++){
            int column=0;
            int row=0;
            int[][] new_mat= new int[get_dimension(mat)[0]-1][get_dimension(mat)[0]-1];
            for (int k=1;k<get_dimension(mat)[0];k++){
                for (int g=0;g<get_dimension(mat)[0];g++){
                    if (g==j){continue;}
                    new_mat[row][column]=mat[k][g];
                    column++;
                }
                column=0;
                row++;
            }
            det += Math.pow(-1,(1 + j+1))*mat[0][j]*determinant(new_mat);
        }
        return det;
    }
    public float[][] transposition(float[][] mat){
        int[] size = fget_dimension(mat);
        float[][] transposed= new float[size[1]][size[0]];
        for (int i = 0 ; i<mat.length;i++){
            for (int j = 0 ; j<mat[0].length;j++) {
                transposed[j][i]=mat[i][j];
            }
        }
        return transposed;
    }
    public float[][] reverse_matrix(){
        int det = determinant(this.matrix);
        float[][] reverse= new float[0][0];
        if (det==0){
            System.out.println("Error! Determinant equal 0");
            return reverse;
        }
        if (det==1000){return reverse;}
        reverse=new float[get_dimension()[0]][get_dimension()[1]];
        for (int i=0;i<get_dimension()[0];i++){
            for (int j=0;j<get_dimension()[0];j++){
                int column=0;
                int row=0;
                int[][] mat= new int[get_dimension()[0]-1][get_dimension()[0]-1];
                for (int k=0;k<get_dimension()[0];k++){
                    if (k==i){continue;}
                    for (int g=0;g<get_dimension()[0];g++){
                        if (g==j){continue;}
                        mat[row][column]=this.matrix[k][g];
                        column++;
                    }
                    column=0;
                    row++;
                }
                reverse[i][j]= (float)determinant(mat);
                reverse[i][j]=reverse[i][j]/det;
                reverse[i][j]= (float) (reverse[i][j]*Math.pow(-1,(i+1 + j+1)));
            }
        }
        return transposition(reverse);
    }
}
