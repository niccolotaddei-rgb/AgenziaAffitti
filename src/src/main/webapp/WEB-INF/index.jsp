<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Agenzia Affitti</title>
    <meta charset="utf-8">
    <style>
        body        { font-family: Arial, sans-serif; margin: 20px; background: #f5f4f0; }
        h1          { background: #1a3c5e; color: #fff; padding: 12px 18px; margin: 0 0 16px; }
        form        { margin-bottom: 16px; }
        form input, form select { padding: 6px 8px; margin-right: 6px; }
        form button { padding: 6px 14px; background: #1a3c5e; color: #fff; border: none; cursor: pointer; }
        table       { border-collapse: collapse; width: 100%; background: #fff; }
        th          { background: #1a3c5e; color: #fff; padding: 8px 12px; text-align: left; }
        td          { padding: 7px 12px; border-bottom: 1px solid #ddd; }
        tr:hover td { background: #eef2f7; }
        .disponibile { color: green;  font-weight: bold; }
        .riservato   { color: orange; font-weight: bold; }
        .affittato   { color: red;    font-weight: bold; }
        a            { color: #1a3c5e; }
        .nessuno     { color: #888; padding: 12px; }
    </style>
</head>
<body>

    <h1>Agenzia Affitti &ndash; Immobili disponibili</h1>

    <p><a href="AgenziaAffitti?action=nuovoForm">[+ Nuovo annuncio]</a></p>

    <!-- Form di ricerca -->
    <form action="AgenziaAffitti" method="get">
        <input type="hidden" name="action" value="cerca">
        <input  type="text"   name="citta" placeholder="Città">
        <select name="tipo">
            <option value="">-- Tipo --</option>
            <option value="appartamento">Appartamento</option>
            <option value="villa">Villa / Casa</option>
            <option value="ufficio">Ufficio</option>
            <option value="locale">Locale commerciale</option>
            <option value="box">Box / Garage</option>
        </select>
        <button type="submit">Cerca</button>
        <a href="AgenziaAffitti?action=lista">[tutti]</a>
    </form>

    <!-- Tabella immobili -->
    <table border="1" cellpadding="6" cellspacing="2">
        <tr>
            <th>ID</th>
            <th>Titolo</th>
            <th>Tipo</th>
            <th>Città</th>
            <th>Indirizzo</th>
            <th>Prezzo/mese</th>
            <th>Mq</th>
            <th>Locali</th>
            <th>Piano</th>
            <th>Stato</th>
            <th>Dettaglio</th>
        </tr>
        <c:choose>
            <c:when test="${empty immobili}">
                <tr><td colspan="11" class="nessuno">Nessun immobile trovato.</td></tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${immobili}" var="imm" varStatus="vs">
                    <tr>
                        <td>${imm.getId()}</td>
                        <td>${imm.getTitolo()}</td>
                        <td>${imm.getTipo()}</td>
                        <td>${imm.getCitta()}</td>
                        <td>${imm.getIndirizzo()}</td>
                        <td>€ ${imm.getPrezzoMensile()}</td>
                        <td>${imm.getMq()}</td>
                        <td>${imm.getLocali()}</td>
                        <td>${imm.getPiano()}</td>
                        <td class="${imm.getStato()}">${imm.getStato()}</td>
                        <td><a href="AgenziaAffitti?action=dettaglio&id=${imm.getId()}">Vedi</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

</body>
</html>
