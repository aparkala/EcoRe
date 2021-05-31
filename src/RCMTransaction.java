import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.sql.Date;

public class RCMTransaction {

        private int transactionId;
        private String rcmId;
        private int itemId;
        private double weight;
        private Date insertedDate;
        private int cash;
       // private double capacityLeft;
        private double price;
        private int isEmpty;
        private String groupId;

        private RCMTransaction(RCMTransactionBuilder rb) {
            this.transactionId=rb.transactionId;
            this.rcmId = rb.rcmId;
            this.itemId = rb.itemId;
            this.weight = rb.weight;
            this.insertedDate = rb.insertedDate;
            this.cash = rb.cash;
           // this.capacityLeft = rb.capacityLeft;
            this.price = rb.price;
            this.isEmpty=rb.isEmpty;
            this.groupId= rb.groupId;
        }

        public int getTransactionId()
        {
            return this.transactionId;
        }

        public void setTransactionId(int transactionId)
        {
            this.transactionId=transactionId;
        }

        public String getRcmId() {
            return rcmId;
        }

        public void setRcmId(String rcmId) {
            this.rcmId = rcmId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public Date getInsertedDate() {
            return insertedDate;
        }

        public void setInsertedDate(Date insertedDate) {
            this.insertedDate = insertedDate;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        /*public double getCapacityLeft() {
            return capacityLeft;
        }*/

        /*public void setCapacityLeft(double capacityLeft) {
            this.capacityLeft = capacityLeft;
        }*/

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

    public int getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(int isEmpty) {
        this.isEmpty = isEmpty;
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }




    public static class RCMTransactionBuilder {
            private int transactionId;
            private String rcmId;
            private int itemId;
            private double weight;
            private Date insertedDate;
            private int cash;
           // private double capacityLeft;
            private double price;
            private int isEmpty;
            private String groupId;

            public RCMTransactionBuilder withTransactionId(int transactionId)
            {
                this.transactionId=transactionId;
                return this;
            }

            public RCMTransactionBuilder withRCMId(String rcmId) {
                this.rcmId = rcmId;
                return this;
            }

            public RCMTransactionBuilder withItemId(int itemId) {
                this.itemId = itemId;
                return this;
            }

            public RCMTransactionBuilder withWeight(double weight) {
                this.weight = weight;
                return this;
            }

            public RCMTransactionBuilder withInsertedDate(Date insertedDate) {
                this.insertedDate = insertedDate;
                return this;
            }

            public RCMTransactionBuilder withCash(int cash) {
                this.cash = cash;
                return this;
            }

           /* public RCMTransactionBuilder withCapacityLeft(double capacityLeft) {
                this.capacityLeft = capacityLeft;
                return this;
            }*/

            public RCMTransactionBuilder withItemPrice(double price) {
                this.price = price;
                return this;
            }

            public RCMTransactionBuilder withIsEmpty(int isEmpty) {
                this.isEmpty = isEmpty;
                return this;
            }

        public RCMTransactionBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

            public RCMTransaction build() {
                return new RCMTransaction(this);
            }
        }
    }


