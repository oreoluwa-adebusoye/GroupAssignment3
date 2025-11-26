package cs485.assignment3.model.entity;

public class Service extends AbstractService{
    private Integer ID = null;
    private String name;
    private String type;
    private String description = null;
    private Float base_price;
    private Integer est_duration;

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

    public Float getBase_price() {
        return base_price;
    }

    public void setBase_price(Float base_price) {
        this.base_price = base_price;
    }

    public Integer getEst_duration() {
        return est_duration;
    }

    public void setEst_duration(Integer est_duration) {
        this.est_duration = est_duration;
    }
}
