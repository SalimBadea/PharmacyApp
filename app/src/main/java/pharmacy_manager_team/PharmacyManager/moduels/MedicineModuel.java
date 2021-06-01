package pharmacy_manager_team.PharmacyManager.moduels;

import java.util.List;


public class MedicineModuel {
    List<Medicine> medicineModuel;
    int size = 0;

    public MedicineModuel() {
    }

    public MedicineModuel(List<Medicine> medicineModuel, int size) {
        this.medicineModuel = medicineModuel;
        this.size = size;
    }

    public List<Medicine> getMedicineModuel() {
        return medicineModuel;
    }

    public void setMedicineModuel(List<Medicine> medicineModuel) {
        this.medicineModuel = medicineModuel;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static class Medicine {
        String name;
        String Ex_date;
        boolean chronic;
        String descreption;
        String RTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEx_date() {
            return Ex_date;
        }

        public void setEx_date(String ex_date) {
            Ex_date = ex_date;
        }

        public boolean isChronic() {
            return chronic;
        }

        public void setChronic(boolean chronic) {
            this.chronic = chronic;
        }

        public String getDescreption() {
            return descreption;
        }

        public void setDescreption(String descreption) {
            this.descreption = descreption;
        }

        public String getRTime() {
            return RTime;
        }

        public void setRTime(String RTime) {
            this.RTime = RTime;
        }

        public Medicine() {
        }

        public Medicine(String name, String ex_date, boolean chronic, String descreption, String RTime) {
            this.name = name;
            Ex_date = ex_date;
            this.chronic = chronic;
            this.descreption = descreption;
            this.RTime = RTime;
        }
    }
}



