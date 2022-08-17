package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.UsersPackage;

public class UsersPackageDao extends BaseDao<UsersPackage, String> {
    public UsersPackageDao() {
        super(UsersPackage.class);
    }

    @Override
    public UsersPackage get(String id) {
        return super.get(id);
    }
}
