public class LbsMetricFactory implements MetricFactory{
    private static final LbsMetricFactory instance = new LbsMetricFactory();

    private LbsMetricFactory(){};

    public static LbsMetricFactory getInstance() {
        return instance;
    }

    @Override
    public MetricStrategy createMetric() {
        return new LbsMetricStrategy();
    }
}
