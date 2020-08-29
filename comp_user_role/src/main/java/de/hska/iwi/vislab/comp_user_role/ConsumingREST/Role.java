package de.hska.iwi.vislab.comp_user_role.ConsumingREST;

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
}