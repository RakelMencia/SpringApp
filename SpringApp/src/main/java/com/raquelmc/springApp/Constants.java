/*
 * Clase con las constantes utilizadas en la app
 */
package com.raquelmc.springApp;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {
	
	/** The Constant VALUE. */
	//Etiqueta del json enviado en la request
	public static final String VALUE = "value";
	
	/** The Constant NAN. */
	//Error valor no numérico
	public static final String NAN = "Se está introduciendo un valor no numérico";
	
	/** The Constant NAN2. */
	//Error valor no numérico 2
	public static final String NAN2 = "Se ha introducido un valor no numérico que no se tendrá en cuenta para los cálculos.";
	
	/** The Constant QUARTILES_INFO. */
	//Info sobre cuartiles
	public static final String QUARTILES_INFO = "Los cuartiles q1 y q3 no existen.";
	
	/** The Constant OK_MONGO. */
	//Documento persistido OK
	public static final String OK_MONGO = "Documento persistido de manera correcta";
	
	/** The Constant KO_MONGO. */
	//Documento persistido KO
	public static final String KO_MONGO = "Document vacío";
	
	/** The Constant EXCHANGE. */
	//Rabbit Exchange
	public static final String EXCHANGE = "micro_streaming_analytics_exchange";
	
	/** The Constant ROUTING_KEY. */
	//Rabbit Routing key
	public static final String ROUTING_KEY = "micro_streaming_analytics_routing_key";
	
	/** The Constant QUEUE. */
	//Rabbit Queue
	public static final String QUEUE = "micro_streaming_analytics_queue";
	
	/** The Constant IS_DURABLE_QUEUE. */
	//Rabbit isDurable
	public static final boolean IS_DURABLE_QUEUE = false;
	
	/** The Constant NULLP_ERROR. */
	public static final String NULLP_ERROR = "Elementos vacíos, no se puede procesar. No se ha podido persistir el registro en bbdd.";
	
	/** The Constant VALUES_EMPTY. */
	public static final String VALUES_EMPTY = "No hay elementos para realizar el cálculo, no se persistirá ningún objeto en bbdd.";
	
	/** The Constant MAIN_PACK. */
	public static final String MAIN_PACK = "com.raquelmc.springApp";
	
	/** The Constant LOG_FILENAME. */
	public static final String LOG_FILENAME = "./Analytics.log";
	
	/** The Constant RECEIVER_LOG. */
	public static final String RECEIVER_LOG = "com.raquelmc.springApp.ReceiverService";
	
	/** The Constant HELPER_LOG. */
	public static final String HELPER_LOG = "com.raquelmc.springApp.ReceiverService";
	
	public static final String MAIN_LOG = "com.raquelmc.springApp.SpringAppApplication";

}
