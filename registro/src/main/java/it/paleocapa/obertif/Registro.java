package it.paleocapa.obertif;
import java.util.HashMap;
import java.util.*;
import java.util.stream.*;

public class Registro{
    
    HashMap<String, LinkedList<Valutazione>> registroElettronico;
    
    public Registro(){
        registroElettronico = new HashMap<String, LinkedList<Valutazione>>();
    }
    
    public void registraValutazione(String matricola, int voto, String materia){
        if(voto <= 0 || voto > 10){
            throw new votoNonValidoException();
        }
        if(registroElettronico.get(matricola) == null){
            registroElettronico.put(matricola, new LinkedList<Valutazione>());
        }
        LinkedList<Valutazione>  l = registroElettronico.get(matricola);
        l.add(new Valutazione(matricola,voto,materia));
    }
    
    public double calcoloMediaAlunno(String matricola) throws matricolaInesistenteException {
        if(!registroElettronico.containsKey(matricola)){
            throw new matricolaInesistenteException();
        }
        double sommaVoti = registroElettronico.get(matricola).stream().reduce(0, (partial, e) -> partial+e.voto, Integer::sum);
        double mediaVoti = sommaVoti/registroElettronico.get(matricola).size();
        return mediaVoti;
    }
    
    public double mediaMateria(String matricola, String materia){
        if(!registroElettronico.containsKey(matricola)){
            throw new matricolaInesistenteException();
        }
        double somma = registroElettronico.get(matricola).stream().filter(s -> s.materia == materia).reduce(0, (partial, e) -> partial+e.voto, Integer::sum);
        double conta = registroElettronico.get(matricola).stream().filter(c -> c.materia == materia).count();
        double mediaGen = somma/conta;
        return mediaGen;
    }
    
    public void visualizzaVoti(String matricola) throws matricolaInesistenteException {
        if(!registroElettronico.containsKey(matricola)){
            throw new matricolaInesistenteException();
        }
        registroElettronico.get(matricola).stream().forEach(System.out::println);
    }
    
    public Stream<String> alunniScarsi(){
        return registroElettronico.keySet().stream().filter( m -> calcoloMediaAlunno(m) < 6);
    }
    
   
    public Stream<String> alunniScarsiPerMateria(String materia){
        return registroElettronico.keySet().stream().filter( m -> mediaMateria(m, materia) < 6);
    }
    
    public Stream<String> alunniPerfetti(){
        return registroElettronico.keySet().stream().filter( m -> registroElettronico.get(m).stream().allMatch(v -> v.voto > 6));
    }
    
    public class matricolaInesistenteException extends RuntimeException{}
    public class votoNonValidoException extends RuntimeException{}
}
