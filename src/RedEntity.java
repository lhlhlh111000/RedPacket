/**
 * Created by win10 on 2017/1/25.
 */
public class RedEntity {

    private String vrid;

    private String startTime;

    public RedEntity(String vird, String startTime) {
        this.vrid = vird;
        this.startTime = startTime;
    }

    public String getVrid() {
        return vrid;
    }

    public void setVrid(String vrid) {
        this.vrid = vrid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
