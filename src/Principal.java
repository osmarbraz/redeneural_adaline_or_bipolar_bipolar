/**
* Adaline para uma funcao OR com entrada e alvo bipolar(1,-1)
* Exemplo 2.19 pag. 85 Livro Fausett, Laurene V Fundamentals of Neural NetWorks
*/
public class Principal{
	
	public static void main(String args[]){
		//Matriz de dados binaria
		int s[][] = { {1,1},{1,-1},{-1,1},{-1,-1}};
		
		//alvo da matriz de dados polar
		int alvo[] = { 1,1,1,-1};
		
		//Numero de Elementos em uma entrada
		int num = 4;     
    
		//Numero de Entradas    
		int n = 2;
    
		//Taxa de aprendizagem
		double alfa = 0.02;

		//Threshold
		double omega = 0;

		//Numero de epocas		
		int epoca = 1000;
		
		System.out.println(">>> Inicio do treinamento <<<");   

		//passo 0
		//inicializar pesos e bias
		
		double b = 0.5; //peso bias		
		
		double w[]=new double[n];		
		for(int i=0;i<n;i++) {
			w[i] = 0.5;
		}
				
		//Variavel para contar o numeros de epocas
		int conta_epoca=0;		
		
		//calcula o erro inicial
		double totalerro=0;
		for (int j=0;j<num;j++){
			double soma=0;
			for (int i=0;i<n;i++) {
				soma=soma + (s[j][i] * w[i]);
			}
			totalerro = totalerro + Math.pow((soma - alvo[j]),2);      
		}				
		//limite de parada
		double limiar=1.25; 
		
		//Variavel utilizada para armazenar as estatisticas
		String saida = "";
		
		//passo 1
		while ((conta_epoca < epoca) &&(totalerro>limiar)) { //condicao de parada     			

			//conta o numero de epocas
			conta_epoca = conta_epoca + 1;
   
			//mostra uma mensagem sobre a epoca atual
			saida = saida + "\nEpoca => " + conta_epoca + "\n";   
			saida = saida + "(      Entrada     )\t\t\t(     Pesos     )\n";
			saida = saida + "x1\tx2\talfa\ty_in\talvo\tw1\tw2\tb";			
			saida = saida + "\n";			
			
			//Passo 2
			//zera o erro do conjunto
			totalerro=0; 
			
			//laco para percorrer todas as entradas
			for (int entrada=0; entrada<num;entrada++) {      				
				int t = alvo[entrada];				
				
				//passo 3				
				//transfere os dados do conjunto de treinamento para a entrada da rede x(i)
				int x[]=new int[n];
				for (int i=0;i<n;i++) {
					x[i] = s[entrada][i];
				}
     
				//passo 4
				double y_in = 0;   
				for(int i=0;i<n;i++) {
					y_in = y_in + (x[i] * w[i]);        
				}
				y_in = y_in + b;
     						
				//passo 5
				double erro = t - y_in;
				
				//calcula o erro total do conjunto de treinamento
				totalerro = totalerro + Math.pow(erro,2);

				double delta[]=new double[n];				
				for(int i=0;i<n;i++){
					delta[i] = alfa * erro * x[i];
				} 
   
				for(int i=0;i<n;i++){
					w[i] = w[i] + delta[i];
				}
				b = b + alfa * (t - y_in);     
				
				//Armazena as estatisticas 	
				for (int j=0;j<n;j++) {
					saida = saida + s[entrada][j] + "\t";   
				}				
				saida = saida + alfa + "\t";
				saida = saida + String.format("%.4f",y_in) + "\t";							
				saida = saida + alvo[entrada] + "\t";				
				for (int j=0;j<n;j++) {
					saida = saida + String.format("%.4f",w[j]) + "\t";   
				}								 
				saida = saida + String.format("%.4f",b) + "\n";
				
			} //Fim For   			
			
		}//Fim While		
		saida = saida + "Erro:" + totalerro + " / Limiar:" + limiar + "\n";		
		System.out.print(saida);
		System.out.println(">>> Fim do treinamento <<<");

		//mensagem de inicio dos teste
		System.out.println(">>> Inicio dos testes <<< ");   		

		//Execucao da rede
		int res = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Deseja fazer teste (1 fazer ou 0 não fazer) : "));
		
		while (res != 0) {
			int letra=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("informe o numero do elemento a ser executado => "));
      			     
			int t = alvo[letra];
   
			//passo 3
			//transfere os dados do conjunto de treinamento para a entrada da rede x(i)
			int x[]=new int[n];
			for (int i=0;i<n;i++) {
				x[i]=s[letra][i];
			}    
     
			//passo 4
			double y_in = 0;   
			for (int i=0;i<n;i++) {
				y_in = y_in + (x[i] * w[i]);        
			}
			y_in = y_in + b;
      	
			//funcao de ativacao
			int y = 0;
			if (y_in>=0) {
				y = 1;
			} else {				
					y = -1;				
			}
     
			//mostra uma mensagem sobre o teste
			System.out.println("\nResultado: " );   			
			System.out.println("Entrada: " + letra + " Alvo:" + alvo[letra]);   			
			for(int i=0;i<n;i++){
				System.out.print("s["+letra+"," + i + "] = " + s[letra][i] + " ");   
			}
			System.out.print(" y:" + y);         
			
			res = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("press 1 para continuar teste ou 0 para parar testes: "));
		} 
		//mensagem
		System.out.println(">>> Fim dos teste <<<");   		
	}
	
}



   