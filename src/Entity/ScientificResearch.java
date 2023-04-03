package Entity;

public class ScientificResearch {
    private String SRname;
    private String SRteacher;
    private String SRoutput;
    private String SRsign;

    private  String SRid;

    public ScientificResearch(String SRname, String SRteacher, String SRoutput, String SRsign, String SRid) {
        this.SRname = SRname;
        this.SRteacher = SRteacher;
        this.SRoutput = SRoutput;
        this.SRsign = SRsign;
        this.SRid = SRid;
    }

    public String getSRid() {
        return SRid;
    }

    public void setSRid(String SRid) {
        this.SRid = SRid;
    }

    public String getSRname() {
        return SRname;
    }

    public void setSRname(String SRname) {
        this.SRname = SRname;
    }

    public String getSRteacher() {
        return SRteacher;
    }

    public void setSRteacher(String SRteacher) {
        this.SRteacher = SRteacher;
    }

    public String getSRoutput() {
        return SRoutput;
    }

    public void setSRoutput(String SRoutput) {
        this.SRoutput = SRoutput;
    }

    public String getSRsign() {
        return SRsign;
    }

    public void setSRsign(String SRsign) {
        this.SRsign = SRsign;
    }

}
