package it.agenzia.model;

/**
 * Model – campi omologhi alle colonne della tabella "Immobile".
 * Stesso stile della classe Film del progetto ProgettoWeb3_2.
 */
public class Immobile {

    // Attributi -tutti privati- omologhi ai campi della tabella "mappata"
    private Integer id;
    private String  titolo;
    private String  tipo;          // appartamento | villa | ufficio | locale | box
    private String  stato;         // disponibile | riservato | affittato
    private String  indirizzo;
    private String  citta;
    private Integer prezzoMensile;
    private Integer mq;
    private Integer locali;
    private String  piano;
    private String  descrizione;

    public Immobile() {}

    public Immobile(Integer id, String titolo, String tipo, String stato,
                    String indirizzo, String citta, Integer prezzoMensile,
                    Integer mq, Integer locali, String piano, String descrizione) {
        this.id            = id;
        this.titolo        = titolo;
        this.tipo          = tipo;
        this.stato         = stato;
        this.indirizzo     = indirizzo;
        this.citta         = citta;
        this.prezzoMensile = prezzoMensile;
        this.mq            = mq;
        this.locali        = locali;
        this.piano         = piano;
        this.descrizione   = descrizione;
    }

    // --- Getter e Setter ---

    public Integer getId()                   { return id; }
    public void    setId(Integer id)         { this.id = id; }

    public String  getTitolo()               { return titolo; }
    public void    setTitolo(String titolo)  { this.titolo = titolo; }

    public String  getTipo()                 { return tipo; }
    public void    setTipo(String tipo)      { this.tipo = tipo; }

    public String  getStato()                { return stato; }
    public void    setStato(String stato)    { this.stato = stato; }

    public String  getIndirizzo()                    { return indirizzo; }
    public void    setIndirizzo(String indirizzo)    { this.indirizzo = indirizzo; }

    public String  getCitta()                { return citta; }
    public void    setCitta(String citta)    { this.citta = citta; }

    public Integer getPrezzoMensile()                        { return prezzoMensile; }
    public void    setPrezzoMensile(Integer prezzoMensile)   { this.prezzoMensile = prezzoMensile; }

    public Integer getMq()               { return mq; }
    public void    setMq(Integer mq)     { this.mq = mq; }

    public Integer getLocali()                   { return locali; }
    public void    setLocali(Integer locali)     { this.locali = locali; }

    public String  getPiano()                { return piano; }
    public void    setPiano(String piano)    { this.piano = piano; }

    public String  getDescrizione()                      { return descrizione; }
    public void    setDescrizione(String descrizione)    { this.descrizione = descrizione; }
}
