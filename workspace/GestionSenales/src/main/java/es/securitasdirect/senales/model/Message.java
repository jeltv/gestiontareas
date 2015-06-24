package es.securitasdirect.senales.model;

/**
 * Clase de modelo para cargar todo lo que se recibe de la cola.
 *
 */

/*
<PARAMS>
<CIBB>
	<EVENTS MMV="020401" Modelo="Modelo Desconocido" Counter="00B"
Ack="1" InsNumber="00000000" InsNumber_e="1555667" DataTime="1502091042" TypeProtocol="E">
		<EVENT id="UCB">
			<type>2</type>
			<ArmType value='Panel disarm'>D</ArmType>
			<ArmForced value='Panel is not forced
armed'>N</ArmForced>
			<Zone value='There is not  any  zone open'>N</Zone>

<CancelPending value='There is not  any  cancel situation
in panel'>N</CancelPending>
			<PanelInFault value='Panel is in Fault'>S</PanelInFault>
			<EventType value='Informative events'>I</EventType>
			<DevIdentification value='Central:Central '>
CE</DevIdentification>
			<EventStatus value='Event status does not apply for this
event.'>N</EventStatus>
			<ArmTime>067511</ArmTime>
			<DevManufacturer>0KYMGB</DevManufacturer>
		</EVENT>
	</EVENTS>  <!-- TimeArrived Mon Feb 09 10:42:35 CET 2015 -->
	<PROPS tfno="0000000000000"
	texto="SDES04702040100B1000000000001502091042E1555667#XDN067511NNSICE0KYMGBNUCB2!C517"
		pais="ESP" host="es1recveri04v" op="MOVS" centro=""
Numero="comfort"
		tipo="SDI2" user="1555667" err=""
		transId="es1recveri04v_rx_GPRS_es_mov7587_20150209104235556"
TimeIn="1423474955000"
		RecepName="rx_GPRS_es_mov7587" Medio="GPRS"
TansmisionType="EVENT"
		SeviceType="comfort" ProtocolType="POSESA" InOrOut="INPUT"
		DestinoType="MACHINE" OrigenType="PANEL" ModeloId=""
origen="panel"
		Ok="true" ServiceType="COM" />
</CIBB>
</PARAMS>
 */
public class Message {

    private String id;

    public Message() {
    }

    public Message(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                '}';
    }
}
