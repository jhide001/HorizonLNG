public class Nomination {

    private int nomId;
    private int tranNum;
    private String pipelineId;
    private String receiptPoint;
    private String deliveryPoint;
    private double nominatedVolume;
    private String nomDate;
    private String nomStatus;
    private String createdDate;

    public Nomination(int nomId, int tranNum, String pipelineId,
                      String receiptPoint, String deliveryPoint,
                      double nominatedVolume, String nomDate,
                      String nomStatus, String createdDate) {
        this.nomId = nomId;
        this.tranNum = tranNum;
        this.pipelineId = pipelineId;
        this.receiptPoint = receiptPoint;
        this.deliveryPoint = deliveryPoint;
        this.nominatedVolume = nominatedVolume;
        this.nomDate = nomDate;
        this.nomStatus = nomStatus;
        this.createdDate = createdDate;
    }

    public int getNomId() { return nomId; }
    public int getTranNum() { return tranNum; }
    public String getPipelineId() { return pipelineId; }
    public String getReceiptPoint() { return receiptPoint; }
    public String getDeliveryPoint() { return deliveryPoint; }
    public double getNominatedVolume() { return nominatedVolume; }
    public String getNomDate() { return nomDate; }
    public String getNomStatus() { return nomStatus; }
    public String getCreatedDate() { return createdDate; }

    public String toString() {
        return "[Nomination] ID: " + nomId +
                " | Deal: " + tranNum +
                " | Pipeline: " + pipelineId +
                " | From: " + receiptPoint +
                " | To: " + deliveryPoint +
                " | Volume: " + nominatedVolume +
                " | Date: " + nomDate +
                " | Status: " + nomStatus;
    }

}