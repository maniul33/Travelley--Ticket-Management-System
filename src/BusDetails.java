public class BusDetails {
    private String bus_name, t_price, bus_id;

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getT_price() {
        return t_price;
    }

    public void setT_price(String t_price) {
        this.t_price = t_price;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    BusDetails(int fromIndex, int toIndex) {
        int Dhaka = 1, Sylhet = 2, Chittagong = 3, Rajshahi = 4;
        if (((fromIndex == Dhaka && toIndex == Sylhet) || (fromIndex == Sylhet && toIndex == Dhaka))) {
            this.bus_name = "HANIF";
            this.t_price = "900";
            this.bus_id = "30303452";
        } else if (((fromIndex == Dhaka && toIndex == Chittagong) || (fromIndex == Chittagong && toIndex == Dhaka))) {
            this.bus_name = "ENA";
            this.t_price = "700";
            this.bus_id = "30304589";
        } else if (((fromIndex == Dhaka && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Dhaka))) {
            this.bus_name = "SHYAMOLI";
            this.t_price = "850";
            this.bus_id = "30309089";
        } else if (((fromIndex == Chittagong && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Chittagong))) {
            this.bus_name = "SAKURA";
            this.t_price = "950";
            this.bus_id = "30308760";
        } else if (((fromIndex == Sylhet && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Sylhet))) {
            this.bus_name = "SHOHAGH";
            this.t_price = "800";
            this.bus_id = "30304100";
        }
    }
}
