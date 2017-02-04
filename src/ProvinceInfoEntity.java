import java.util.List;

/**
 * Created by win10 on 2017/2/4.
 */
public class ProvinceInfoEntity {

    private String Name;

    private List<String> citys;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getCitys() {
        return citys;
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }
}
