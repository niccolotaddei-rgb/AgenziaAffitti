package it.agenzia.controller;

import it.agenzia.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

/*
 * Presupposto: DB "AgenziaAffitti" con tabella "Immobile" (DDL e DML in AgenziaAffitti.sql).
 * MySQL o MariaDB in XAMPP avviato.
 * Utente DB "agenziauser" con password "unapasswd":
 *   GRANT ALL ON AgenziaAffitti.* TO 'agenziauser'@'localhost';
 *   FLUSH PRIVILEGES;
 */

/**
 * Servlet principale dell'agenzia immobiliare (solo affitti).
 * Gestisce le azioni: lista, dettaglio, cerca, nuovoForm, inserisci.
 *
 * Stesso stile di EsempioFilm del progetto ProgettoWeb3_2 (prof. Benigni).
 */
@WebServlet("/AgenziaAffitti")
public class AgenziaAffitti extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PATH_JSP = "/WEB-INF/";

    public AgenziaAffitti() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        System.out.println("Loaded AgenziaAffitti Servlet");
    }

    public void destroy() {
        // TODO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "lista";

        try (Connection conne = DriverManager.getConnection(DBDriver.getInstance().getConnectionURI());) {

            ImmobileManager immMng = new ImmobileManager(conne);

            switch (action) {

                case "dettaglio": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Immobile imm = immMng.selectOne(id);
                    if (imm != null)
                        request.setAttribute("immobile", imm);
                    request.getRequestDispatcher(PATH_JSP + "dettaglio.jsp").forward(request, response);
                    break;
                }

                case "cerca": {
                    String citta = request.getParameter("citta");
                    String tipo  = request.getParameter("tipo");
                    Vector<Immobile> risultati = immMng.selectByCriteria(citta, tipo);
                    if (risultati != null && risultati.size() > 0)
                        request.setAttribute("immobili", risultati);
                    request.getRequestDispatcher(PATH_JSP + "index.jsp").forward(request, response);
                    break;
                }

                case "nuovoForm": {
                    // Mostra il form di inserimento (nessun dato da caricare)
                    request.getRequestDispatcher(PATH_JSP + "nuovo.jsp").forward(request, response);
                    break;
                }

                default: { // "lista"
                    Vector<Immobile> immobili = immMng.selectAll();
                    if (immobili != null && immobili.size() > 0)
                        request.setAttribute("immobili", immobili);
                    request.getRequestDispatcher(PATH_JSP + "index.jsp").forward(request, response);
                    break;
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (ServletException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("inserisci".equals(action)) {

            // Raccolta parametri dal form
            String idStr       = request.getParameter("id");
            String titolo      = request.getParameter("titolo");
            String tipo        = request.getParameter("tipo");
            String stato       = request.getParameter("stato");
            String indirizzo   = request.getParameter("indirizzo");
            String citta       = request.getParameter("citta");
            String prezzoStr   = request.getParameter("prezzoMensile");
            String mqStr       = request.getParameter("mq");
            String localiStr   = request.getParameter("locali");
            String piano       = request.getParameter("piano");
            String descrizione = request.getParameter("descrizione");

            // Validazione minima: titolo obbligatorio
            if (titolo == null || titolo.isBlank()) {
                request.setAttribute("errore", "Il titolo e' obbligatorio.");
                request.getRequestDispatcher(PATH_JSP + "nuovo.jsp").forward(request, response);
                return;
            }

            // Costruzione oggetto Immobile
            Immobile imm = new Immobile();
            try {
                imm.setId           (idStr     != null && !idStr.isBlank()     ? Integer.parseInt(idStr)     : 0);
                imm.setPrezzoMensile(prezzoStr != null && !prezzoStr.isBlank() ? Integer.parseInt(prezzoStr) : 0);
                imm.setMq           (mqStr     != null && !mqStr.isBlank()     ? Integer.parseInt(mqStr)     : 0);
                imm.setLocali       (localiStr != null && !localiStr.isBlank() ? Integer.parseInt(localiStr) : 0);
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "Valore numerico non valido: " + e.getMessage() + ". Riprovare.");
                request.getRequestDispatcher(PATH_JSP + "nuovo.jsp").forward(request, response);
                return;
            }
            imm.setTitolo    (titolo.trim());
            imm.setTipo      (tipo);
            imm.setStato     (stato);
            imm.setIndirizzo (indirizzo);
            imm.setCitta     (citta);
            imm.setPiano     (piano);
            imm.setDescrizione(descrizione);

            try (Connection conne = DriverManager.getConnection(DBDriver.getInstance().getConnectionURI());) {
                ImmobileManager immMng = new ImmobileManager(conne);
                immMng.insert(imm);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                request.setAttribute("errore", "Errore DB: " + sqle.getMessage());
                request.getRequestDispatcher(PATH_JSP + "nuovo.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Redirect alla lista dopo inserimento riuscito
            response.sendRedirect(request.getContextPath() + "/AgenziaAffitti?action=lista");

        } else {
            doGet(request, response);
        }
    }
}
