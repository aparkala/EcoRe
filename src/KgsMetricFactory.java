public class KgsMetricFactory implements MetricFactory{
    private static final KgsMetricFactory instance = new KgsMetricFactory();

    private KgsMetricFactory(){};

    public static KgsMetricFactory getInstance() {
        return instance;
    }

    @Override
    public MetricStrategy createMetric() {
        return new KgsMetricStrategy();
    }
}
