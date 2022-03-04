package g2048.v3_5.CNGame;

/**
 * @author Xcl
 * @date 2021/12/3 09:48
 * @package g2048.v3
 */
public class UserCN {
    private String id, name, password, card;
    private char sex;

    UserCN(String id, String name, String password, char sex, String card) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.card = card;
    }

    @Override
    public String toString() {
        return "UserCN{" +
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
