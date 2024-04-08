public class Task {
    int number,bt,at,prio, tl,wt;
    public Task(int number, int bt, int at, int prio) {
        this.number = number;
        this.bt = bt;
        this.at = at;
        this.prio = prio;
        this.tl = this.bt;
        this.wt = 0;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getBt() {
        return bt;
    }
    public void setBt(int bt) {
        this.bt = bt;
    }
    public int getAt() {
        return at;
    }
    public void setAt(int at) {
        this.at = at;
    }
    public int getPrio() {
        return prio;
    }
    public void setPrio(int prio) {
        this.prio = prio;
    }
    public int getTl() {
        return tl;
    }
    public void setTl(int tl) {
        this.tl = tl;
    }
    public int getWt() {
        return wt;
    }
    public void setWt(int wt) {
        this.wt = wt;
    }
    public  void info(){
        System.out.printf("""
                    Task [%d]
                    Burst Time: %d
                    Arrival Time: %d
                    Priority: %d
                    ---------------
                    """
                ,getNumber(),getBt(),getAt(),getPrio());
    }
}
