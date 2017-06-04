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

    @NotNull
    private Double value;

    @NotNull
    private Date dateTimeUTC;

    private Long idIndicatorPVPC;

    private transient DaoSession daoSession;

    private transient HourPricePVPCDao myDao;

    public HourPricePVPC(Long id, @NotNull Double value,
                         @NotNull Date dateTimeUTC) {
        this.id = id;
        this.value = value;
        this.dateTimeUTC = dateTimeUTC;
    }

    @Generated(hash = 1091432998)
    public HourPricePVPC() {
    }

    @Generated(hash = 686963695)
    public HourPricePVPC(Long id, @NotNull Double value, @NotNull Date dateTimeUTC,
            Long idIndicatorPVPC) {
        this.id = id;
        this.value = value;
        this.dateTimeUTC = dateTimeUTC;
        this.idIndicatorPVPC = idIndicatorPVPC;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdIndicatorPVPC() {
        return idIndicatorPVPC;
    }

    public void setIdIndicatorPVPC(Long idIndicatorPVPC) {
        this.idIndicatorPVPC = idIndicatorPVPC;
    }

    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHourPricePVPCDao() : null;
    }

}
