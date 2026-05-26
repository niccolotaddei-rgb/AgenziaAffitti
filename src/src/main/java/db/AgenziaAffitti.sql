-- Questo file utilizza la codifica UTF-8
-- DB e tabella per l'agenzia immobiliare (solo affitti)

CREATE DATABASE IF NOT EXISTS AgenziaAffitti;
USE AgenziaAffitti;

CREATE USER IF NOT EXISTS 'agenziauser'@'localhost' IDENTIFIED BY 'unapasswd';
GRANT ALL ON AgenziaAffitti.* TO 'agenziauser'@'localhost';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS Immobile;

CREATE TABLE Immobile (
    ID_immobile   INT             NOT NULL,
    Titolo        VARCHAR(120)    NOT NULL,
    Tipo          VARCHAR(30)     NOT NULL,   -- appartamento, villa, ufficio, locale, box
    Stato         VARCHAR(20)     NOT NULL,   -- disponibile, riservato, affittato
    Indirizzo     VARCHAR(100),
    Citta         VARCHAR(60),
    PrezzoMensile INT,
    Mq            INT,
    Locali        INT,
    Piano         VARCHAR(20),
    Descrizione   TEXT,
    PRIMARY KEY (ID_immobile)
);

INSERT INTO Immobile (ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione)
VALUES (1, 'Appartamento 3 locali - Centro', 'appartamento', 'disponibile', 'Via Garibaldi 12', 'Milano', 950, 75, 3, '2', 'Ampio appartamento luminoso in pieno centro storico. Recentemente ristrutturato, doppi servizi, balcone.');

INSERT INTO Immobile (ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione)
VALUES (2, 'Bilocale luminoso - Navigli', 'appartamento', 'disponibile', 'Via Corsico 5', 'Milano', 780, 55, 2, '3', 'Delizioso bilocale nella vivace zona Navigli. Arredato, vicino ai mezzi pubblici.');

INSERT INTO Immobile (ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione)
VALUES (3, 'Ufficio 60 mq - Porta Romana', 'ufficio', 'riservato', 'Corso Lodi 8', 'Milano', 1200, 60, 0, 'T', 'Ufficio a piano terra con vetrina, ideale per studi professionali.');

INSERT INTO Immobile (ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione)
VALUES (4, 'Monolocale zona fiera', 'appartamento', 'disponibile', 'Viale Scarampo 3', 'Milano', 620, 35, 1, '5', 'Monolocale recentemente ristrutturato, luminoso, in zona fiera.');

INSERT INTO Immobile (ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione)
VALUES (5, 'Villa con giardino', 'villa', 'affittato', 'Via delle Rose 10', 'Sesto San Giovanni', 2200, 200, 6, 'T', 'Villa bifamiliare con ampio giardino privato.');
