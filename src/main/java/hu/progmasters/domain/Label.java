package hu.progmasters.domain;

public class Label {
    private int id;
    private String labelContent;

    public Label(int id, String labelContent) {
        this.id = id;
        this.labelContent = labelContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }
}
