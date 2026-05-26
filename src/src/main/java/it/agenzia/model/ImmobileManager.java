package it.agenzia.model;

import java.util.Vector;
import java.sql.*;

/**
 * Gestore JDBC per la tabella Immobile.
 * Stesso pattern di FilmManager del progetto ProgettoWeb3_2:
 * - Statement per selectAll
 * - PreparedStatement per selectOne, insert, update, delete
 * - try-with-resources per il rilascio automatico delle risorse
 */
public class ImmobileManager {

    private Connection conne = null;

    public ImmobileManager(Connection conne) {
        this.conne = conne;
    }

    // ---------------------------------------------------------------
    //  SELECT ALL
    // ---------------------------------------------------------------
    public Vector<Immobile> selectAll() throws SQLException {
        Vector<Immobile> immobili = new Vector<Immobile>();
        String query = "SELECT * FROM Immobile";

        try (Statement stmt = conne.createStatement();
             ResultSet rs   = stmt.executeQuery(query);) {

            while (rs.next())
                immobili.add(fromResultSet(rs));

        } catch (SQLException sqle) {
            System.err.print("Eccezione SQL: ");
            System.err.println(sqle.getMessage());
            sqle.printStackTrace();
            return null;
        }
        return immobili;
    }

    // ---------------------------------------------------------------
    //  SELECT ONE  (per ID)
    // ---------------------------------------------------------------
    public Immobile selectOne(int id) throws SQLException {
        Immobile immobile = null;
        ResultSet rs = null;

        try (PreparedStatement prepstmt = conne.prepareStatement(
                "SELECT * FROM Immobile WHERE ID_immobile=?;");) {

            prepstmt.setInt(1, id);
            prepstmt.executeQuery();
            rs = prepstmt.getResultSet();
            if (rs.next())
                immobile = fromResultSet(rs);

        } catch (SQLException sqle) {
            System.err.println("Eccezione SQL: " + sqle.getMessage());
            sqle.printStackTrace();
            return null;
        } finally {
            if (rs != null)
                try { rs.close(); }
                catch (SQLException sqle) {
                    System.out.println("Errore nella chiusura del ResultSet.");
                    System.out.println(sqle);
                }
        }
        return immobile;
    }

    // ---------------------------------------------------------------
    //  SELECT per CITTA' e/o TIPO  (ricerca pubblica)
    // ---------------------------------------------------------------
    public Vector<Immobile> selectByCriteria(String citta, String tipo) throws SQLException {
        Vector<Immobile> immobili = new Vector<Immobile>();

        // Costruzione dinamica della WHERE
        StringBuilder sb = new StringBuilder("SELECT * FROM Immobile WHERE 1=1");
        if (citta != null && !citta.isBlank()) sb.append(" AND Citta=?");
        if (tipo  != null && !tipo.isBlank())  sb.append(" AND Tipo=?");

        try (PreparedStatement prepstmt = conne.prepareStatement(sb.toString());) {
            int idx = 1;
            if (citta != null && !citta.isBlank()) prepstmt.setString(idx++, citta);
            if (tipo  != null && !tipo.isBlank())  prepstmt.setString(idx,   tipo);

            try (ResultSet rs = prepstmt.executeQuery();) {
                while (rs.next())
                    immobili.add(fromResultSet(rs));
            }

        } catch (SQLException sqle) {
            System.err.println("Eccezione SQL: " + sqle.getMessage());
            sqle.printStackTrace();
            return null;
        }
        return immobili;
    }

    // ---------------------------------------------------------------
    //  INSERT  (prende oggetto Immobile)
    // ---------------------------------------------------------------
    public void insert(Immobile imm) throws SQLException {
        if (imm == null) throw new IllegalArgumentException("L'immobile deve essere valido!");
        String query = "INSERT INTO Immobile "
                + "(ID_immobile, Titolo, Tipo, Stato, Indirizzo, Citta, PrezzoMensile, Mq, Locali, Piano, Descrizione) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conne.prepareStatement(query);) {
            stmt.setInt   (1,  imm.getId());
            stmt.setString(2,  imm.getTitolo());
            stmt.setString(3,  imm.getTipo());
            stmt.setString(4,  imm.getStato());
            stmt.setString(5,  imm.getIndirizzo());
            stmt.setString(6,  imm.getCitta());
            stmt.setInt   (7,  imm.getPrezzoMensile());
            stmt.setInt   (8,  imm.getMq());
            stmt.setInt   (9,  imm.getLocali());
            stmt.setString(10, imm.getPiano());
            stmt.setString(11, imm.getDescrizione());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Eccezione SQL: " + sqle.getMessage());
            sqle.printStackTrace();
        }
    }

    // ---------------------------------------------------------------
    //  UPDATE  (aggiorna Stato e PrezzoMensile per ID)
    // ---------------------------------------------------------------
    public void update(Immobile imm) throws SQLException {
        if (imm == null) throw new IllegalArgumentException("L'immobile deve essere valido!");
        String query = "UPDATE Immobile SET Stato=?, PrezzoMensile=?, Descrizione=? WHERE ID_immobile=?";

        try (PreparedStatement stmt = conne.prepareStatement(query);) {
            stmt.setString(1, imm.getStato());
            stmt.setInt   (2, imm.getPrezzoMensile());
            stmt.setString(3, imm.getDescrizione());
            stmt.setInt   (4, imm.getId());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Eccezione SQL: " + sqle.getMessage());
            sqle.printStackTrace();
        }
    }

    // ---------------------------------------------------------------
    //  DELETE  (per ID)
    // ---------------------------------------------------------------
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM Immobile WHERE ID_immobile=?";

        try (PreparedStatement stmt = conne.prepareStatement(query);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Eccezione SQL: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    //  Helper privato: costruisce Immobile da una riga del ResultSet
    // ---------------------------------------------------------------
    private Immobile fromResultSet(ResultSet rs) throws SQLException {
        return new Immobile(
            rs.getInt   ("ID_immobile"),
            rs.getString("Titolo"),
            rs.getString("Tipo"),
            rs.getString("Stato"),
            rs.getString("Indirizzo"),
            rs.getString("Citta"),
            rs.getInt   ("PrezzoMensile"),
            rs.getInt   ("Mq"),
            rs.getInt   ("Locali"),
            rs.getString("Piano"),
            rs.getString("Descrizione")
        );
    }
}
