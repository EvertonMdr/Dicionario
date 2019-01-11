
package appdicionario;
import java.io.Serializable;



public class Termo extends abstractTermo implements Serializable{
 
    protected String pronuncia;
    public String getPronuncia() {
        return pronuncia;
    }
    public void setPronuncia(String pronuncia) {
        this.pronuncia = pronuncia;
    }
   
    Termo()
    {
        pronuncia="";
    }
    Termo(String palavra,String traducao,String pronuncia)
    {
       super(palavra,traducao);
       this.pronuncia=pronuncia;
    }
    
    @Override
   public String toString() 
    {
        if(pronuncia.equals(""))
       return palavra+"#"+traducao+pronuncia; 
       
       return palavra+"#"+traducao+"#"+pronuncia;    
    }
   
   
   @Override
  boolean contem(String palavra)
    {
        return this.palavra.equalsIgnoreCase(palavra);
    }
   
  
   
}
       
  

