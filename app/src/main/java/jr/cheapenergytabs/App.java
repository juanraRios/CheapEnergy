package jr.cheapenergytabs;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import jr.cheapenergytabs.domain.DaoMaster;
import jr.cheapenergytabs.domain.DaoMaster.DevOpenHelper;
import jr.cheapenergytabs.domain.DaoSession;

public class App extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "indicators-db-encrypted" : "indicators-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
