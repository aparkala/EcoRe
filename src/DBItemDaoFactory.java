public class DBItemDaoFactory implements ItemDaoFactory{
    private static final DBItemDaoFactory instance = new DBItemDaoFactory();

    private DBItemDaoFactory(){};

    public static DBItemDaoFactory getInstance() {
        return instance;
    }
    @Override
    public ItemDao createItemDao() {
        return new DBItemDao();
    }
}
