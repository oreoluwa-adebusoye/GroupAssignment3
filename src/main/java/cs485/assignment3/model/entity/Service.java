package cs485.assignment3.model.entity;

public class Service extends AbstractService{
    private Integer ID = null;
    private String name;
    private String type;
    private String description = null;
    private Double BasePrice;
    private Integer EstimatedDuration;

    public Integer getID(){
        return ID;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(Double base_price) {
        this.BasePrice = BasePrice;
    }

    public Integer getEstimatedDuration() {
        return EstimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.EstimatedDuration = estimatedDuration;
    }

    @Override
    public String toString() {
        return getID() + ": " + getName() +
                " (" + getType() + ") - $" + getBasePrice() +
                " / " + getEstimatedDuration() + "min";
    }
}
