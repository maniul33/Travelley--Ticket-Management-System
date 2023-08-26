public class trainDetails {
    private String train_name, t_price, train_id;

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getT_price() {
        return t_price;
    }

    public void setT_price(String t_price) {
        this.t_price = t_price;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    trainDetails(int fromIndex, int toIndex) {
        int Dhaka = 1, Sylhet = 2, Chittagong = 3, Rajshahi = 4;
        if (((fromIndex == Dhaka && toIndex == Sylhet) || (fromIndex == Sylhet && toIndex == Dhaka))) {
            this.train_name = "UPABAN_EXPRESS";
            this.t_price = "350";
            this.train_id = "40409908";
        } else if (((fromIndex == Dhaka && toIndex == Chittagong) || (fromIndex == Chittagong && toIndex == Dhaka))) {
            this.train_name = "MOHANAGAR_EXPRESS";
            this.t_price = "850";
            this.train_id = "40409789";
        } else if (((fromIndex == Dhaka && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Dhaka))) {
            this.train_name = "SUBORNO_EXPRESS";
            this.t_price = "680";
            this.train_id = "40404439";
        } else if (((fromIndex == Chittagong && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Chittagong))) {
            this.train_name = "CHITRA_EXPRESS";
            this.t_price = "530";
            this.train_id = "40403109";
        } else if (((fromIndex == Sylhet && toIndex == Rajshahi) || (fromIndex == Rajshahi && toIndex == Sylhet))) {
            this.train_name = "PARABAT_EXPRESS";
            this.t_price = "960";
            this.train_id = "40404352";
        }
    }
}
