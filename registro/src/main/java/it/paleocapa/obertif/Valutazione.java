package it.paleocapa.obertif;
public class Valutazione
{
    String matricola, materia;
    int voto;
    
    public Valutazione(String matricola, int voto, String materia){
        this.matricola=matricola;
        this.voto=voto;
        this.materia=materia;
    }
    
    public String getMatricola(){
        return matricola;
    }
    
    public String getMateria(){
        return materia;
    }
    
    public int avarage(int somma){
        return somma+=voto;
    }
    
    public String toString(){
        String string = "La matricola: " + matricola + " con voto: " + voto + " della materia: " + materia;
        return string;
    }
    
    public int getVoto(){
        return voto;
    }
    
}
