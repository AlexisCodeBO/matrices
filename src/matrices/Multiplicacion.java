package matrices;

import java.util.Random;

public class Multiplicacion {
	

	//Creando las matrices
	static int[][] mat = new int[4][4];
	static int[][] mat2 = new int[4][4];
	static int[][] result = new int[4][4];

	public static void main(String [] args){

	    //Creando el objeto de clase Random
	    Random rand = new Random();


	    //Llenando la primera matriz con valores aleatorios
	    System.out.println("\n\n1era matriz:");
	    for (int i = 0; i < mat.length; i++) {
	        for (int j = 0; j < mat[i].length; j++) {
	            mat[i][j]=rand.nextInt(1)+1;
	            System.out.print(mat[i][j]+" ");
	        }
	        System.out.println();
	    }
	    
	    
	    //Llenando la segunda matriz con valores aleatorios
	    System.out.println("\n\n2da matriz:");
	    for (int i = 0; i < mat2.length; i++) {
	        for (int j = 0; j < mat2[i].length; j++) {
	            mat2[i][j]=rand.nextInt(1)+1;
	            System.out.print(mat2[i][j]+" ");
	        }
	        System.out.println();
	    }
	    
	   
	    try{
	        //Objeto de la clase multiplico
	        Multiplico multi = new Multiplico(4,4);

	        //Hilos
	        MatrizProducto hilo1 = new  MatrizProducto(multi);
	        MatrizProducto hilo2 = new MatrizProducto(multi);
	        MatrizProducto hilo3 = new MatrizProducto(multi);

	        //Implementando hilos
	        Thread th1 = new Thread(hilo1);
	        Thread th2 = new Thread(hilo2);
	        Thread th3 = new Thread(hilo3);

	        //Start hilos
	        th1.start();
	        th2.start();
	        th3.start();

	        th1.join();
	        th2.join();
	        th3.join();
	        
	       
	    }catch (Exception e) {
	    	//es lo mismo que RuntimeException
	        e.printStackTrace();
	        
	    }

	    //Imprimiendo el resultado
	    System.out.println("\n\nResultado:");
	    for (int i = 0; i < result.length; i++) {
	        for (int j = 0; j < result[i].length; j++) {
	            System.out.print(result[i][j]+" ");
	        }
	        System.out.println();
	    }
	  }// main

	  }// Class

	//Multiplico Clase
		class Multiplico extends Multiplicacion {

			private int i;
			private int j;
			private int chance;

			public Multiplico(int i, int j){
				this.i=i;
				this.j=j;
				chance=0;
			}

			//Funcion Multiplicación de matrices
			public  void MultiplicoMatriz(){

				int sum=0;
				int a=0;
				for(a=0;a<i;a++){
					sum=0;
					for(int b=0;b<j;b++){
					sum=sum+mat[chance][b]*mat2[b][a];
					}
					result[chance][a]=sum;
				}

				if(chance>=i)
					return;
				chance++;
			}
		}//Multiplico clase

		//Clase del hilo
		class MatrizProducto implements Runnable {

			private final Multiplico mul;

			public MatrizProducto(Multiplico mul){
				this.mul=mul;
			}

			@Override
			public void run() {
				mul.MultiplicoMatriz();
			}

			}