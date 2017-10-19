package com.jsonp.object.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class JsonPObjectWriter {

	public static void main(String[] args) {
		JsonPObjectWriter objectWriter = new JsonPObjectWriter();
		JsonObject orderObject = objectWriter.buildPurchaseOrder();
		// Write to console
		System.out.println("Order::\n" + orderObject);

		// write to file.
		objectWriter.writeToFile(orderObject);
	}

	public void writeToFile(JsonObject orderObject) {
		File orderFile = Paths.get("src/main/resources/order.json").toFile();
		if (orderFile.exists()) {
			orderFile.delete();
		}
		try {
			OutputStream outStream = new FileOutputStream(orderFile);
			JsonWriter jsonWriter = Json.createWriter(outStream);
			jsonWriter.writeObject(orderObject);
			jsonWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JsonObject buildPurchaseOrder() {

		// Customer Details
		JsonObjectBuilder customerBuilder = Json.createObjectBuilder().add("@first_name", "johny")
				.add("@last_name", "shaik").add("email", "johnyzhub@github.com").add("phoneNumber", "1456345789");

		// Line Order Details
		JsonObjectBuilder lineItemOneBuilder = Json.createObjectBuilder().add("@item", "H2G2").add("@quantity", "1")
				.add("unit_price", "23.5");
		JsonObjectBuilder lineItemTwoBuilder = Json.createObjectBuilder().add("@item", "Matrix").add("@quantity", "2")
				.add("unit_price", "32.5");
		JsonArrayBuilder lineArrayBuilder = Json.createArrayBuilder().add(lineItemOneBuilder).add(lineItemTwoBuilder);
		JsonObjectBuilder orderLineBuilder = Json.createObjectBuilder().add("order_line", lineArrayBuilder);

		// Credit Card Details
		JsonObjectBuilder creditCardBuilder = Json.createObjectBuilder().add("@number", "1234")
				.add("@expiry_date", "10/13").add("@control_number", "465").add("@type", "visa");

		// Order Details
		JsonObjectBuilder orderBuilder = Json.createObjectBuilder().add("@id", "1234").add("@date", "10/16/2017")
				.add("customer", customerBuilder).add("content", orderLineBuilder)
				.add("credit_card", creditCardBuilder);
		return orderBuilder.build();
	}
}