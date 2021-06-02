public class RcmCounter implements Visitor{
    int counter = 0;
    @Override
    public void visit(RCM rcm) {
        this.counter++;
    }
}
