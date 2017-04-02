package jr.cheapenergytabs.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by juanra on 02/04/17.
 */

@Entity
public class HourPricePVPC {

    @Id
    private Long id;

    private long idIndicatorPVPC;

    @ToOne(joinProperty = "idIndicatorPVPC")
    private IndicatorPVPC indicatorPVPC;

    @NotNull
    private Double value;

    @NotNull
    private Date dateTimeUTC;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 975550580)
    private transient HourPricePVPCDao myDao;

    @Generated(hash = 1575255102)
    public HourPricePVPC(Long id, long idIndicatorPVPC, @NotNull Double value,
            @NotNull Date dateTimeUTC) {
        this.id = id;
        this.idIndicatorPVPC = idIndicatorPVPC;
        this.value = value;
        this.dateTimeUTC = dateTimeUTC;
    }

    @Generated(hash = 1091432998)
    public HourPricePVPC() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdIndicatorPVPC() {
        return this.idIndicatorPVPC;
    }

    public void setIdIndicatorPVPC(long idIndicatorPVPC) {
        this.idIndicatorPVPC = idIndicatorPVPC;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDateTimeUTC() {
        return this.dateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    @Generated(hash = 1220671795)
    private transient Long indicatorPVPC__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1977132531)
    public IndicatorPVPC getIndicatorPVPC() {
        long __key = this.idIndicatorPVPC;
        if (indicatorPVPC__resolvedKey == null
                || !indicatorPVPC__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IndicatorPVPCDao targetDao = daoSession.getIndicatorPVPCDao();
            IndicatorPVPC indicatorPVPCNew = targetDao.load(__key);
            synchronized (this) {
                indicatorPVPC = indicatorPVPCNew;
                indicatorPVPC__resolvedKey = __key;
            }
        }
        return indicatorPVPC;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1562263791)
    public void setIndicatorPVPC(@NotNull IndicatorPVPC indicatorPVPC) {
        if (indicatorPVPC == null) {
            throw new DaoException(
                    "To-one property 'idIndicatorPVPC' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.indicatorPVPC = indicatorPVPC;
            idIndicatorPVPC = indicatorPVPC.getId();
            indicatorPVPC__resolvedKey = idIndicatorPVPC;
        }
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1427626248)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHourPricePVPCDao() : null;
    }

}
