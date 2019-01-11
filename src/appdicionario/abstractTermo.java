package appdicionario;

public abstract class abstractTermo 
{
    protected String palavra;
    protected String traducao;

    public abstractTermo(String palavra, String traducao) {
        this.palavra = palavra;
        this.traducao = traducao;
    }

    public abstractTermo() 
    {
       palavra="";
       traducao="";
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }
   
   
    @Override
    public String toString()
    {
       return palavra+"->"+traducao;
    }
    
    boolean contem(String palavra)
    {
        return this.palavra.equals(palavra);
    }

}
    

     