package us.tacticaledge.telemetry.shipments.model;// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: /Users/pauld/Android/TelemetryTest/semantic-itv-telemetry/data/src/main/proto/shipment.proto


import io.objectbox.BoxStore;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.relation.ToOne;
import us.tacticaledge.telemetry.integration.Exclude;

@Entity
public final class ShipmentNote {

    @Id long id;

    @Exclude
    public ToOne<Shipment> shipment;


    public String postingUser;

    public String note;

    public String noteURI;

    public String created;

    /** Used to resolve relations */
    @Internal
    transient BoxStore __boxStore;

    @Internal
    transient ToOne<Shipment> shipmentToOne;

    public ShipmentNote(String postingUser, String note, String noteURI, String created) {
        this.postingUser = postingUser;
        this.note = note;
        this.noteURI = noteURI;
        this.created = created;
    }

    public ShipmentNote() {
    }

    @Internal
    /** This constructor was generated by ObjectBox and may change any time. */
    public ShipmentNote(long id, String postingUser, String note, String noteURI, String created,
            long uid) {
        this.id = id;
        this.postingUser = postingUser;
        this.note = note;
        this.noteURI = noteURI;
        this.created = created;
        this.shipmentToOne.setTargetId(uid);
    }

    @Override
    public String toString() {
        return "us.tacticaledge.telemetry.shipments.model.ShipmentNote{" +
                "postingUser='" + postingUser + '\'' +
                ", note='" + note + '\'' +
                ", noteURI='" + noteURI + '\'' +
                ", created='" + created + '\'' +
                    '}';
    }




//    /** To-one relationship, resolved on first access. */
//    public us.tacticaledge.telemetry.shipments.model.Shipment getShipment() {
//        shipment = shipmentToOne.getTarget();
//        return shipment;
//    }
//
//
//
//
//    /** Set the to-one relation including its ID property. */
//    public void setShipment(us.tacticaledge.telemetry.shipments.model.Shipment shipment) {
//        shipmentToOne.setTarget(shipment);
//        this.shipment = shipment;
//    }
}
