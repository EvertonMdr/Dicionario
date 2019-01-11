package appdicionario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

public class Dicionario implements Serializable
{
    
  final private ArrayList<abstractTermo> dic;
  Dicionario()
  {
      dic = new ArrayList();
  }
  public int  getTotalTermos()
  {
       return dic.size();
  }
 public  void importar(String arquivo) 
 {
     RandomAccessFile arq;
     try
     {
       String linha;
      arq = new RandomAccessFile(arquivo,"r");
       TermoCompleto aux;
      while(arq.getFilePointer()<arq.length())
      {
           linha=arq.readLine();
           if(linha.contains(","))
           {
           if(linha.contains("["))
           {
          
             aux =new TermoCompleto(linha.substring(0, linha.indexOf(',')),linha.substring(linha.indexOf('#')+1, linha.lastIndexOf('#')),linha.substring(linha.indexOf('['),linha.lastIndexOf(']')+1));
             aux.addSinonimos(linha.substring(linha.indexOf(',')+1,linha.indexOf('#'))); 
             dic.add(aux);
             
           }
           }
           else
           if(linha.contains("["))
          dic.add(new Termo(linha.substring(0, linha.indexOf('#')),linha.substring(linha.indexOf('#')+1,linha.lastIndexOf('#')),linha.substring(linha.indexOf('['),linha.lastIndexOf(']')+1)));
           else
          dic.add(new Termo(linha.substring(0, linha.indexOf('#')),linha.substring(linha.indexOf('#')+1,linha.length()),""));      
        
      }
      arq.close();
     }
     catch(Exception e)
      {
          System.out.println("Erro"+e.getMessage());
      }           
}
 public void inserir(abstractTermo t)     
{
    if(!((Termo)t).equals(""))
    ((Termo)t).pronuncia="["+((Termo)t).pronuncia+"]";
     
    dic.add(t);
}
 
 public boolean contem(String palavra)
 {
     palavra=palavra.toLowerCase();
     for(abstractTermo a:dic)
     {
       if(a  instanceof TermoCompleto)
       {
          if(((TermoCompleto)a).contem(palavra))
          return true;
       }
        else
         if(((Termo)a).contem(palavra))   
         return true; 
     }
     return false;
 }     
      
 public String traduzir(String palavra)
 {
     if(contem(palavra))
     {
         int i=0;
         while(i<dic.size()&&!dic.get(i).contem(palavra))
         i++;
         return "=>"+dic.get(i).getTraducao()+" "+((Termo)dic.get(i)).getPronuncia();
      
     }
     else
       return "=>"+palavra;
 }
public boolean remover(String palavra)
{
    palavra =palavra.toLowerCase();
    if(contem(palavra))
    {
        int i=0;
        while(i<dic.size()&&!dic.get(i).contem(palavra))
        i++;
        
        dic.remove(i);
        return true;   
    }
    else
        System.out.println("Não existe essa palavra para remover");
    
 return false;
}

      public static Dicionario  carregar()
    {   
        Dicionario dic=null;
        try 
        {
            FileInputStream fis=new FileInputStream("d:\\objeto.obj");
            ObjectInputStream ois=new ObjectInputStream(fis);
            dic=(Dicionario)ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Erro ao desserializar "+e);
        }
        return dic;
    }

  public void gravar()
    {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos=new FileOutputStream("d:\\objeto.obj");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) 
        {  System.out.println("Erro ao serializar "+e);
        }
    }
  
  public  void exporta(String Arquivo)
  {
        String aux ="";
        TermoCompleto t;
        int i=0;
        while(i<dic.size())
        {
              if(dic.get(i) instanceof Termo )
              aux=aux+((Termo)dic.get(i)).toString()+"\r\n";
              else
              aux=aux+((TermoCompleto)dic.get(i)).toString()+"\r\n";
              
            i++;
        }
        File f = new File(Arquivo);
        if(f.exists())
          f.delete();  
        
        try
        {
        RandomAccessFile arqnovo = new RandomAccessFile(Arquivo,"rw");
        arqnovo.setLength(0); // apaga o arquivo
        arqnovo.writeBytes(aux);
        arqnovo.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro "+e.getMessage());
        }
  }
  
  
  public String traduzirSetenca(String palavra)
  {
      String pal,sen="",aux;   
      for(int i=0,j;i<palavra.length();i++)
      {
          j=i;
         while(i<palavra.length()&&palavra.charAt(i)!=' '&&palavra.charAt(i)!=','&&palavra.charAt(i)!='.')
         i++;
         
         pal=palavra.substring(j,i);
         if(contem(pal))
         {
             aux=traduzir(pal);
             if(i!=palavra.length())
             sen=sen+aux.substring(aux.indexOf('>')+1,aux.indexOf(' '))+palavra.charAt(i);
             else
             sen=sen+aux.substring(aux.indexOf('>')+1,aux.indexOf(' '));
         }
         else
         if(i!=palavra.length())
         sen=sen+pal+palavra.charAt(i);
         else
          sen=sen+pal;
      }
      return sen;
  }

}