// Nome: Everton Gabriel de Medeiros RA:101426496
//Nome: PEDRO HENRIQUE EVANGELISTA ZANON RA:101426186
package appdicionario;
public class AppDicionario {

    public static void main(String[] args)
    {
        Dicionario dic = new Dicionario();
        
        dic.importar("lista_palavras.txt"); // lendo o arquivo texto
     
        //inserindo novas palavras no arquivo texto
        dic.inserir(new Termo("correção","correction","currequechân")); 
        dic.inserir( new Termo ("piedade","piety","pairi"));
    
        TermoCompleto  tc= new TermoCompleto("professor","teacher","títier");
        tc.addSinonimos("mestre");
        tc.addSinonimos("preceptor");
    
        dic.inserir(tc);
     

     System.out.println(dic.traduzir("cachorro")); // tradução da palavra cachorro
     
    
     dic.exporta("d:\\lista_palavras_novo.txt"); // salvando o arquivo texto com as novas palavras no diretorio d:
                                                    
         
     
    }
    
}


