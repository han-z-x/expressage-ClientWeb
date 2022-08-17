/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * <p>
 * This is an automatic generated file. It will be regenerated every time
 * you generate persistence class.
 * <p>
 * Modifying its content may cause the program not work, or your work may lost.
 * <p>
 * Licensee:
 * License Type: Evaluation
 * <p>
 * Licensee:
 * License Type: Evaluation
 */

/**
 * Licensee:
 * License Type: Evaluation
 */
package ts.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "TransHistory")
@XmlRootElement(name="TransHistory")
public class TransHistory implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6973429316324711538L;

    public TransHistory() {
    }

    @Column(name = "SN", nullable = false)
    @Id
    @GeneratedValue(generator = "MODEL_TRANSHISTORY_SN_GENERATOR")
    @org.hibernate.annotations.GenericGenerator(name = "MODEL_TRANSHISTORY_SN_GENERATOR", strategy = "native")
    private int SN;

    /*
    @ManyToOne(targetEntity = ExpressSheet.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns({@JoinColumn(name = "ExpressID", referencedColumnName = "ID", nullable = false)})
    private TransPackage expressSheet;
    */

    @Column(name = "ExpressID",nullable = false,length = 24)
    private String ExpressID;

    @Column(name = "ActTime", nullable = false)
    private Date ActTime;
    /*
    @Column(name = "UIDFrom", nullable = true, length = 10)
    private int UIDFrom;

    @Column(name = "UIDTo", nullable = true, length = 10)
    private int UIDTo;
    */
    @Column(name = "x", nullable = true)
    private Float x;

    @Column(name = "y", nullable = true)
    private Float y;

    @Column(name = "Action", nullable = false, length = 100)
    private String action;

    public void setSN(int value) {
        this.SN = value;
    }

    public int getSN() {
        return SN;
    }

    public int getORMID() {
        return getSN();
    }

    public void setActTime(Date value) {
        this.ActTime = value;
    }

    public Date getActTime() {
        return ActTime;
    }
    /*
    public void setUIDFrom(int value) {
        this.UIDFrom = value;
    }

    public int getUIDFrom() {
        return UIDFrom;
    }

    public void setUIDTo(int value) {
        this.UIDTo = value;
    }

    public int getUIDTo() {
        return UIDTo;
    }
    */
    public void setX(Float value) {
        this.x = value;
    }

    public Float getX() {
        return x;
    }

    public void setY(Float value) {
        this.y = value;
    }

    public Float getY() {
        return y;
    }

    /*
    public void setExpressSheet(ExpressSheet value) {
        this.expressSheet = value;
    }

    public ExpressSheet getExpressSheet() {
        return expressSheet;
    }
    */

    public String toString() {
        return toString(false);
    }

    public String toString(boolean idOnly) {
        if (idOnly) {
            return String.valueOf(getSN());
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("TransHistory[ ");
            sb.append("SN=").append(getSN()).append(" ");
            //sb.append("ExpressSheet=").append(getExpressSheet().toString(true)).append(" ");
            sb.append("ExpressID=").append(getExpressID()).append(" ");
            sb.append("ActTime=").append(getActTime()).append(" ");
            sb.append("Action=").append(getAction()).append(" ");
            //sb.append("UIDFrom=").append(getUIDFrom()).append(" ");
            //sb.append("UIDTo=").append(getUIDTo()).append(" ");
            sb.append("X=").append(getX()).append(" ");
            sb.append("Y=").append(getY()).append(" ");
            sb.append("]");
            return sb.toString();
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpressID() {
        return ExpressID;
    }

    public void setExpressID(String expressID) {
        ExpressID = expressID;
    }
}
