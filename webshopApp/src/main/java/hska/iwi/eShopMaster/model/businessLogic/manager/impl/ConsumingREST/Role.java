package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

public class Role {

    private int id;
    private String type;
    private int level;

    protected Role() {
    }

    public Role(String type, int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("Role[id=%d, type='%s', level=%d]", id, type, level);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}