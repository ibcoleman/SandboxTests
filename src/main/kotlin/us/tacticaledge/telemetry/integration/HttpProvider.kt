package us.tacticaledge.telemetry.integration

import com.google.gson.*
import com.google.gson.JsonParseException
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import us.tacticaledge.telemetry.shipments.model.*
import java.lang.reflect.Type


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Exclude

/**
 * A CustomExclusionStrategy to allow us to ignore fields annotated with the Exclude annotation. We do that with
 * the ToOne<Shipment> field so we don't end up following references forever.
 */
class CustomExclusionStrategy()  : ExclusionStrategy {
    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }

    override fun shouldSkipField(f: FieldAttributes?): Boolean =
        f?.let {
            val found = it.getAnnotation(Exclude::class.java)
            (found != null)
        } ?: false
    }

/**
 * A JsonDeserializer for Shipment entities. This will allow us to handle the ToOne<> and ToMany<> properties, but
 * the problem is you have to specify every single property.
 */



fun getInitializedShipment() = Shipment().apply {
    shipmentItems = ToMany<ShipmentItem>(this, Shipment_.shipmentItems)
    shipmentNotes = ToMany<ShipmentNote>(this, Shipment_.shipmentNotes)
    shipmentLocations = ToMany<ShipmentLocation>(this, Shipment_.shipmentLocations)
}

fun getInitializedShipmentItem() = ShipmentItem().apply {
    shipment = ToOne<Shipment>(this, ShipmentItem_.shipment)
}

fun getInitializedShipmentNote() = ShipmentNote().apply {
    shipment = ToOne<Shipment>(this, ShipmentItem_.shipment)
}

fun getInitializedShipmentLocation() = ShipmentLocation().apply {
    shipment = ToOne<Shipment>(this, ShipmentItem_.shipment)
}