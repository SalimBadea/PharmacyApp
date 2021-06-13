package pharmacy_manager_team.PharmacyManager.moduels;

public class ClientMedicines {
    String id;
    String name;
    String description;
    String date;
    String chronic;
    long time;
    String mTime;
    String client_id;

    public ClientMedicines() {
    }

    public ClientMedicines(String id, String name, String description, String date, String chronic, long time,String mTime, String client_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.chronic = chronic;
        this.time = time;
        this.mTime = mTime;
        this.client_id = client_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChronic() {
        return chronic;
    }

    public void setChronic(String chronic) {
        this.chronic = chronic;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
