package us.tacticaledge.telemetry.integration

import com.fatboyindustrial.gsonjavatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.objectbox.BoxStore
import io.objectbox.relation.RelationInfo
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.aaronhe.threetengson.InstantConverter
import org.aaronhe.threetengson.ThreeTenGsonAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test
import us.tacticaledge.telemetry.shipments.model.*
import java.io.File

class HttpProviderTest {

//    private lateinit var testStore: BoxStore
//
//    @Before
//    fun setUp() {
//
//        testStore = MyObjectBox.builder()
//            .directory(File("/tmp/mock-store"))
//            .build()
//    }
//
//    @After
//    fun tearDown() {
//        testStore.close()
//        testStore.deleteAllFiles()
//    }

    private val gson: Gson

    init {
        val builder = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        builder.setExclusionStrategies(CustomExclusionStrategy())
        builder.registerTypeAdapter(Converters.INSTANT_TYPE, InstantConverter()).create()
        builder.registerTypeAdapter(Shipment::class.java, ShipmentDeserializer())
        builder.registerTypeAdapter(ShipmentItem::class.java, ShipmentItemDeserializer())
        //gson = builder.create()
        gson = ThreeTenGsonAdapter.registerInstant(builder).create()
    }

    @Test
    fun serializeOddBallShipment() {


//        val shipmenbtBox = testStore.boxFor(Shipment::class.java)

        val aShipment = getInitializedShipment()
        val aShipmentItem = getInitializedShipmentItem()
        aShipment.shipmentItems.add(aShipmentItem)

        val json = gson.toJson(aShipment)
        //TestCase.assertEquals("", json)

        val s: Shipment = gson.fromJson(json, Shipment::class.java)
        assertEquals(s.dataSource, "foobar")
    }

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
}
