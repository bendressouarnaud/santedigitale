package com.maternite.gestion.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_ticket_medic")
public class TicketMedic {

    @Id
    @Column(name = "idtck")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtck;

    @Column(name = "dates")
    private Date dates;

    public TicketMedic() {
    }

    public int getIdtck() {
        return idtck;
    }

    public void setIdtck(int idtck) {
        this.idtck = idtck;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
