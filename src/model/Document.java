package model;

public class Document {

    private boolean aadhaar;
    private boolean incomeCertificate;
    private boolean marksheet;
    private boolean bankDetails;

    public Document(
            boolean aadhaar,
            boolean incomeCertificate,
            boolean marksheet,
            boolean bankDetails) {

        this.aadhaar = aadhaar;
        this.incomeCertificate = incomeCertificate;
        this.marksheet = marksheet;
        this.bankDetails = bankDetails;
    }

    public boolean isAadhaar() {
        return aadhaar;
    }

    public boolean isIncomeCertificate() {
        return incomeCertificate;
    }

    public boolean isMarksheet() {
        return marksheet;
    }

    public boolean isBankDetails() {
        return bankDetails;
    }

    public boolean isComplete() {
        return aadhaar
                && incomeCertificate
                && marksheet
                && bankDetails;
    }
}