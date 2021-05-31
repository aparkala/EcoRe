import java.sql.Date;

//builder pattern applied
public class RCMCreate {
    private String groupId;
    private String rcmId;
    private double capacity;
    private double capacityLeft;
    private double money;
    private String location;
    private String opStatus;

    private RCMCreate(RCMCreate.RCMCreateBuilder rb)
    {
        this.groupId=rb.groupId;
        this.rcmId = rb.rcmId;
        this.capacity = rb.capacity;
        this.capacityLeft = rb.capacityLeft;
        this.money = rb.money;
        this.location = rb.location;
        this.opStatus = rb.opStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public String getRcmId() {
        return rcmId;
    }

    public void setRcmId(String rcmId) {
        this.rcmId = rcmId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacityLeft() {
        return capacityLeft;
    }

    public void setCapacityLeft(double capacityLeft) {
        this.capacityLeft = capacityLeft;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus)
    {
        this.opStatus = opStatus;
    }

   public static class RCMCreateBuilder {
        private String groupId;
        private String rcmId;
        private double capacity;
        private double capacityLeft;
        private double money;
        private String location;
        private String opStatus;




        public RCMCreateBuilder withRCMId(String rcmId) {
            this.rcmId = rcmId;
            return this;
        }

        public RCMCreateBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RCMCreateBuilder withCapacity(double capacity) {
            this.capacity = capacity;
            return this;
        }

        public RCMCreateBuilder withCapacityLeft(double capacityLeft) {
            this.capacityLeft = capacityLeft;
            return this;
        }

        public RCMCreateBuilder withMoney(double money) {
            this.money = money;
            return this;
        }

       public RCMCreateBuilder withLocation(String location) {
           this.location = location;
           return this;
       }

        public RCMCreateBuilder withOpStatus(String opStatus) {
            this.opStatus = opStatus;
            return this;
        }



        public RCMCreate build() {
            return new RCMCreate(this);
        }
    }
}
