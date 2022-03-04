package fuxi.bigexam;

/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v2
 */
public class User {
    String id;
    String name;
    String password;
    String card;
    char sex;

    User(String id, String name, String password, char sex, String card) {
        super();
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setSex(sex);
        this.setCard(card);
    }

    /**
     * user实体类中所有的get、set方法
     */
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", card='" + card + '\'' +
                ", sex=" + sex +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
