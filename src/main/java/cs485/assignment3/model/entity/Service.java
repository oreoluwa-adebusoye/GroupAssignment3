package cs485.assignment3.model.entity;

public class Service extends AbstractEntity {
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

    public void setName(String Name) {
        name = Name;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        type = Type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        description = Description;
    }

    public Double getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(Double base_price) {
        BasePrice = base_price;
    }

    public Integer getEstimatedDuration() {
        return EstimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        EstimatedDuration = estimatedDuration;
    }

    @Override
    public String toString() {
        return getID() + ": " + getName() +
                " (" + getType() + ") - $" + getBasePrice() +
                " / " + getEstimatedDuration() + "min";
    }
}
