package appdicionario;

import java.util.ArrayList;

public class TermoCompleto extends Termo {

    private ArrayList<String> sinonimos;

    TermoCompleto()
    {
        super("", "", "");
        sinonimos= new ArrayList();
        
    }
        public void addSinonimos(String sinonimo)
    {
        while(!sinonimo.equals(""))
      {
          if(sinonimo.contains(",")) 
          {
             sinonimos.add(sinonimo.substring(0, sinonimo.indexOf(',')));
             sinonimo=sinonimo.substring(sinonimo.indexOf(',')+1);
          }
         
          else
          {
           sinonimos.add(sinonimo);
           sinonimo="";
          }
      }
    }
    
    TermoCompleto(String palavra,String traducao,String pronuncia)
    {
      super(palavra,traducao,pronuncia);
      sinonimos= new ArrayList();
    }
    
    public String getSinonimos() {
        
       String sinonimo="";
        if(!sinonimos.isEmpty())
        for(String aux:sinonimos)
        sinonimo=sinonimo+","+aux;
            
        return sinonimo;
    }
    
    @Override
    public String toString()
    {
      
       return palavra+getSinonimos()+"#"+traducao+"#"+pronuncia;
    }
    
   @Override
  boolean contem(String palavra)
    {
        if(!palavra.equals(""))
       {
        if(!super.contem(palavra))
        {
            if(sinonimos.contains(palavra))
            return true;
        }
        else
        return true;
       }
       
        return false;
    }   
}
