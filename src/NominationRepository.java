import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominationRepository {

    public void insertNomination(int tranNum, String pipelineId,
                                 String receiptPoint, String deliveryPoint,
                                 double nominatedVolume, String nomDate,
                                 String nomStatus) {

        String sql = "INSERT INTO gas_nom " +
                "(nom_id, tran_num, pipeline_id, receipt_point, " +
                "delivery_point, nominated_volume, nom_date, nom_status) " +
                "VALUES (gas_nom_seq.NEXTVAL, ?, ?, ?, ?, ?, " +
                "TO_DATE(?, 'YYYY-MM-DD'), ?)";

        try {
            Connection conn = GMotionConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tranNum);
            ps.setString(2, pipelineId);
            ps.setString(3, receiptPoint);
            ps.setString(4, deliveryPoint);
            ps.setDouble(5, nominatedVolume);
            ps.setString(6, nomDate);
            ps.setString(7, nomStatus);
            ps.executeUpdate();
            System.out.println("[GMotion] Nomination inserted for deal: " + tranNum);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to insert nomination: " + e.getMessage());
        }
    }

    public List getAllNominations() {
        List nominations = new ArrayList();
        String sql = "SELECT nom_id, tran_num, pipeline_id, " +
                "receipt_point, delivery_point, nominated_volume, " +
                "TO_CHAR(nom_date, 'YYYY-MM-DD'), nom_status, " +
                "TO_CHAR(created_date, 'YYYY-MM-DD') FROM gas_nom";

        try {
            Connection conn = GMotionConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nomination nom = new Nomination(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                );
                nominations.add(nom);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to read nominations: " + e.getMessage());
        }

        return nominations;
    }

    public static void main(String[] args) {
        NominationRepository repo = new NominationRepository();

        repo.insertNomination(
                1005,
                "SHELL_PIPE_001",
                "Sabine Pass Terminal",
                "Henry Hub",
                50000.0,
                "2026-07-06",
                "NEW"
        );
        List noms = repo.getAllNominations();
        System.out.println("[GMotion] Total nominations found: " + noms.size());
        for (int i = 0; i < noms.size(); i++) {
            System.out.println(noms.get(i));
        }

        System.out.println("[GMotion] Test nomination inserted successfully.");
    }

}