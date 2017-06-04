package jr.cheapenergytabs.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class IndicatorPVPC {

    @NotNull
    private String name;

    @Id
    private Long id;

    @NotNull
    private Date dateTimeUTC;

    @ToMany(referencedJoinProperty = "idIndicatorPVPC")
    private List<HourPricePVPC> values;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 776829233)
    private transient IndicatorPVPCDao myDao;

    public IndicatorPVPC(String name, Long id, Date dateTimeUTC, List<HourPricePVPC> values) {
        this.name = name;
        this.id = id;
        this.dateTimeUTC = dateTimeUTC;
        this.values = values;
    }

    @Generated(hash = 1582657250)
    public IndicatorPVPC(@NotNull String name, Long id, @NotNull Date dateTimeUTC) {
        this.name = name;
        this.id = id;
        this.dateTimeUTC = dateTimeUTC;
    }

    @Generated(hash = 1423908952)
    public IndicatorPVPC() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 553388805)
    public List<HourPricePVPC> getValues() {
        if (values == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HourPricePVPCDao targetDao = daoSession.getHourPricePVPCDao();
            List<HourPricePVPC> valuesNew = targetDao
                    ._queryIndicatorPVPC_Values(id);
            synchronized (this) {
                if (values == null) {
                    values = valuesNew;
                }
            }
        }
        return values;
    }

    public void setValues(List<HourPricePVPC> values) {
        this.values = values;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 51507580)
    public synchronized void resetValues() {
        values = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Date getDateTimeUTC() {
        return this.dateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1500130419)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIndicatorPVPCDao() : null;
    }
    
}
