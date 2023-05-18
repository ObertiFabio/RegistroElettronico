package it.paleocapa.obertif;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppTest {
    Registro r = new Registro();
    Registro r1 = new Registro();
    @Test
    void registraValutazione() {
        r.registraValutazione("123", 8, "storia");
        assertEquals(1, r.registroElettronico.get("123").size());
    }

    @Test
    void calcoloMediaAlunno() {

        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 6, "storia");
        assertEquals(7, r.calcoloMediaAlunno("123"));
    }

    @Test
    void mediaMateria() {

        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 6, "storia");
        r.registraValutazione("123", 7, "storia");
        r.registraValutazione("123", 6, "geografia");
        assertEquals(7, r.mediaMateria("123", "storia"));
    }
    /* 
    @Test
    void visualizzaVoti() {

        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 6, "storia");
        r1.registraValutazione("345", 7, "storia");
        r1.registraValutazione("345", 6, "geografia");

        assertEquals(r.visualizzaVoti("123"),r1.visualizzaVoti("345"));
    }
    */
    @Test
    void alunniScarsi() {
        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 3, "storia");
        r.registraValutazione("456", 5, "storia");
        r.registraValutazione("456", 4, "storia");
        r.calcoloMediaAlunno("123");
        r.calcoloMediaAlunno("456");
        assertEquals(2, r.alunniScarsi().count());
    }

    @Test
    void alunniScarsiPerMateria() {
        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 3, "storia");
        r.registraValutazione("456", 5, "storia");
        r.registraValutazione("456", 4, "storia");
        r.calcoloMediaAlunno("123");
        r.calcoloMediaAlunno("456");
        assertEquals(2, r.alunniScarsiPerMateria("storia").count());
    }

    @Test
    void alunniPerfetti() {
        r.registraValutazione("123", 8, "storia");
        r.registraValutazione("123", 10, "storia");
        r.registraValutazione("456", 9, "storia");
        r.registraValutazione("456", 8, "storia");
        r.calcoloMediaAlunno("123");
        r.calcoloMediaAlunno("456");
        assertEquals(2, r.alunniPerfetti().count());
    }



}
