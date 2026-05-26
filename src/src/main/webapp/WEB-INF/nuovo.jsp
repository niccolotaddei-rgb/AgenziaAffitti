<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Nuovo Annuncio – Agenzia Affitti</title>
    <meta charset="utf-8">
    <style>
        body        { font-family: Arial, sans-serif; margin: 20px; background: #f5f4f0; }
        h1          { background: #1a3c5e; color: #fff; padding: 12px 18px; margin: 0 0 16px; }
        .errore     { background: #fdd; border: 1px solid #c00; color: #c00;
                      padding: 8px 12px; margin-bottom: 14px; }
        table       { border-collapse: collapse; background: #fff; width: 520px; }
        th          { background: #dde4ed; padding: 8px 12px; text-align: left;
                      width: 160px; font-weight: normal; }
        td          { padding: 6px 12px; border-bottom: 1px solid #ddd; }
        input[type=text], input[type=number], select, textarea {
                      width: 98%; padding: 5px; font-family: Arial, sans-serif; font-size: 0.9em; }
        textarea    { resize: vertical; height: 80px; }
        .btn        { padding: 8px 20px; background: #1a3c5e; color: #fff;
                      border: none; cursor: pointer; font-size: 0.95em; margin-right: 8px; }
        .btn-ann    { padding: 8px 16px; background: #888; color: #fff;
                      border: none; cursor: pointer; font-size: 0.95em; }
        a           { color: #1a3c5e; }
        .nota       { font-size: 0.75em; color: #888; }
    </style>
</head>
<body>

    <h1>Inserisci nuovo annuncio</h1>

    <p><a href="AgenziaAffitti?action=lista">&larr; Torna alla lista</a></p>

    <%-- Eventuale messaggio di errore impostato dalla servlet --%>
    <c:if test="${not empty errore}">
        <div class="errore">${errore}</div>
    </c:if>

    <%-- Il form invia i dati in POST alla stessa servlet con action=inserisci --%>
    <form action="AgenziaAffitti" method="post">
        <input type="hidden" name="action" value="inserisci">

        <table border="1" cellpadding="4" cellspacing="2">

            <tr>
                <th>ID immobile *</th>
                <td>
                    <input type="number" name="id" min="1" required>
                    <div class="nota">Deve essere univoco (non già presente in tabella)</div>
                </td>
            </tr>

            <tr>
                <th>Titolo *</th>
                <td><input type="text" name="titolo" maxlength="120" required
                           placeholder="Es. Appartamento 3 locali – Centro"></td>
            </tr>

            <tr>
                <th>Tipo *</th>
                <td>
                    <select name="tipo" required>
                        <option value="">-- Seleziona --</option>
                        <option value="appartamento">Appartamento</option>
                        <option value="villa">Villa / Casa</option>
                        <option value="ufficio">Ufficio</option>
                        <option value="locale">Locale commerciale</option>
                        <option value="box">Box / Garage</option>
                    </select>
                </td>
            </tr>

            <tr>
                <th>Stato</th>
                <td>
                    <select name="stato">
                        <option value="disponibile" selected>Disponibile</option>
                        <option value="riservato">Riservato</option>
                        <option value="affittato">Affittato</option>
                    </select>
                </td>
            </tr>

            <tr>
                <th>Indirizzo</th>
                <td><input type="text" name="indirizzo" maxlength="100"
                           placeholder="Es. Via Roma 1"></td>
            </tr>

            <tr>
                <th>Città</th>
                <td><input type="text" name="citta" maxlength="60"
                           placeholder="Es. Milano"></td>
            </tr>

            <tr>
                <th>Prezzo mensile (€)</th>
                <td><input type="number" name="prezzoMensile" min="1"></td>
            </tr>

            <tr>
                <th>Superficie (mq)</th>
                <td><input type="number" name="mq" min="1"></td>
            </tr>

            <tr>
                <th>N° locali</th>
                <td><input type="number" name="locali" min="0"></td>
            </tr>

            <tr>
                <th>Piano</th>
                <td><input type="text" name="piano" maxlength="20"
                           placeholder="Es. 2 / T / Attico"></td>
            </tr>

            <tr>
                <th>Descrizione</th>
                <td><textarea name="descrizione"
                              placeholder="Descrizione libera dell'immobile..."></textarea></td>
            </tr>

            <tr>
                <td colspan="2" style="padding: 12px; border: none;">
                    <button type="submit" class="btn">Inserisci annuncio</button>
                    <button type="reset"  class="btn-ann">Cancella</button>
                </td>
            </tr>

        </table>
    </form>

</body>
</html>
